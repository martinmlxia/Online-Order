package com.conant.order.vo;

public class UserDeptInfo implements java.io.Serializable {

	private String user_id; // id
	private String dept_id; // login name
	private String add_date;
	private String add_userid;
	private String upd_date;
	private String upd_userid;
	private String del_flag;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
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

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

}
