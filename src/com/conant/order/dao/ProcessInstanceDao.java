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
	 * 根据流程实例id获取流程实例实体。
	 * @param processId 流程实例id
	 * @return 流程实体
	 * @throws ProcessException
	 */
	ProcessInstance getProcessInstance(int processId) throws ProcessException;
	
	/**
	 * 保存流程实体。
	 * @param processInstance 流程实体
	 * @throws ProcessException
	 */
	void saveProcessInstance(ProcessInstance processInstance) throws ProcessException;
}
