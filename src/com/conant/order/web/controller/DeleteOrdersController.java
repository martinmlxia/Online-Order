package com.conant.order.web.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OnlineOrderFacade;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrderQuerier;
import com.conant.order.vo.OrderUtils;
import com.conant.order.vo.OrsOrder;
import com.conant.order.web.editor.OrderStatusEditor;
import com.conant.order.web.editor.OrderTypeEditor;

/**
 * ����ɾ������������������
 * @author Administrator
 * @deprecated ʹ��BatchCommandController����
 */
public class DeleteOrdersController extends SimpleFormController
{
	private static final Logger log = Logger.getLogger(
			"DeleteOrdersController", Logger.DEBUG, true);
	
	private OnlineOrderFacade onlineOrder;
	private String errorView;
	private String returnUrl;

	public DeleteOrdersController()
	{
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
	
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception
	{
		log.info("DeleteOrdersController formBackingObject...");
		
		OrderQuerier querier = new OrderQuerier();
		if(isFormSubmission(request))
		{
			// ��List������Ҫʹ��LazyList�������޷���
			List listOrder = ListUtils.lazyList(new ArrayList(), new Factory()
			{
				public Object create()
				{
					OrsOrder order = new OrsOrder();
					// ��ʼ������ʵ������
					OrderUtils.completeProcessInstance(order);
					return order;
				}
			});
			querier.setListOrder(listOrder);
		}
		return querier;
	}
	
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception
	{
		log.info("DeleteOrdersController initBinder...");
		
		super.initBinder(request, binder);
		// ΪList�����ָ���ֶ�ע��editor
		// ��ȡlocale��messageSource
		Locale locale = RequestContextUtils.getLocale(request);
		MessageSource source = (MessageSource)getApplicationContext().getBean("messageSource");
		binder.registerCustomEditor(Integer.class, "listOrder.processInstance.token",
				new OrderStatusEditor(true, locale, source));
		binder.registerCustomEditor(Integer.class, "listOrder.ordertype",
				new OrderTypeEditor(true, locale, source));
	}

	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception
	{
		log.info("DeleteOrdersController onSubmit...");

		OrderQuerier querier = (OrderQuerier)command;
		List<OrsOrder> orders = querier.getListOrder();
		
		log.info("DeleteOrdersController ids length === " + orders.size());
		// ����LazyList
		List<OrsOrder> selectOrders = new ArrayList<OrsOrder>();
		for(Iterator<OrsOrder> iter = orders.iterator(); iter.hasNext();)
		{
			OrsOrder order = iter.next();
			// ����null�����id�ֶ�Ϊnull�Ķ���
			if((order != null) 
					&& (order.getId() != null))
			{
				selectOrders.add(order);
			}
		}
		
		PageMsg pageMsg = new PageMsg();
		pageMsg.setUrl(getReturnUrl());
		pageMsg.setTarget("_self");

		try
		{
			//onlineOrder.deleteOrders(selectOrders);
			pageMsg.setCode("400010");
			return new ModelAndView(this.getSuccessView(), "success", pageMsg);
		}
		/*
		catch(ProcessException pe)
		{
			pageMsg.setCode(pe.getErrorCode());
			return new ModelAndView("common/err", "error", pageMsg);
		}
		*/
		catch(Exception ex)
		{
			pageMsg.setMsg(ex.getMessage());
			return new ModelAndView(getErrorView(), "error", pageMsg);
		}
	}
}
