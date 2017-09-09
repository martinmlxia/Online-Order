/**
 * 
 */
package com.conant.order.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.conant.order.dao.OnlineOrderFacade;

/**
 * �鿴�����б������������
 * @author Administrator
 * @deprecated ����ʹ�øÿ�����
 */
public class ListOrdersController implements Controller
{
	private OnlineOrderFacade onlineOrder;

	public void setOnlineOrder(OnlineOrderFacade onlineOrder)
	{
		this.onlineOrder = onlineOrder;
	}

	//@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String username = "aaa";
		Map model = new HashMap();
		model.put("orderList", onlineOrder.getOrdersByUsername(username));
		return new ModelAndView("ListOrders", model);
	}
}
