/**
 * OrderStatus.java
 * 2008-12-11
 * Administrator
 */
package com.conant.order.vo;

/**
 * ����״̬���塣
 * @author Administrator
 */
public class OrderStatus
{
	public static final int TYPE_START = 1;
	public static final int TYPE_END = 4;
	/**
	 * ����С�
	 */
	public static final int TYPE_AUDITING = 1;
	/**
	 * �����С�
	 */
	public static final int TYPE_PRODUCING = 2;
	/**
	 * �ѷ�����
	 */
	public static final int TYPE_DELIVERED = 3;
	/**
	 * ����ɡ�
	 */
	public static final int TYPE_COMPLETE = 4;
	@Deprecated
	public static final int TYPE_CHECKING = 4;
}
