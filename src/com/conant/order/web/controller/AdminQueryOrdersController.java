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
 * ����Ա�ò�ѯ����������Ա��ѯ���ж�����
 * @author Administrator
 * @deprecated ��ʱ����
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
