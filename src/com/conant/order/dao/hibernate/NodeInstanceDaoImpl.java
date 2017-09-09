/**
 * NodeInstanceDaoImpl.java
 * 2009-1-7
 * Administrator
 */
package com.conant.order.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.conant.order.dao.NodeInstanceDao;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.NodeInstance;

/**
 * @author Administrator
 *
 */
public class NodeInstanceDaoImpl implements NodeInstanceDao
{
	private static final Logger log = Logger.getLogger("NodeInstanceDaoImpl",
			Logger.DEBUG, true);
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) throws ProcessException
	{
		this.template = template;
	}
	
	/* (non-Javadoc)
	 * @see com.conant.order.dao.NodeInstanceDao#getNodeInstance(int)
	 */
	//@Override
	public NodeInstance getNodeInstance(int id) throws ProcessException
	{
		NodeInstance nodeInstance = null;
		try
		{
			nodeInstance = (NodeInstance)template.load(NodeInstance.class, new Integer(id));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new ProcessException(400020);
		}

		return nodeInstance;
	}
	
	//@Override
	public NodeInstance getNodeInstance(int processId, int nodeId)
			throws ProcessException
	{
		NodeInstance nodeInstance = null;
		try
		{
			DetachedCriteria criteria = DetachedCriteria.forClass(NodeInstance.class);
			criteria.add(Expression.eq("node", new Integer(nodeId)));
			criteria.add(Expression.eq("processInstance.id", new Integer(processId)));
			List listNode = template.findByCriteria(criteria);
			nodeInstance = (NodeInstance)listNode.get(0);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new ProcessException(400020);
		}

		return nodeInstance;
	}

	/* (non-Javadoc)
	 * @see com.conant.order.dao.NodeInstanceDao#saveNodeInstance(com.conant.order.vo.NodeInstance)
	 */
	//@Override
	public void saveNodeInstance(NodeInstance nodeInstance)
			throws ProcessException
	{
		try
		{
			if(nodeInstance != null)
			{
				template.update(nodeInstance);
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
}
