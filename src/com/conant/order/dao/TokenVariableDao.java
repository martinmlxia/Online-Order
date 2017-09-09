/**
 * TokenVariableMapDao.java
 * 2009-7-19
 * Administrator
 */
package com.conant.order.dao;

import java.util.List;

import com.conant.order.util.ProcessException;
import com.conant.order.vo.TokenVariable;

/**
 * @author Administrator
 *
 */
public interface TokenVariableDao
{
	/**
	 * 根据id获取实体。
	 * @param id 实体id
	 * @return 实体对象
	 * @throws ProcessException
	 */
	TokenVariable getTokenVariable(int id) throws ProcessException;
	
	/**
	 * 根据流程实例id、节点id和变量名称获取变量实体。
	 * @param processId 流程实例id
	 * @param nodeId 节点id
	 * @param name 变量名称
	 * @return 变量实体
	 * @throws ProcessException
	 */	
	TokenVariable getTokenVariable(int processId, int nodeId, String name) throws ProcessException;
	
	/**
	 * 根据流程实例id和节点id获取变量实体列表。
	 * @param processId 流程实例id
	 * @param nodeId 节点id
	 * @return 变量实体列表
	 * @throws ProcessException
	 */
	List getTokenVariables(int processId, int nodeId) throws ProcessException;
	
	/**
	 * 保存指定的变量实体。
	 * @param tokenVariable 变量实体
	 * @throws ProcessException
	 */
	void saveTokenVariable(TokenVariable tokenVariable) throws ProcessException;
}
