package com.conant.order.dao;

import java.util.List;

import com.conant.order.util.ProcessException;

public interface LensDesignDao
{
	/**
	 * ��ȡ��������б�
	 * @return LensDesign�����б�
	 * @throws ProcessException
	 */
	List getLensDesigns() throws ProcessException;
}
