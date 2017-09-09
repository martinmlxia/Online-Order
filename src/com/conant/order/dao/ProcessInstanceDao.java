/**
 * ProcessInstanceDao.java
 * 2009-1-7
 * Administrator
 */
package com.conant.order.dao;

import com.conant.order.util.ProcessException;
import com.conant.order.vo.ProcessInstance;

/**
 * @author Administrator
 *
 */
public interface ProcessInstanceDao
{
	/**
	 * ��������ʵ��id��ȡ����ʵ��ʵ�塣
	 * @param processId ����ʵ��id
	 * @return ����ʵ��
	 * @throws ProcessException
	 */
	ProcessInstance getProcessInstance(int processId) throws ProcessException;
	
	/**
	 * ��������ʵ�塣
	 * @param processInstance ����ʵ��
	 * @throws ProcessException
	 */
	void saveProcessInstance(ProcessInstance processInstance) throws ProcessException;
}
