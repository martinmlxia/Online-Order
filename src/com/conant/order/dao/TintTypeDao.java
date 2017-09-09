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
	 * 获取镜片染色类型列表。
	 * @return LensTint对象列表
	 * @throws ProcessException
	 */
	List getTintTypes() throws ProcessException;
}
