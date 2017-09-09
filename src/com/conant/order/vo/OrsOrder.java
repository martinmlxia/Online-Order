package com.conant.order.vo;

import java.util.Date;

/**
 * ¶©µ¥ΚµΜε΅£
 * @author Administrator
 *
 */
public class OrsOrder implements java.io.Serializable
{
	private Integer id;
	private Integer exist;
	private Integer ordertype;
	private String telephone;
	private Date ordereddate;
	private String referenceno;
	private String courier;
	private String couriercode;
	private String remarks;
	private Boolean emergent;
	private String patient;
	private String tray;
	private Float price;
	private String invoiceid;
	private String patientAddress;
	private String shipmentTrackingNumber;
	private LensDetail lensdetail;
	private FrameDetail framedetail;
	private ProcessInstance processInstance;
	private Couriers couriers;
	private UserInfo user;

	public OrsOrder()
	{
		emergent = new Boolean(false);
	}

	public OrsOrder(Integer ordertype)
	{
		this();
		this.ordertype = ordertype;
	}

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getExist()
	{
		return this.exist;
	}

	public void setExist(Integer exist)
	{
		this.exist = exist;
	}

	public Integer getOrdertype()
	{
		return this.ordertype;
	}

	public void setOrdertype(Integer ordertype)
	{
		this.ordertype = ordertype;
	}

	public String getTelephone()
	{
		return this.telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	public Date getOrdereddate()
	{
		return this.ordereddate;
	}

	public void setOrdereddate(Date ordereddate)
	{
		this.ordereddate = ordereddate;
	}

	public String getReferenceno()
	{
		return this.referenceno;
	}

	public void setReferenceno(String referenceno)
	{
		this.referenceno = referenceno;
	}

	public String getCourier()
	{
		return this.courier;
	}

	public void setCourier(String courier)
	{
		this.courier = courier;
	}

	public String getCouriercode()
	{
		return this.couriercode;
	}

	public void setCouriercode(String couriercode)
	{
		this.couriercode = couriercode;
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

	public LensDetail getLensdetail()
	{
		return this.lensdetail;
	}

	public void setLensdetail(LensDetail lensdetail)
	{
		this.lensdetail = lensdetail;
	}

	public FrameDetail getFramedetail()
	{
		return this.framedetail;
	}

	public void setFramedetail(FrameDetail framedetail)
	{
		this.framedetail = framedetail;
	}

	public ProcessInstance getProcessInstance()
	{
		return processInstance;
	}

	public void setProcessInstance(ProcessInstance processInstance)
	{
		this.processInstance = processInstance;
	}

	public String getPatient()
	{
		return patient;
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
	
	public String getPatientAddress()
	{
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress)
	{
		this.patientAddress = patientAddress;
	}

	public String getShipmentTrackingNumber()
	{
		return shipmentTrackingNumber;
	}

	public void setShipmentTrackingNumber(String shipmentTrackingNumber)
	{
		this.shipmentTrackingNumber = shipmentTrackingNumber;
	}

	public Couriers getCouriers()
	{
		return couriers;
	}

	public void setCouriers(Couriers couriers)
	{
		this.couriers = couriers;
	}
	
	public UserInfo getUser()
	{
		return this.user;
	}

	public void setUser(UserInfo user)
	{
		this.user = user;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(String invoiceid) {
		this.invoiceid = invoiceid;
	}	
}
