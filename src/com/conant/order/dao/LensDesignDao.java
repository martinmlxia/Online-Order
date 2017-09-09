package com.conant.order.dao;

import java.util.List;

import com.conant.order.util.ProcessException;

public interface LensDesignDao
{
	/**
	 * 获取设计类型列表。
	 * @return LensDesign对象列表
	 * @throws ProcessException
	 */
	List getLensDesigns() throws ProcessException;
}
