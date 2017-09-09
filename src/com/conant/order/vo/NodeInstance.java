/**
 * NodeInstance.java
 * 2009-1-4
 * Administrator
 */
package com.conant.order.vo;

import java.util.Date;

/**
 * 流程节点实体。
 * @author Administrator
 */
public class NodeInstance implements java.io.Serializable
{
	private Integer id;
	private Integer node;
	private Date enterdate;
	private Date leavedate;
	private String user;
	private String message;
	private ProcessInstance processInstance;

	public NodeInstance()
	{
		
	}
	
	public NodeInstance(Integer node, ProcessInstance processInstance)
	{
		this.node = node;
		this.processInstance = processInstance;
	}
	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getNode()
	{
		return node;
	}

	public void setNode(Integer node)
	{
		this.node = node;
	}
	
	public Date getEnterdate()
	{
		return enterdate;
	}

	public void setEnterdate(Date enterdate)
	{
		this.enterdate = enterdate;
	}	
	
	public Date getLeavedate()
	{
		return leavedate;
	}

	public void setLeavedate(Date leavedate)
	{
		this.leavedate = leavedate;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public ProcessInstance getProcessInstance()
	{
		return processInstance;
	}

	public void setProcessInstance(ProcessInstance processInstance)
	{
		this.processInstance = processInstance;
	}
}
