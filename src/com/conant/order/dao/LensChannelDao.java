package com.conant.order.dao;

import java.util.List;

import com.conant.order.util.ProcessException;

public interface LensChannelDao
{
	/**
	 * 获取通道类型列表。
	 * @return LensChannel对象列表
	 * @throws ProcessException
	 */
	List getLensChannels() throws ProcessException;
}
