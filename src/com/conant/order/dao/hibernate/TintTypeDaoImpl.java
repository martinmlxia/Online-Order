/**
 * TintTypeDaoImpl.java
 * 2009-2-23
 * Administrator
 */
package com.conant.order.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.conant.order.dao.TintTypeDao;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.LensTint;

/**
 * @author Administrator
 * 
 */
public class TintTypeDaoImpl implements TintTypeDao
{
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) throws ProcessException
	{
		this.template = template;
	}

	/**
	 * ��ȡ��ƬȾɫ�����б�
	 * @return LensTint�����б�
	 * @throws ProcessException
	 */
	public List getTintTypes() throws ProcessException
	{
		try
		{
			List list = template.loadAll(LensTint.class);
			return list;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			throw new ProcessException(400020);
		}
	}
}
