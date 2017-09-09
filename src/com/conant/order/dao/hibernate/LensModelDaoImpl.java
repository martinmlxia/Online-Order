/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conant.order.dao.hibernate;

import java.util.List;
import java.util.Locale;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.conant.order.dao.LensModelDao;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.LensModel;

/**
 * 
 * @author Administrator
 */
public class LensModelDaoImpl implements LensModelDao
{
	private static Logger log = Logger.getLogger("LensModelDaoImpl",
			Logger.DEBUG, true);
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) throws ProcessException
	{
		this.template = template;
	}

	public List getLensModel() throws ProcessException
	{
		try
		{
			List list = template.loadAll(LensModel.class);
			return list;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			throw new ProcessException(400020);			
		}
	}

	//@Override
	public List getLensModel(Locale locale) throws ProcessException
	{
		try
		{
			DetachedCriteria criteria = DetachedCriteria.forClass(LensModel.class);
			criteria.add(Restrictions.eq("lang", locale.getLanguage().toLowerCase()));
			log.info("language == " + locale.getLanguage().toLowerCase());
			return template.findByCriteria(criteria);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			throw new ProcessException(400020);
		}
	}
}
