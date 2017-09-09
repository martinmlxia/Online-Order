/**
 * FrameLensOrder.java
 * 2009-2-23
 * Administrator
 */
package com.conant.order.web.form;

/**
 * Frame&Lens订单form。定义本form以解决直接使用订单实体做form存在的数据转换繁琐的问题。
 * @author Administrator
 * 
 */
public class FrameLensOrder
{
	// -------------------------------------------------------------
	// order
	// -------------------------------------------------------------
	private Integer id;
	private String patient;
	private String tray;
	private String remarks;
	private Boolean emergent;
	// -------------------------------------------------------------
	// lens detail
	// -------------------------------------------------------------
	private String diameter;
	private Integer diameterUnit;
	private String rsphere;
	private String lsphere;
	private String rcylinder;
	private String lcylinder;
	private String radd;
	private String ladd;
	private String raxis;
	private String laxis;
	private String rhprism;
	private String lhprism;
	private Integer rhpd;
	private Integer lhpd;
	private String verdis;
	private String pantilt;
	private String wrapangle;
	//add 2.22
	private String rbhprism;
	private String lbhprism;
	private Integer rbhpd;
	private Integer lbhpd;
	//add end
	private String rbasecurve;
	private String lbasecurve;
	private String rsegheight;
	private String lsegheight;
	private String rfarpd;
	private String lfarpd;
	private String rnearpd;
	private String lnearpd;
	private String rochgt;
	private String lochgt;
	private String material;
	private String material2;
	private String lenstype;
	private String tinttype;
	private String tintcolor1;
	private String tintcolor2;
	private String uv;
	private Integer treat;
	private String lensdesign;
	private String channellength;
	private Float price;
	private String tframe;
	private String userid;
	// -------------------------------------------------------------
	// frame detail
	// -------------------------------------------------------------
	private String rboxasize;
	private String rboxbsize;
	private String dbl;
	private String ed;
	private String framestyle;
	private Integer frametype;
	private Integer framepattern;
	// -------------------------------------------------------------
	// others
	// -------------------------------------------------------------
	private Boolean printAfterCommit;
	private String language;

	public FrameLensOrder()
	{
		emergent = new Boolean(false);
		printAfterCommit = new Boolean(false);
	}
	
