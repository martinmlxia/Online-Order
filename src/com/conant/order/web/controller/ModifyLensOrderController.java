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
 * 修改Lens订单操作的controller。
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
		// 获取客户端的locale信息
		Locale locale = RequestContextUtils.getLocale(request);
		// 只获取locale可用的镜片类型	
		List listLensmodels = getOnlineOrder().getLensModel(locale);
		return listLensmodels;
	}

	protected Map referenceData(HttpServletRequest request) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		
		List lensmodels = getLensModel(request);
		
		// 获取客户端的locale信息
		Locale locale = RequestContextUtils.getLocale(request);
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
