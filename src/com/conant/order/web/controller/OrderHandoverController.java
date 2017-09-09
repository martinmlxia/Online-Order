/**
 * OrderHandoverController.java
 * 2009-1-11
 * Administrator
 */
package com.conant.order.web.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.conant.order.dao.OnlineOrderFacade;
import com.conant.order.util.Logger;
import com.conant.order.vo.ProcessInstance;
import com.conant.order.web.editor.OrderStatusEditor;
import com.conant.order.web.form.HandoverForm;

/**
 * 订单移交操作的通用controller。
 * @author Administrator
 * 
 */
public class OrderHandoverController extends SimpleFormController
{
	private static final Logger log = Logger.getLogger(
			"OrderHandoverController", Logger.DEBUG, true);

	private OnlineOrderFacade onlineOrder;
	private String errorView;
	private String returnUrl;

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

	public String getReturnUrl()
	{
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl)
	{
		this.returnUrl = returnUrl;
	}

	public int getTransition(HttpServletRequest request)
	{
		boolean forward = (request.getParameter("next") != null);
		boolean backward = (request.getParameter("prev") != null);
		boolean parallel = (request.getParameter("paral") != null);

		if(forward && !backward && !parallel)
		{
			return TransitionType.TD_FORWARD;
		}
		else if(backward && !forward && !parallel)
		{
			return TransitionType.TD_BACKWARD;
		}
		else if(parallel && !forward && !backward)
		{
			return TransitionType.TD_PARALLEL;
		}

		return TransitionType.TD_INVALID;
	}
	
	protected Map referenceData(HttpServletRequest request) throws Exception
	{
		Map model = new HashMap();
		// 返回url
		model.put("returnUrl", getReturnUrl());
		return model;
	}

	protected Object formBackingObject(HttpServletRequest request)
	{
		HandoverForm command = new HandoverForm();
		if(!isFormSubmission(request))
		{
			ProcessInstance processInstance = (ProcessInstance)request.getAttribute("processInstance");
			int token = processInstance.getToken();
			command.setCurrentNode(token);
			command.setPreviousNode(token - 1);
			command.setNextNode(token + 1);
			command.setProcessInstance(processInstance);
		}
		else
		{
			command.setProcessInstance(new ProcessInstance());
		}

		return command;
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception
	{
		super.initBinder(request, binder);
		// 获取locale和messageSource
		Locale locale = RequestContextUtils.getLocale(request);
		MessageSource source = (MessageSource)getApplicationContext().getBean(
				"messageSource");
		binder.registerCustomEditor(Integer.class, "currentNode",
				new OrderStatusEditor(true, locale, source));
		binder.registerCustomEditor(Integer.class, "previousNode",
				new OrderStatusEditor(true, locale, source));
		binder.registerCustomEditor(Integer.class, "nextNode",
				new OrderStatusEditor(true, locale, source));
	}

	protected ModelAndView showHandoverForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		return super.showNewForm(request, response);
	}

	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors)
			throws Exception
	{
		return super.showForm(request, response, errors);
	}
}
