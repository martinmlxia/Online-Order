package com.conant.order.dao;

import java.util.List;

import com.conant.order.util.ProcessException;

public interface LensChannelDao
{
	/**
	 * ��ȡͨ�������б�
	 * @return LensChannel�����б�
	 * @throws ProcessException
	 */
	List getLensChannels() throws ProcessException;
}
