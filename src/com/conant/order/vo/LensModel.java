package com.conant.order.vo;

/**
 * æµ∆¨–Õ∫≈ µÃÂ°£
 * LensModel
 */
public class LensModel implements java.io.Serializable
{
	private Integer id;
	private Integer opticaltype;
	private Integer lenscolor;
	private Integer lenscoating;
	private Integer customizable;
	private String lang;
	private String typeNo;
	private String typeName;
	private LensMaterial material;
	private LensType lenstype;

	public LensModel()
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

	public Integer getOpticaltype()
	{
		return this.opticaltype;
	}

	public void setOpticaltype(Integer opticaltype)
	{
		this.opticaltype = opticaltype;
	}

	public Integer getLenscolor()
	{
		return this.lenscolor;
	}

	public void setLenscolor(Integer lenscolor)
	{
		this.lenscolor = lenscolor;
	}

	public Integer getLenscoating()
	{
		return this.lenscoating;
	}

	public void setLenscoating(Integer lenscoating)
	{
		this.lenscoating = lenscoating;
	}

	public Integer getCustomizable()
	{
		return this.customizable;
	}

	public void setCustomizable(Integer customizable)
	{
		this.customizable = customizable;
	}

	public String getLang()
	{
		return lang;
	}

	public void setLang(String lang)
	{
		this.lang = lang;
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

	public LensMaterial getMaterial()
	{
		return material;
	}

	public void setMaterial(LensMaterial material)
	{
		this.material = material;
	}

	public LensType getLenstype()
	{
		return lenstype;
	}

	public void setLenstype(LensType lenstype)
	{
		this.lenstype = lenstype;
	}
}
