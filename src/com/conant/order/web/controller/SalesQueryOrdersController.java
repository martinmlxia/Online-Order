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
 * ���۲��ò�ѯ���������۲���ѯ���Լ�����Ķ�����
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
		// ����salesowner
		String salesowner = (String)request.getSession().getAttribute("user_id");
		// ���۲���ѯ���Լ�����Ķ���
		querier.setSalesowner(salesowner);
	}
}
