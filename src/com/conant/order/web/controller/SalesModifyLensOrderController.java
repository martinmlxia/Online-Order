/**
 * SalesModifyLensOrderController.java
 * 2009-1-22
 * Administrator
 */
package com.conant.order.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * ���۽�ɫ�޸Ķ���������controller��
 * @author Administrator
 *
 */
public class SalesModifyLensOrderController extends ModifyLensOrderController
{
	protected List getLensModel(HttpServletRequest request)throws Exception
	{
		// ��ȡ���о�Ƭ����		
		List lensmodels = getOnlineOrder().getLensModel();
		return lensmodels;
	}
}
