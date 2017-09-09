/**
 * HandoverForm.java
 * 2008-12-18
 * Administrator
 */
package com.conant.order.web.form;

import com.conant.order.vo.ProcessInstance;

/**
 * ¶©µ¥ÒÆ½»form¡£
 * @author Administrator
 *
 */
public class HandoverForm
{
	private ProcessInstance processInstance;
	private Integer currentNode;
	private Integer previousNode;
	private Integer nextNode;
	private String message;
	
	public ProcessInstance getProcessInstance()
	{
		return processInstance;
	}
	
	public void setProcessInstance(ProcessInstance processInstance)
	{
		this.processInstance = processInstance;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}

	public Integer getCurrentNode()
	{
		return currentNode;
	}

	public void setCurrentNode(Integer currentNode)
	{
		this.currentNode = currentNode;
	}

	public Integer getPreviousNode()
	{
		return previousNode;
	}

	public void setPreviousNode(Integer previousNode)
	{
		this.previousNode = previousNode;
	}

	public Integer getNextNode()
	{
		return nextNode;
	}

	public void setNextNode(Integer nextNode)
	{
		this.nextNode = nextNode;
	}
}
