package com.conant.order.web.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conant.order.dao.*;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.*;
import com.conant.order.common.PageMsg;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class UpdateUserInfoController extends SimpleFormController {

	private static Logger log = Logger.getLogger("view userInfo", Logger.DEBUG,
			true);

	private RegisterDao registerDao;
	private String errorView;

	public UpdateUserInfoController() {
		setCommandClass(UserInfo.class);
	}

	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors)
			throws ProcessException {

		log.debugT("Enter updateUserInfoController showForm...");

		PageMsg pageMsg = new PageMsg();

		// 构建查询条件
		UserInfo userInfo = new UserInfo();
		String id = request.getParameter("user_id");

		try {

			// 开始查询
			userInfo = registerDao.getUserInfo(id);
			List<UserInfo> salesList = registerDao.getSalesList();
			List<UserInfo> productList = registerDao.getProductList();
			request.setAttribute("salesList", salesList);
			request.setAttribute("productList", productList);
			return new ModelAndView(this.getFormView(), "success", userInfo);

		} catch (ProcessException pe) {
			pageMsg.setMsg(pe.getErrorReason());
			pageMsg.setUrl("/queryUser.ord");
			pageMsg.setTarget("mainFrame");
			return new ModelAndView("common/err", "error", pageMsg);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("common/err", "error", pageMsg);
		}

	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		log.info("updateUserInfoController onSubmit...");
		UserInfo form = (UserInfo) command;

		PageMsg pageMsg = new PageMsg();
		pageMsg.setTarget("_self");

		String loginId = (String) request.getSession().getAttribute("loginId");

		// 构建查询条件
		UserInfo userInfo = new UserInfo();
		String id = form.getUser_id();
		// String is_audit = form.getIs_audit();
		String user_code = form.getUser_code();
		String saleOwner = form.getSaleOwner();
		String productOwner = form.getProductOwner();
		

		try
		{
			// 开始查询
			userInfo = registerDao.getUserInfo(id);
			// 如果修改了用户代码，则 用户代码不能重复
			// 如果没有修改用户代码，则不判断
			boolean userCodeChanged = 
				(user_code == null) ? userInfo.getUser_code() != null 
						: !user_code.equalsIgnoreCase(userInfo.getUser_code());
			if(userCodeChanged && user_code != null && user_code.trim().length() > 0)
			{
				if(Integer.parseInt(user_code) 
						>= Integer.parseInt(registerDao.getMaxUserCode(loginId)))
				{
					userInfo.setUser_code(user_code);
					userInfo.setIs_audit("1");
				}
				else
				{
					pageMsg.setUrl("/queryUser.ord");
					pageMsg.setCode("400032");
					return new ModelAndView("common/err", "error", pageMsg);
				}
			}
			// 设置销售主管和生产主管
			// 允许取消设置
			saleOwner = (saleOwner != null && saleOwner.trim().length() > 0) ? saleOwner : null;
			productOwner = (productOwner != null && productOwner.trim().length() > 0) ? productOwner : null;
			userInfo.setSaleOwner(saleOwner);
			userInfo.setProductOwner(productOwner);

			registerDao.updateUserInfo(loginId, userInfo);
			pageMsg.setUrl("/queryUser.ord");
			pageMsg.setCode("400033");
			return new ModelAndView(this.getSuccessView(), "success", pageMsg);

		}
		catch(ProcessException pe)
		{
			pageMsg.setMsg(pe.getErrorReason());
			pageMsg.setUrl("/queryUser.ord");
			return new ModelAndView("common/err", "error", pageMsg);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ModelAndView("common/err", "error", pageMsg);
		}
	}

	public RegisterDao getRegisterDao()
	{
		return registerDao;
	}

	public void setRegisterDao(RegisterDao registerDao)
	{
		this.registerDao = registerDao;
	}

	public String getErrorView()
	{
		return errorView;
	}

	public void setErrorView(String errorView)
	{
		this.errorView = errorView;
	}
}
