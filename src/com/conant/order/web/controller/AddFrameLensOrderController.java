/**
 * AddFrameLensOrderController.java
 * 2009-2-23
 * Administrator
 */
package com.conant.order.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OnlineOrderFacade;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrderStatus;
import com.conant.order.vo.OrderUtils;
import com.conant.order.vo.OrsOrder;
import com.conant.order.vo.UserInfo;
import com.conant.order.web.editor.DiameterUnitEditor;
import com.conant.order.web.editor.FramePatternEditor;
import com.conant.order.web.editor.FrameTypeEditor;
import com.conant.order.web.editor.LensTreatEditor;
import com.conant.order.web.editor.PrismDirectionEditor;
import com.conant.order.web.form.FormUtils;
import com.conant.order.web.form.FrameLensOrder;

/**
 * 添加Frame&Lens订单操作的controller。
 * @author Administrator
 *
 */
public class AddFrameLensOrderController extends SimpleFormController
{
	private static final Logger log = Logger.getLogger(
			"AddFrameLensOrderController", Logger.ALL, true);

	private OnlineOrderFacade onlineOrder;
	private String errorView;
	private String printView;
	private String returnUrl;
	
	public OnlineOrderFacade getOnlineOrder()
	{
		return onlineOrder;
	}
	
	public void setOnlineOrder(OnlineOrderFacade onlineOrder)
	{
		this.onlineOrder = onlineOrder;
	}
	
	public String getErrorView()
	{
		return errorView;
	}

	public void setErrorView(String errorView)
	{
		this.errorView = errorView;
	}

	public String getPrintView()
	{
		return printView;
	}

	public void setPrintView(String printView)
	{
		this.printView = printView;
	}
	
	public String getReturnUrl()
	{
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl)
	{
		this.returnUrl = returnUrl;
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws ModelAndViewDefiningException
	{
		log.info("AddFrameLensOrderController formBackingObject...");
		
		FrameLensOrder order = new FrameLensOrder();
		return order;
	}
	
	protected Map referenceData(HttpServletRequest request) throws Exception
	{
		Map model = new HashMap();
		
		// 获取客户端的locale信息
		Locale locale = RequestContextUtils.getLocale(request);
		model.put("lang", locale);
		// 返回url
		model.put("returnUrl", getReturnUrl());
		// 获取messageSource对象
		MessageSource source = (MessageSource)getApplicationContext().getBean("messageSource");
		// 直径单位
		DiameterUnitEditor diameterUnit = new DiameterUnitEditor(true, locale, source);
		List<String> listDiameterUnit = new ArrayList<String>();
		listDiameterUnit.add(source.getMessage("list.pleaseselect", null, locale));
		listDiameterUnit.addAll(diameterUnit.getListItems());
		model.put("listDiameterUnit", listDiameterUnit);
		// 镜架类型
		FrameTypeEditor frametypeEditor = new FrameTypeEditor(locale, source);
		List<String> listFrameType = new ArrayList<String>();
		listFrameType.add(source.getMessage("list.pleaseselect", null, locale));
		listFrameType.addAll(frametypeEditor.getListItems());
		model.put("listFrameType", listFrameType);
		//镜架款式
		FramePatternEditor framepatternEditor=new FramePatternEditor(locale,source);
		List<String> listFramePattern=new ArrayList<String>();
		listFramePattern.add(source.getMessage("list.pleaseselect", null,locale));
		listFramePattern.addAll(framepatternEditor.getListItems());
		model.put("listFramePattern", listFramePattern);
		return model;
	}
	
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception
	{
		super.initBinder(request, binder);
		
		PrismDirectionEditor prismEditor = new PrismDirectionEditor();
		binder.registerCustomEditor(Integer.class, "rhpd", prismEditor);
		binder.registerCustomEditor(Integer.class, "lhpd", prismEditor);
		binder.registerCustomEditor(Integer.class, "rbhpd",prismEditor);
		binder.registerCustomEditor(Integer.class, "lbhpd",prismEditor);
		
		// 获取客户端的locale信息
		Locale locale = RequestContextUtils.getLocale(request);
		// 获取messageSource对象
		MessageSource source = (MessageSource)getApplicationContext().getBean("messageSource");
		// 注册直径单位editor，实现数据类型自动转换
		DiameterUnitEditor diameterEditor = new DiameterUnitEditor(true, locale, source);
		binder.registerCustomEditor(Integer.class, "diameterUnit", diameterEditor);
		// 注册镜架类型editor，实现数据类型自动转换
		FrameTypeEditor frametypeEditor = new FrameTypeEditor(locale, source);
		binder.registerCustomEditor(Integer.class, "frametype", frametypeEditor);
		//注册镜架款式editor，实现数据类型自动转换
		FramePatternEditor framepatternEditor=new FramePatternEditor(locale,source);
		binder.registerCustomEditor(Integer.class, "framepattern",framepatternEditor);
		//注册加工程度editor,实现数据类型自动转换
		LensTreatEditor lenstreatEditor=new LensTreatEditor(locale,source);
		binder.registerCustomEditor(Integer.class, "treat",lenstreatEditor);
	}
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception
	{

		log.info("AddFrameLensOrderController onSubmit...");
		FrameLensOrder form = (FrameLensOrder)command;

		PageMsg pageMsg = new PageMsg();
		pageMsg.setTarget("_self");
		pageMsg.setUrl(getReturnUrl());
		
		try
		{
			// 将form转换为实体，转换后的订单已经经过初始化，订单类型为镜片+镜架订单
			OrsOrder order = FormUtils.convertRXOrderFrom(form, onlineOrder);
			// 缺省字段
			// 获取客户名称
			String userId = (String)request.getSession().getAttribute("user_id");
			// 设置用户名称，不允许编辑
			UserInfo user = new UserInfo();
			user.setUser_id(userId);
			order.setUser(user);
			// 设置订单日期，不允许编辑
			order.setOrdereddate(new Date());
			// 初始化流程
			OrderUtils.completeProcessInstance(order);
			// 初始化流程节点
			OrderUtils.initializeProcessInstance(order);
			// 设置流程起始节点
			order.getProcessInstance().setToken(OrderStatus.TYPE_AUDITING);
			onlineOrder.saveOrder(order);
			pageMsg.setCode("400009");
			if(form.isPrintAfterCommit())
			{
				pageMsg.setUrl(getPrintView() + "?orderId=" + order.getId());
			}
			return new ModelAndView(this.getSuccessView(), "success", pageMsg);
		}
		catch(ProcessException pe)
		{
			pageMsg.setCode(pe.getErrorCode());
			return new ModelAndView(this.getErrorView(), "error", pageMsg);
		}
		catch(Exception ex)
		{
			log.exception(ex);
			pageMsg.setMsg(ex.getMessage());
			return new ModelAndView(this.getErrorView(), "error", pageMsg);
		}
	}
}
