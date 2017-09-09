/**
 * StaffQueryOrdersController.java
 * 2008-12-16
 * Administrator
 */
package com.conant.order.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrderQuerier;

/**
 * 销售部用查询订单。销售部查询归自己管理的订单。
 * 
 * @author Administrator
 */
public class SalesQueryOrdersController extends QueryOrdersController
{
	@Override
	public void afterExecution(HttpServletRequest request, HttpServletResponse response, 
			OrderQuerier querier) throws ProcessException
	{
	}

	@Override
	public void beforeExecution(HttpServletRequest request, HttpServletResponse response, 
			OrderQuerier querier) throws ProcessException
	{
		// 设置salesowner
		String salesowner = (String)request.getSession().getAttribute("user_id");
		// 销售部查询归自己管理的订单
		querier.setSalesowner(salesowner);
	}
}
