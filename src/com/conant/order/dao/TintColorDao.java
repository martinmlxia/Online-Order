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
	 * 获取所有染色实体。
	 * @return 染色实体列表
	 * @throws ProcessException
	 */
	List getTintColors() throws ProcessException;
}
