/**
 * LensTreatmentDao.java
 * 2009-2-23
 * Administrator
 */
package com.conant.order.dao;

import java.util.List;

import com.conant.order.util.ProcessException;

public interface LensTreatmentDao
{
	/**
	 * ��ȡ��Ƭ�ӹ��̶��б�
	 * @return LensTreatment�����б�
	 * @throws ProcessException
	 */
	List getLensTreatments() throws ProcessException;
}
