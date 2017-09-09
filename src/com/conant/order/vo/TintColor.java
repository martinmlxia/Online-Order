package com.conant.order.vo;

/**
 * 染色颜色实体。
 * Tintcolor
 */
public class TintColor implements java.io.Serializable
{
	private Integer id;
	private String typeNo;
	private String typeName;
	private Integer transparency;

	public TintColor()
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

	public Integer getTransparency()
	{
		return transparency;
	}

	public void setTransparency(Integer transparency)
	{
		this.transparency = transparency;
	}	
}
