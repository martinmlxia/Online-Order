/**
 * LensMaterialDaoImpl.java
 * 2009-2-23
 * Administrator
 */
package com.conant.order.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.conant.order.dao.LensMaterialDao;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.LensMaterial;

/**
 * @author Administrator
 * 
 */
public class LensMaterialDaoImpl implements LensMaterialDao
{
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) throws ProcessException
	{
		this.template = template;
	}

	/**
	 * 获取镜片材料列表。
	 * @return LensMaterial对象列表
	 * @throws ProcessException
	 */
	public List getLensMaterials() throws ProcessException
	{
		try
		{
			DetachedCriteria criteria = DetachedCriteria.forClass(LensMaterial.class);
			criteria.add(Expression.or(Expression.le("id", new Integer(6)), Expression.eq("id", new Integer(12))));
			return template.findByCriteria(criteria);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			throw new ProcessException(400020);
		}
	}
	
	/**
	 * 获取镜片材料列表2。
	 * @return LensMaterial对象列表
	 * @throws ProcessException
	 */
	public List getLensMaterials2() throws ProcessException
	{
		try
		{
			DetachedCriteria criteria = DetachedCriteria.forClass(LensMaterial.class);
			criteria.add(Expression.and(Expression.gt("id", new Integer(6)),Expression.not(Expression.eq("id", new Integer(12)))));
			return template.findByCriteria(criteria);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			throw new ProcessException(400020);
		}
	}	
}
