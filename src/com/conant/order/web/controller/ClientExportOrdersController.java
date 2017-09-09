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
 * 客户导出订单操作的控制器。
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
			// 没有符合条件的订单
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
			// 没有用户名则提示用户登陆
			if(!StringUtils.hasText(user))
			{
				throw new ProcessException(400004);
			}
			querier.setUsername(user);
			// 查询所有记录,-1表示不分页
			querier.setPageSize(-1);
		}
	}
}
