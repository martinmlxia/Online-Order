package com.conant.order.vo;

/**
 * ¾µ¼ÜÑÕÉ«ÊµÌå¡£
 * Framecolor
 */

public class FrameColor implements java.io.Serializable
{
	private Integer id;
	private String typeNo;
	private String typeName;
	
	public FrameColor()
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
