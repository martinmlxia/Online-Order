/**
 * OrderAuditController.java
 * 2009-1-8
 * Administrator
 */
package com.conant.order.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.conant.order.common.PageMsg;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.NodeInstance;
import com.conant.order.vo.OrderStatus;
import com.conant.order.vo.ProcessInstance;
import com.conant.order.vo.ProcessLog;
import com.conant.order.web.form.HandoverForm;

/**
 * 审核订单操作的controller。
 * @author Administrator
 *
 */
public class OrderAuditController extends OrderHandoverController
{
	private static final Logger log = Logger.getLogger(
			"OrderAuditController", Logger.DEBUG, true);
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception
	{
		log.info("OrderAuditController onSubmit...");
		
		HandoverForm handoverForm = (HandoverForm)command;

		PageMsg pageMsg = new PageMsg();
		pageMsg.setUrl(getReturnUrl());
		pageMsg.setTarget("_self");

		String user = (String)request.getSession().getAttribute("user_name");
		int transition = getTransition(request);
		try
		{
			if(transition == TransitionType.TD_INVALID)
			{
				// 无效移交操作
				throw new ProcessException(400005);
			}
			// 从数据库获取流程对象
			ProcessInstance process = getOnlineOrder().getProcessInstance(handoverForm.getProcessInstance().getId());
			// 判断是否有效移交，即是否过期移交动作
			if(process.getToken() != OrderStatus.TYPE_AUDITING)
			{
				// 移交操作已过期
				throw new ProcessException(400022);
			}
			// 向上移交，本节点不允许向上移交
			if(transition == TransitionType.TD_BACKWARD)
			{
				// 已到达起始节点
				throw new ProcessException(400007);
			}
			// 更新节点信息
			// 更新当前节点
			NodeInstance currentNode = getOnlineOrder()
				.getNodeInstance(process.getId(), process.getToken());
			currentNode.setLeavedate(new Date());
			currentNode.setUser(user);
			getOnlineOrder().saveNodeInstance(currentNode);
			// 更新目标节点
			int targetToken = process.getToken() + 1;
			boolean withdrawal = false;
			NodeInstance targetNode = getOnlineOrder().getNodeInstance(process.getId(), targetToken);
			targetNode.setEnterdate(new Date());
			getOnlineOrder().saveNodeInstance(targetNode);
			// 生成移交记录
			ProcessLog processLog = new ProcessLog();
			processLog.setNode(process.getToken());
			processLog.setUser(user);
			processLog.setProcessdate(new Date());
			processLog.setMessage(handoverForm.getMessage());
			processLog.setProcessInstance(process);
			getOnlineOrder().saveProcessLog(processLog);
			// 向下移交
			process.setWithdrawal(withdrawal);
			// 更新流程信息
			process.setToken(targetToken);
			getOnlineOrder().saveProcessInstance(process);
			// 移交成功
			pageMsg.setCode("400011");
			return new ModelAndView(getSuccessView(), "success", pageMsg);
		}
		catch(ProcessException exp)
		{
			pageMsg.setCode(exp.getErrorCode());
			return new ModelAndView(getErrorView(), "error", pageMsg);
		}
	}
}
