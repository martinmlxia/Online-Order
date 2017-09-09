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
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.conant.order.util.Logger;
import com.conant.order.vo.OrderUtils;
import com.conant.order.vo.OrsOrder;
import com.conant.order.web.editor.LensModelEditor;
import com.conant.order.web.editor.TintColorEditor;

/**
 * �޸�Lens����������controller��
 * @author Administrator
 *
 */
public class ModifyLensOrderController extends ModifyOrderController
{
	private static final Logger log = Logger.getLogger(
			"ModifyLensOrderController", Logger.DEBUG, true);

	public ModifyLensOrderController()
	{
		// Initialize controller properties here or
		// in the Web Application Context
	}

	public OrsOrder initializeOrder(HttpServletRequest request, OrsOrder order)
	{
		OrderUtils.completeProcessInstance(order);
		OrderUtils.completeLensdetail(order);
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
		Map<String, Object> model = new HashMap<String, Object>();
		
		List lensmodels = getLensModel(request);
		
		// ��ȡ�ͻ��˵�locale��Ϣ
		Locale locale = RequestContextUtils.getLocale(request);
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
