/**
 * BatchCommandController.java
 * 2009-2-14
 * Administrator
 */
package com.conant.order.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OnlineOrderFacade;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.NodeInstance;
import com.conant.order.vo.OrderStatus;
import com.conant.order.vo.OrsOrder;
import com.conant.order.vo.ProcessInstance;
import com.conant.order.vo.ProcessLog;
import com.conant.order.web.form.BatchModifyForm;
import com.conant.order.web.form.BatchPrintForm;
import com.conant.order.web.form.OrdersForm;

/**
 * 执行多个提交操作的controller。
 * @author Administrator
 * 
 */
public class BatchCommandController extends MultiActionController
{
	private static final Logger log = Logger.getLogger(
			"BatchCommandController", Logger.DEBUG, true);

	private OnlineOrderFacade onlineOrder;
	private String returnUrl;
	private String successView;
	private String errorView;
	private String batchPrintView;
	private String batchModifyView;
	private String batchPackingPrintView;
	
	public void setOnlineOrder(OnlineOrderFacade onlineOrder)
	{
		this.onlineOrder = onlineOrder;
	}

	public void setReturnUrl(String returnUrl)
	{
		this.returnUrl = returnUrl;
	}

	public void setSuccessView(String successView)
	{
		this.successView = successView;
	}

	public void setErrorView(String errorView)
	{
		this.errorView = errorView;
	}

	public void setBatchPrintView(String batchPrintView)
	{
		this.batchPrintView = batchPrintView;
	}

	public void setBatchModifyView(String batchModifyView)
	{
		this.batchModifyView = batchModifyView;
	}
	
	public void setBatchPackingPrintView(String batchPackingPrintView) {
		this.batchPackingPrintView = batchPackingPrintView;
	}

	private void batchCommitOrders(HttpServletRequest request, OrdersForm form) throws ProcessException
	{
		// 检查订单状态
		// 位于结束状态的订单不进行提交
		String user = (String)request.getSession().getAttribute("user_name");
		List listOrder = onlineOrder.getOrders(form.getOrderIds());
		int size = listOrder.size();
		int token;
		for(int i = size - 1; i >= 0; i--)
		{
			OrsOrder order = (OrsOrder)listOrder.get(i);
			ProcessInstance process = order.getProcessInstance();
			token = process.getToken(); 
			if(token == OrderStatus.TYPE_END)
			{
				continue;
			}
			else
			{
				// 更新节点信息
				// 更新当前节点
				NodeInstance currentNode = onlineOrder
					.getNodeInstance(process.getId(), token);
				currentNode.setLeavedate(new Date());
				currentNode.setUser(user);
				onlineOrder.saveNodeInstance(currentNode);
				// 更新目标节点
				int targetToken = process.getToken() + 1;
				boolean withdrawal = false;
				NodeInstance targetNode = onlineOrder.getNodeInstance(process.getId(), targetToken);
				targetNode.setEnterdate(new Date());
				onlineOrder.saveNodeInstance(targetNode);
				// 生成移交记录
				ProcessLog processLog = new ProcessLog();
				processLog.setNode(token);
				processLog.setUser(user);
				processLog.setProcessdate(new Date());
				processLog.setProcessInstance(process);
				onlineOrder.saveProcessLog(processLog);
				// 向下移交
				process.setWithdrawal(withdrawal);
				// 更新流程信息
				process.setToken(targetToken);
				onlineOrder.saveProcessInstance(process);
			}
		}
	}
	
	public ModelAndView batchCommitOrders(HttpServletRequest request,
			HttpServletResponse response, OrdersForm form)
	{
		log.info("BatchCommandController batchCommitOrders...");
		
		PageMsg pageMsg = new PageMsg();
		pageMsg.setUrl(returnUrl);
		pageMsg.setTarget("_self");

		try
		{
			batchCommitOrders(request, form);
			pageMsg.setCode("400011");
			return new ModelAndView(successView, "success", pageMsg);
		}
		catch(ProcessException pe)
		{
			pageMsg.setCode(pe.getErrorCode());
			return new ModelAndView(errorView, "error", pageMsg);
		}
		catch(Exception ex)
		{
			pageMsg.setMsg(ex.getMessage());
			return new ModelAndView(errorView, "error", pageMsg);
		}
	}
	
