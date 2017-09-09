/**
 * BatchPrintForm.java
 * 2009-4-18
 * Administrator
 */
package com.conant.order.web.form;

/**
 * 批量打印订单form。
 * @author Administrator
 *
 */
public class BatchPrintForm extends OrdersForm
{
	private boolean doubleMode = false;

	public boolean isDoubleMode()
	{
		return doubleMode;
	}

	public void setDoubleMode(boolean doubleMode)
	{
		this.doubleMode = doubleMode;
	}
}
