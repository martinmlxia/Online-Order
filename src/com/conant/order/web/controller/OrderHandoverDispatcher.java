/**
 * OrderHandoverController.java
 * 2009-1-8
 * Administrator
 */
package com.conant.order.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OnlineOrderFacade;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrderStatus;
import com.conant.order.vo.ProcessInstance;

/**
 * �����ƽ��������ɷ��������ɷ������ݶ���״̬���ƽ������ɷ�����ȷ��controller��
 * @author Administrator
 *
 */
public class OrderHandoverDispatcher extends WebApplicationObjectSupport implements Controller
{
	private OnlineOrderFacade onlineOrder;	
	private String errorView;
	
	public OnlineOrderFacade getOnlineOrder()
	{
		return onlineOrder;
	}

	public void setOnlineOrder(OnlineOrderFacade onlineOrder)
	{
		this.onlineOrder = onlineOrder;
	}
	
	public String getErrorView()
	{
		return errorView;
	}

	public void setErrorView(String errorView)
	{
		this.errorView = errorView;
	}

	/**
	 * �������̵�ǰ״̬��ȡ������ͼ��
	 * @param processId
	 * @return
	 */
	private OrderHandoverController getFormController(ProcessInstance process)
	{
		int token = process.getToken();
		
		String controllerName = null; 
		switch(token)
		{
		case OrderStatus.TYPE_AUDITING:
			controllerName = "orderAuditController";
			break;
		case OrderStatus.TYPE_PRODUCING:
			controllerName = "orderProduceController";
			break;
		case OrderStatus.TYPE_DELIVERED:
			controllerName = "orderDeliverController";
			break;
		case OrderStatus.TYPE_COMPLETE:
			controllerName = "orderCompleteController";
			break;
		default:
			break;
		}
		OrderHandoverController controller = (OrderHandoverController)getApplicationContext().getBean(controllerName);
		
		return controller;
	}

	//@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		// ��ȡ����ʵ������
		String strProcessId = request.getParameter("processId");
		String returnUrl = request.getParameter("returnUrl");
		
		PageMsg pageMsg = new PageMsg();
		pageMsg.setTarget("_self");
		pageMsg.setUrl(returnUrl);
		pageMsg.setCode("400005");
		if(!StringUtils.hasText(strProcessId))
		{
			// ��Ч�ƽ�����
			return new ModelAndView(getErrorView(), "error", pageMsg);
		}
		ProcessInstance processInstance = null;
		try
		{
			int processId = Integer.parseInt(strProcessId);
			processInstance = onlineOrder.getProcessInstance(processId);
			request.setAttribute("processInstance", processInstance);
		}
		catch(ProcessException exp)
		{
			// ��Ч�ƽ�����
			return new ModelAndView(getErrorView(), "error", pageMsg);
		}
		// ��ȡ������
		OrderHandoverController controller = getFormController(processInstance);
		controller.setReturnUrl(returnUrl);
		return controller.showHandoverForm(request, response);
	}
}
