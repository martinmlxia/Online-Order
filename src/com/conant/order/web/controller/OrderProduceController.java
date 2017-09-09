/**
 * OrderProduceController.java
 * 2009-1-11
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
 * ����״̬�¶����ƽ�������controller��
 * @author Administrator
 *
 */
public class OrderProduceController extends OrderHandoverController
{
	private static final Logger log = Logger.getLogger(
			"OrderProduceController", Logger.DEBUG, true);
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception
	{
		log.info("OrderProduceController onSubmit...");
		
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
			if(process.getToken() != OrderStatus.TYPE_PRODUCING)
			{
				// �ƽ������ѹ���
				throw new ProcessException(400022);
			}
			// ���½ڵ���Ϣ
			// ���µ�ǰ�ڵ�
			NodeInstance currentNode = getOnlineOrder()
				.getNodeInstance(process.getId(), process.getToken());
			currentNode.setLeavedate(new Date());
			currentNode.setUser(user);
			getOnlineOrder().saveNodeInstance(currentNode);
			// ����Ŀ��ڵ�
			int targetToken;
			boolean withdrawal;
			if(transition == TransitionType.TD_BACKWARD)
			{
				targetToken = process.getToken() - 1;
				withdrawal = true;
			}
			else
			{
				targetToken = process.getToken() + 1;
				withdrawal = false;
			}
			NodeInstance nextNode = getOnlineOrder()
				.getNodeInstance(process.getId(), targetToken);
			nextNode.setEnterdate(new Date());
			getOnlineOrder().saveNodeInstance(nextNode);
			// �����ƽ���¼
			ProcessLog processLog = new ProcessLog();
			processLog.setNode(process.getToken());
			processLog.setUser(user);
			processLog.setProcessdate(new Date());
			processLog.setMessage(handoverForm.getMessage());
			processLog.setProcessInstance(process);
			getOnlineOrder().saveProcessLog(processLog);
			// ����������Ϣ
			// �����˻ر�־
			process.setWithdrawal(withdrawal);
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
