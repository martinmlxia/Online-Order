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

public class ViewUserInfoController extends SimpleFormController {

	private static Logger log = Logger.getLogger("view userInfo",
			Logger.DEBUG, true);

	private RegisterDao registerDao;

	public ViewUserInfoController() {
		setCommandClass(UserInfo.class);
	}

	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors)
			throws ProcessException {

		log.debugT("Enter ViewUserInfoController showForm...");

		PageMsg pageMsg = new PageMsg();
		pageMsg.setTarget("_self");
		String loginId = (String) request.getSession().getAttribute("loginId");

		// 构建查询条件
		UserInfo userInfo = new UserInfo();
		String id = request.getParameter("user_id");

		try {

			// 开始查询
			userInfo = registerDao.getUserInfo(id);			
//			System.out.println(userInfo.getUser_id() + "sdfasfasffffffffffff");
//			System.out.println(userInfo.getAdd_userid() + "1111111111111");
			return new ModelAndView(this.getSuccessView(), "success",userInfo);

		} catch (ProcessException pe) {
			pageMsg.setMsg(pe.getErrorReason());
			pageMsg.setUrl("/queryUser.ord");
			return new ModelAndView("common/err", "error", pageMsg);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("common/err", "error", pageMsg);
		}

	}

	public RegisterDao getRegisterDao() {
		return registerDao;
	}

	public void setRegisterDao(RegisterDao registerDao) {
		this.registerDao = registerDao;
	}

}
