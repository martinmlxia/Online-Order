/**
 * LensTreatmentDaoImpl.java
 * 2009-2-23
 * Administrator
 */
package com.conant.order.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.conant.order.dao.LensTreatmentDao;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.LensTreatment;

/**
 * @author Administrator
 * 
 */
public class LensTreatmentDaoImpl implements LensTreatmentDao
{
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) throws ProcessException
	{
		this.template = template;
	}

	/**
	 * 获取镜片加工程度列表。
	 * @return LensTreatment对象列表
	 * @throws ProcessException
	 */
	public List getLensTreatments() throws ProcessException
	{
		try
		{
			List list = template.loadAll(LensTreatment.class);
			return list;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			throw new ProcessException(400020);
		}
	}
}
