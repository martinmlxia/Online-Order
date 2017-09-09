/**
 * TokenVariableMap.java
 * 2009-7-19
 * Administrator
 */
package com.conant.order.vo;


/**
 * @author Administrator
 * 
 */
public class TokenVariable implements java.io.Serializable
{
	private Integer id;
	private Integer token;
	private String name;
	private String stringvalue;
	private ProcessInstance processInstance;

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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getStringvalue()
	{
		return stringvalue;
	}

	public void setStringvalue(String stringvalue)
	{
		this.stringvalue = stringvalue;
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
