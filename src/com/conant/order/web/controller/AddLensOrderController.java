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
 * ��Ӿ�Ƭ����������controller��
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
		// ���þ�Ƭ��������
		OrderUtils.completeLensdetail(order);
		// ��ȡ�û�����
		String userId = (String)request.getSession().getAttribute("user_id");
		if(!StringUtils.hasText(userId))
		{
			PageMsg pageMsg = new PageMsg();
			pageMsg.setTarget("_self");
			pageMsg.setCode("400004");
			ModelAndView modelAndView = new ModelAndView(getErrorView(), "error", pageMsg);
			throw new ModelAndViewDefiningException(modelAndView);
		}
		// ���ÿͻ����ƣ�������༭
		UserInfo user = new UserInfo();
		user.setUser_id(userId);
		order.setUser(user);
		// ���ö������ڣ�������༭
		order.setOrdereddate(new Date());
		// �����form�ύ����ʼ�����̽ڵ�
		if(isFormSubmission(request))
		{
			OrderUtils.initializeProcessInstance(order);
		}
		
		return order;
	}
	
	protected List getLensModel(HttpServletRequest request)throws Exception
	{
		// ��ȡ�ͻ��˵�locale��Ϣ
		Locale locale = RequestContextUtils.getLocale(request);
		// ֻ��ȡlocale���õľ�Ƭ����	
		List listLensmodels = getOnlineOrder().getLensModel(locale);
		return listLensmodels;
	}

	protected Map referenceData(HttpServletRequest request) throws Exception
	{
		Map model = new HashMap();
		
		// ��ȡ�ͻ��˵�locale��Ϣ
		Locale locale = RequestContextUtils.getLocale(request);
		
		// ֻ��ȡlocale���õľ�Ƭ����
		List lensmodels = getOnlineOrder().getLensModel(locale);
		// ��ȡmessageSource����
		MessageSource source = (MessageSource)getApplicationContext().getBean("messageSource");
		
		List<String> listModels = new ArrayList<String>();
		// ��ȡ��Ƭ�����б�
		LensModelEditor modelEditor = new LensModelEditor(lensmodels);
		// ���"��ѡ��"ѡ��
		listModels.add(source.getMessage("list.pleaseselect", null, locale));
		// ��ӻ�ȡ�������о�Ƭ����
		listModels.addAll(modelEditor.getListItems());
		model.put("lensmodels", listModels);
		
		// ��ȡȾɫƬ�б�
		List tintcolors = getOnlineOrder().getTintColors();
		List<String> listColors = new ArrayList<String>();
		TintColorEditor colorEditor = new TintColorEditor(true, tintcolors, locale, source);
		// ���"��ѡ��"ѡ��
		listColors.add(source.getMessage("list.pleaseselect", null, locale));
		// ��ӻ�ȡ��������ȾɫƬ����
		listColors.addAll(colorEditor.getListItems());
		model.put("tintcolors", listColors);
		
		return model;
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception
	{
		super.initBinder(request, binder);
		
		// ��ȡ�ͻ��˵�locale��Ϣ
		Locale locale = RequestContextUtils.getLocale(request);
		// ��ȡmessageSource����
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
