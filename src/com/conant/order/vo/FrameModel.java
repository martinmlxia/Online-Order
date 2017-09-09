package com.conant.order.vo;

/**
 * æµº‹–Õ∫≈ µÃÂ°£
 * FrameModel
 */
public class FrameModel implements java.io.Serializable
{
	private Integer id;
	private String collection;
	private Boolean rimless;
	private String typeNo;
	private String typeName;

	public FrameModel()
	{
		rimless = Boolean.FALSE;
	}

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getCollection()
	{
		return this.collection;
	}

	public void setCollection(String collection)
	{
		this.collection = collection;
	}

	public Boolean isRimless()
	{
		return this.rimless;
	}

	public void setRimless(Boolean rimless)
	{
		this.rimless = rimless;
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

	public Boolean getRimless()
	{
		return rimless;
	}
}
