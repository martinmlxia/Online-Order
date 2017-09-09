package com.conant.order.web.controller;

import java.sql.Connection;
import java.util.*;
import javax.servlet.http.*;
import org.springframework.validation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.*;
import org.springframework.mail.javamail.*;
import org.springframework.mail.*;

import com.conant.order.util.Logger;
import com.conant.order.web.form.RegisterForm;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.*;
import com.conant.order.common.PageMsg;
import com.conant.order.dao.RegisterDao;
import com.conant.ums.util.Decode;

/**
 * <p>
 * Title: Online Order Management System
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * 
 * <p>
 * Company: conant
 * </p>
 * 
 * @author Martin
 * @version 1.0
 */
public class RegisterController extends SimpleFormController {

	private static Logger log = Logger
			.getLogger("add user", Logger.DEBUG, true);

	private RegisterDao registerDao;
	private MailSender mailSender;
	private SimpleMailMessage mailMessage;
	
	
	public RegisterController() {
		setCommandClass(RegisterForm.class);
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ProcessException {

		PageMsg pageMsg = new PageMsg();
		pageMsg.setTarget("_self");
		String loginId = (String) request.getSession().getAttribute("loginId");

		RegisterForm registerForm = (RegisterForm) command;
		UserInfo userInfo = new UserInfo();
		UserDeptInfo userDeptInfo = new UserDeptInfo();
		UserRoleInfo userRoleInfo = new UserRoleInfo();
		
		String password = registerForm.getUser_passwd();
		String passwordConfirm = registerForm.getUser_passwdConfirm();
		if(!password.equals(passwordConfirm)){
			pageMsg.setMsg("The CONFIRM PASSWORD must be identical to the entered PASSWORD!");
			pageMsg.setUrl("/register.ord");
			return new ModelAndView("common/err", "error", pageMsg);
		}
		
		String email = registerForm.getEmail();
		String emailConfirm = registerForm.getEmailConfirm();
		if(!email.equals(emailConfirm)){
			pageMsg.setMsg("The CONFIRM EMAIL ADDRESS must be identical to the entered EMAIL!");
			pageMsg.setUrl("/register.ord");
			return new ModelAndView("common/err", "error", pageMsg);
		}
		String user_tag = registerForm.getUser_tag();
		String user_name = registerForm.getFirstName() + " " + registerForm.getLastName();
		String home_tel = registerForm.getHome_tel();
		String mobile = registerForm.getMobile();   
		String businessName = registerForm.getBusinessName();
		String businessStreetNumber = registerForm.getBusinessStreetNumber();
		String businessStreetName = registerForm.getBusinessStreetName();
	    String businessSuit = registerForm.getBusinessSuit();
		String businessCity = registerForm.getBusinessCity();
		String businessState = registerForm.getBusinessState();
		String businessCountry = registerForm.getBusinessCountry();
		String businessZip = registerForm.getBusinessZip();
		String businessPhone = registerForm.getBusinessPhone();
		String businessExt = registerForm.getBusinessExt();
		String businessFax = registerForm.getBusinessFax();
		//String businessType = registerForm.getBusinessType();
		
		userInfo.setUser_passwd(Decode.encrypt(password));
		userInfo.setEmail(email);
		userInfo.setUser_tag(user_tag);
		userInfo.setUser_name(user_name);
		userInfo.setHome_tel(home_tel);
		userInfo.setMobile(mobile);
		userInfo.setBusinessName(businessName);
		userInfo.setBusinessStreetName(businessStreetName);
		userInfo.setBusinessStreetNumber(businessStreetNumber);
		userInfo.setBusinessSuit(businessSuit);
		userInfo.setBusinessCity(businessCity);
		userInfo.setBusinessState(businessState);
		userInfo.setBusinessCountry(businessCountry);
		userInfo.setBusinessZip(businessZip);
		userInfo.setBusinessPhone(businessPhone);
		userInfo.setBusinessExt(businessExt);
		userInfo.setBusinessFax(businessFax);
		
		userInfo.setLock_flag("0");
        userInfo.setIs_admin("0");
        userInfo.setIs_login("0");
        userInfo.setDel_flag("0");
        userInfo.setIs_audit("0");
        
        userDeptInfo.setDept_id("000000000000003");
        userDeptInfo.setDel_flag("0");
        
        userRoleInfo.setRole_id("0000000006");
        
        
		try {
			registerDao.registerUser(loginId, userInfo, userDeptInfo, userRoleInfo);
			
			// …Ë÷√emailƒ⁄»›,
//			mailMessage.setText("≤‚ ‘Spring ∑¢ÀÕEmail.");
//			System.out.println(mailMessage.getFrom() + "111111111");
//			System.out.println(mailSender.getClass() + "111111111");
//			mailSender.send(mailMessage);
			
			pageMsg.setMsg("Register success£°");
			pageMsg.setUrl("/welcome.ord?targetURL=login");
			return new ModelAndView(this.getSuccessView(), "success", pageMsg);
		} catch (ProcessException pe) {
			pageMsg.setMsg(pe.getErrorReason());
			pageMsg.setUrl("/register.ord");
			return new ModelAndView("common/err", "error", pageMsg);
		} catch (Exception ex) {
			pageMsg.setMsg(ex.getMessage());
			pageMsg.setUrl("/register.ord");
			return new ModelAndView("common/err", "error", pageMsg);
		}

	}

	public void setRegisterDao(RegisterDao registerDao) {
		this.registerDao = registerDao;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}
	
	

}
