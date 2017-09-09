/**
 * QueryOrdersController.java
 * 2008-11-23
 * Administrator
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
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OnlineOrderFacade;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrderQuerier;
import com.conant.order.web.editor.OrderStatusEditor;
import com.conant.order.web.editor.OrderTypeEditor;

/**
 * 订单查询操作的通用controller。
 * @author Administrator
 * 
 */
public abstract class QueryOrdersController extends SimpleFormController
{
	private static final Logger log = Logger.getLogger("QueryOrdersController",
			Logger.DEBUG, true);
	
	private OnlineOrderFacade onlineOrder;
	private int pageSize;
	private String errorView;
	private String returnUrl;
	private String paramPrefix;

	public QueryOrdersController()
	{
		// Initialize controller properties here or
		// in the Web Application Context
	}

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
	
	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public String getReturnUrl()
	{
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl)
	{
		this.returnUrl = returnUrl;
	}
	
	public String getParamPrefix()
	{
		return paramPrefix;
	}

	public void setParamPrefix(String paramPrefix)
	{
		this.paramPrefix = paramPrefix;
	}
	
	private String getQueryParamPrefix(HttpServletRequest request)
	{
		// 获取用户id
		String user = (String)WebUtils.getRequiredSessionAttribute(request, "user_id");
		// 设置键值前缀
		String prefix = "";
		if(StringUtils.hasText(user))
		{
			prefix = String.format("%s.%s.", user, getParamPrefix()); 
		}
		else
		{
			prefix = String.format("%s.", getParamPrefix());
		}
		
		return prefix;
	}

	private void persistOrderQuerier(HttpServletRequest request, OrderQuerier querier)
	{
		// 获取键值前缀
		String prefix = getQueryParamPrefix(request);
		// 将查询对象保存到session中
		// 保存的格式为：key="user.query.field"
		// id
		WebUtils.setSessionAttribute(request, prefix + "id", querier.getId());
		// ordertype
		WebUtils.setSessionAttribute(request, prefix + "ordertype", querier.getOrdertype());
		// orderstatus
		WebUtils.setSessionAttribute(request, prefix + "orderstatus", querier.getOrderstatus());
		// start_orderstatus
		WebUtils.setSessionAttribute(request, prefix + "start_orderstatus", querier.getStart_orderstatus());
		// end_orderstatus
		WebUtils.setSessionAttribute(request, prefix + "end_orderstatus", querier.getEnd_orderstatus());
		// username
		WebUtils.setSessionAttribute(request, prefix + "username", querier.getUsername());
		// telephone
		WebUtils.setSessionAttribute(request, prefix + "telephone", querier.getTelephone());
		// ordereddate
		WebUtils.setSessionAttribute(request, prefix + "ordereddate", querier.getOrdereddate());
		// start_ordereddate
		WebUtils.setSessionAttribute(request, prefix + "start_ordereddate", querier.getStart_ordereddate());
		// end_ordereddate
		WebUtils.setSessionAttribute(request, prefix + "end_ordereddate", querier.getEnd_ordereddate());
		// page no
		WebUtils.setSessionAttribute(request, prefix + "pageNo", querier.getPageNo());
		// patient
		WebUtils.setSessionAttribute(request, prefix + "patient", querier.getPatient());
		// tray
		WebUtils.setSessionAttribute(request, prefix + "tray", querier.getTray());
		// start_delivereddate
		WebUtils.setSessionAttribute(request, prefix + "start_delivereddate", querier.getStart_delivereddate());
		// end_delivereddate
		WebUtils.setSessionAttribute(request, prefix + "end_delivereddate", querier.getEnd_delivereddate());		
	}
	
	private void persistSessionParam(HttpServletRequest request, String paramName, Object param)
	{
		// 获取键值前缀
		String prefix = getQueryParamPrefix(request);
		WebUtils.setSessionAttribute(request, prefix + paramName, param);
	}
	
