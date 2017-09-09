/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conant.order.dao;

import java.util.List;
import java.util.Locale;

import com.conant.order.util.ProcessException;

/**
 * 
 * @author Administrator
 */
public interface LensModelDao
{
	/**
	 * 获取所有镜片类型实体。
	 * @return 镜片类型实体列表
	 * @throws ProcessException
	 */
	List getLensModel() throws ProcessException;
	
	/**
	 * 获取指定地域的镜片类型实体。
	 * @param locale 地域对象
	 * @return 镜片类型实体列表
	 * @throws ProcessException
	 */
	List getLensModel(Locale locale) throws ProcessException;
}
