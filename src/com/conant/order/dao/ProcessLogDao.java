/**
 * ProcessLogDao.java
 * 2009-1-7
 * Administrator
 */
package com.conant.order.dao;

import com.conant.order.util.ProcessException;
import com.conant.order.vo.ProcessLog;

/**
 * @author Administrator
 *
 */
public interface ProcessLogDao
{
	/**
	 * 保存处理日志。
	 * @param processLog 处理日志对象
	 * @throws ProcessException
	 */
	void saveProcessLog(ProcessLog processLog) throws ProcessException;
}