	public ModelAndView batchPrintOrders(HttpServletRequest request,
			HttpServletResponse response, BatchPrintForm form)
	{
		log.info("BatchCommandController batchPrintOrders...");
		
		PageMsg pageMsg = new PageMsg();
		pageMsg.setUrl(returnUrl);
		pageMsg.setTarget("_self");

		try
		{
			List listOrder = onlineOrder.getOrders(form.getOrderIds());
			Map model = new HashMap();
			model.put("listOrder", listOrder);
			model.put("doubleMode", form.isDoubleMode());
			model.put("returnUrl", returnUrl);
			return new ModelAndView(batchPrintView, model);
		}
		catch(ProcessException pe)
		{
			pageMsg.setCode(pe.getErrorCode());
			return new ModelAndView(errorView, "error", pageMsg);
		}
		catch(Exception ex)
		{
			pageMsg.setMsg(ex.getMessage());
			return new ModelAndView(errorView, "error", pageMsg);
		}
	}
	
	public ModelAndView batchPackingPrints(HttpServletRequest request,
			HttpServletResponse response, BatchPrintForm form)
	{
		log.info("BatchCommandController batchPrintOrders...");
		
		PageMsg pageMsg = new PageMsg();
		pageMsg.setUrl(returnUrl);
		pageMsg.setTarget("_self");

		try
		{
			List listOrder = onlineOrder.getOrders(form.getOrderIds());
			Map model = new HashMap();
			model.put("listOrder", listOrder);
			model.put("doubleMode", form.isDoubleMode());
			model.put("returnUrl", returnUrl);
			return new ModelAndView(batchPackingPrintView, model);
		}
		catch(ProcessException pe)
		{
			pageMsg.setCode(pe.getErrorCode());
			return new ModelAndView(errorView, "error", pageMsg);
		}
		catch(Exception ex)
		{
			pageMsg.setMsg(ex.getMessage());
			return new ModelAndView(errorView, "error", pageMsg);
		}
	}
	
	public ModelAndView batchModifyOrders(HttpServletRequest request,
			HttpServletResponse response, OrdersForm form)
	{
		log.info("BatchCommandController batchModifyOrders...");
		
		PageMsg pageMsg = new PageMsg();
		pageMsg.setUrl(returnUrl);
		pageMsg.setTarget("_self");

		try
		{
			BatchModifyForm modifyForm = new BatchModifyForm();
			modifyForm.setOrderIds(form.getOrderIds());
			return new ModelAndView(batchModifyView, "form", modifyForm);
		}
		catch(Exception ex)
		{
			pageMsg.setMsg(ex.getMessage());
			return new ModelAndView(errorView, "error", pageMsg);
		}
	}
	
	public ModelAndView batchDeleteOrders(HttpServletRequest request,
			HttpServletResponse response, OrdersForm form)
	{
		log.info("BatchCommandController batchDeleteOrders...");
		
		PageMsg pageMsg = new PageMsg();
		pageMsg.setUrl(returnUrl);
		pageMsg.setTarget("_self");

		try
		{
			onlineOrder.deleteOrders(form.getOrderIds());
			pageMsg.setCode("400010");
			return new ModelAndView(successView, "success", pageMsg);
		}
		catch(ProcessException pe)
		{
			pageMsg.setCode(pe.getErrorCode());
			return new ModelAndView(errorView, "error", pageMsg);
		}
		catch(Exception ex)
		{
			pageMsg.setMsg(ex.getMessage());
			return new ModelAndView(errorView, "error", pageMsg);
		}
	}
	
	public ModelAndView clientBatchDeleteOrders(HttpServletRequest request,
			HttpServletResponse response, OrdersForm form)
	{
		log.info("BatchCommandController clientBatchDeleteOrders...");
		
		PageMsg pageMsg = new PageMsg();
		pageMsg.setUrl(returnUrl);
		pageMsg.setTarget("_self");

		try
		{
			onlineOrder.deleteUnauditedOrders(form.getOrderIds());
			pageMsg.setCode("400010");
			return new ModelAndView(successView, "success", pageMsg);
		}
		catch(ProcessException pe)
		{
			pageMsg.setCode(pe.getErrorCode());
			return new ModelAndView(errorView, "error", pageMsg);
		}
		catch(Exception ex)
		{
			pageMsg.setMsg(ex.getMessage());
			return new ModelAndView(errorView, "error", pageMsg);
		}
	}
}
