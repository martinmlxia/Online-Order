/**
 * Couriers.java
 * 2009-6-14
 * Administrator
 */
package com.conant.order.vo;

/**
 * 快递公司实体。
 * @author Administrator
 * 
 */
public class Couriers
{
	private int id;
	private String couriersNo;
	private String name;
	private String homepage;
	private String trackingUrl;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getCouriersNo()
	{
		return couriersNo;
	}

	public void setCouriersNo(String couriersNo)
	{
		this.couriersNo = couriersNo;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getHomepage()
	{
		return homepage;
	}

	public void setHomepage(String homepage)
	{
		this.homepage = homepage;
	}

	public String getTrackingUrl()
	{
		return trackingUrl;
	}

	public void setTrackingUrl(String trackingUrl)
	{
		this.trackingUrl = trackingUrl;
	}
}
