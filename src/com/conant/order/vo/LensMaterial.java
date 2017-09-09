/**
 * LensMaterial.java
 * 2009-2-22
 * Administrator
 */
package com.conant.order.vo;

import java.io.Serializable;

/**
 * æµ∆¨≤ƒ¡œ µÃÂ°£
 * @author Administrator
 */
public class LensMaterial implements Serializable
{
	private Integer id;
	private String typeNo;
	private String typeName;
	
	public LensMaterial()
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
