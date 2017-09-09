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
	 * ����id��ȡʵ�塣
	 * @param id ʵ��id
	 * @return ʵ�����
	 * @throws ProcessException
	 */
	TokenVariable getTokenVariable(int id) throws ProcessException;
	
	/**
	 * ��������ʵ��id���ڵ�id�ͱ������ƻ�ȡ����ʵ�塣
	 * @param processId ����ʵ��id
	 * @param nodeId �ڵ�id
	 * @param name ��������
	 * @return ����ʵ��
	 * @throws ProcessException
	 */	
	TokenVariable getTokenVariable(int processId, int nodeId, String name) throws ProcessException;
	
	/**
	 * ��������ʵ��id�ͽڵ�id��ȡ����ʵ���б�
	 * @param processId ����ʵ��id
	 * @param nodeId �ڵ�id
	 * @return ����ʵ���б�
	 * @throws ProcessException
	 */
	List getTokenVariables(int processId, int nodeId) throws ProcessException;
	
	/**
	 * ����ָ���ı���ʵ�塣
	 * @param tokenVariable ����ʵ��
	 * @throws ProcessException
	 */
	void saveTokenVariable(TokenVariable tokenVariable) throws ProcessException;
}
