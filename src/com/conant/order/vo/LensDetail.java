package com.conant.order.vo;

/**
 * 镜片信息实体。
 * LensDetail
 */
public class LensDetail implements java.io.Serializable
{
	private Integer id;
	private Integer diameter;
	private Integer diameterUnit;
	private Float rsphere;
	private Float lsphere;
	private Float rcylinder;
	private Float lcylinder;
	private Float radd;
	private Float ladd;
	private Integer raxis;
	private Integer laxis;
	private Integer rvpd;
	private Integer lvpd;
	private Float rvprism;
	private Float lvprism;
	private Integer rhpd;
	private Integer lhpd;
	private Float rhprism;
	private Float lhprism;
	private Float rbhprism;
	private Float lbhprism;
	private Integer rbhpd;
	private Integer lbhpd;
	private Integer rbasecurve;
	private Integer lbasecurve;
	private Float rdecentration;
	private Float ldecentration;
	private Float redgethickness;
	private Float ledgethickness;
	private Float rcenterthickness;
	private Float lcenterthickness;
	private String rxtreatment;
	private String framestyle;
	private String refractive;
	private String tintlens;
	private String tintcolor;
	private Float rmrp;
	private Float lmrp;
	private Float rpd;
	private Float lpd;
	private Float quantity;
	private Float rsegheight;
	private Float lsegheight;
	private Float rsegdrop;
	private Float lsegdrop;
	private Float rfarpd;
	private Float lfarpd;
	private Float rnearpd;
	private Float lnearpd;
	private Float rochgt;
	private Float lochgt;
	private Integer treat;
	private Boolean uv;
	private OrsOrder orsorder;
	private LensTint tinttype;
	private TintColor tintcolor1;
	private TintColor tintcolor2;
	private LensMaterial material;
	private LensMaterial material2;
	private LensType lenstype;
	private LensDesign lensdesign;
	private LensChannel channellength;
	private String tframe;
	private String verdis;
	private String pantilt;
	private String wrapangle;

