/**
 * LensTypeDao.java
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
public interface LensTypeDao
{
	/**
	 * ��ȡ��Ƭ�����б�
	 * @return LensType�����б�
	 * @throws ProcessException
	 */
	List getLensTypes() throws ProcessException;
}
