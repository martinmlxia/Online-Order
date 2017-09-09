/**
 * ClientQueryOrdersController.java
 * 2008-12-16
 * Administrator
 */
package com.conant.order.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrderQuerier;

/**
 * �ͻ��ò�ѯ�������ͻ���ѯ������Ҫ�����û������й��ˡ�
 * 
 * @author Administrator
 */
public class ClientQueryOrdersController extends QueryOrdersController
{	
	public void beforeExecution(HttpServletRequest request, HttpServletResponse response, 
			OrderQuerier querier) throws ProcessException
	{
		String user = (String)request.getSession().getAttribute("user_name");
		// û���û�������ʾ�û���½
		if(!StringUtils.hasText(user))
		{
			throw new ProcessException(400004);
		}
		querier.setUsername(user);
	}

	public void afterExecution(HttpServletRequest request, HttpServletResponse response,  
			OrderQuerier querier) throws ProcessException
	{

	}
}
