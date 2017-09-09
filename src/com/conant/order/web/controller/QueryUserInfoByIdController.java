package com.conant.order.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conant.order.dao.*;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.*;
import com.conant.order.common.PageMsg;
import com.conant.ums.util.Decode;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class QueryUserInfoByIdController extends SimpleFormController {

	private static Logger log = Logger.getLogger("query userInfo by id",
			Logger.DEBUG, true);

	private RegisterDao registerDao;

	public QueryUserInfoByIdController() {
		setCommandClass(UserInfo.class);
	}

	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors)
			throws ProcessException {

		log.debugT("Enter QueryUserInfoByIdController showForm...");

		PageMsg pageMsg = new PageMsg();
		pageMsg.setTarget("_self");
		String loginId = (String) request.getSession().getAttribute("user_id");

		// 构建查询条件
		UserInfo userInfo = new UserInfo();

		try {

			// 开始查询
			userInfo = registerDao.getUserInfo(loginId);
			return new ModelAndView(this.getSuccessView(), "success", userInfo);

		} catch (ProcessException pe) {
			pageMsg.setMsg(pe.getErrorReason());
			pageMsg.setUrl("/queryUserInfoById.ord");
			return new ModelAndView("common/err", "error", pageMsg);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("common/err", "error", pageMsg);
		}

	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		log.info("updatePersonalUserInfo onSubmit...");
		UserInfo form = (UserInfo) command;

		PageMsg pageMsg = new PageMsg();
		pageMsg.setTarget("_self");

		String loginId = (String) request.getSession().getAttribute("loginId");

		// 构建查询条件
		UserInfo userInfo = new UserInfo();

		String id = form.getUser_id();
		String password = form.getUser_passwd();
		String passwordConfirm = (String) request.getParameter("user_Cpasswd");

		if (!password.equals(passwordConfirm)) {
			pageMsg
					.setMsg("The CONFIRM PASSWORD must be identical to the entered PASSWORD!");
			pageMsg.setUrl("/queryUserInfoById.ord");
			return new ModelAndView("common/err", "error", pageMsg);
		}
		String email = form.getEmail();
		String address = form.getAddress();

		try {
			// 开始查询
			userInfo = registerDao.getUserInfo(id);
			System.out.println("adfasfasfasfas" + userInfo.getUser_name());
			userInfo.setUser_passwd(Decode.encrypt(password));
			userInfo.setEmail(email);
			userInfo.setAddress(address);
			registerDao.updateUserInfo(loginId, userInfo);
			pageMsg.setUrl("/queryUserInfoById.ord");
			pageMsg.setCode("400033");
			return new ModelAndView("common/ok", "success", pageMsg);

		} catch (ProcessException pe) {
			pageMsg.setMsg(pe.getErrorReason());
			pageMsg.setUrl("/queryUserInfoById.ord");
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
