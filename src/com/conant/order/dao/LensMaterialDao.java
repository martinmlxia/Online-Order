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
	 * 获取镜片材料列表。
	 * @return LensMaterial对象列表
	 * @throws ProcessException
	 */
	List getLensMaterials() throws ProcessException;
	
	/**
	 * 获取镜片材料列表2。
	 * @return LensMaterial对象列表
	 * @throws ProcessException
	 */
	List getLensMaterials2() throws ProcessException;
}
