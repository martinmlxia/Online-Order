/**
 * TintColorDao.java
 * 2009-1-22
 * Administrator
 */
package com.conant.order.dao;

import java.util.List;

import com.conant.order.util.ProcessException;

/**
 * @author Administrator
 *
 */
public interface TintColorDao
{
	/**
	 * ��ȡ����Ⱦɫʵ�塣
	 * @return Ⱦɫʵ���б�
	 * @throws ProcessException
	 */
	List getTintColors() throws ProcessException;
}
