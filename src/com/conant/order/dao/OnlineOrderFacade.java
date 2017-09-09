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
 * 在线订单系统核心业务接口。
 * @author Administrator
 *
 */
public interface OnlineOrderFacade
{
	//---------------------------------------------------------------
	// order
	//---------------------------------------------------------------
	
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
	 * @param orderIds 订单id数据
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
	 * @param orders 订单列表
	 * @throws ProcessException
	 */
	void batchImportOrders(List orders) throws ProcessException;

	//---------------------------------------------------------------
	// lens model
	//---------------------------------------------------------------
	
	/**
	 * 获取所有镜片类型实体。
	 * @return 镜片类型实体列表
	 * @throws ProcessException
	 */
	List getLensModel() throws ProcessException;
	
	/**
	 * 获取指定地域的镜片类型实体。
	 * @param locale 地域对象
	 * @return 镜片类型实体列表
	 * @throws ProcessException
	 */
	List getLensModel(Locale locale) throws ProcessException;
	
	//---------------------------------------------------------------
	// node instance
	//---------------------------------------------------------------
	
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

	//---------------------------------------------------------------
	// process instance
	//---------------------------------------------------------------
	
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

	//---------------------------------------------------------------
	// process log
	//---------------------------------------------------------------
	
	/**
	 * 保存处理日志。
	 * @param processLog 处理日志对象
	 * @throws ProcessException
	 */
	void saveProcessLog(ProcessLog processLog) throws ProcessException;

	//---------------------------------------------------------------
	// tint color
	//---------------------------------------------------------------
	
	/**
	 * 获取所有染色实体。
	 * @return 染色实体列表
	 * @throws ProcessException
	 */
	List getTintColors() throws ProcessException;
	
	//---------------------------------------------------------------
	// lens material
	//---------------------------------------------------------------
	
	/**
	 * 获取镜片材料列表。
	 * @return LensMaterial对象列表
	 * @throws ProcessException
	 */
	List getLensMaterials() throws ProcessException;
	
	/**
	 * 获取镜片材料列表2。
	 * @return LensMaterial对象列表
	 * @throws ProcessException
	 */
	List getLensMaterials2() throws ProcessException;	
	
	//---------------------------------------------------------------
	// lens treatment
	//---------------------------------------------------------------
	
	/**
	 * 获取镜片加工程度列表。
	 * @return LensTreatment对象列表
	 * @throws ProcessException
	 */
	List getLensTreatments() throws ProcessException;
	
	//---------------------------------------------------------------
	// lens type
	//---------------------------------------------------------------
	
	/**
	 * 获取镜片类型列表。
	 * @return LensType对象列表
	 * @throws ProcessException
	 */
	List getLensTypes() throws ProcessException;
	
	//---------------------------------------------------------------
	// lens tint
	//---------------------------------------------------------------
	
	/**
	 * 获取镜片染色类型列表。
	 * @return LensTint对象列表
	 * @throws ProcessException
	 */
	List getTintTypes() throws ProcessException;
	
	//---------------------------------------------------------------
	// lens design
	//---------------------------------------------------------------
	
	/**
	 * 获取镜片设计类型列表。
	 * @return LensDesign对象列表
	 * @throws ProcessException
	 */
	List getLensDesigns() throws ProcessException;
	
	//---------------------------------------------------------------
	// lens channel
	//---------------------------------------------------------------
	
	/**
	 * 获取通道列表。
	 * @return LensChannel对象列表
	 * @throws ProcessException
	 */
	List getLensChannels() throws ProcessException;
	
	//---------------------------------------------------------------
	// token variable map
	//---------------------------------------------------------------
	
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
	 * @param token 节点id
	 * @param name 变量名称
	 * @return 变量实体
	 * @throws ProcessException
	 */	
	TokenVariable getTokenVariable(int processId, int token, String name) throws ProcessException;
	
	/**
	 * 根据流程实例id和节点id获取变量实体列表。
	 * @param processId 流程实例id
	 * @param token 节点id
	 * @return 变量实体列表
	 * @throws ProcessException
	 */
	List getTokenVariables(int processId, int token) throws ProcessException;
	
	/**
	 * 保存指定的变量实体。
	 * @param tokenVariable 变量实体
	 * @throws ProcessException
	 */
	void saveTokenVariable(TokenVariable tokenVariable) throws ProcessException;
}
