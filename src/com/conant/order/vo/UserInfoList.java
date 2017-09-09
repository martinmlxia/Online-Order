package com.conant.order.vo;

/**
 * <p>Title: Online Order Management System</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: conant</p>
 *
 * @author Martin
 * @version 1.0
 */
public class UserInfoList {

    private UserInfo[] userInfos;
    private int totalRecord;
    
    
	public UserInfo[] getUserInfos() {
		return userInfos;
	}
	public void setUserInfos(UserInfo[] userInfos) {
		this.userInfos = userInfos;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
    
    
}
