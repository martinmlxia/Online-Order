/**
 * SalesModifyLensOrderController.java
 * 2009-1-22
 * Administrator
 */
package com.conant.order.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 销售角色修改订单操作的controller。
 * @author Administrator
 *
 */
public class SalesModifyLensOrderController extends ModifyLensOrderController
{
	protected List getLensModel(HttpServletRequest request)throws Exception
	{
		// 获取所有镜片类型		
		List lensmodels = getOnlineOrder().getLensModel();
		return lensmodels;
	}
}
