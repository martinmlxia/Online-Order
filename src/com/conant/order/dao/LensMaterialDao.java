/**
 * LensMaterialDao.java
 * 2009-2-23
 * Administrator
 */
package com.conant.order.dao;

import java.util.List;

import com.conant.order.util.ProcessException;

/**
 * @author Administrator
 *
 */
public interface LensMaterialDao
{
	/**
	 * ��ȡ��Ƭ�����б�
	 * @return LensMaterial�����б�
	 * @throws ProcessException
	 */
	List getLensMaterials() throws ProcessException;
	
	/**
	 * ��ȡ��Ƭ�����б�2��
	 * @return LensMaterial�����б�
	 * @throws ProcessException
	 */
	List getLensMaterials2() throws ProcessException;
}
