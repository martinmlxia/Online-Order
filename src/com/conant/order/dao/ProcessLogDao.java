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
	 * ���洦����־��
	 * @param processLog ������־����
	 * @throws ProcessException
	 */
	void saveProcessLog(ProcessLog processLog) throws ProcessException;
}
