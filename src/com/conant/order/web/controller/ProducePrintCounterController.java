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

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.Controller;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OnlineOrderFacade;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrderStatus;
import com.conant.order.vo.OrsOrder;
import com.conant.order.vo.TokenVariable;


/**
 * 打印单个生产订单操作的controller。
 * @author Administrator
 * 
 */
public class ProducePrintCounterController implements Controller
{
	private static final Logger log = Logger.getLogger("ProducePrintCounterController",
			Logger.DEBUG, true);

	private OnlineOrderFacade onlineOrder;
	private String errorView;
	private String successView;
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
	
	public String getSuccessView()
	{
		return successView;
	}

	public void setSuccessView(String successView)
	{
		this.successView = successView;
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
		log.info("ProducePrintCounterController handleRequest...");
		
		try
		{
			String orderIds = request.getParameter("orderIds");
			if(orderIds != null && orderIds.length() > 0)
			{
				if(orderIds.endsWith(","))
				{
					orderIds = orderIds.substring(0, orderIds.length() - 1);
				}
			}
			String[] listStringIds = orderIds.split(",");
			Integer[] listIds = new Integer[listStringIds.length];
			for(int i = 0; i < listStringIds.length; i++)
			{
				listIds[i] = new Integer(listStringIds[i]);
			}
			List<OrsOrder> listOrder = onlineOrder.getOrders(listIds);
			// 增加打印次数
			for(OrsOrder order : listOrder)
			{
				TokenVariable variable = null;
				try
				{
					variable = onlineOrder.getTokenVariable(order.getProcessInstance().getId(), 
						OrderStatus.TYPE_PRODUCING, "printTimes");
				}
				catch(ProcessException exp)
				{
					variable = null;
				}
				// 不存在则新建变量
				if(variable == null)
				{
					variable = new TokenVariable();
					variable.setName("printTimes");
					variable.setStringvalue("1");
					variable.setToken(OrderStatus.TYPE_PRODUCING);
					variable.setProcessInstance(order.getProcessInstance());
				}
				else
				{
					int times = Integer.parseInt(variable.getStringvalue());
					variable.setStringvalue(String.valueOf(times + 1));
				}
				onlineOrder.saveTokenVariable(variable);
			}
			Map model = new HashMap();
			model.put("listOrder", listOrder);
			model.put("returnUrl", getReturnUrl());
			return new ModelAndView(getSuccessView(), model);
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
		catch(Exception e)
		{
			throw e;
		}		
	}
}
