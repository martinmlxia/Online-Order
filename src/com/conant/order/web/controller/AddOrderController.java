/**
 * AddOrderController.java
 * 2008-12-14
 * Administrator
 */
package com.conant.order.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OnlineOrderFacade;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrderStatus;
import com.conant.order.vo.OrderUtils;
import com.conant.order.vo.OrsOrder;

/**
 * @author Administrator
 *
 */
public class AddOrderController extends SimpleFormController
{
	private static final Logger log = Logger.getLogger(
			"AddOrderController", Logger.DEBUG, true);
	
	private OnlineOrderFacade onlineOrder;
	private String errorView;

	public AddOrderController()
	{
		// Initialize controller properties here or
		// in the Web Application Context
	}

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
	
	protected Object formBackingObject(HttpServletRequest request)
		throws ModelAndViewDefiningException
	{
		OrsOrder order = new OrsOrder();
		OrderUtils.completeProcessInstance(order);
		
		return order;
	}
	
	// Use onSubmit instead of doSubmitAction
	// when you need access to the Request, Response, or BindException objects
	//@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception
	{

		log.info("AddOrderController onSubmit...");
		OrsOrder order = (OrsOrder)command;

		PageMsg pageMsg = new PageMsg();
		pageMsg.setTarget("_self");
		pageMsg.setUrl("/myOrders.ord");

		try
		{
			// 设置流程起始节点
			order.getProcessInstance().setToken(OrderStatus.TYPE_AUDITING);
			onlineOrder.saveOrder(order);
			pageMsg.setCode("400009");
			return new ModelAndView(this.getSuccessView(), "success", pageMsg);
		}
		catch(ProcessException pe)
		{
			pageMsg.setCode(pe.getErrorCode());
			return new ModelAndView(this.getErrorView(), "error", pageMsg);
		}
		catch(Exception ex)
		{
			log.exception(ex);
			pageMsg.setMsg(ex.getMessage());
			return new ModelAndView(this.getErrorView(), "error", pageMsg);
		}
	}
}
