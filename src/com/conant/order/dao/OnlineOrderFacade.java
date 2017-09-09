/**
 * OnlineOrderFacade.java
 * 2009-2-13
 * Administrator
 */
package com.conant.order.dao;

import java.util.List;
import java.util.Locale;

import com.conant.order.util.ProcessException;
import com.conant.order.vo.NodeInstance;
import com.conant.order.vo.OrderQuerier;
import com.conant.order.vo.OrsOrder;
import com.conant.order.vo.ProcessInstance;
import com.conant.order.vo.ProcessLog;
import com.conant.order.vo.TokenVariable;

/**
 * ���߶���ϵͳ����ҵ��ӿڡ�
 * @author Administrator
 *
 */
public interface OnlineOrderFacade
{
	//---------------------------------------------------------------
	// order
	//---------------------------------------------------------------
	
	/**
	 * �����û�����ȡһ�鶩��ʵ�塣
	 * @param username �û�����
	 * @return ���������б�
	 * @throws ProcessException
	 */
	List getOrdersByUsername(String username) throws ProcessException;
	
	/**
	 * ���ݶ���id��ȡ����ʵ�塣
	 * @param orderId ����id
	 * @return ����ʵ��
	 * @throws ProcessException
	 */
	OrsOrder getOrder(int orderId) throws ProcessException;

	/**
	 * ���ݲ�ѯ�����ȡһ�鶩��ʵ�塣
	 * @param querier ��ѯ����
	 * @return ����˲�ѯ����Ĳ�ѯ����
	 * @throws ProcessException
	 */
	OrderQuerier getOrders(OrderQuerier querier) throws ProcessException;
	
	/**
	 * ���ݶ���id�����ȡһ�鶩��ʵ�塣
	 * @param orderIds ����id����
	 * @return ��ѯ�õ���һ�鶩��ʵ��
	 * @throws ProcessException
	 */
	List getOrders(Integer[] orderIds) throws ProcessException;
	
	/**
	 * ����ָ���Ķ���ʵ�塣�ڲ��ľ������Ϊinsert��update��
	 * @param order ��������
	 * @throws ProcessException
	 */
	void saveOrder(OrsOrder order) throws ProcessException;
	
	/**
	 * ����ָ����һ�鶩��ʵ�塣
	 * @param orders ���������б�
	 * @throws ProcessException
	 */
	void saveOrders(List orders) throws ProcessException;

	/**
	 * ɾ��ָ���Ķ�����
	 * @param order ��������
	 * @throws ProcessException
	 */
	void deleteOrder(OrsOrder order) throws ProcessException;

	/**
	 * ɾ��ָ����һ�鶩����
	 * @param orderIds ����id����
	 * @throws ProcessException
	 */
	void deleteOrders(Integer[] orderIds) throws ProcessException;
	
	/**
	 * �߼�ɾ��ָ����һ�鶩����
	 * @param orderIds ����id����
	 * @throws ProcessException
	 */
	void logicalDeleteOrders(Integer[] orderIds) throws ProcessException;	
	
	/**
	 * ��ԭ�߼�ɾ����һ�鶩����
	 * @param orderIds ����id����
	 * @throws ProcessException
	 */
	void restoreOrders(Integer[] orderIds) throws ProcessException;
	
	/**
	 * ɾ��ָ����һ�鶩����δ���������Ķ�����
	 * @param orderIds ����id����
	 * @throws ProcessException
	 */
	void deleteUnauditedOrders(Integer[] orderIds) throws ProcessException;
	
	/**
	 * ��������һ�鶩����
	 * @param queryString ִ�и��µ�HQL��䣬����ֵʹ��"?"���
	 * @param values ����ֵ����
	 * @return ���³ɹ��ĸ���
	 * @throws ProcessException
	 */
	int batchUpdateOrders(String queryString, Object[] values) throws ProcessException;
	
	/**
	 * ��������һ�鶩����
	 * @param orders �����б�
	 * @throws ProcessException
	 */
	void batchImportOrders(List orders) throws ProcessException;

	//---------------------------------------------------------------
	// lens model
	//---------------------------------------------------------------
	
