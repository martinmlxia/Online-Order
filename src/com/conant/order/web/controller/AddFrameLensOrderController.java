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
 * ���Frame&Lens����������controller��
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
		
		// ��ȡ�ͻ��˵�locale��Ϣ
		Locale locale = RequestContextUtils.getLocale(request);
		model.put("lang", locale);
		// ����url
		model.put("returnUrl", getReturnUrl());
		// ��ȡmessageSource����
		MessageSource source = (MessageSource)getApplicationContext().getBean("messageSource");
		// ֱ����λ
		DiameterUnitEditor diameterUnit = new DiameterUnitEditor(true, locale, source);
		List<String> listDiameterUnit = new ArrayList<String>();
		listDiameterUnit.add(source.getMessage("list.pleaseselect", null, locale));
		listDiameterUnit.addAll(diameterUnit.getListItems());
		model.put("listDiameterUnit", listDiameterUnit);
		// ��������
		FrameTypeEditor frametypeEditor = new FrameTypeEditor(locale, source);
		List<String> listFrameType = new ArrayList<String>();
		listFrameType.add(source.getMessage("list.pleaseselect", null, locale));
		listFrameType.addAll(frametypeEditor.getListItems());
		model.put("listFrameType", listFrameType);
		//���ܿ�ʽ
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
		
		// ��ȡ�ͻ��˵�locale��Ϣ
		Locale locale = RequestContextUtils.getLocale(request);
		// ��ȡmessageSource����
		MessageSource source = (MessageSource)getApplicationContext().getBean("messageSource");
		// ע��ֱ����λeditor��ʵ�����������Զ�ת��
		DiameterUnitEditor diameterEditor = new DiameterUnitEditor(true, locale, source);
		binder.registerCustomEditor(Integer.class, "diameterUnit", diameterEditor);
		// ע�᾵������editor��ʵ�����������Զ�ת��
		FrameTypeEditor frametypeEditor = new FrameTypeEditor(locale, source);
		binder.registerCustomEditor(Integer.class, "frametype", frametypeEditor);
		//ע�᾵�ܿ�ʽeditor��ʵ�����������Զ�ת��
		FramePatternEditor framepatternEditor=new FramePatternEditor(locale,source);
		binder.registerCustomEditor(Integer.class, "framepattern",framepatternEditor);
		//ע��ӹ��̶�editor,ʵ�����������Զ�ת��
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
			// ��formת��Ϊʵ�壬ת����Ķ����Ѿ�������ʼ������������Ϊ��Ƭ+���ܶ���
			OrsOrder order = FormUtils.convertRXOrderFrom(form, onlineOrder);
			// ȱʡ�ֶ�
			// ��ȡ�ͻ�����
			String userId = (String)request.getSession().getAttribute("user_id");
			// �����û����ƣ�������༭
			UserInfo user = new UserInfo();
			user.setUser_id(userId);
			order.setUser(user);
			// ���ö������ڣ�������༭
			order.setOrdereddate(new Date());
			// ��ʼ������
			OrderUtils.completeProcessInstance(order);
			// ��ʼ�����̽ڵ�
			OrderUtils.initializeProcessInstance(order);
			// ����������ʼ�ڵ�
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
