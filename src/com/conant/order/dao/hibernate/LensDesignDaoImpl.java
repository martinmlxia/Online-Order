package com.conant.order.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.conant.order.dao.LensDesignDao;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.LensDesign;

public class LensDesignDaoImpl implements LensDesignDao
{
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) throws ProcessException
	{
		this.template = template;
	}
	
	//@Override
	public List getLensDesigns() throws ProcessException
	{
		try
		{
			List list = template.loadAll(LensDesign.class);
			return list;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			throw new ProcessException(400020);
		}
	}
}
