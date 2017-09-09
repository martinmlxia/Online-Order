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
 * �������ò�ѯ��������������ѯ����ֻ��ѯ�������Ķ�����
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
		// ���ò�ѯ����״̬
		// ���������Բ�ѯ������-�ѷ���֮��Ķ���
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
