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
     * ע����Ա
     * @param userInfo, �û�VO
     * @param loginId,ϵͳ��½ID
     * @return
     * @throws ProcessException
     */
    public void insertUserInfo(String loginId,UserInfo userInfo) throws ProcessException;

    
    /**
     * ע����Ա
     * @param userInfo, �û�VO
     * @param userDeptInfo
     * @param userRoleInfo
     * @param loginId,ϵͳ��½ID
     * @return
     * @throws ProcessException
     */
    public void registerUser(String loginId,UserInfo userInfo,UserDeptInfo userDeptInfo,UserRoleInfo userRoleInfo) throws ProcessException;
    
    
    /**
     * ע����Ա����ɫ
     * @param userRoleInfo �û���ɫVO
     * @param loginId,ϵͳ��½ID
     * @return
     * @throws ProcessException
     */
    public void insertUserRoleInfo(String loginId,UserRoleInfo userRoleInfo) throws ProcessException;


    /**
     * ע����Ա���Ż���
     * @param userDeptInfo �û�����VO
     * @param loginId,ϵͳ��½ID
     * @return
     * @throws ProcessException
     */
    public void insertUserDeptInfo(String loginId,UserDeptInfo userDeptInfo) throws ProcessException;
    
    /**
     * ȡ���ID
     * @param loginId,ϵͳ��½ID
     * @return
     * @throws ProcessException
     */
    public String getMaxUserId(String loginId) throws ProcessException;

    /**
     * ȡUserInfo
     * @param loginId,ϵͳ��½ID
     * @return
     * @throws ProcessException
     */   
    public UserInfo getUserInfo(String loginId) throws ProcessException;


    /**
     * ע����Ա
     * @param userInfo, �û�VO
     * @param loginId,ϵͳ��½ID
     * @return
     * @throws ProcessException
     */
    public void updateUserInfo(String loginId,UserInfo userInfo) throws ProcessException;

    
    /**
     * ��ѯ��Ա�б�
     * @param userInfoListQuerier ��Ա��ѯVO
     * @param loginId,ϵͳ��½ID
     * @return
     * @throws ProcessException
     */

    public UserInfoListQuerier queryUserInfoList(String longId,UserInfoListQuerier userInfoListQuerier) throws ProcessException;
    
    /**
     * ɾ����Ա�б�
     * @param userIds ��ԱID�б�
     * @return
     * @throws ProcessException
     */   
    public void deleteUserInfos(Integer[] userIds) throws ProcessException;
    

    /**
     * ȡ����û�����
     * @param loginId ϵͳ��½ID
     * @return
     * @throws ProcessException
     */     
    public String getMaxUserCode(String loginId) throws ProcessException;
    
    public List<UserInfo> getProductList() throws ProcessException;
    
    public List<UserInfo> getSalesList() throws ProcessException;

    public String getProductOwnerId(String user_id) throws ProcessException;
    
    public String getSaleOwnerId(String user_id) throws ProcessException; 
}
