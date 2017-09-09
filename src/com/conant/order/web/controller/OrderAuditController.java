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
 * ��˶���������controller��
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
				// ��Ч�ƽ�����
				throw new ProcessException(400005);
			}
			// �����ݿ��ȡ���̶���
			ProcessInstance process = getOnlineOrder().getProcessInstance(handoverForm.getProcessInstance().getId());
			// �ж��Ƿ���Ч�ƽ������Ƿ�����ƽ�����
			if(process.getToken() != OrderStatus.TYPE_AUDITING)
			{
				// �ƽ������ѹ���
				throw new ProcessException(400022);
			}
			// �����ƽ������ڵ㲻���������ƽ�
			if(transition == TransitionType.TD_BACKWARD)
			{
				// �ѵ�����ʼ�ڵ�
				throw new ProcessException(400007);
			}
			// ���½ڵ���Ϣ
			// ���µ�ǰ�ڵ�
			NodeInstance currentNode = getOnlineOrder()
				.getNodeInstance(process.getId(), process.getToken());
			currentNode.setLeavedate(new Date());
			currentNode.setUser(user);
			getOnlineOrder().saveNodeInstance(currentNode);
			// ����Ŀ��ڵ�
			int targetToken = process.getToken() + 1;
			boolean withdrawal = false;
			NodeInstance targetNode = getOnlineOrder().getNodeInstance(process.getId(), targetToken);
			targetNode.setEnterdate(new Date());
			getOnlineOrder().saveNodeInstance(targetNode);
			// �����ƽ���¼
			ProcessLog processLog = new ProcessLog();
			processLog.setNode(process.getToken());
			processLog.setUser(user);
			processLog.setProcessdate(new Date());
			processLog.setMessage(handoverForm.getMessage());
			processLog.setProcessInstance(process);
			getOnlineOrder().saveProcessLog(processLog);
			// �����ƽ�
			process.setWithdrawal(withdrawal);
			// ����������Ϣ
			process.setToken(targetToken);
			getOnlineOrder().saveProcessInstance(process);
			// �ƽ��ɹ�
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
