/**
 * HandoverOrderController.java
 * 2008-12-17
 * Administrator
 */
package com.conant.order.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.Controller;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OnlineOrderFacade;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrderStatus;
import com.conant.order.vo.ProcessInstance;
import com.conant.order.web.form.HandoverForm;

/**
 * ͨ�ö����ƽ�������������
 * @author Administrator
 * @deprecated ʹ��OrderHandoverController����
 */
public class HandoverOrderController implements Controller
{
	private static final Logger log = Logger.getLogger(
			"HandoverOrderController", Logger.DEBUG, true);

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
	
	//@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		// ��ȡ����ʵ������
		String strProcessId = request.getParameter("processId");
		
		PageMsg pageMsg = new PageMsg();
		pageMsg.setTarget("_self");
		pageMsg.setUrl(getReturnUrl());
		pageMsg.setCode("400005");		
		if(!StringUtils.hasText(strProcessId))
		{
			// ��Ч�ƽ�����
			return new ModelAndView("common/err", "error", pageMsg);
		}
		ProcessInstance processInstance = null;
		try
		{
			int processId = Integer.parseInt(strProcessId);
			processInstance = onlineOrder.getProcessInstance(processId);
		}
		catch(ProcessException exp)
		{
			// ��Ч�ƽ�����
			return new ModelAndView("common/err", "error", pageMsg);
		}
		// ��ȡ������ͼ
		String formView = getFormView(processInstance);
		if(formView == null)
		{
			// ��Ч�ƽ�����
			return new ModelAndView("common/err", "error", pageMsg);
		}
		
		Map model = new HashMap();
		model.put("returnUrl", getReturnUrl());
		model.put("process", processInstance);		
		return new ModelAndView(formView, model);
	}
	
	/**
	 * �������̵�ǰ״̬��ȡ������ͼ��
	 * @param processId
	 * @return
	 */
	private String getFormView(ProcessInstance process)
	{
		int token = process.getToken();
		
		String formView = null;
		switch(token)
		{
		case OrderStatus.TYPE_AUDITING:
			formView = "Order_Audit";
			break;
		case OrderStatus.TYPE_PRODUCING:
			formView = "Order_Produce";
			break;
		case OrderStatus.TYPE_DELIVERED:
			formView = "Order_Deliver";
			break;
		case OrderStatus.TYPE_COMPLETE:
			formView = "Order_Complete";
			break;
		default:
			break;
		}
		
		return formView;
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws ModelAndViewDefiningException
	{
		log.info("HandoverOrderController formBackingObject...");

		HandoverForm command = new HandoverForm();

		String strProcessId = request.getParameter("processId");
		try
		{
			if(StringUtils.hasText(strProcessId))
			{
				int processId = Integer.parseInt(strProcessId);
				ProcessInstance processInstance = onlineOrder.getProcessInstance(processId);
			}
			return command;
		}
		catch(ProcessException exp)
		{
			PageMsg pageMsg = new PageMsg();
			pageMsg.setTarget("_self");
			pageMsg.setCode(exp.getErrorCode());
			pageMsg.setUrl(getReturnUrl());
			ModelAndView modelAndView = new ModelAndView("common/err", "error",
					pageMsg);
			throw new ModelAndViewDefiningException(modelAndView);
		}
		catch(Exception ex)
		{
			PageMsg pageMsg = new PageMsg();
			pageMsg.setTarget("_self");
			pageMsg.setMsg(ex.getMessage());
			pageMsg.setUrl(getReturnUrl());
			ModelAndView modelAndView = new ModelAndView("common/err", "error",
					pageMsg);
			throw new ModelAndViewDefiningException(modelAndView);			
		}
	}
	
	/*
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception
	{
		log.info("HandoverOrderController initBinder...");
		
		super.initBinder(request, binder);
		// ��ȡlocale��messageSource
		Locale locale = RequestContextUtils.getLocale(request);
		MessageSource source = (MessageSource)getApplicationContext().getBean("messageSource");		
		binder.registerCustomEditor(Integer.class, "currentStatus",
				new OrderStatusEditor(true, locale, source));
		binder.registerCustomEditor(Integer.class, "previousStatus",
				new OrderStatusEditor(true, locale, source));
		binder.registerCustomEditor(Integer.class, "nextStatus",
				new OrderStatusEditor(true, locale, source));
	}

	private void fillHandoverInfo(OrsOrder order, int srcStatus, int tarStatus,
			HandoverForm info)
	{
		log.info("handover from " + srcStatus + " to " + tarStatus);
		log.info("handover info [Date: " + info.getDate() + "][User: " + info.getUser() + "][Remarks: " + info.getRemarks() + "].");
		
		if(tarStatus == OrderStatus.TYPE_PRODUCING)
		{
			// �����
			order.setAuditeddate(info.getDate());
			order.setAuditIncharge(info.getUser());
		}
		else if(tarStatus == OrderStatus.TYPE_DELIVERING)
		{
			// ������
			order.setProduceddate(info.getDate());
			order.setProduceIncharge(info.getUser());
		}
		else if(tarStatus == OrderStatus.TYPE_CHECKING)
		{
			// �ѷ���
			order.setDelivereddate(info.getDate());
			order.setDeliverIncharge(info.getUser());
		}
		else if(tarStatus == OrderStatus.TYPE_COMPLETE)
		{
			// �ѽ���
			order.setCheckeddate(info.getDate());
			order.setCheckIncharge(info.getUser());
		}
		// ����״̬
	}

	//@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception
	{
		log.info("HandoverOrderController onSubmit...");

		HandoverForm info = (HandoverForm)command;

		PageMsg pageMsg = new PageMsg();
		pageMsg.setUrl(getPageMsgUrl());
		pageMsg.setTarget("_self");

		boolean handoverNext = (request.getParameter("next") != null);
		boolean handoverPrev = (request.getParameter("prev") != null);

		try
		{
			if(handoverPrev && handoverNext)
			{
				// ��Ч�ƽ�����
				throw new ProcessException(400005);
			}
			// ��ѯ������ǰ״̬
			OrsOrder order = orderDao.getOrder(info.getOrderId());
			int status = 0;
			if(status < OrderStatus.TYPE_START || status > OrderStatus.TYPE_END)
			{
				// ��Ч����״̬
				throw new ProcessException(400006);
			}
			// ��ȡ���ں��û�
			info.setDate(new Date());
			String user = (String)request.getSession().getAttribute("user_name");
			info.setUser(user);
			if(handoverPrev)
			{
				// �����ƽ�
				if(status == OrderStatus.TYPE_START)
				{
					throw new ProcessException(400007);
				}
				// ��д�ƽ���Ϣ
				fillHandoverInfo(order, status, status - 1, info);
			}
			else
			{
				// �����ƽ�
				if(status == OrderStatus.TYPE_END)
				{
					throw new ProcessException(400008);
				}
				// ��д�ƽ���Ϣ
				fillHandoverInfo(order, status, status + 1, info);
			}
			// ִ���ƽ�
			orderDao.saveOrder(order);
			pageMsg.setCode("400011");
			return new ModelAndView("common/ok", "success", pageMsg);
		}
		catch(ProcessException exp)
		{
			pageMsg.setCode(exp.getErrorCode());
			return new ModelAndView("common/err", "error", pageMsg);
		}
		catch(Exception ex)
		{
			pageMsg.setMsg(ex.getMessage());
			return new ModelAndView("common/err", "error", pageMsg);
		}
	}
*/	
}
