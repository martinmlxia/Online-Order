/**
 * TintColorImpl.java
 * 2009-1-22
 * Administrator
 */
package com.conant.order.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.conant.order.dao.TintColorDao;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.TintColor;

/**
 * @author Administrator
 *
 */
public class TintColorDaoImpl implements TintColorDao
{
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) throws ProcessException
	{
		this.template = template;
	}
	
	/**
	 * 获取所有染色实体。
	 * @return 染色实体列表
	 * @throws ProcessException
	 */
	public List getTintColors() throws ProcessException
	{
		try
		{
			List list = template.loadAll(TintColor.class);
			return list;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			throw new ProcessException(400020);			
		}
	}
}
