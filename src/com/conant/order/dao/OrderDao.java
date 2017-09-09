/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conant.order.dao;

import java.util.List;

import com.conant.order.util.ProcessException;
import com.conant.order.vo.OrderQuerier;
import com.conant.order.vo.OrsOrder;

/**
 * 
 * @author Administrator
 */
public interface OrderDao
{
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
	 * @param orders ����ʵ���б�
	 * @throws ProcessException
	 */
	void batchImportOrders(List orders) throws ProcessException;
}
