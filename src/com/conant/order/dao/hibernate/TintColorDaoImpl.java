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
	 * ��ȡ����Ⱦɫʵ�塣
	 * @return Ⱦɫʵ���б�
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