	private void restoreOrderQuerier(HttpServletRequest request, OrderQuerier querier)
	{
		// 获取键值前缀
		String prefix = getQueryParamPrefix(request);
		// 从session中还原查询对象
		// id
		Integer id = (Integer)WebUtils.getSessionAttribute(request, prefix + "id");
		querier.setId(id);
		// ordertype
		Integer ordertype = (Integer)WebUtils.getSessionAttribute(request, prefix + "ordertype");
		querier.setOrdertype(ordertype);
		// orderstatus
		Integer orderstatus = (Integer)WebUtils.getSessionAttribute(request, prefix + "orderstatus");
		querier.setOrderstatus(orderstatus);
		// start_orderstatus
		Integer start_orderstatus = (Integer)WebUtils.getSessionAttribute(request, prefix + "start_orderstatus");
		querier.setStart_orderstatus(start_orderstatus);
		// end_orderstatus
		Integer end_orderstatus = (Integer)WebUtils.getSessionAttribute(request, prefix + "end_orderstatus");
		querier.setEnd_orderstatus(end_orderstatus);
		// username
		String username = (String)WebUtils.getSessionAttribute(request, prefix + "username");
		querier.setUsername(username);
		// telephone
		String telephone = (String)WebUtils.getSessionAttribute(request, prefix + "telephone");
		querier.setTelephone(telephone);
		// ordereddate
		Date ordereddate = (Date)WebUtils.getSessionAttribute(request, prefix + "ordereddate");
		querier.setOrdereddate(ordereddate);
		// start_ordereddate
		Date start_ordereddate = (Date)WebUtils.getSessionAttribute(request, prefix + "start_ordereddate");
		querier.setStart_ordereddate(start_ordereddate);
		// end_ordereddate
		Date end_ordereddate = (Date)WebUtils.getSessionAttribute(request, prefix + "end_ordereddate");
		querier.setEnd_ordereddate(end_ordereddate);
		// page no
		Integer pageNo = (Integer)WebUtils.getSessionAttribute(request, prefix + "pageNo");
		querier.setPageNo((pageNo == null) ? 0 : pageNo);
		// patient
		String patient = (String)WebUtils.getSessionAttribute(request, prefix + "patient");
		querier.setPatient(patient);		
		// tray
		String tray = (String)WebUtils.getSessionAttribute(request, prefix + "tray");
		querier.setTray(tray);
		// start_delivereddate
		Date start_delivereddate = (Date)WebUtils.getSessionAttribute(request, prefix + "start_delivereddate");
		querier.setStart_delivereddate(start_delivereddate);
		// end_delivereddate
		Date end_delivereddate = (Date)WebUtils.getSessionAttribute(request, prefix + "end_delivereddate");
		querier.setEnd_delivereddate(end_delivereddate);
	}

	public abstract void beforeExecution(HttpServletRequest request, HttpServletResponse response,
			OrderQuerier querier) throws ProcessException;

	public abstract void afterExecution(HttpServletRequest request, HttpServletResponse response, 
			OrderQuerier querier) throws ProcessException;

