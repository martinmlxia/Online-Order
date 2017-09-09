/**
 * ProcessInstanceDaoImpl.java
 * 2009-1-7
 * Administrator
 */
package com.conant.order.dao.hibernate;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.conant.order.dao.ProcessInstanceDao;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.ProcessInstance;

/**
 * @author Administrator
 *
 */
public class ProcessInstanceDaoImpl implements ProcessInstanceDao
{
	private static final Logger log = Logger.getLogger("ProcessInstanceDaoImpl",
			Logger.DEBUG, true);
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) throws ProcessException
	{
		this.template = template;
	}
	
	/* (non-Javadoc)
	 * @see com.conant.order.dao.ProcessInstanceDao#getProcessInstance(int)
	 */
	//@Override
	public ProcessInstance getProcessInstance(int processId)
			throws ProcessException
	{
		ProcessInstance processInstance = null;
		try
		{
			processInstance = (ProcessInstance)template.load(ProcessInstance.class, new Integer(processId));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new ProcessException(400020);
		}

		return processInstance;
	}

	/* (non-Javadoc)
	 * @see com.conant.order.dao.ProcessInstanceDao#saveProcessInstance(com.conant.order.vo.ProcessInstance)
	 */
	//@Override
	public void saveProcessInstance(ProcessInstance processInstance)
			throws ProcessException
	{
		try
		{
			if(processInstance != null)
			{
				template.update(processInstance);
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
