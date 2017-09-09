/**
 * ProducePrintOrdersController.java
 * 2009-2-9
 * Administrator
 */
package com.conant.order.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OnlineOrderFacade;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.web.form.OrdersForm;

/**
 * 批量打印生产订单操作的controller。
 * @author Administrator
 * 
 */
public class ProducePrintOrdersController extends SimpleFormController
{
	private static final Logger log = Logger.getLogger(
			"ProducePrintOrdersController", Logger.DEBUG, true);
	
	private OnlineOrderFacade onlineOrder;
	private String errorView;
	private String returnUrl;

	public String getErrorView()
	{
		return errorView;
	}

	public void setErrorView(String errorView)
	{
		this.errorView = errorView;
	}
	
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

	protected Map referenceData(HttpServletRequest request) throws Exception
	{
		Map model = new HashMap();
		// 返回url
		model.put("returnUrl", getReturnUrl());
		return model;
	}
		
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception
	{
		log.info("ProducePrintOrdersController formBackingObject...");

		OrdersForm command = new OrdersForm();
		return command;
	}

	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception
	{
		log.info("ProducePrintOrdersController onSubmit...");

		OrdersForm form = (OrdersForm)command;
		
		PageMsg pageMsg = new PageMsg();
		pageMsg.setUrl(getReturnUrl());
		pageMsg.setTarget("_self");

		try
		{
			List listOrder = onlineOrder.getOrders(form.getOrderIds());
			return new ModelAndView(this.getSuccessView(), "listOrder", listOrder);
		}
		catch(ProcessException pe)
		{
			pageMsg.setCode(pe.getErrorCode());
			return new ModelAndView(getErrorView(), "error", pageMsg);
		}
		catch(Exception ex)
		{
			pageMsg.setMsg(ex.getMessage());
			return new ModelAndView(getErrorView(), "error", pageMsg);
		}
	}
}
