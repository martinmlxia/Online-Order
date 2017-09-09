package com.conant.order.vo;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

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
public class UserInfoListQuerier {

	private List<UserInfo> listUser;
	private String user_id; // id
	private String user_code; // 6 bit code
	private String user_tag; // login name
	private String user_name; // user name
	private String email;
	private String address;
	private String mobile;
	private String add_date;
	private String add_userid;
	private String is_audit;
	private Order[] orders;
	private Integer pageNo;
	private Integer pageSize;
	private Integer pageCount;
	private Integer startIndex;
	private Integer recordCount;

	
	
	public String getIs_audit() {
		return is_audit;
	}

	public void setIs_audit(String is_audit) {
		this.is_audit = is_audit;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public List<UserInfo> getListUser() {
		return listUser;
	}

	public void setListUser(List<UserInfo> listUser) {
		this.listUser = listUser;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getUser_tag() {
		return user_tag;
	}

	public void setUser_tag(String user_tag) {
		this.user_tag = user_tag;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAdd_date() {
		return add_date;
	}

	public void setAdd_date(String add_date) {
		this.add_date = add_date;
	}

	public String getAdd_userid() {
		return add_userid;
	}

	public void setAdd_userid(String add_userid) {
		this.add_userid = add_userid;
	}

	public Order[] getOrders() {
		return orders;
	}

	public void setOrders(Order[] orders) {
		this.orders = orders;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

}
