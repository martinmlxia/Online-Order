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
	 * ���ݽڵ�ʵ��id��ȡ�ڵ�ʵ�塣
	 * @param id �ڵ�ʵ��id
	 * @return �ڵ�ʵ��
	 * @throws ProcessException
	 */
	NodeInstance getNodeInstance(int id) throws ProcessException;
	
	/**
	 * ��������ʵ��id�ͽڵ�id��ȡ�ڵ�ʵ�塣
	 * @param processId ����ʵ��id
	 * @param nodeId �ڵ�id
	 * @return �ڵ�ʵ��
	 * @throws ProcessException
	 */
	NodeInstance getNodeInstance(int processId, int nodeId) throws ProcessException;
	
	/**
	 * ����ָ���Ľڵ�ʵ�塣
	 * @param nodeInstance �ڵ�ʵ��
	 * @throws ProcessException
	 */
	void saveNodeInstance(NodeInstance nodeInstance) throws ProcessException;
}
