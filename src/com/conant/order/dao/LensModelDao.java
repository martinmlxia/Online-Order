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
	 * ��ȡ���о�Ƭ����ʵ�塣
	 * @return ��Ƭ����ʵ���б�
	 * @throws ProcessException
	 */
	List getLensModel() throws ProcessException;
	
	/**
	 * ��ȡָ������ľ�Ƭ����ʵ�塣
	 * @param locale �������
	 * @return ��Ƭ����ʵ���б�
	 * @throws ProcessException
	 */
	List getLensModel(Locale locale) throws ProcessException;
}
