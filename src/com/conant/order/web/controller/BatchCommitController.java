/**
 * BatchCommitController.java
 * 2009-2-12
 * Administrator
 */
package com.conant.order.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OnlineOrderFacade;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.NodeInstance;
import com.conant.order.vo.OrderStatus;
import com.conant.order.vo.OrsOrder;
import com.conant.order.vo.ProcessInstance;
import com.conant.order.vo.ProcessLog;
import com.conant.order.web.form.OrdersForm;

/**
 * �����ύ����������controller��
 * @author Administrator
 *
 */
public class BatchCommitController extends SimpleFormController
{
	private static final Logger log = Logger.getLogger(
			"BatchCommitController", Logger.DEBUG, true);

	private OnlineOrderFacade onlineOrder;
	private String returnUrl;

	public void setOnlineOrder(OnlineOrderFacade onlineOrder)
	{
		this.onlineOrder = onlineOrder;
	}
	
	public String getReturnUrl()
	{
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl)
	{
		this.returnUrl = returnUrl;
	}	

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception
	{
		log.info("BatchCommitController formBackingObject...");

		OrdersForm command = new OrdersForm();
		return command;
	}
	
	private List batchCommitOrders(HttpServletRequest request, OrdersForm form) throws ProcessException
	{
		// ��鶩��״̬
		// λ�ڽ���״̬�Ķ����������ύ
		String user = (String)request.getSession().getAttribute("user_name");
		List listOrder = onlineOrder.getOrders(form.getOrderIds());
		int size = listOrder.size();
		int token;
		for(int i = size - 1; i > 0; i--)
		{
			OrsOrder order = (OrsOrder)listOrder.get(i);
			ProcessInstance process = order.getProcessInstance();
			token = process.getToken(); 
			if(token == OrderStatus.TYPE_END)
			{
				continue;
			}
			else
			{
				// ���½ڵ���Ϣ
				// ���µ�ǰ�ڵ�
				NodeInstance currentNode = onlineOrder
					.getNodeInstance(process.getId(), token);
				currentNode.setLeavedate(new Date());
				currentNode.setUser(user);
				onlineOrder.saveNodeInstance(currentNode);
				// ����Ŀ��ڵ�
				int targetToken = process.getToken() + 1;
				boolean withdrawal = false;
				NodeInstance targetNode = onlineOrder.getNodeInstance(process.getId(), targetToken);
				targetNode.setEnterdate(new Date());
				onlineOrder.saveNodeInstance(targetNode);
				// �����ƽ���¼
				ProcessLog processLog = new ProcessLog();
				processLog.setNode(token);
				processLog.setUser(user);
				processLog.setProcessdate(new Date());
				processLog.setProcessInstance(process);
				onlineOrder.saveProcessLog(processLog);
				// �����ƽ�
				process.setWithdrawal(withdrawal);
				// ����������Ϣ
				process.setToken(targetToken);
				onlineOrder.saveProcessInstance(process);
			}
		}
		
		return listOrder;
	}

	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception
	{
		log.info("BatchCommitController onSubmit...");

		OrdersForm form = (OrdersForm)command;
		
		PageMsg pageMsg = new PageMsg();
		pageMsg.setUrl(getReturnUrl());
		pageMsg.setTarget("_self");

		try
		{
			batchCommitOrders(request, form);
			return new ModelAndView(this.getSuccessView(), "success", pageMsg);
		}
		catch(ProcessException pe)
		{
			pageMsg.setCode(pe.getErrorCode());
			return new ModelAndView("common/err", "error", pageMsg);
		}
		catch(Exception ex)
		{
			pageMsg.setMsg(ex.getMessage());
			return new ModelAndView("common/err", "error", pageMsg);
		}
	}
}
