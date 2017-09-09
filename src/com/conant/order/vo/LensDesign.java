/**
 * LensDesign.java
 * 2009-2-22
 * Administrator
 */
package com.conant.order.vo;

import java.io.Serializable;

/**
 * 设计类型实体。
 * @author Administrator
 */
public class LensDesign implements Serializable
{
	private Integer id;
	private String typeNo;
	private String typeName;
	
	public LensDesign()
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
