package com.conant.order.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.conant.order.dao.LensChannelDao;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.LensChannel;

public class LensChannelDaoImpl implements LensChannelDao
{
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) throws ProcessException
	{
		this.template = template;
	}
	
	//@Override
	public List getLensChannels() throws ProcessException
	{
		try
		{
			List list = template.loadAll(LensChannel.class);
			return list;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			throw new ProcessException(400020);
		}
	}
}
