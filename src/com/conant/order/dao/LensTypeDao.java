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
	 * 获取镜片类型列表。
	 * @return LensType对象列表
	 * @throws ProcessException
	 */
	List getLensTypes() throws ProcessException;
}
