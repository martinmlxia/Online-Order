/**
 * OnlineOrderImpl.java
 * 2009-2-23
 * Administrator
 */
package com.conant.order.dao.hibernate;

import java.util.List;
import java.util.Locale;

import com.conant.order.dao.LensChannelDao;
import com.conant.order.dao.LensDesignDao;
import com.conant.order.dao.LensMaterialDao;
import com.conant.order.dao.LensModelDao;
import com.conant.order.dao.LensTreatmentDao;
import com.conant.order.dao.LensTypeDao;
import com.conant.order.dao.NodeInstanceDao;
import com.conant.order.dao.OnlineOrderFacade;
import com.conant.order.dao.OrderDao;
import com.conant.order.dao.ProcessInstanceDao;
import com.conant.order.dao.ProcessLogDao;
import com.conant.order.dao.TintColorDao;
import com.conant.order.dao.TintTypeDao;
import com.conant.order.dao.TokenVariableDao;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.NodeInstance;
import com.conant.order.vo.OrderQuerier;
import com.conant.order.vo.OrsOrder;
import com.conant.order.vo.ProcessInstance;
import com.conant.order.vo.ProcessLog;
import com.conant.order.vo.TokenVariable;

public class OnlineOrderImpl implements OnlineOrderFacade
{
	private OrderDao orderDao;
	private LensModelDao lensModelDao;
	private NodeInstanceDao nodeInstanceDao;
	private ProcessInstanceDao processInstanceDao;
	private ProcessLogDao processLogDao;
	private TintColorDao tintColorDao;
	private LensMaterialDao lensMaterialDao;
	private LensTypeDao lensTypeDao;
	private LensTreatmentDao lensTreatmentDao;
	private TintTypeDao tintTypeDao;
	private TokenVariableDao tokenVariableDao;
	private LensDesignDao lensDesignDao;
	private LensChannelDao lensChannelDao;

	//--------------------------------------------------
	// business dao
	//--------------------------------------------------

	public OrderDao getOrderDao()
	{
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao)
	{
		this.orderDao = orderDao;
	}

	public LensModelDao getLensModelDao()
	{
		return lensModelDao;
	}

	public void setLensModelDao(LensModelDao lensModelDao)
	{
		this.lensModelDao = lensModelDao;
	}

	public NodeInstanceDao getNodeInstanceDao()
	{
		return nodeInstanceDao;
	}

	public void setNodeInstanceDao(NodeInstanceDao nodeInstanceDao)
	{
		this.nodeInstanceDao = nodeInstanceDao;
	}

	public ProcessInstanceDao getProcessInstanceDao()
	{
		return processInstanceDao;
	}

	public void setProcessInstanceDao(ProcessInstanceDao processInstanceDao)
	{
		this.processInstanceDao = processInstanceDao;
	}

	public ProcessLogDao getProcessLogDao()
	{
		return processLogDao;
	}

	public void setProcessLogDao(ProcessLogDao processLogDao)
	{
		this.processLogDao = processLogDao;
	}

	public TintColorDao getTintColorDao()
	{
		return tintColorDao;
	}

	public void setTintColorDao(TintColorDao tintColorDao)
	{
		this.tintColorDao = tintColorDao;
	}

	public LensMaterialDao getLensMaterialDao()
	{
		return lensMaterialDao;
	}

	public void setLensMaterialDao(LensMaterialDao lensMaterialDao)
	{
		this.lensMaterialDao = lensMaterialDao;
	}

	public LensTypeDao getLensTypeDao()
	{
		return lensTypeDao;
	}

	public void setLensTypeDao(LensTypeDao lensTypeDao)
	{
		this.lensTypeDao = lensTypeDao;
	}

	public LensTreatmentDao getLensTreatmentDao()
	{
		return lensTreatmentDao;
	}

	public void setLensTreatmentDao(LensTreatmentDao lensTreatmentDao)
	{
		this.lensTreatmentDao = lensTreatmentDao;
	}

	public TintTypeDao getTintTypeDao()
	{
		return tintTypeDao;
	}

	public void setTintTypeDao(TintTypeDao tintTypeDao)
	{
		this.tintTypeDao = tintTypeDao;
	}
	
	public TokenVariableDao getTokenVariableDao()
	{
		return tokenVariableDao;
	}

	public void setTokenVariableDao(TokenVariableDao tokenVariableDao)
	{
		this.tokenVariableDao = tokenVariableDao;
	}
	
	public LensDesignDao getLensDesignDao()
	{
		return lensDesignDao;
	}

	public void setLensDesignDao(LensDesignDao lensDesignDao)
	{
		this.lensDesignDao = lensDesignDao;
	}

	public LensChannelDao getLensChannelDao()
	{
		return lensChannelDao;
	}

	public void setLensChannelDao(LensChannelDao lensChannelDao)
	{
		this.lensChannelDao = lensChannelDao;
	}
	
	//--------------------------------------------------
	// order
	//--------------------------------------------------

	//@Override
	public List getOrdersByUsername(String username) throws ProcessException
	{
		return orderDao.getOrdersByUsername(username);
	}
	
	//@Override
	public int batchUpdateOrders(String queryString, Object[] values)
			throws ProcessException
	{
		return orderDao.batchUpdateOrders(queryString, values);
	}

