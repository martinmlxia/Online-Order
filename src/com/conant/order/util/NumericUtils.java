/**
 * NumericUtils.java
 * 2009-4-8
 * Administrator
 */
package com.conant.order.util;

import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

/**
 * @author Administrator
 *
 */
public class NumericUtils
{
	public static boolean isNumeric(String value)
	{
		// 去掉前导'+'
		String numberValue = StringUtils.trimLeadingCharacter(value, '+');
		return org.apache.commons.lang.NumberUtils.isNumber(numberValue);
	}
	
	public static boolean isInteger(String value)
	{
		return parseInteger(value) != null;
	}
	
	public static Integer parseInteger(String value)
	{
		try
		{
			// 去掉前导'+'
			String numberValue = StringUtils.trimLeadingCharacter(value, '+');
			// 去掉前导'0',0开头的整数是8进制数的特征
			// numberValue = StringUtils.trimLeadingCharacter(numberValue, '0');
			// return (Integer)NumberUtils.parseNumber(numberValue, Integer.class);
			return Integer.valueOf(numberValue);
		}
		catch(Exception exp)
		{
			return null;
		}
	}
	
	public static Integer parseIntegerDefault(String value)
	{
		Integer intValue = parseInteger(value);
		return (intValue == null) ? 0 : intValue;
	}
	
	public static Float parseFloat(String value)
	{
		try
		{
			// 去掉前导'+'
			String numberValue = StringUtils.trimLeadingCharacter(value, '+');
			return (Float)NumberUtils.parseNumber(numberValue, Float.class);
		}
		catch(Exception exp)
		{
			return null;
		}
	}
	
	public static Float parseFloatDefault(String value)
	{
		Float floatValue = parseFloat(value);
		return (floatValue == null) ? 0f : floatValue;
	}
	
	public static Double parseDouble(String value)
	{
		try
		{
			// 去掉前导'+'
			String numberValue = StringUtils.trimLeadingCharacter(value, '+');	
			return (Double)NumberUtils.parseNumber(numberValue, Double.class);
		}
		catch(Exception exp)
		{
			return null;
		}
	}
	
	public static Double parseDoubleDefault(String value)
	{
		Double doubleValue = parseDouble(value);
		return (doubleValue == null) ? 0f : doubleValue;
	}	
}
