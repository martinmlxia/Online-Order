/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conant.order.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.conant.order.common.PageMsg;
import com.conant.order.util.Logger;
import com.conant.order.vo.OrderType;
import com.conant.order.vo.OrderUtils;
import com.conant.order.vo.OrsOrder;
import com.conant.order.vo.UserInfo;
import com.conant.order.web.editor.LensModelEditor;
import com.conant.order.web.editor.TintColorEditor;

/**
 * 添加镜片订单操作的controller。
 * @author Administrator
 */
public class AddLensOrderController extends AddOrderController
{
	private static final Logger log = Logger.getLogger(
			"AddLensOrderController", Logger.DEBUG, true);

	public AddLensOrderController()
	{
		// Initialize controller properties here or
		// in the Web Application Context
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws ModelAndViewDefiningException
	{
		log.info("AddOrderFormController formBackingObject...");
		
		OrsOrder order = (OrsOrder)super.formBackingObject(request); 
		order.setOrdertype(OrderType.TYPE_LS);
		// 设置镜片参数对象
		OrderUtils.completeLensdetail(order);
		// 获取用户名称
		String userId = (String)request.getSession().getAttribute("user_id");
		if(!StringUtils.hasText(userId))
		{
			PageMsg pageMsg = new PageMsg();
			pageMsg.setTarget("_self");
			pageMsg.setCode("400004");
			ModelAndView modelAndView = new ModelAndView(getErrorView(), "error", pageMsg);
			throw new ModelAndViewDefiningException(modelAndView);
		}
		// 设置客户名称，不允许编辑
		UserInfo user = new UserInfo();
		user.setUser_id(userId);
		order.setUser(user);
		// 设置订单日期，不允许编辑
		order.setOrdereddate(new Date());
		// 如果是form提交，初始化流程节点
		if(isFormSubmission(request))
		{
			OrderUtils.initializeProcessInstance(order);
		}
		
		return order;
	}
	
	protected List getLensModel(HttpServletRequest request)throws Exception
	{
		// 获取客户端的locale信息
		Locale locale = RequestContextUtils.getLocale(request);
		// 只获取locale可用的镜片类型	
		List listLensmodels = getOnlineOrder().getLensModel(locale);
		return listLensmodels;
	}

	protected Map referenceData(HttpServletRequest request) throws Exception
	{
		Map model = new HashMap();
		
		// 获取客户端的locale信息
		Locale locale = RequestContextUtils.getLocale(request);
		
		// 只获取locale可用的镜片类型
		List lensmodels = getOnlineOrder().getLensModel(locale);
		// 获取messageSource对象
		MessageSource source = (MessageSource)getApplicationContext().getBean("messageSource");
		
		List<String> listModels = new ArrayList<String>();
		// 获取镜片类型列表
		LensModelEditor modelEditor = new LensModelEditor(lensmodels);
		// 添加"请选择"选项
		listModels.add(source.getMessage("list.pleaseselect", null, locale));
		// 添加获取到的所有镜片类型
		listModels.addAll(modelEditor.getListItems());
		model.put("lensmodels", listModels);
		
		// 获取染色片列表
		List tintcolors = getOnlineOrder().getTintColors();
		List<String> listColors = new ArrayList<String>();
		TintColorEditor colorEditor = new TintColorEditor(true, tintcolors, locale, source);
		// 添加"请选择"选项
		listColors.add(source.getMessage("list.pleaseselect", null, locale));
		// 添加获取到的所有染色片类型
		listColors.addAll(colorEditor.getListItems());
		model.put("tintcolors", listColors);
		
		return model;
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception
	{
		super.initBinder(request, binder);
		
		// 获取客户端的locale信息
		Locale locale = RequestContextUtils.getLocale(request);
		// 获取messageSource对象
		MessageSource source = (MessageSource)getApplicationContext().getBean("messageSource");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);
		// CustomDateEditor should be set allowEmpty, otherwise it would throw
		// exception
		// before validator can handle invalid values.
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		List lensmodels = getLensModel(request);
		binder.registerCustomEditor(Integer.class, "lensdetail.lensmodel.id", new LensModelEditor(lensmodels));
		List tintcolors = getOnlineOrder().getTintColors();
		binder.registerCustomEditor(String.class, "lensdetail.tintlens", new TintColorEditor(true, tintcolors, locale, source));
	}
}
