/**
 * ModifyFrameLensOrderController.java
 * 2009-2-27
 * Administrator
 */
package com.conant.order.web.controller;

import java.util.ArrayList;
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
import com.conant.order.vo.OrsOrder;
import com.conant.order.web.editor.DiameterUnitEditor;
import com.conant.order.web.editor.FramePatternEditor;
import com.conant.order.web.editor.FrameTypeEditor;
import com.conant.order.web.editor.LensTreatEditor;
import com.conant.order.web.editor.PrismDirectionEditor;
import com.conant.order.web.form.FormUtils;
import com.conant.order.web.form.FrameLensOrder;

/**
 * �޸�Frame&Les����������controller��
 * @author Administrator
 * 
 */
public class ModifyFrameLensOrderController extends SimpleFormController
{
	private static final Logger log = Logger.getLogger(
			"ModifyFrameLensOrderController", Logger.ALL, true);

	private OnlineOrderFacade onlineOrder;
	private String errorView;
	private String returnUrl;
	private String commitUrl;
	
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

	public String getReturnUrl()
	{
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl)
	{
		this.returnUrl = returnUrl;
	}

	public String getCommitUrl()
	{
		return commitUrl;
	}

	public void setCommitUrl(String commitUrl)
	{
		this.commitUrl = commitUrl;
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws ModelAndViewDefiningException
	{
		log.info("ModifyFrameLensOrderController formBackingObject...");
		
		FrameLensOrder form = new FrameLensOrder();
		// ���ύ������ִ�в�ѯ��������
		try
		{
			if(!isFormSubmission(request))
			{
				String orderId = request.getParameter("orderId");
				OrsOrder order = onlineOrder.getOrder(Integer.parseInt(orderId));
				// ���������ݸ��µ�Form
				FormUtils.synchronizeFrom(order, form, onlineOrder);
			}
		}
		catch(ProcessException exp)
		{
			PageMsg pageMsg = new PageMsg();
			pageMsg.setTarget("_self");
			pageMsg.setCode(exp.getErrorCode());
			pageMsg.setUrl(getReturnUrl());
			throw new ModelAndViewDefiningException(new ModelAndView(getErrorView(), "error", pageMsg));
		}
		
		return form;
	}
	
	protected Map referenceData(HttpServletRequest request) throws Exception
	{
		Map model = new HashMap();
		// ��ȡ�ͻ��˵�locale��Ϣ
		Locale locale = RequestContextUtils.getLocale(request);
		model.put("lang", locale);
		// ����url
		model.put("returnUrl", getReturnUrl());
		// �ύurl
		model.put("commitUrl", getCommitUrl());
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
		binder.registerCustomEditor(Integer.class, "rbhpd", prismEditor);
		binder.registerCustomEditor(Integer.class, "lbhpd", prismEditor);
		
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
		log.info("ModifyFrameLensOrderController onSubmit...");
		FrameLensOrder form = (FrameLensOrder)command;

		PageMsg pageMsg = new PageMsg();
		pageMsg.setTarget("_self");
		pageMsg.setUrl(getReturnUrl());
		
		try
		{
			// ����Ƿ���ڶ���id
			if(form.getId() == null || form.getId() == 0)
			{
				throw new ProcessException(400025);
			}
			// ���ݶ���id��ѯ����
			OrsOrder order = onlineOrder.getOrder(form.getId());
			// ��form���¶���
			order = FormUtils.updateFrom(form, order, onlineOrder);
			onlineOrder.saveOrder(order);
			pageMsg.setCode("400019");
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
