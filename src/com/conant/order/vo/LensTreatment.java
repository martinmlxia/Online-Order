/**
 * LensTreatment.java
 * 2009-2-22
 * Administrator
 */
package com.conant.order.vo;

import java.io.Serializable;

/**
 * ��Ƭ�ӹ���ʽʵ�塣
 * @author Administrator
 */
public class LensTreatment implements Serializable
{
	private Integer id;
	private String typeNo;
	private String typeName;
	
	public LensTreatment()
	{
	}

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getTypeNo()
	{
		return typeNo;
	}

	public void setTypeNo(String typeNo)
	{
		this.typeNo = typeNo;
	}

	public String getTypeName()
	{
		return typeName;
	}

	public void setTypeName(String typeName)
	{
		this.typeName = typeName;
	}
}
