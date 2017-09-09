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
	 * 根据用户名获取一组订单实体。
	 * @param username 用户名称
	 * @return 订单对象列表
	 * @throws ProcessException
	 */
	List getOrdersByUsername(String username) throws ProcessException;

	/**
	 * 根据订单id获取订单实体。
	 * @param orderId 订单id
	 * @return 订单实体
	 * @throws ProcessException
	 */
	OrsOrder getOrder(int orderId) throws ProcessException;

	/**
	 * 根据查询对象获取一组订单实体。
	 * @param querier 查询对象
	 * @return 填充了查询结果的查询对象
	 * @throws ProcessException
	 */
	OrderQuerier getOrders(OrderQuerier querier) throws ProcessException;
	
	/**
	 * 根据订单id数组获取一组订单实体。
	 * @param orderIds 订单id数组
	 * @return 查询得到的一组订单实体
	 * @throws ProcessException
	 */
	List getOrders(Integer[] orderIds) throws ProcessException;
	
	/**
	 * 保存指定的订单实体。内部的具体操作为insert或update。
	 * @param order 订单对象
	 * @throws ProcessException
	 */
	void saveOrder(OrsOrder order) throws ProcessException;
	
	/**
	 * 保存指定的一组订单实体。
	 * @param orders 订单对象列表
	 * @throws ProcessException
	 */
	void saveOrders(List orders) throws ProcessException;

	/**
	 * 删除指定的订单。
	 * @param order 订单对象
	 * @throws ProcessException
	 */
	void deleteOrder(OrsOrder order) throws ProcessException;

	/**
	 * 删除指定的一组订单。
	 * @param orderIds 订单id数组
	 * @throws ProcessException
	 */
	void deleteOrders(Integer[] orderIds) throws ProcessException;
	
	/**
	 * 逻辑删除指定的一组订单。
	 * @param orderIds 订单id数组
	 * @throws ProcessException
	 */
	void logicalDeleteOrders(Integer[] orderIds) throws ProcessException;
	
	/**
	 * 还原逻辑删除的一组订单。
	 * @param orderIds 订单id数组
	 * @throws ProcessException
	 */
	void restoreOrders(Integer[] orderIds) throws ProcessException;
	
	/**
	 * 删除指定的一组订单中未进行审批的订单。
	 * @param orderIds 订单id数组
	 * @throws ProcessException
	 */
	void deleteUnauditedOrders(Integer[] orderIds) throws ProcessException;
	
	/**
	 * 批量更新一组订单。
	 * @param queryString 执行更新的HQL语句，参数值使用"?"标记
	 * @param values 参数值数组
	 * @return 更新成功的个数
	 * @throws ProcessException
	 */
	int batchUpdateOrders(String queryString, Object[] values) throws ProcessException;
	
	/**
	 * 批量导入一组订单。
	 * @param orders 订单实体列表
	 * @throws ProcessException
	 */
	void batchImportOrders(List orders) throws ProcessException;
}
