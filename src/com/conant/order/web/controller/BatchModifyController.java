/**
 * BatchModifyController.java
 * 2009-2-13
 * Administrator
 */
package com.conant.order.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OnlineOrderFacade;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.web.form.BatchModifyForm;

/**
 * 批量修改订单操作的controller。
 * @author Administrator
 *
 */
public class BatchModifyController extends SimpleFormController
{
	private static final Logger log = Logger.getLogger(
			"BatchModifyController", Logger.DEBUG, true);
	
	private OnlineOrderFacade onlineOrder;
	private String errorView;
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
	
	public void setErrorView(String errorView)
	{
		this.errorView = errorView;
	}

	protected Object formBackingObject(HttpServletRequest request)
		throws Exception
	{
		log.info("BatchModifyController formBackingObject...");
		
		BatchModifyForm command = new BatchModifyForm();
		return command;
	}
	
	private int batchModifyOrders(HttpServletRequest request, BatchModifyForm form) throws ProcessException
	{
		// 生成HQL语句
		StringBuilder queryString = new StringBuilder();
		queryString.append("update OrsOrder ");
		// 更新字段emergent
		queryString.append("set emergent = ? ");
		// 子句where
		queryString.append("where id in (");
		Integer[] orderIds = form.getOrderIds();
		for(int i = orderIds.length - 1; i >= 0; i--)
		{
			queryString.append(orderIds[i].intValue());
			if(i > 0)
			{
				queryString.append(",");
			}
		}
		queryString.append(")");
		// 参数值数组
		Object[] values = new Object[1];
		values[0] = new Boolean(form.isEmergent());
		
		log.info("BatchModifyController bulk update HQL: " + queryString.toString() + " @@emergent = " + form.isEmergent());
		
		// 执行批量更新
		return onlineOrder.batchUpdateOrders(queryString.toString(), values);
	}
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception
	{
		log.info("BatchModifyController onSubmit...");

		BatchModifyForm form = (BatchModifyForm)command;
		
		PageMsg pageMsg = new PageMsg();
		pageMsg.setUrl(getReturnUrl());
		pageMsg.setTarget("_self");

		try
		{
			batchModifyOrders(request, form);
			pageMsg.setCode("400019");
			return new ModelAndView(this.getSuccessView(), "success", pageMsg);
		}
		catch(ProcessException pe)
		{
			pageMsg.setCode(pe.getErrorCode());
			return new ModelAndView(errorView, "error", pageMsg);
		}
		catch(Exception ex)
		{
			pageMsg.setMsg(ex.getMessage());
			return new ModelAndView(errorView, "error", pageMsg);
		}
	}
}
