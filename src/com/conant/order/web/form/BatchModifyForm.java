/**
 * BatchModifyForm.java
 * 2009-2-12
 * Administrator
 */
package com.conant.order.web.form;

/**
 * �����޸Ķ���form��
 * @author Administrator
 *
 */
public class BatchModifyForm extends OrdersForm
{
	private boolean emergent = false;

	public boolean isEmergent()
	{
		return emergent;
	}

	public void setEmergent(boolean emergent)
	{
		this.emergent = emergent;
	}
}