	// -------------------------------------------------------------
	// order
	// -------------------------------------------------------------
	public String getPatient()
	{
		return patient;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public void setPatient(String patient)
	{
		this.patient = patient;
	}

	public String getTray()
	{
		return tray;
	}

	public void setTray(String tray)
	{
		this.tray = tray;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
	
	public Boolean getEmergent()
	{
		return emergent;
	}

	public void setEmergent(Boolean emergent)
	{
		this.emergent = emergent;
	}	

	// -------------------------------------------------------------
	// lens detail
	// -------------------------------------------------------------
	public String getDiameter()
	{
		return diameter;
	}

	public void setDiameter(String diameter)
	{
		this.diameter = diameter;
	}

	public Integer getDiameterUnit()
	{
		return diameterUnit;
	}

	public void setDiameterUnit(Integer diameterUnit)
	{
		this.diameterUnit = diameterUnit;
	}
	
	public String getRsphere()
	{
		return rsphere;
	}

	public void setRsphere(String rsphere)
	{
		this.rsphere = rsphere;
	}

	public String getLsphere()
	{
		return lsphere;
	}

	public void setLsphere(String lsphere)
	{
		this.lsphere = lsphere;
	}

	public String getRcylinder()
	{
		return rcylinder;
	}

	public void setRcylinder(String rcylinder)
	{
		this.rcylinder = rcylinder;
	}

	public String getLcylinder()
	{
		return lcylinder;
	}

	public void setLcylinder(String lcylinder)
	{
		this.lcylinder = lcylinder;
	}

	public String getRadd()
	{
		return radd;
	}

	public void setRadd(String radd)
	{
		this.radd = radd;
	}

	public String getLadd()
	{
		return ladd;
	}

	public void setLadd(String ladd)
	{
		this.ladd = ladd;
	}

	public String getRaxis()
	{
		return raxis;
	}

	public void setRaxis(String raxis)
	{
		this.raxis = raxis;
	}

	public String getLaxis()
	{
		return laxis;
	}

	public void setLaxis(String laxis)
	{
		this.laxis = laxis;
	}

	public String getRhprism()
	{
		return rhprism;
	}

	public void setRhprism(String rhprism)
	{
		this.rhprism = rhprism;
	}

	public String getLhprism()
	{
		return lhprism;
	}

	public void setLhprism(String lhprism)
	{
		this.lhprism = lhprism;
	}
	
	public Integer getRhpd()
	{
		return rhpd;
	}

	public void setRhpd(Integer rhpd)
	{
		this.rhpd = rhpd;
	}

	public Integer getLhpd()
	{
		return lhpd;
	}

	public void setLhpd(Integer lhpd)
	{
		this.lhpd = lhpd;
	}

	public String getRbasecurve()
	{
		return rbasecurve;
	}

	public void setRbasecurve(String rbasecurve)
	{
		this.rbasecurve = rbasecurve;
	}

	public String getLbasecurve()
	{
		return lbasecurve;
	}

	public void setLbasecurve(String lbasecurve)
	{
		this.lbasecurve = lbasecurve;
	}

	public String getRsegheight()
	{
		return rsegheight;
	}

	public void setRsegheight(String rsegheight)
	{
		this.rsegheight = rsegheight;
	}

	public String getLsegheight()
	{
		return lsegheight;
	}

	public void setLsegheight(String lsegheight)
	{
		this.lsegheight = lsegheight;
	}

	public String getRfarpd()
	{
		return rfarpd;
	}

	public void setRfarpd(String rfarpd)
	{
		this.rfarpd = rfarpd;
	}

	public String getLfarpd()
	{
		return lfarpd;
	}

	public void setLfarpd(String lfarpd)
	{
		this.lfarpd = lfarpd;
	}

	public String getRnearpd()
	{
		return rnearpd;
	}

	public void setRnearpd(String rnearpd)
	{
		this.rnearpd = rnearpd;
	}

	public String getLnearpd()
	{
		return lnearpd;
	}

	public void setLnearpd(String lnearpd)
	{
		this.lnearpd = lnearpd;
	}

	public String getRochgt()
	{
		return rochgt;
	}

	public void setRochgt(String rochgt)
	{
		this.rochgt = rochgt;
	}

	public String getLochgt()
	{
		return lochgt;
	}

	public void setLochgt(String lochgt)
	{
		this.lochgt = lochgt;
	}

	public String getMaterial()
	{
		return material;
	}

	public void setMaterial(String material)
	{
		this.material = material;
	}
	
	public String getMaterial2()
	{
		return material2;
	}

	public void setMaterial2(String material2)
	{
		this.material2 = material2;
	}

	public String getLenstype()
	{
		return lenstype;
	}

	public void setLenstype(String lenstype)
	{
		this.lenstype = lenstype;
	}

	public String getTinttype()
	{
		return tinttype;
	}

	public void setTinttype(String tinttype)
	{
		this.tinttype = tinttype;
	}

	public String getTintcolor1()
	{
		return tintcolor1;
	}

	public void setTintcolor1(String tintcolor1)
	{
		this.tintcolor1 = tintcolor1;
	}

	public String getTintcolor2()
	{
		return tintcolor2;
	}

	public void setTintcolor2(String tintcolor2)
	{
		this.tintcolor2 = tintcolor2;
	}

	public String getUv()
	{
		return uv;
	}

	public void setUv(String uv)
	{
		this.uv = uv;
	}

	public String getLensdesign()
	{
		return lensdesign;
	}

	public void setLensdesign(String lensdesign)
	{
		this.lensdesign = lensdesign;
	}

	public String getChannellength()
	{
		return channellength;
	}

	public void setChannellength(String channellength)
	{
		this.channellength = channellength;
	}	
	// -------------------------------------------------------------
	// frame detail
	// -------------------------------------------------------------	
	public String getRboxasize()
	{
		return rboxasize;
	}

	public void setRboxasize(String rboxasize)
	{
		this.rboxasize = rboxasize;
	}

	public String getRboxbsize()
	{
		return rboxbsize;
	}

	public void setRboxbsize(String rboxbsize)
	{
		this.rboxbsize = rboxbsize;
	}

	public String getDbl()
	{
		return dbl;
	}

	public void setDbl(String dbl)
	{
		this.dbl = dbl;
	}

	public String getEd()
	{
		return ed;
	}

	public void setEd(String ed)
	{
		this.ed = ed;
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

	// -------------------------------------------------------------
	// others
	// -------------------------------------------------------------		
	public boolean isPrintAfterCommit()
	{
		return printAfterCommit;
	}

	public void setPrintAfterCommit(boolean printAfterCommit)
	{
		this.printAfterCommit = printAfterCommit;
	}

	public Integer getFramepattern() {
		return framepattern;
	}

	public void setFramepattern(Integer framepattern) {
		this.framepattern = framepattern;
	}

	public Integer getTreat() {
		return treat;
	}

	public void setTreat(Integer treat) {
		this.treat = treat;
	}

	public String getRbhprism() {
		return rbhprism;
	}

	public void setRbhprism(String rbhprism) {
		this.rbhprism = rbhprism;
	}

	public String getLbhprism() {
		return lbhprism;
	}

	public void setLbhprism(String lbhprism) {
		this.lbhprism = lbhprism;
	}

	public Integer getRbhpd() {
		return rbhpd;
	}

	public void setRbhpd(Integer rbhpd) {
		this.rbhpd = rbhpd;
	}

	public Integer getLbhpd() {
		return lbhpd;
	}

	public void setLbhpd(Integer lbhpd) {
		this.lbhpd = lbhpd;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getTframe() {
		return tframe;
	}

	public void setTframe(String tframe) {
		this.tframe = tframe;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getVerdis() {
		return verdis;
	}

	public void setVerdis(String verdis) {
		this.verdis = verdis;
	}

	public String getPantilt() {
		return pantilt;
	}

	public void setPantilt(String pantilt) {
		this.pantilt = pantilt;
	}

	public String getWrapangle() {
		return wrapangle;
	}

	public void setWrapangle(String wrapangle) {
		this.wrapangle = wrapangle;
	}

	public Boolean getPrintAfterCommit() {
		return printAfterCommit;
	}

	public void setPrintAfterCommit(Boolean printAfterCommit) {
		this.printAfterCommit = printAfterCommit;
	}
}