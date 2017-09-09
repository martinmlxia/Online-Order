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

public class QueryUserInfoController extends SimpleFormController {

	private static Logger log = Logger.getLogger("query userInfo",
			Logger.DEBUG, true);

	private RegisterDao registerDao;
	private Integer pageNo = 0;
	private int pageSize = this.pageSize;

	public QueryUserInfoController() {
		setCommandClass(UserInfo.class);
	}

	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors)
			throws ProcessException {

		log.debugT("Enter QueryUserInfoController showForm...");

		PageMsg pageMsg = new PageMsg();
		String loginId = (String) request.getSession().getAttribute("loginId");

		// 构建查询条件
		UserInfo userInfo = new UserInfo();
		String id = "";

		try {

			// 开始查询
			UserInfoListQuerier userInfoListQuerier = new UserInfoListQuerier();

			if (null != id && !"".equals(id)) { // 单个查询（按id查询）
				userInfoListQuerier.setUser_id(id);
			}
			
			userInfoListQuerier.setIs_audit("0");
			
			if(null != request.getParameter("pageNo") && !"".equals(request.getParameter("pageNo"))){
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
				
			}
			
			userInfoListQuerier.setPageNo(pageNo);

			userInfoListQuerier.setStartIndex(pageNo * pageSize);
			userInfoListQuerier.setPageSize(pageSize);
			

			registerDao.queryUserInfoList(loginId, userInfoListQuerier);
			
			//System.out.println(userInfoListQuerier.getRecordCount() + "sdfasfasffffffffffff");
			return new ModelAndView(this.getSuccessView(), "success",userInfoListQuerier);

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

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
