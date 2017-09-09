/**
 * ExportOrdersController.java
 * 2009-2-15
 * Administrator
 */
package com.conant.order.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrderQuerier;
import com.conant.order.web.common.ExcelUtils;

/**
 * 导出销售要求的订单报表操作的controller。
 * @author Administrator
 * 
 */
public class SalesExportOrdersController extends QueryOrdersController
{
	private static final Logger log = Logger.getLogger(
			"SalesExportOrdersController", Logger.DEBUG, true);
	
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
				ExcelUtils.excelExportSalesReport(this.getOnlineOrder(), querier.getListOrder(), 
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
		// 设置salesowner
		String salesowner = (String)request.getSession().getAttribute("user_id");
		// 销售部查询归自己管理的订单
		querier.setSalesowner(salesowner);		
		if(isFormSubmission(request))
		{
			querier.setPageSize(-1);
		}
	}
}
