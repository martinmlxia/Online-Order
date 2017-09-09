/**
 * ExportOrdersController.java
 * 2009-2-15
 * Administrator
 */
package com.conant.order.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrderQuerier;
import com.conant.order.web.common.ExcelUtils;

/**
 * �ͻ��������������Ŀ�������
 * @author Administrator
 * 
 */
public class ClientExportOrdersController extends QueryOrdersController
{
	private static final Logger log = Logger.getLogger(
			"ClientExportOrdersController", Logger.DEBUG, true);
	
	@Override
	public void afterExecution(HttpServletRequest request, HttpServletResponse response, 
			OrderQuerier querier) throws ProcessException
	{
		if(isFormSubmission(request))
		{
			// û�з��������Ķ���
			if(querier.getListOrder() == null 
					|| querier.getListOrder().size() == 0)
			{
				throw new ProcessException(400023);
			}
			try
			{
				ExcelUtils.excelExportClientReport(this.getOnlineOrder(), querier.getListOrder(), 
					request, response, this.getApplicationContext());
			}
			catch(Exception exp)
			{
				log.exception(exp);
				throw new ProcessException(400024);
			}
		}
	}

	@Override
	public void beforeExecution(HttpServletRequest request, HttpServletResponse response, 
			OrderQuerier querier) throws ProcessException
	{
		if(isFormSubmission(request))
		{
			String user = (String)request.getSession().getAttribute("user_name");
			// û���û�������ʾ�û���½
			if(!StringUtils.hasText(user))
			{
				throw new ProcessException(400004);
			}
			querier.setUsername(user);
			// ��ѯ���м�¼,-1��ʾ����ҳ
			querier.setPageSize(-1);
		}
	}
}
