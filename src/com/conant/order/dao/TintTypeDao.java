/**
 * TintTypeDao.java
 * 2009-2-23
 * Administrator
 */
package com.conant.order.dao;

import java.util.List;

import com.conant.order.util.ProcessException;

public interface TintTypeDao
{
	/**
	 * ��ȡ��ƬȾɫ�����б�
	 * @return LensTint�����б�
	 * @throws ProcessException
	 */
	List getTintTypes() throws ProcessException;
}
