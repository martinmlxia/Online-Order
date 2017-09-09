/**
 * ModifyOrderController.java
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
import com.conant.order.vo.OrsOrder;

/**
 * @author Administrator
 */
public abstract class ModifyOrderController extends SimpleFormController
{
	private static final Logger log = Logger.getLogger("ModifyOrderController",
			Logger.DEBUG, true);
	
	private OnlineOrderFacade onlineOrder;
	private String errorView;
	private String returnUrl;

	public ModifyOrderController()
	{
		// Initialize controller properties here or
		// in the Web Application Context
	}

	public OnlineOrderFacade getOnlineOrder()
	{
		return this.onlineOrder;
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

	public String getReturnUrl()
	{
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl)
	{
		this.returnUrl = returnUrl;
	}

	public abstract OrsOrder initializeOrder(HttpServletRequest request, OrsOrder order);

	protected Object formBackingObject(HttpServletRequest request)
			throws ModelAndViewDefiningException
	{
		try
		{
			OrsOrder order;
			if(!isFormSubmission(request))
			{
				String orderId = request.getParameter("orderId");
				order = onlineOrder.getOrder(Integer.parseInt(orderId));
			}
			else
			{
				order = new OrsOrder(); 
				initializeOrder(request, order);
			}
			return order;
		}
		catch(ProcessException exp)
		{
			PageMsg pageMsg = new PageMsg();
			pageMsg.setTarget("_self");
			pageMsg.setCode(exp.getErrorCode());
			pageMsg.setUrl(getReturnUrl());
			throw new ModelAndViewDefiningException(new ModelAndView(
					"common/err", "error", pageMsg));
		}
	}
	
	// Use onSubmit instead of doSubmitAction
	// when you need access to the Request, Response, or BindException objects
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception
	{
		log.info("ModifyOrderController onSubmit...");
		OrsOrder order = (OrsOrder)command;

		PageMsg pageMsg = new PageMsg();
		pageMsg.setTarget("_self");
		pageMsg.setUrl(getReturnUrl());

		try
		{
			onlineOrder.saveOrder(order);
			pageMsg.setCode("400019");
			return new ModelAndView(this.getSuccessView(), "success", pageMsg);
		}
		catch(ProcessException exp)
		{
			pageMsg.setCode(exp.getErrorCode());
			return new ModelAndView(getErrorView(), "error", pageMsg);
		}
		catch(Exception ex)
		{
			pageMsg.setMsg(ex.getMessage());
			return new ModelAndView(getErrorView(), "error", pageMsg);
		}
	}
}
