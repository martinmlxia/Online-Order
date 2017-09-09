package com.conant.order.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conant.order.dao.*;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.*;
import com.conant.order.web.form.*;
import com.conant.order.common.PageMsg;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class DeleteUserInfoController extends SimpleFormController {

	private static Logger log = Logger.getLogger("Delete userInfo",
			Logger.DEBUG, true);

	private RegisterDao registerDao;

	public DeleteUserInfoController() {
		setCommandClass(UserInfoForm.class);
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ProcessException {

		log.debugT("Enter deleteUserInfoController onSubmit...");

		PageMsg pageMsg = new PageMsg();
		String loginId = (String) request.getSession().getAttribute("loginId");

		// 构建查询条件
		UserInfo userInfo = new UserInfo();
		UserInfoForm userInfoForm = (UserInfoForm) command;
		Integer[] userIds = userInfoForm.getUserIds();

		try {

			if (null != userIds && userIds.length > 0) {
				List<UserInfo> listUser = new ArrayList<UserInfo>();
				for (Integer userId : userIds) {
					// 存在级联关系的实体删除前需要先load
					userInfo = registerDao.getUserInfo(String.valueOf(userId));
					if ("root".equals(userInfo.getUser_tag())
							|| "admin".equals(userInfo.getUser_tag())) {
						throw new ProcessException(100001);
					}
				}
			}
			// 开始查询
			registerDao.deleteUserInfos(userIds);
			pageMsg.setMsg("Delete UserInfos success!");
			pageMsg.setUrl("/queryUser.ord");
			return new ModelAndView(this.getSuccessView(), "success", pageMsg);

		} catch (ProcessException pe) {
			pageMsg.setCode(pe.getErrorCode());
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
