/**
 * NodeInstanceDao.java
 * 2009-1-7
 * Administrator
 */
package com.conant.order.dao;

import com.conant.order.util.ProcessException;
import com.conant.order.vo.NodeInstance;

/**
 * @author Administrator
 *
 */
public interface NodeInstanceDao
{
	/**
	 * 根据节点实体id获取节点实体。
	 * @param id 节点实体id
	 * @return 节点实体
	 * @throws ProcessException
	 */
	NodeInstance getNodeInstance(int id) throws ProcessException;
	
	/**
	 * 根据流程实例id和节点id获取节点实体。
	 * @param processId 流程实例id
	 * @param nodeId 节点id
	 * @return 节点实体
	 * @throws ProcessException
	 */
	NodeInstance getNodeInstance(int processId, int nodeId) throws ProcessException;
	
	/**
	 * 保存指定的节点实体。
	 * @param nodeInstance 节点实体
	 * @throws ProcessException
	 */
	void saveNodeInstance(NodeInstance nodeInstance) throws ProcessException;
}
