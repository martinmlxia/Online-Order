/**
 * ProduceQueryOrdersController.java
 * 2008-12-16
 * Administrator
 */
package com.conant.order.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrderQuerier;
import com.conant.order.vo.OrderStatus;

/**
 * 生产部用查询订单。生产部查询订单只查询审批过的订单。
 * 
 * @author Administrator
 */
public class ProduceQueryOrdersController extends QueryOrdersController
{
	private static final Logger log = Logger.getLogger("ProduceQueryOrdersController",
			Logger.DEBUG, true);
	
	@Override
	public void beforeExecution(HttpServletRequest request, HttpServletResponse response, 
			OrderQuerier querier) throws ProcessException
	{
		// 设置查询订单状态
		// 生产部可以查询已审批-已发货之间的订单
		querier.setStart_orderstatus(OrderStatus.TYPE_PRODUCING);
		querier.setEnd_orderstatus(OrderStatus.TYPE_DELIVERED);
		querier.setProductowner((String)request.getSession().getAttribute("user_id"));
	}
	
	@Override
	public void afterExecution(HttpServletRequest request, HttpServletResponse response, 
			OrderQuerier querier) throws ProcessException
	{
		// TODO Auto-generated method stub
	}
}