	public LensDetail()
	{
		quantity = 1f;
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

	public Integer getDiameter()
	{
		return this.diameter;
	}

	public void setDiameter(Integer diameter)
	{
		this.diameter = diameter;
	}
	
	public Integer getDiameterUnit()
	{
		return this.diameterUnit;
	}

	public void setDiameterUnit(Integer diameterUnit)
	{
		this.diameterUnit = diameterUnit;
	}

	public Float getRsphere()
	{
		return this.rsphere;
	}

	public void setRsphere(Float rsphere)
	{
		this.rsphere = rsphere;
	}

	public Float getLsphere()
	{
		return this.lsphere;
	}

	public void setLsphere(Float lsphere)
	{
		this.lsphere = lsphere;
	}

	public Float getRcylinder()
	{
		return this.rcylinder;
	}

	public void setRcylinder(Float rcylinder)
	{
		this.rcylinder = rcylinder;
	}

	public Float getLcylinder()
	{
		return this.lcylinder;
	}

	public void setLcylinder(Float lcylinder)
	{
		this.lcylinder = lcylinder;
	}

	public Float getRadd()
	{
		return this.radd;
	}

	public void setRadd(Float radd)
	{
		this.radd = radd;
	}

	public Float getLadd()
	{
		return this.ladd;
	}

	public void setLadd(Float ladd)
	{
		this.ladd = ladd;
	}

	public Integer getRaxis()
	{
		return this.raxis;
	}

	public void setRaxis(Integer raxis)
	{
		this.raxis = raxis;
	}

	public Integer getLaxis()
	{
		return this.laxis;
	}

	public void setLaxis(Integer laxis)
	{
		this.laxis = laxis;
	}

	public Integer getRvpd()
	{
		return this.rvpd;
	}

	public void setRvpd(Integer rvpd)
	{
		this.rvpd = rvpd;
	}

	public Integer getLvpd()
	{
		return this.lvpd;
	}

	public void setLvpd(Integer lvpd)
	{
		this.lvpd = lvpd;
	}

	public Float getRvprism()
	{
		return this.rvprism;
	}

	public void setRvprism(Float rvprism)
	{
		this.rvprism = rvprism;
	}

	public Float getLvprism()
	{
		return this.lvprism;
	}

	public void setLvprism(Float lvprism)
	{
		this.lvprism = lvprism;
	}

	public Integer getRhpd()
	{
		return this.rhpd;
	}

	public void setRhpd(Integer rhpd)
	{
		this.rhpd = rhpd;
	}

	public Integer getLhpd()
	{
		return this.lhpd;
	}

	public void setLhpd(Integer lhpd)
	{
		this.lhpd = lhpd;
	}

	public Float getRhprism()
	{
		return this.rhprism;
	}

	public void setRhprism(Float rhprism)
	{
		this.rhprism = rhprism;
	}

	public Float getLhprism()
	{
		return this.lhprism;
	}

	public void setLhprism(Float lhprism)
	{
		this.lhprism = lhprism;
	}

	public Integer getRbasecurve()
	{
		return this.rbasecurve;
	}

	public void setRbasecurve(Integer rbasecurve)
	{
		this.rbasecurve = rbasecurve;
	}

	public Integer getLbasecurve()
	{
		return this.lbasecurve;
	}

	public void setLbasecurve(Integer lbasecurve)
	{
		this.lbasecurve = lbasecurve;
	}

	public Float getRdecentration()
	{
		return this.rdecentration;
	}

	public void setRdecentration(Float rdecentration)
	{
		this.rdecentration = rdecentration;
	}

	public Float getLdecentration()
	{
		return this.ldecentration;
	}

	public void setLdecentration(Float ldecentration)
	{
		this.ldecentration = ldecentration;
	}

	public Float getRedgethickness()
	{
		return this.redgethickness;
	}

	public void setRedgethickness(Float redgethickness)
	{
		this.redgethickness = redgethickness;
	}

	public Float getLedgethickness()
	{
		return this.ledgethickness;
	}

	public void setLedgethickness(Float ledgethickness)
	{
		this.ledgethickness = ledgethickness;
	}

	public Float getRcenterthickness()
	{
		return this.rcenterthickness;
	}

	public void setRcenterthickness(Float rcenterthickness)
	{
		this.rcenterthickness = rcenterthickness;
	}

	public Float getLcenterthickness()
	{
		return this.lcenterthickness;
	}

	public void setLcenterthickness(Float lcenterthickness)
	{
		this.lcenterthickness = lcenterthickness;
	}

	public String getRxtreatment()
	{
		return rxtreatment;
	}

	public void setRxtreatment(String rxtreatment)
	{
		this.rxtreatment = rxtreatment;
	}

	public String getFramestyle()
	{
		return framestyle;
	}

	public void setFramestyle(String framestyle)
	{
		this.framestyle = framestyle;
	}

	public String getRefractive()
	{
		return refractive;
	}

	public void setRefractive(String refractive)
	{
		this.refractive = refractive;
	}

	public String getTintlens()
	{
		return tintlens;
	}

	public void setTintlens(String tintlens)
	{
		this.tintlens = tintlens;
	}

	public String getTintcolor()
	{
		return tintcolor;
	}

	public void setTintcolor(String tintcolor)
	{
		this.tintcolor = tintcolor;
	}

	public Float getRmrp()
	{
		return rmrp;
	}

	public void setRmrp(Float rmrp)
	{
		this.rmrp = rmrp;
	}

	public Float getLmrp()
	{
		return lmrp;
	}

	public void setLmrp(Float lmrp)
	{
		this.lmrp = lmrp;
	}

	public Float getRpd()
	{
		return rpd;
	}

	public void setRpd(Float rpd)
	{
		this.rpd = rpd;
	}

	public Float getLpd()
	{
		return lpd;
	}

	public void setLpd(Float lpd)
	{
		this.lpd = lpd;
	}

	public Float getQuantity()
	{
		return quantity;
	}

	public void setQuantity(Float quantity)
	{
		this.quantity = quantity;
	}

	public LensTint getTinttype()
	{
		return tinttype;
	}

	public void setTinttype(LensTint tinttype)
	{
		this.tinttype = tinttype;
	}

	public TintColor getTintcolor1()
	{
		return tintcolor1;
	}

	public void setTintcolor1(TintColor tintcolor1)
	{
		this.tintcolor1 = tintcolor1;
	}

	public TintColor getTintcolor2()
	{
		return tintcolor2;
	}

	public void setTintcolor2(TintColor tintcolor2)
	{
		this.tintcolor2 = tintcolor2;
	}
	public Boolean getUv()
	{
		return uv;
	}

	public void setUv(Boolean uv)
	{
		this.uv = uv;
	}
	public Float getRsegheight()
	{
		return rsegheight;
	}

	public void setRsegheight(Float rsegheight)
	{
		this.rsegheight = rsegheight;
	}

	public Float getLsegheight()
	{
		return lsegheight;
	}

	public void setLsegheight(Float lsegheight)
	{
		this.lsegheight = lsegheight;
	}

	public Float getRsegdrop()
	{
		return rsegdrop;
	}

	public void setRsegdrop(Float rsegdrop)
	{
		this.rsegdrop = rsegdrop;
	}

	public Float getLsegdrop()
	{
		return lsegdrop;
	}

	public void setLsegdrop(Float lsegdrop)
	{
		this.lsegdrop = lsegdrop;
	}

	public Float getRfarpd()
	{
		return rfarpd;
	}

	public void setRfarpd(Float rfarpd)
	{
		this.rfarpd = rfarpd;
	}

	public Float getLfarpd()
	{
		return lfarpd;
	}

	public void setLfarpd(Float lfarpd)
	{
		this.lfarpd = lfarpd;
	}

	public Float getRnearpd()
	{
		return rnearpd;
	}

	public void setRnearpd(Float rnearpd)
	{
		this.rnearpd = rnearpd;
	}

	public Float getLnearpd()
	{
		return lnearpd;
	}

	public void setLnearpd(Float lnearpd)
	{
		this.lnearpd = lnearpd;
	}

	public Float getRochgt()
	{
		return rochgt;
	}

	public void setRochgt(Float rochgt)
	{
		this.rochgt = rochgt;
	}

	public Float getLochgt()
	{
		return lochgt;
	}

	public void setLochgt(Float lochgt)
	{
		this.lochgt = lochgt;
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

	public LensMaterial getMaterial2()
	{
		return material2;
	}

	public void setMaterial2(LensMaterial material2)
	{
		this.material2 = material2;
	}
	
	public LensDesign getLensdesign()
	{
		return lensdesign;
	}

	public void setLensdesign(LensDesign lensdesign)
	{
		this.lensdesign = lensdesign;
	}

	public LensChannel getChannellength()
	{
		return channellength;
	}

	public void setChannellength(LensChannel channellength)
	{
		this.channellength = channellength;
	}

	public Integer getTreat() {
		return treat;
	}

	public void setTreat(Integer treat) {
		this.treat = treat;
	}

	public Float getRbhprism() {
		return rbhprism;
	}

	public void setRbhprism(Float rbhprism) {
		this.rbhprism = rbhprism;
	}

	public Float getLbhprism() {
		return lbhprism;
	}

	public void setLbhprism(Float lbhprism) {
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

	public String getTframe() {
		return tframe;
	}

	public void setTframe(String tframe) {
		this.tframe = tframe;
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
}
