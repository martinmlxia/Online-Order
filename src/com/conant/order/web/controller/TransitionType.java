/**
 * TransitionType.java
 * 2009-1-8
 * Administrator
 */
package com.conant.order.web.controller;

/**
 * 移交方向定义。
 * @author Administrator
 *
 */
public class TransitionType
{
	/**
	 * 无效移交操作。
	 */
	public static final int TD_INVALID = 0;
	/**
	 * 向下移交。
	 */
	public static final int TD_FORWARD = 1;
	/**
	 * 向上移交。
	 */
	public static final int TD_BACKWARD = 2;
	/**
	 * 平行移交。
	 */
	public static final int TD_PARALLEL = 3;
}