	public void execute(HttpServletRequest request, HttpServletResponse response, OrderQuerier querier)
			throws ProcessException
	{
		try
		{
			// 设置登陆用户名
			querier.setLoginUser((String)WebUtils.getSessionAttribute(request, "user_name"));
			// 执行查询
			beforeExecution(request, response, querier);
			onlineOrder.getOrders(querier);
			afterExecution(request, response, querier);
		}
		catch(ProcessException exp)
		{
			throw exp;
		}
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws ModelAndViewDefiningException
	{
		log.info("QueryOrdersController formBackingObject...");

		OrderQuerier querier = new OrderQuerier();

		Integer pageNo = 0;
		int orderStatus = 0;
		int pageSize = this.pageSize;

		// 处理有请求参数的情况
		if(StringUtils.hasText(request.getParameter("pageNo")))
		{
			try
			{
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
				persistSessionParam(request, "pageNo", pageNo);
			}
			catch(Exception exp)
			{
			}
		}
		if(StringUtils.hasText(request.getParameter("status")))
		{
			try
			{
				orderStatus = Integer.parseInt(request.getParameter("status"));
				querier.setOrderstatus(orderStatus);
			}
			catch(Exception exp)
			{
			}
		}
		if(StringUtils.hasText(request.getParameter("pageSize")))
		{
			try
			{
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}
			catch(Exception exp)
			{
			}
		}
		querier.setPageSize(pageSize);
		querier.setStartIndex(pageNo * pageSize);
		// 如果是form提交不执行查询操作
		if(!isFormSubmission(request))
		{
			// 从session中还原查询对象
			restoreOrderQuerier(request, querier);
			// 重新计算起始索引
			querier.setStartIndex(querier.getPageNo() * pageSize);
			try
			{
				// 执行查询
				execute(request, null, querier);
			}
			catch(ProcessException exp)
			{
				PageMsg pageMsg = new PageMsg();
				pageMsg.setTarget("_self");
				pageMsg.setUrl(getReturnUrl());
				pageMsg.setCode(exp.getErrorCode());
				ModelAndView modelAndView = new ModelAndView(getErrorView(),
						"error", pageMsg);
				throw new ModelAndViewDefiningException(modelAndView);
			}
		}

		return querier;
	}

	protected Map referenceData(HttpServletRequest request) throws Exception
	{
		log.info("QueryOrdersController referenceData...");

		Map<Object, Object> model = new HashMap<Object, Object>();

		// 返回url
		model.put("returnUrl", getReturnUrl());		
		// 获取客户端的locale信息
		Locale locale = RequestContextUtils.getLocale(request);
		model.put("lang", locale);
		// 获取messageSource对象
		MessageSource source = (MessageSource)getApplicationContext().getBean("messageSource");
		OrderStatusEditor orderstatus = new OrderStatusEditor(true, locale, source);
		OrderTypeEditor ordertype = new OrderTypeEditor(true, locale, source);
		
		List<String> listOrderstatus = new ArrayList<String>();
		listOrderstatus.add(source.getMessage("list.pleaseselect", null, locale));
		listOrderstatus.addAll(orderstatus.getListItems());
		List<String> listOrdertype = new ArrayList<String>();
		listOrdertype.add(source.getMessage("list.pleaseselect", null, locale));
		listOrdertype.addAll(ordertype.getListItems());
		model.put("listOrderstatus", listOrderstatus);
		model.put("listOrdertype", listOrdertype);

		return model;
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception
	{
		log.info("QueryOrdersController initBinder...");

		super.initBinder(request, binder);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);

		// 获取客户端的locale信息
		Locale locale = RequestContextUtils.getLocale(request);
		// 获取messageSource对象
		MessageSource source = (MessageSource)getApplicationContext().getBean("messageSource");		
		binder.registerCustomEditor(Integer.class, "processInstance.token",
				new OrderStatusEditor(true, locale, source));
		binder.registerCustomEditor(Integer.class, "ordertype",
				new OrderTypeEditor(true, locale, source));
		binder.registerCustomEditor(Integer.class, "orderstatus",
				new OrderStatusEditor(true, locale, source));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		// 为List对象的指定字段注册editor
		binder.registerCustomEditor(Integer.class, "listOrder.processInstance.token",
				new OrderStatusEditor(true, locale, source));
		binder.registerCustomEditor(Integer.class, "listOrder.ordertype",
				new OrderTypeEditor(true, locale, source));
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception
	{

		log.info("QueryOrdersController onSubmit...");
		OrderQuerier querier = (OrderQuerier)command;

		PageMsg pageMsg = new PageMsg();
		pageMsg.setTarget("_self");
		pageMsg.setUrl(getReturnUrl());

		try
		{
			// 保存查询对象到session中
			persistOrderQuerier(request, querier);
			// 执行查询
			execute(request, response, querier);
			// 如果直接返回ModelAndView对象，需要手动调用referenceData
			// 调用showForm会自动调用
			// Map controlModel = errors.getModel();
			// controlModel.putAll(referenceData(request));
			// return new ModelAndView(this.getSuccessView(), controlModel);
			return showForm(request, errors, this.getSuccessView());
		}
		catch(ProcessException exp)
		{
			pageMsg.setCode(exp.getErrorCode());
			return new ModelAndView(getErrorView(), "error", pageMsg);
		}
		catch(Exception ex)
		{
			pageMsg.setMsg(ex.getMessage());
			return new ModelAndView(getErrorView(), "error", pageMsg);
		}
	}
}
