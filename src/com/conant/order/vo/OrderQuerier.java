/**
 * OrderQuerier.java
 * 2008-11-22
 * Administrator
 */
package com.conant.order.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

/**
 * 订单查询对象。
 * @author Administrator
 * 
 */
public class OrderQuerier implements Serializable
{
	private Integer id;
	private Boolean exist;
	private Integer ordertype;
	private Integer orderstatus;
	private Integer start_orderstatus;
	private Integer end_orderstatus;
	private Integer[] listOrderstatus;
	private String loginUser;
	private String username;
	private String salesowner;
	private String productowner;
	private String telephone;
	private Date ordereddate;
	private Date start_ordereddate;
	private Date end_ordereddate;
	private String requesteddate;
	private String remarks;
	private String[] ids;
	private Integer[] orderIds;
	private Order[] orders;
	private Integer pageNo;
	private Integer pageSize;
	private Integer pageCount;
	private Integer startIndex;
	private List<OrsOrder> listOrder;
	private Integer recordCount;
	private boolean filterAuditedOrders;
	private String patient;
	private String tray;
	private Date start_delivereddate;
	private Date end_delivereddate;	

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Boolean isExist()
	{
		return exist;
	}

	public void setExist(Boolean exist)
	{
		this.exist = exist;
	}

	public Integer getOrdertype()
	{
		return ordertype;
	}

	public void setOrdertype(Integer ordertype)
	{
		this.ordertype = ordertype;
	}

	public Integer getOrderstatus()
	{
		return orderstatus;
	}

	public void setOrderstatus(Integer orderstatus)
	{
		this.orderstatus = orderstatus;
	}
	public Integer getStart_orderstatus()
	{
		return start_orderstatus;
	}

	public void setStart_orderstatus(Integer start_orderstatus)
	{
		this.start_orderstatus = start_orderstatus;
	}

	public Integer getEnd_orderstatus()
	{
		return end_orderstatus;
	}

	public void setEnd_orderstatus(Integer end_orderstatus)
	{
		this.end_orderstatus = end_orderstatus;
	}

	public Integer[] getListOrderstatus()
	{
		return listOrderstatus;
	}

	public void setListOrderstatus(Integer[] listOrderstatus)
	{
		this.listOrderstatus = listOrderstatus;
	}

	public String getLoginUser()
	{
		return loginUser;
	}

	public void setLoginUser(String loginUser)
	{
		this.loginUser = loginUser;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getSalesowner()
	{
		return salesowner;
	}

	public void setSalesowner(String salesowner)
	{
		this.salesowner = salesowner;
	}

	public String getTelephone()
	{
		return telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	public Date getOrdereddate()
	{
		return ordereddate;
	}

	public void setOrdereddate(Date ordereddate)
	{
		this.ordereddate = ordereddate;
	}
	
	public Date getStart_ordereddate()
	{
		return start_ordereddate;
	}

	public void setStart_ordereddate(Date start_ordereddate)
	{
		this.start_ordereddate = start_ordereddate;
	}

	public Date getEnd_ordereddate()
	{
		return end_ordereddate;
	}

	public void setEnd_ordereddate(Date end_ordereddate)
	{
		this.end_ordereddate = end_ordereddate;
	}	

	public String getRequesteddate()
	{
		return requesteddate;
	}

	public void setRequesteddate(String requesteddate)
	{
		this.requesteddate = requesteddate;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public Order[] getOrders()
	{
		return orders;
	}

	public void setOrders(Order[] orders)
	{
		this.orders = orders;
	}

	public Integer getPageNo()
	{
		return pageNo;
	}

	public void setPageNo(Integer pageNo)
	{
		this.pageNo = pageNo;
	}

	public Integer getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	public Integer getPageCount()
	{
		return pageCount;
	}

	public void setPageCount(Integer pageCount)
	{
		this.pageCount = pageCount;
	}

	public Integer getStartIndex()
	{
		return startIndex;
	}

	public void setStartIndex(Integer startIndex)
	{
		this.startIndex = startIndex;
	}
	
	public String[] getIds()
	{
		return ids;
	}

	public void setIds(String[] ids)
	{
		this.ids = ids;
	}

	public Integer[] getOrderIds()
	{
		return orderIds;
	}

	public void setOrderIds(Integer[] orderIds)
	{
		this.orderIds = orderIds;
	}
	
	public List<OrsOrder> getListOrder()
	{
		return listOrder;
	}

	public void setListOrder(List<OrsOrder> listOrder)
	{
		this.listOrder = listOrder;
	}

	public Integer getRecordCount()
	{
		return recordCount;
	}

	public void setRecordCount(Integer recordCount)
	{
		this.recordCount = recordCount;
	}

	public boolean isFilterAuditedOrders()
	{
		return filterAuditedOrders;
	}

	public void setFilterAuditedOrders(boolean filterAuditedOrders)
	{
		this.filterAuditedOrders = filterAuditedOrders;
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
	
	public Date getStart_delivereddate()
	{
		return start_delivereddate;
	}

	public void setStart_delivereddate(Date start_delivereddate)
	{
		this.start_delivereddate = start_delivereddate;
	}

	public Date getEnd_delivereddate()
	{
		return end_delivereddate;
	}

	public void setEnd_delivereddate(Date end_delivereddate)
	{
		this.end_delivereddate = end_delivereddate;
	}

	public String getProductowner() {
		return productowner;
	}

	public void setProductowner(String productowner) {
		this.productowner = productowner;
	}	
}
