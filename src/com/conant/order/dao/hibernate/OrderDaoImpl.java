/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conant.order.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.StringUtils;

import com.conant.order.dao.OrderDao;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.HbOrder;
import com.conant.order.vo.OrderQuerier;
import com.conant.order.vo.OrderStatus;
import com.conant.order.vo.OrsOrder;

/**
 * 
 * @author Administrator
 */
public class OrderDaoImpl implements OrderDao
{
	private static final Logger log = Logger.getLogger("OrderDaoImpl",
			Logger.DEBUG, true);
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) throws ProcessException
	{
		this.template = template;
	}

	public List getOrdersByUsername(String username) throws ProcessException
	{
		List list = template
				.find("from OrsOrder orsorder where orsorder.user.user_name = '"
						+ username + "'");
		return list;
	}

	public OrsOrder getOrder(int orderId) throws ProcessException
	{
		OrsOrder order = null;
		try
		{
			order = (OrsOrder)template.load(OrsOrder.class, new Integer(orderId));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new ProcessException(400020);
		}

		return order;
	}
	
	public void saveOrder(OrsOrder order) throws ProcessException
	{
		try
		{
			if(order != null)
			{
				template.saveOrUpdate(order);
			}
			else
			{
				throw new ProcessException(217001);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new ProcessException(120001);
		}
	}
	
	//@Override
	public void saveOrders(List orders) throws ProcessException
	{
		try
		{
			if(orders != null && orders.size() > 0)
			{
				template.saveOrUpdate(orders);
			}
			else
			{
				throw new ProcessException(217001);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new ProcessException(120001);
		}
	}

	public void deleteOrder(OrsOrder order) throws ProcessException
	{
		try
		{
			if(order != null)
			{
				template.delete(order);
			}
			else
			{
				throw new ProcessException(400021);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new ProcessException(120001);
		}
	}

	public void deleteOrders(Integer[] orderIds) throws ProcessException
	{
		try
		{
			if(orderIds != null && orderIds.length > 0)
			{
				List<OrsOrder> listOrders = new ArrayList<OrsOrder>();
				for(Integer orderId : orderIds)
				{
					// 存在级联关系的实体删除前需要先load
					OrsOrder item = getOrder(orderId);
					if(item != null)
					{
						listOrders.add((OrsOrder)item);
					}
				}
				template.deleteAll(listOrders);
			}
			else
			{
				throw new ProcessException(400021);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new ProcessException(120001);
		}
	}
	
	public void logicalDeleteOrders(Integer[] orderIds) throws ProcessException
	{
		// 生成HQL语句
		StringBuilder queryString = new StringBuilder();
		queryString.append("update OrsOrder ");
		// 更新字段exist
		queryString.append("set exist = 0 ");
		// 子句where
		queryString.append("where id in (");
		for(int i = orderIds.length - 1; i >= 0; i--)
		{
			queryString.append(orderIds[i].intValue());
			if(i > 0)
			{
				queryString.append(",");
			}
		}
		queryString.append(")");
		// 执行批量更新
		try
		{
			template.bulkUpdate(queryString.toString());
		}
		catch(Exception e)
		{
			log.exception(e);
			throw new ProcessException(120001);
		}
	}
	
	public void restoreOrders(Integer[] orderIds) throws ProcessException
	{
		// 生成HQL语句
		StringBuilder queryString = new StringBuilder();
		queryString.append("update OrsOrder ");
		// 更新字段exist
		queryString.append("set exist = 1 ");
		// 子句where
		queryString.append("where id in (");
		for(int i = orderIds.length - 1; i >= 0; i--)
		{
			queryString.append(orderIds[i].intValue());
			if(i > 0)
			{
				queryString.append(",");
			}
		}
		queryString.append(")");
		// 执行批量更新
		try
		{
			template.bulkUpdate(queryString.toString());
		}
		catch(Exception e)
		{
			log.exception(e);
			throw new ProcessException(120001);
		}
	}

	public void deleteUnauditedOrders(Integer[] orderIds) throws ProcessException
	{
		try
		{
			if(orderIds != null && orderIds.length > 0)
			{
				List<OrsOrder> listOrders = new ArrayList<OrsOrder>();
				for(Integer orderId : orderIds)
				{
					// 存在级联关系的实体删除前需要先load
					OrsOrder item = getOrder(orderId);
					if(item != null)
					{
						// 判断订单状态，只删除未审核的订单
						Integer token = item.getProcessInstance().getToken();
						if(token == OrderStatus.TYPE_AUDITING)
						{
							listOrders.add((OrsOrder)item);
						}
					}
				}
				if(listOrders.size() > 0)
				{
					template.deleteAll(listOrders);
				}
			}
			else
			{
				throw new ProcessException(400021);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new ProcessException(120001);
		}
	}
	
	public OrderQuerier getOrders(OrderQuerier querier)
			throws ProcessException
	{
		DetachedCriteria queryCriteria = DetachedCriteria.forClass(OrsOrder.class);
		DetachedCriteria countCriteria = DetachedCriteria.forClass(OrsOrder.class);

		try
		{
			if(querier == null)
			{
				throw new ProcessException(217001);
			}
			// query by field
			// create alias
			queryCriteria.createAlias("processInstance", "pi");
			countCriteria.createAlias("processInstance", "pi");
			queryCriteria.createAlias("user", "ui");
			countCriteria.createAlias("user", "ui");
			// by id
			if(querier.getId() != null)
			{
				queryCriteria.add(Restrictions.eq("id", Integer
						.valueOf(querier.getId())));
				countCriteria.add(Restrictions.eq("id", Integer
						.valueOf(querier.getId())));
			}
			// by exist
			if(querier.isExist() != null)
			{
				queryCriteria.add(Restrictions.eq("exist", querier.isExist()));
				countCriteria.add(Restrictions.eq("exist", querier.isExist()));				
			}
			// by order type
			queryCriteria.add(Restrictions.lt("ordertype", 4));
			countCriteria.add(Restrictions.lt("ordertype", 4));
			// by order status
			if(querier.getOrderstatus() != null)
			{
				log.debugT("OrderDaoImpl: order status === "
						+ querier.getOrderstatus());
				queryCriteria.add(Restrictions.eq("pi.token", querier.getOrderstatus()));
				countCriteria.add(Restrictions.eq("pi.token", querier.getOrderstatus()));		
			}
			// by specified order status range
			if(querier.getOrderstatus() == null 
					&& querier.getStart_orderstatus() != null
					&& querier.getEnd_orderstatus() != null)
			{
				log.debugT("OrderDaoImpl: order status between " + querier.getStart_orderstatus()
						+ " and " + querier.getEnd_orderstatus());		
				queryCriteria.add(Restrictions.between("pi.token", 
						querier.getStart_orderstatus(), querier.getEnd_orderstatus()));
				countCriteria.add(Restrictions.between("pi.token", 
						querier.getStart_orderstatus(), querier.getEnd_orderstatus()));									
			}
			// by specified order status list
			if(querier.getOrderstatus() == null 
					&& querier.getStart_orderstatus() == null
					&& querier.getListOrderstatus() != null)
			{
				log.debugT("OrderDaoImpl: order status in list.size = " + querier.getListOrderstatus().length);
				queryCriteria.add(Restrictions.in("pi.token", querier.getListOrderstatus()));
				countCriteria.add(Restrictions.in("pi.token", querier.getListOrderstatus()));
			}
			// by user name
			if(StringUtils.hasText(querier.getUsername()))
			{
				log.debugT("OrderDaoImpl: user name === "
						+ querier.getUsername());
				queryCriteria.add(Restrictions.ilike("ui.user_tag", querier.getUsername(), MatchMode.START));
				countCriteria.add(Restrictions.ilike("ui.user_tag", querier.getUsername(), MatchMode.START));
			}
			// by sales owner (except admin)
			if(StringUtils.hasText(querier.getSalesowner())
					&& (querier.getLoginUser() != null && !querier.getLoginUser().equalsIgnoreCase("admin")))
			{
				log.debugT("OrderDaoImpl: by sales owner === " + querier.getSalesowner());
				queryCriteria.add(
						Restrictions.or(
						Restrictions.eq("ui.saleOwner", querier.getSalesowner()), 
						Restrictions.isNull("ui.saleOwner")
						));
				countCriteria.add(
						Restrictions.or(
						Restrictions.eq("ui.saleOwner", querier.getSalesowner()), 
						Restrictions.isNull("ui.saleOwner")
						));
			}
			// by product owner (except admin)
			if(StringUtils.hasText(querier.getProductowner())
					&& (querier.getLoginUser() != null && !querier.getLoginUser().equalsIgnoreCase("admin")))
			{
				log.debugT("OrderDaoImpl: by product owner === " + querier.getProductowner());
				queryCriteria.add(
						Restrictions.or(
						Restrictions.eq("ui.productOwner", querier.getProductowner()), 
						Restrictions.isNull("ui.productOwner")
						));
				countCriteria.add(
						Restrictions.or(
						Restrictions.eq("ui.productOwner", querier.getProductowner()), 
						Restrictions.isNull("ui.productOwner")
						));
			}
			// by telephone
			if(StringUtils.hasText(querier.getTelephone()))
			{
				log.debugT("OrderDaoImpl: telephone === "
						+ querier.getTelephone());
				queryCriteria.add(Restrictions.eq("telephone", querier
						.getTelephone()));
				countCriteria.add(Restrictions.eq("telephone", querier
						.getTelephone()));
			}
			// by ordered date
			if(querier.getOrdereddate() != null)
			{
				log.debugT("OrderDaoImpl: ordered date === "
						+ querier.getOrdereddate());
				queryCriteria.add(Restrictions.eq("ordereddate", querier
						.getOrdereddate()));
				countCriteria.add(Restrictions.eq("ordereddate", querier
						.getOrdereddate()));
			}
			// by ordered date range
			if(querier.getOrdereddate() == null
					&& querier.getStart_ordereddate() != null
					&& querier.getEnd_ordereddate() != null)
			{
				log.debugT("OrderDaoImpl: ordered date between "
						+ querier.getStart_ordereddate() + " and " + querier.getEnd_ordereddate());
				queryCriteria.add(Restrictions.between("ordereddate", querier.getStart_ordereddate(), querier.getEnd_ordereddate()));
				countCriteria.add(Restrictions.between("ordereddate", querier.getStart_ordereddate(), querier.getEnd_ordereddate()));
			}
			// by requested date
			if(querier.getRequesteddate() != null)
			{
				log.debugT("OrderDaoImpl: requested date === "
						+ querier.getRequesteddate());
				queryCriteria.add(Restrictions.eq("requesteddate", querier
						.getRequesteddate()));
				countCriteria.add(Restrictions.eq("requesteddate", querier
						.getRequesteddate()));
			}
			// by remarks
			if(StringUtils.hasText(querier.getRemarks()))
			{
				log.debugT("OrderDaoImpl: remarks === " + querier.getRemarks());
				queryCriteria.add(Restrictions.eq("remarks", querier
						.getRemarks()));
				countCriteria.add(Restrictions.eq("remarks", querier
						.getRemarks()));
			}
			// by ids
			if(querier.getOrderIds() != null && querier.getOrderIds().length > 0)
			{
				queryCriteria.add(Restrictions.in("id", querier.getOrderIds()));
				countCriteria.add(Restrictions.in("id", querier.getOrderIds()));
			}
			// by patient
			if(StringUtils.hasText(querier.getPatient()))
			{
				log.debugT("OrderDaoImpl: patient === " + querier.getPatient());
				queryCriteria.add(Restrictions.eq("patient", querier
						.getPatient()));
				countCriteria.add(Restrictions.eq("patient", querier
						.getPatient()));
			}
			// by tray
			if(StringUtils.hasText(querier.getTray()))
			{
				log.debugT("OrderDaoImpl: tray === " + querier.getTray());
				queryCriteria.add(Restrictions.eq("tray", querier
						.getTray()));
				countCriteria.add(Restrictions.eq("tray", querier
						.getTray()));
			}
			// by delivered date range
			if(querier.getStart_delivereddate() != null
					&& querier.getEnd_delivereddate() != null)
			{
				log.debugT("OrderDaoImpl: delivered date between "
						+ querier.getStart_delivereddate() + " and " + querier.getEnd_delivereddate());
				queryCriteria.add(Restrictions.ge("pi.token", new Integer(OrderStatus.TYPE_DELIVERED)))				
				.createAlias("pi.nodeInstances", "nis")
				.add(Restrictions.eq("nis.node", new Integer(OrderStatus.TYPE_DELIVERED)))
				.add(Restrictions.between("nis.leavedate", querier.getStart_delivereddate(), querier.getEnd_delivereddate()));
				countCriteria.add(Restrictions.ge("pi.token", new Integer(OrderStatus.TYPE_DELIVERED)))				
				.createAlias("pi.nodeInstances", "nis")
				.add(Restrictions.eq("nis.node", new Integer(OrderStatus.TYPE_DELIVERED)))
				.add(Restrictions.between("nis.leavedate", querier.getStart_delivereddate(), querier.getEnd_delivereddate()));				
			}
			// query total count
			Session session = template.getSessionFactory().openSession();
			int recordCount = ((Integer)countCriteria.getExecutableCriteria(
					session).setProjection(Projections.rowCount())
					.uniqueResult()).intValue();
			session.close();
			log.debugT("OrderDaoImpl: totalCount === " + recordCount);
			// order by
			if(querier.getOrders() == null)
			{
				Order ordereddateOrder = new HbOrder("id", false);
				querier.setOrders(new Order[] { ordereddateOrder });				
			}
			for(Order order : querier.getOrders())
			{
				if(order != null)
				{
					queryCriteria.addOrder(order);
				}
			}
			// pagination
			if(querier.getStartIndex() == null)
			{
				querier.setStartIndex(0);
			}
			// -1 for all records
			if(querier.getPageSize() == -1)
			{
				querier.setPageSize(recordCount);
			}			
			// default 20
			if(querier.getPageSize() == null || querier.getPageSize() == 0)
			{
				querier.setPageSize(20);
			}
			List list = template.findByCriteria(queryCriteria, querier
					.getStartIndex(), querier.getPageSize());
			querier.setListOrder(list);
			querier.setRecordCount(recordCount);
			if(recordCount >= 0)
			{
				if(querier.getPageSize() > querier.getRecordCount())
				{
					querier.setPageCount(1);
				}
				else
				{
					int pageCount = recordCount / querier.getPageSize();
					if(recordCount % querier.getPageSize() != 0)
					{
						pageCount++;
					}
					querier.setPageCount(pageCount);
				}
			}

			return querier;
		}
		catch(Exception e)
		{
			log.exception(e);
			throw new ProcessException(120001);
		}
	}

	//@Override
	public List getOrders(Integer[] orderIds) throws ProcessException
	{
		DetachedCriteria queryCriteria = DetachedCriteria.forClass(OrsOrder.class);
		try
		{
			if(orderIds == null || orderIds.length == 0)
			{
				throw new ProcessException(217001);
			}
			queryCriteria.add(Restrictions.in("id", orderIds));
			return template.findByCriteria(queryCriteria);
		}
		catch(Exception e)
		{
			log.exception(e);
			throw new ProcessException(120001);
		}
	}

	//@Override
	public int batchUpdateOrders(String queryString, Object[] values)
			throws ProcessException
	{
		try
		{
			if(queryString == null || queryString.trim().length() == 0)
			{
				throw new ProcessException(217001);
			}
			return template.bulkUpdate(queryString, values);
		}
		catch(Exception e)
		{
			log.exception(e);
			throw new ProcessException(120001);
		}
	}

	//@Override
	public void batchImportOrders(List orders) throws ProcessException
	{
		try
		{
			if(orders != null && orders.size() > 0)
			{
				Session session = template.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				for(Iterator it = orders.iterator(); it.hasNext();)
				{
					session.saveOrUpdate(it.next());
				}
				tx.commit();
				session.flush();
				session.clear();
				session.close();
			}
		}
		catch(Exception e)
		{
			log.exception(e);
			throw new ProcessException(120001);
		}
	}
}
