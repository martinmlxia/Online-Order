package com.conant.order.vo;

/**
 * 镜架信息实体。
 * FrameDetail
 */

public class FrameDetail implements java.io.Serializable
{
	private Integer id;
	private Float rboxasize;
	private Integer lboxasize;
	private Float rboxbsize;
	private Integer lboxbsize;
	private Float rfittingheight;
	private Float lfittingheight;
	private Float rdistance;
	private Float ldistance;
	private Float dbl;
	private Float ed;
	private Float dbc;
	private String framestyle;
	private Integer frametype;
	private OrsOrder orsorder;
	private FrameColor framecolor;
	private FrameModel framemodel;
	private Integer framepattern;

	public FrameDetail()
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

	public OrsOrder getOrsorder()
	{
		return this.orsorder;
	}

	public void setOrsorder(OrsOrder orsorder)
	{
		this.orsorder = orsorder;
	}

	public FrameColor getFramecolor()
	{
		return this.framecolor;
	}

	public void setFramecolor(FrameColor framecolor)
	{
		this.framecolor = framecolor;
	}

	public FrameModel getFramemodel()
	{
		return this.framemodel;
	}

	public void setFramemodel(FrameModel framemodel)
	{
		this.framemodel = framemodel;
	}

	public Float getRboxasize()
	{
		return this.rboxasize;
	}

	public void setRboxasize(Float rboxasize)
	{
		this.rboxasize = rboxasize;
	}

	public Integer getLboxasize()
	{
		return this.lboxasize;
	}

	public void setLboxasize(Integer lboxasize)
	{
		this.lboxasize = lboxasize;
	}

	public Float getRboxbsize()
	{
		return this.rboxbsize;
	}

	public void setRboxbsize(Float rboxbsize)
	{
		this.rboxbsize = rboxbsize;
	}

	public Integer getLboxbsize()
	{
		return this.lboxbsize;
	}

	public void setLboxbsize(Integer lboxbsize)
	{
		this.lboxbsize = lboxbsize;
	}

	public Float getRfittingheight()
	{
		return this.rfittingheight;
	}

	public void setRfittingheight(Float rfittingheight)
	{
		this.rfittingheight = rfittingheight;
	}

	public Float getLfittingheight()
	{
		return this.lfittingheight;
	}

	public void setLfittingheight(Float lfittingheight)
	{
		this.lfittingheight = lfittingheight;
	}

	public Float getRdistance()
	{
		return this.rdistance;
	}

	public void setRdistance(Float rdistance)
	{
		this.rdistance = rdistance;
	}

	public Float getLdistance()
	{
		return this.ldistance;
	}

	public void setLdistance(Float ldistance)
	{
		this.ldistance = ldistance;
	}

	public Float getDbl()
	{
		return this.dbl;
	}

	public void setDbl(Float dbl)
	{
		this.dbl = dbl;
	}

	public Float getEd()
	{
		return ed;
	}

	public void setEd(Float ed)
	{
		this.ed = ed;
	}

	public Float getDbc()
	{
		return dbc;
	}

	public void setDbc(Float dbc)
	{
		this.dbc = dbc;
	}

	public String getFramestyle()
	{
		return framestyle;
	}

	public void setFramestyle(String framestyle)
	{
		this.framestyle = framestyle;
	}

	public Integer getFrametype()
	{
		return frametype;
	}

	public void setFrametype(Integer frametype)
	{
		this.frametype = frametype;
	}

	public Integer getFramepattern() {
		return framepattern;
	}

	public void setFramepattern(Integer framepattern) {
		this.framepattern = framepattern;
	}
}
