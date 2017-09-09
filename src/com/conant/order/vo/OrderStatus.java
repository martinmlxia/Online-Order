/**
 * OrderStatus.java
 * 2008-12-11
 * Administrator
 */
package com.conant.order.vo;

/**
 * 订单状态定义。
 * @author Administrator
 */
public class OrderStatus
{
	public static final int TYPE_START = 1;
	public static final int TYPE_END = 4;
	/**
	 * 审核中。
	 */
	public static final int TYPE_AUDITING = 1;
	/**
	 * 生产中。
	 */
	public static final int TYPE_PRODUCING = 2;
	/**
	 * 已发货。
	 */
	public static final int TYPE_DELIVERED = 3;
	/**
	 * 已完成。
	 */
	public static final int TYPE_COMPLETE = 4;
	@Deprecated
	public static final int TYPE_CHECKING = 4;
}
