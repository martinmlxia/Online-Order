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
	 * 获取镜片加工程度列表。
	 * @return LensTreatment对象列表
	 * @throws ProcessException
	 */
	List getLensTreatments() throws ProcessException;
}
