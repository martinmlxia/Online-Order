/**
 * ProcessLog.java
 * 2009-1-3
 * Administrator
 */
package com.conant.order.vo;

import java.util.Date;

/**
 * 流程移交记录实体。
 * @author Administrator
 * 
 */
public class ProcessLog implements java.io.Serializable
{
	private Integer id;
	private Integer node;
	private Date processdate;
	private String user;
	private String message;
	private ProcessInstance processInstance;

	public ProcessLog()
	{
		
	}
	
	public ProcessLog(Integer node, ProcessInstance processInstance)
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
	
	public Date getProcessdate()
	{
		return processdate;
	}

	public void setProcessdate(Date processdate)
	{
		this.processdate = processdate;
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