	//@Override
	public void deleteOrder(OrsOrder order) throws ProcessException
	{
		orderDao.deleteOrder(order);
	}

	//@Override
	public void deleteOrders(Integer[] orderIds) throws ProcessException
	{
		orderDao.deleteOrders(orderIds);
	}
	
	//@Override
	public void logicalDeleteOrders(Integer[] orderIds) throws ProcessException
	{
		orderDao.logicalDeleteOrders(orderIds);
	}
	
	//@Override
	public void restoreOrders(Integer[] orderIds) throws ProcessException
	{
		orderDao.restoreOrders(orderIds);
	}	

	//@Override
	public void deleteUnauditedOrders(Integer[] orderIds) throws ProcessException
	{
		orderDao.deleteUnauditedOrders(orderIds);
	}
	
	//@Override
	public void saveOrder(OrsOrder order) throws ProcessException
	{
		orderDao.saveOrder(order);
	}

	//@Override
	public void saveOrders(List orders) throws ProcessException
	{
		orderDao.saveOrders(orders);	
	}
	
	//@Override
	public OrsOrder getOrder(int orderId) throws ProcessException
	{
		return orderDao.getOrder(orderId);
	}

	//@Override
	public OrderQuerier getOrders(OrderQuerier querier) throws ProcessException
	{
		return orderDao.getOrders(querier);
	}

	//@Override
	public List getOrders(Integer[] orderIds) throws ProcessException
	{
		return orderDao.getOrders(orderIds);
	}
	
	//@Override
	public void batchImportOrders(List orders) throws ProcessException
	{
		orderDao.batchImportOrders(orders);	
	}
	
	//--------------------------------------------------
	// lens model
	//--------------------------------------------------
	
	//@Override
	public List getLensModel() throws ProcessException
	{
		return lensModelDao.getLensModel();
	}

	//@Override
	public List getLensModel(Locale locale) throws ProcessException
	{
		return lensModelDao.getLensModel(locale);
	}

	//--------------------------------------------------
	// node instance
	//--------------------------------------------------
	
	//@Override
	public NodeInstance getNodeInstance(int id) throws ProcessException
	{
		return nodeInstanceDao.getNodeInstance(id);
	}

	//@Override
	public NodeInstance getNodeInstance(int processId, int nodeId)
			throws ProcessException
	{
		return nodeInstanceDao.getNodeInstance(processId, nodeId);
	}

	//@Override
	public void saveNodeInstance(NodeInstance nodeInstance)
			throws ProcessException
	{
		nodeInstanceDao.saveNodeInstance(nodeInstance);	
	}
	
	//--------------------------------------------------
	// process instance
	//--------------------------------------------------
	
	//@Override
	public ProcessInstance getProcessInstance(int processId)
			throws ProcessException
	{
		return processInstanceDao.getProcessInstance(processId);
	}

	//@Override
	public void saveProcessInstance(ProcessInstance processInstance)
			throws ProcessException
	{
		processInstanceDao.saveProcessInstance(processInstance);
	}

	//--------------------------------------------------
	// process log
	//--------------------------------------------------
	
	//@Override
	public void saveProcessLog(ProcessLog processLog) throws ProcessException
	{
		processLogDao.saveProcessLog(processLog);
	}

	//--------------------------------------------------
	// tint color
	//--------------------------------------------------
	
	//@Override
	public List getTintColors() throws ProcessException
	{
		return tintColorDao.getTintColors();
	}

	//--------------------------------------------------
	// lens material
	//--------------------------------------------------
	
	//@Override
	public List getLensMaterials() throws ProcessException
	{
		return lensMaterialDao.getLensMaterials();
	}
	
	//@Override
	public List getLensMaterials2() throws ProcessException
	{
		return lensMaterialDao.getLensMaterials2();
	}

	//--------------------------------------------------
	// lens treatment
	//--------------------------------------------------
	
	//@Override
	public List getLensTreatments() throws ProcessException
	{
		return lensTreatmentDao.getLensTreatments();
	}

	//--------------------------------------------------
	// lens type
	//--------------------------------------------------
	
	//@Override
	public List getLensTypes() throws ProcessException
	{
		return lensTypeDao.getLensTypes();
	}

	//--------------------------------------------------
	// lens tint type
	//--------------------------------------------------
	
	//@Override
	public List getTintTypes() throws ProcessException
	{
		return tintTypeDao.getTintTypes();
	}

	//@Override
	public TokenVariable getTokenVariable(int id) throws ProcessException
	{
		return tokenVariableDao.getTokenVariable(id);
	}

	//@Override
	public TokenVariable getTokenVariable(int processId, int nodeId,
			String name) throws ProcessException
	{
		return tokenVariableDao.getTokenVariable(processId, nodeId, name);
	}

	//@Override
	public List getTokenVariables(int processId, int nodeId)
			throws ProcessException
	{
		return tokenVariableDao.getTokenVariables(processId, nodeId);
	}

	//@Override
	public void saveTokenVariable(TokenVariable tokenVariable)
			throws ProcessException
	{
		tokenVariableDao.saveTokenVariable(tokenVariable);
	}

	//@Override
	public List getLensChannels() throws ProcessException
	{
		return lensChannelDao.getLensChannels();
	}

	//@Override
	public List getLensDesigns() throws ProcessException
	{
		return lensDesignDao.getLensDesigns();
	}
}
