package com.conant.order.vo;

public class UserRoleInfo implements java.io.Serializable {

	private String user_id; // id
	private String role_id; // login name
	private String add_date;
	private String add_userid;
	private String upd_date;
	private String upd_userid;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
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

	public String getUpd_date() {
		return upd_date;
	}

	public void setUpd_date(String upd_date) {
		this.upd_date = upd_date;
	}

	public String getUpd_userid() {
		return upd_userid;
	}

	public void setUpd_userid(String upd_userid) {
		this.upd_userid = upd_userid;
	}

}