	/**
	 * ��ȡ���о�Ƭ����ʵ�塣
	 * @return ��Ƭ����ʵ���б�
	 * @throws ProcessException
	 */
	List getLensModel() throws ProcessException;
	
	/**
	 * ��ȡָ������ľ�Ƭ����ʵ�塣
	 * @param locale �������
	 * @return ��Ƭ����ʵ���б�
	 * @throws ProcessException
	 */
	List getLensModel(Locale locale) throws ProcessException;
	
	//---------------------------------------------------------------
	// node instance
	//---------------------------------------------------------------
	
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

	//---------------------------------------------------------------
	// process instance
	//---------------------------------------------------------------
	
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

	//---------------------------------------------------------------
	// process log
	//---------------------------------------------------------------
	
	/**
	 * ���洦����־��
	 * @param processLog ������־����
	 * @throws ProcessException
	 */
	void saveProcessLog(ProcessLog processLog) throws ProcessException;

	//---------------------------------------------------------------
	// tint color
	//---------------------------------------------------------------
	
	/**
	 * ��ȡ����Ⱦɫʵ�塣
	 * @return Ⱦɫʵ���б�
	 * @throws ProcessException
	 */
	List getTintColors() throws ProcessException;
	
	//---------------------------------------------------------------
	// lens material
	//---------------------------------------------------------------
	
	/**
	 * ��ȡ��Ƭ�����б�
	 * @return LensMaterial�����б�
	 * @throws ProcessException
	 */
	List getLensMaterials() throws ProcessException;
	
	/**
	 * ��ȡ��Ƭ�����б�2��
	 * @return LensMaterial�����б�
	 * @throws ProcessException
	 */
	List getLensMaterials2() throws ProcessException;	
	
	//---------------------------------------------------------------
	// lens treatment
	//---------------------------------------------------------------
	
	/**
	 * ��ȡ��Ƭ�ӹ��̶��б�
	 * @return LensTreatment�����б�
	 * @throws ProcessException
	 */
	List getLensTreatments() throws ProcessException;
	
	//---------------------------------------------------------------
	// lens type
	//---------------------------------------------------------------
	
	/**
	 * ��ȡ��Ƭ�����б�
	 * @return LensType�����б�
	 * @throws ProcessException
	 */
	List getLensTypes() throws ProcessException;
	
	//---------------------------------------------------------------
	// lens tint
	//---------------------------------------------------------------
	
	/**
	 * ��ȡ��ƬȾɫ�����б�
	 * @return LensTint�����б�
	 * @throws ProcessException
	 */
	List getTintTypes() throws ProcessException;
	
	//---------------------------------------------------------------
	// lens design
	//---------------------------------------------------------------
	
	/**
	 * ��ȡ��Ƭ��������б�
	 * @return LensDesign�����б�
	 * @throws ProcessException
	 */
	List getLensDesigns() throws ProcessException;
	
	//---------------------------------------------------------------
	// lens channel
	//---------------------------------------------------------------
	
	/**
	 * ��ȡͨ���б�
	 * @return LensChannel�����б�
	 * @throws ProcessException
	 */
	List getLensChannels() throws ProcessException;
	
	//---------------------------------------------------------------
	// token variable map
	//---------------------------------------------------------------
	
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
	 * @param token �ڵ�id
	 * @param name ��������
	 * @return ����ʵ��
	 * @throws ProcessException
	 */	
	TokenVariable getTokenVariable(int processId, int token, String name) throws ProcessException;
	
	/**
	 * ��������ʵ��id�ͽڵ�id��ȡ����ʵ���б�
	 * @param processId ����ʵ��id
	 * @param token �ڵ�id
	 * @return ����ʵ���б�
	 * @throws ProcessException
	 */
	List getTokenVariables(int processId, int token) throws ProcessException;
	
	/**
	 * ����ָ���ı���ʵ�塣
	 * @param tokenVariable ����ʵ��
	 * @throws ProcessException
	 */
	void saveTokenVariable(TokenVariable tokenVariable) throws ProcessException;
}
