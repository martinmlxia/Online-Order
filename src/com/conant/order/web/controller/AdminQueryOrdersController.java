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
 * 管理员用查询订单。管理员查询所有订单。
 * @author Administrator
 * @deprecated 暂时不用
 */
public class AdminQueryOrdersController extends QueryOrdersController
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
	}
}
