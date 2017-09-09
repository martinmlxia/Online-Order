package com.conant.order.dao;

import java.util.List;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.*;

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
public interface RegisterDao {

    /**
     * 注册人员
     * @param userInfo, 用户VO
     * @param loginId,系统登陆ID
     * @return
     * @throws ProcessException
     */
    public void insertUserInfo(String loginId,UserInfo userInfo) throws ProcessException;

    
    /**
     * 注册人员
     * @param userInfo, 用户VO
     * @param userDeptInfo
     * @param userRoleInfo
     * @param loginId,系统登陆ID
     * @return
     * @throws ProcessException
     */
    public void registerUser(String loginId,UserInfo userInfo,UserDeptInfo userDeptInfo,UserRoleInfo userRoleInfo) throws ProcessException;
    
    
    /**
     * 注册人员赋角色
     * @param userRoleInfo 用户角色VO
     * @param loginId,系统登陆ID
     * @return
     * @throws ProcessException
     */
    public void insertUserRoleInfo(String loginId,UserRoleInfo userRoleInfo) throws ProcessException;


    /**
     * 注册人员部门划分
     * @param userDeptInfo 用户部门VO
     * @param loginId,系统登陆ID
     * @return
     * @throws ProcessException
     */
    public void insertUserDeptInfo(String loginId,UserDeptInfo userDeptInfo) throws ProcessException;
    
    /**
     * 取最大ID
     * @param loginId,系统登陆ID
     * @return
     * @throws ProcessException
     */
    public String getMaxUserId(String loginId) throws ProcessException;

    /**
     * 取UserInfo
     * @param loginId,系统登陆ID
     * @return
     * @throws ProcessException
     */   
    public UserInfo getUserInfo(String loginId) throws ProcessException;


    /**
     * 注册人员
     * @param userInfo, 用户VO
     * @param loginId,系统登陆ID
     * @return
     * @throws ProcessException
     */
    public void updateUserInfo(String loginId,UserInfo userInfo) throws ProcessException;

    
    /**
     * 查询人员列表
     * @param userInfoListQuerier 人员查询VO
     * @param loginId,系统登陆ID
     * @return
     * @throws ProcessException
     */

    public UserInfoListQuerier queryUserInfoList(String longId,UserInfoListQuerier userInfoListQuerier) throws ProcessException;
    
    /**
     * 删除人员列表
     * @param userIds 人员ID列表
     * @return
     * @throws ProcessException
     */   
    public void deleteUserInfos(Integer[] userIds) throws ProcessException;
    

    /**
     * 取最大用户编码
     * @param loginId 系统登陆ID
     * @return
     * @throws ProcessException
     */     
    public String getMaxUserCode(String loginId) throws ProcessException;
    
    public List<UserInfo> getProductList() throws ProcessException;
    
    public List<UserInfo> getSalesList() throws ProcessException;

    public String getProductOwnerId(String user_id) throws ProcessException;
    
    public String getSaleOwnerId(String user_id) throws ProcessException; 
}
