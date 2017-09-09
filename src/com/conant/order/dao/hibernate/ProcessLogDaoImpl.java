/**
 * ProcessLogDaoImpl.java
 * 2009-1-7
 * Administrator
 */
package com.conant.order.dao.hibernate;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.conant.order.dao.ProcessLogDao;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.ProcessLog;

/**
 * @author Administrator
 *
 */
public class ProcessLogDaoImpl implements ProcessLogDao
{
	private static Logger log = Logger.getLogger("ProcessLogDaoImpl",
			Logger.DEBUG, true);
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) throws ProcessException
	{
		this.template = template;
	}
	
	/* (non-Javadoc)
	 * @see com.conant.order.dao.ProcessLogDao#saveProcessLog(com.conant.order.vo.ProcessLog)
	 */
	//@Override
	public void saveProcessLog(ProcessLog processLog) throws ProcessException
	{
		try
		{
			if(processLog != null)
			{
				template.save(processLog);
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
