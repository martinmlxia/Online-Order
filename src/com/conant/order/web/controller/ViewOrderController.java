/**
 * ViewOrderController.java
 * 2008-11-22
 * Administrator
 */
package com.conant.order.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OnlineOrderFacade;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrsOrder;
import com.conant.order.web.editor.LensMaterialEditor;
import com.conant.order.web.editor.LensTintEditor;
import com.conant.order.web.editor.LensTypeEditor;
import com.conant.order.web.editor.OrderStatusEditor;
import com.conant.order.web.editor.OrderTypeEditor;
import com.conant.order.web.editor.TintColorEditor;

/**
 * 查看订单操作的controller。
 * @author Administrator
 * 
 */
public class ViewOrderController extends SimpleFormController
{
	private static final Logger log = Logger.getLogger("ViewOrderController",
			Logger.DEBUG, true);

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
	
	protected Map referenceData(HttpServletRequest request) throws Exception
	{
		Map model = new HashMap();
		model.put("returnUrl", getReturnUrl());
		Locale locale = RequestContextUtils.getLocale(request);
		model.put("lang", locale);
		return model;
	}
	
	protected Object formBackingObject(HttpServletRequest request)
			throws ModelAndViewDefiningException
	{
		log.info("ViewOrderController formBackingObject...");
		
		try
		{
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			OrsOrder order = onlineOrder.getOrder(orderId);
			return order;
		}
		catch(ProcessException exp)
		{
			PageMsg pageMsg = new PageMsg();
			pageMsg.setTarget("_self");
			pageMsg.setCode(exp.getErrorCode());
			pageMsg.setUrl(getReturnUrl());
			ModelAndView modelAndView = new ModelAndView(getErrorView(), "error",
					pageMsg);
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}
	
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception
	{
		log.info("ViewOrderController initBinder...");
		
		super.initBinder(request, binder);
		// 获取locale和messageSource
		Locale locale = RequestContextUtils.getLocale(request);
		MessageSource source = (MessageSource)getApplicationContext().getBean("messageSource");		
		binder.registerCustomEditor(Integer.class, "processInstance.token",
				new OrderStatusEditor(true, locale, source));
		binder.registerCustomEditor(Integer.class, "ordertype",
				new OrderTypeEditor(true, locale, source));
		List materials = onlineOrder.getLensMaterials();
		binder.registerCustomEditor(Integer.class, "lensdetail.material.id",
				new LensMaterialEditor(materials));
		List lenstypes = onlineOrder.getLensTypes();
		binder.registerCustomEditor(Integer.class, "lensdetail.lenstype.id",
				new LensTypeEditor(lenstypes));
		List tinttypes = onlineOrder.getTintTypes();
		binder.registerCustomEditor(Integer.class, "lensdetail.tinttype.id",
				new LensTintEditor(tinttypes));
		List tintcolors = onlineOrder.getTintColors();
		TintColorEditor colorEditor = new TintColorEditor(true, tintcolors, locale, source);
		binder.registerCustomEditor(Integer.class, "lensdetail.tintcolor1.id", colorEditor);
		binder.registerCustomEditor(Integer.class, "lensdetail.tintcolor2.id", colorEditor);
	}
}
