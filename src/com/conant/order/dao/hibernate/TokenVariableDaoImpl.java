/**
 * TokenVariableMapDaoImpl.java
 * 2009-7-19
 * Administrator
 */
package com.conant.order.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.conant.order.dao.TokenVariableDao;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.TokenVariable;

/**
 * @author Administrator
 *
 */
public class TokenVariableDaoImpl implements TokenVariableDao
{
	private static final Logger log = Logger.getLogger("TokenVariableMapDaoImpl",
			Logger.DEBUG, true);	
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) throws ProcessException
	{
		this.template = template;
	}
	
	/* (non-Javadoc)
	 * @see com.conant.order.dao.TokenVariableMapDao#getTokenVariable(int)
	 */
	//@Override
	public TokenVariable getTokenVariable(int id) throws ProcessException
	{
		TokenVariable variable = null;
		try
		{
			variable = (TokenVariable)template.load(TokenVariable.class, new Integer(id));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new ProcessException(400020);
		}

		return variable;
	}

	/* (non-Javadoc)
	 * @see com.conant.order.dao.TokenVariableMapDao#getTokenVariables(int, int, java.lang.String)
	 */
	//@Override
	public TokenVariable getTokenVariable(int processId, int token,
			String name) throws ProcessException
	{
		TokenVariable variable = null;
		try
		{
			DetachedCriteria queryCriteria = DetachedCriteria.forClass(TokenVariable.class);
			queryCriteria.add(Expression.eq("token", new Integer(token)));
			queryCriteria.add(Expression.eq("processInstance.id", new Integer(processId)));
			queryCriteria.add(Expression.eq("name", name));
			List listVariable = template.findByCriteria(queryCriteria);
			variable = (TokenVariable)listVariable.get(0);
		}
		catch(Exception e)
		{
			log.exception(e);
			throw new ProcessException(120001);
		}
		return variable;
	}

	/* (non-Javadoc)
	 * @see com.conant.order.dao.TokenVariableMapDao#getTokenVariables(int, int)
	 */
	//@Override
	public List getTokenVariables(int processId, int token)
			throws ProcessException
	{
		DetachedCriteria queryCriteria = DetachedCriteria.forClass(TokenVariable.class);
		try
		{
			queryCriteria.add(Expression.eq("token", new Integer(token)));
			queryCriteria.add(Expression.eq("processInstance.id", new Integer(processId)));
			return template.findByCriteria(queryCriteria);
		}
		catch(Exception e)
		{
			log.exception(e);
			throw new ProcessException(120001);
		}
	}

	/* (non-Javadoc)
	 * @see com.conant.order.dao.TokenVariableMapDao#saveTokenVariable(com.conant.order.vo.TokenVariableMap)
	 */
	//@Override
	public void saveTokenVariable(TokenVariable tokenVariable)
			throws ProcessException
	{
		try
		{
			if(tokenVariable != null)
			{
				template.saveOrUpdate(tokenVariable);
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
