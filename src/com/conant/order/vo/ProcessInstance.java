/**
 * ProcessInstance.java
 * 2009-1-3
 * Administrator
 */
package com.conant.order.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ����ʵ�塣
 * @author Administrator
 *
 */
public class ProcessInstance implements java.io.Serializable
{
	private Integer id;
	private Integer token;
	private Boolean withdrawal;
	private Set<ProcessLog> processLogs = new HashSet<ProcessLog>(0);
	private Set<NodeInstance> nodeInstances = new HashSet<NodeInstance>(0);
	private Set<TokenVariable> tokenVariables = new HashSet<TokenVariable>(0);

	public ProcessInstance()
	{
		// �����˻ر�־
		this.withdrawal = Boolean.FALSE;
	}
	
	public void initializeNodeInstances()
	{
		// ������нڵ�
		// �׸����̽ڵ㣬����enter date
		NodeInstance startNode = new NodeInstance(OrderStatus.TYPE_AUDITING, this);
		startNode.setEnterdate(new Date());
		nodeInstances.add(startNode);
		nodeInstances.add(new NodeInstance(OrderStatus.TYPE_PRODUCING, this));
		nodeInstances.add(new NodeInstance(OrderStatus.TYPE_DELIVERED, this));
		//nodeInstances.add(new NodeInstance(OrderStatus.TYPE_CHECKING, this));
		nodeInstances.add(new NodeInstance(OrderStatus.TYPE_COMPLETE, this));
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public Integer getToken()
	{
		return token;
	}
	
	public void setToken(Integer token)
	{
		this.token = token;
	}
	
	public Boolean getWithdrawal()
	{
		return withdrawal;
	}
	
	public void setWithdrawal(Boolean withdrawal)
	{
		this.withdrawal = withdrawal;
	}
	
	public Set<ProcessLog> getProcessLogs()
	{
		return processLogs;
	}

	public void setProcessLogs(Set<ProcessLog> processLogs)
	{
		this.processLogs = processLogs;
	}
	
	public Set<NodeInstance> getNodeInstances()
	{
		return nodeInstances;
	}

	public void setNodeInstances(Set<NodeInstance> nodeInstances)
	{
		this.nodeInstances = nodeInstances;
	}

	public Set<TokenVariable> getTokenVariables()
	{
		return tokenVariables;
	}

	public void setTokenVariables(Set<TokenVariable> tokenVariables)
	{
		this.tokenVariables = tokenVariables;
	}
}
