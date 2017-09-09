/**
 * LensTypeDaoImpl.java
 * 2009-2-23
 * Administrator
 */
package com.conant.order.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.conant.order.dao.LensTypeDao;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.LensType;

/**
 * @author Administrator
 * 
 */
public class LensTypeDaoImpl implements LensTypeDao
{
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) throws ProcessException
	{
		this.template = template;
	}

	/**
	 * ��ȡ��Ƭ�����б�
	 * @return LensType�����б�
	 * @throws ProcessException
	 */
	public List getLensTypes() throws ProcessException
	{
		try
		{
			List list = template.loadAll(LensType.class);
			return list;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			throw new ProcessException(400020);
		}
	}
}
