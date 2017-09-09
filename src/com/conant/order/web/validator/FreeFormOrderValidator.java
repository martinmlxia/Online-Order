/**
 * FrameLensOrderValidator.java
 * 2009-2-26
 * Administrator
 */
package com.conant.order.web.validator;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.conant.order.util.Logger;
import com.conant.order.util.NumericUtils;
import com.conant.order.vo.PrismDirectionType;
import com.conant.order.web.form.FrameLensOrder;

/**
 * FreeForm订单验证器。内部使用的验证参数范围值在order-config.xml文件中配置。
 * @author Administrator
 *
 */
public class FreeFormOrderValidator implements Validator
{
	private static final Logger log = Logger.getLogger(
			"FreeFormOrderValidator", Logger.DEBUG, true);

	private Float minSphere;
	private Float maxSphere;
	private Float minCylinder;
	private Float maxCylinder;
	private Integer minAxis;
	private Integer maxAxis;
	private Float minPrism;
	private Float maxPrism;
	
	//@Override
	public boolean supports(Class clazz)
	{
		return FrameLensOrder.class.isAssignableFrom(clazz);
	}

	public Float getMinSphere()
	{
		return minSphere;
	}

	public void setMinSphere(Float minSphere)
	{
		this.minSphere = minSphere;
	}

	public Float getMaxSphere()
	{
		return maxSphere;
	}

	public void setMaxSphere(Float maxSphere)
	{
		this.maxSphere = maxSphere;
	}

	public Float getMinCylinder()
	{
		return minCylinder;
	}

	public void setMinCylinder(Float minCylinder)
	{
		this.minCylinder = minCylinder;
	}

	public Float getMaxCylinder()
	{
		return maxCylinder;
	}

	public void setMaxCylinder(Float maxCylinder)
	{
		this.maxCylinder = maxCylinder;
	}
	
	public Integer getMinAxis()
	{
		return minAxis;
	}

	public void setMinAxis(Integer minAxis)
	{
		this.minAxis = minAxis;
	}

	public Integer getMaxAxis()
	{
		return maxAxis;
	}

	public void setMaxAxis(Integer maxAxis)
	{
		this.maxAxis = maxAxis;
	}

	public Float getMinPrism()
	{
		return minPrism;
	}

	public void setMinPrism(Float minPrism)
	{
		this.minPrism = minPrism;
	}

	public Float getMaxPrism()
	{
		return maxPrism;
	}

	public void setMaxPrism(Float maxPrism)
	{
		this.maxPrism = maxPrism;
	}

	private boolean validateNumeric(String field, String value, Errors errors)
	{
		if(StringUtils.hasLength(value))
		{
			if(!NumericUtils.isNumeric(value))
			{
				errors.rejectValue(field, "typeMismatch");
				return false;
			}
		}
		return true;
	}
	
	private boolean validateInteger(String field, String value, Errors errors)
	{
		if(StringUtils.hasLength(value))
		{
			if(!NumericUtils.isInteger(value))
			{
				errors.rejectValue(field, "typeMismatch");
				return false;
			}
		}
		return true;
	}
	
	private boolean validateEmpty(String field, String value, Errors errors)
	{
		if(!StringUtils.hasLength(value))
		{
			errors.rejectValue(field, "required");
			return false;
		}
		return true;
	}
	
	/**
	 * 验证底向字段值是否有效。
	 * @param field 字段名称
	 * @param value 字段值
	 * @param errors 错误对象
	 * @return 验证结果
	 */
	private boolean validatePrismDirection(String field, Integer value, Errors errors)
	{
		if(value == null || value < PrismDirectionType.TYPE_START || value > PrismDirectionType.TYPE_END)
		{
			errors.rejectValue(field, "required");
			return false;
		}
		return true;
	}
	
	/**
	 * 验证材料字段值是否有效。
	 * @param order 订单对象
	 * @param errors 错误对象
	 * @return 验证结果
	 */
	private boolean validateMaterial(FrameLensOrder order, Errors errors)
	{
		// material和material2不能都为空
		if(!StringUtils.hasLength(order.getMaterial()) 
				&& !StringUtils.hasLength(order.getMaterial2()))
		{
			errors.rejectValue("material", "required");
			return false;
		}
		// material和material2不能都为None
		if(StringUtils.hasLength(order.getMaterial()) 
				&& StringUtils.hasLength(order.getMaterial2())
				&& order.getMaterial().equals("None")
				&& order.getMaterial2().equals("None"))
		{
			errors.rejectValue("material", "required");
			return false;
		}
		return true;
	}
	
	/**
	 * 验证镜片类型是否有效。
	 * @param order 订单对象
	 * @param errors 错误对象
	 * @return 验证结果
	 */
	private boolean validateDesign(FrameLensOrder order, Errors errors)
	{
		// lensdesign和channellength不能为空
		if(!StringUtils.hasLength(order.getLensdesign()))
		{
			errors.rejectValue("lensdesign", "required");
			return false;
		}
		if(!StringUtils.hasLength(order.getChannellength()))
		{
			errors.rejectValue("channellength", "required");
			return false;
		}
		return true;
	}
	
	/**
	 * 验证柱镜字段值是否有效。
	 * @param field 字段名称
	 * @param value 字段值
	 * @param errors 错误对象
	 * @return 验证结果
	 */
	private boolean validateCylinder(String field, String value, Errors errors)
	{
		if(validateNumeric(field, value, errors))
		{
			if(StringUtils.hasLength(value))
			{
				Float floatValue = Float.valueOf(value);
				// 小于minCylinder，无效值
				if(floatValue.compareTo(minCylinder) < 0)
				{
					errors.rejectValue(field, "invalid");
					return false;
				}
				// 大于maxCylinder，需要确认方可
				if(floatValue.compareTo(maxCylinder) > 0)
				{
					errors.rejectValue(field, "confirm");
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 验证球镜值是否有效。
	 * @param field 字段名称
	 * @param value 字段值
	 * @param errors 错误对象
	 * @return 验证结果
	 */
	private boolean validateSphere(String field, String value, Errors errors)
	{
		if(validateNumeric(field, value, errors))
		{
			if(StringUtils.hasLength(value))
			{
				Float floatValue = Float.valueOf(value);
				// 小于minSphere，无效值
				if(floatValue.compareTo(minSphere) < 0)
				{
					errors.rejectValue(field, "invalid");
					return false;
				}
				// 大于maxSphere，需要确认方可
				if(floatValue.compareTo(maxSphere) > 0)
				{
					errors.rejectValue(field, "confirm");
					return false;
				}
			}
			return true;
		}
		return false;		
	}
	
	/**
	 * 验证轴位值是否有效。
	 * @param field 字段名称
	 * @param value 字段值
	 * @param errors 错误对象
	 * @return 验证结果
	 */	
	private boolean validateAxis(String field, String value, Errors errors)
	{
		if(validateInteger(field, value, errors))
		{
			if(StringUtils.hasLength(value))
			{
				Integer intValue = Integer.valueOf(value);
				// 小于minAxis或大于maxAxis，无效值
				if(intValue.compareTo(minAxis) < 0 || intValue.compareTo(maxAxis) > 0)
				{
					errors.rejectValue(field, "invalid");
					return false;
				}
			}
			return true;
		}
		return false;		
	}
	
	/**
	 * 验证棱镜值是否有效。
	 * @param field 字段名称
	 * @param value 字段值
	 * @param errors 错误对象
	 * @return 验证结果
	 */	
	private boolean validatePrism(String field, String value, Errors errors)
	{
		if(validateNumeric(field, value, errors))
		{
			if(StringUtils.hasLength(value))
			{
				Float floatValue = Float.valueOf(value);
				// 小于minPrism或大于maxPrism，无效值
				if(floatValue.compareTo(minPrism) < 0 || floatValue.compareTo(maxPrism) > 0)
				{
					errors.rejectValue(field, "invalid");
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 验证镜架、镜片尺寸是否有效。
	 * @param order 订单对象
	 * @param errors 错误对象
	 * @return 验证结果
	 */		
	private boolean validateFrameSize(FrameLensOrder order, Errors errors)
	{
		// "水平宽度，垂直长度，有效直径，鼻梁距"和"镜片直径"这两项至少要填写一项
		boolean diameterExist = StringUtils.hasLength(order.getDiameter());
		boolean frameSizeExist = StringUtils.hasLength(order.getRboxasize());
		frameSizeExist |= StringUtils.hasLength(order.getRboxbsize());
		frameSizeExist |= StringUtils.hasLength(order.getDbl());
		frameSizeExist |= StringUtils.hasLength(order.getEd());
		if(!diameterExist && !frameSizeExist)
		{
			errors.reject("framesize.required");
			return false;
		}
		return true;
	}
	
	//@Override
	public void validate(Object target, Errors errors)
	{
		FrameLensOrder order = (FrameLensOrder)target;
		// rsphere
		validateSphere("rsphere", order.getRsphere(), errors);
		// lsphere
		validateSphere("lsphere", order.getLsphere(), errors);
		// 填了右柱镜必须要填右轴位
		// raxis
		validateAxis("raxis", order.getRaxis(), errors);
		// rcylinder
		if(validateCylinder("rcylinder", order.getRcylinder(), errors))
		{
			if(StringUtils.hasLength(order.getRcylinder()))
			{
				validateEmpty("raxis", order.getRaxis(), errors);
			}
		}
		// 填了左柱镜必须要填左轴位
		// laxis
		validateAxis("laxis", order.getLaxis(), errors);
		// lcylinder
		if(validateCylinder("lcylinder", order.getLcylinder(), errors))
		{
			if(StringUtils.hasLength(order.getLcylinder()))
			{
				validateEmpty("laxis", order.getLaxis(), errors);
			}
		}
		// radd
		validateNumeric("radd", order.getRadd(), errors);
		// ladd
		validateNumeric("ladd", order.getLadd(), errors);
		// 填了右棱镜必须要选右底向
		// rhprism
		if(validateNumeric("rhprism", order.getRhprism(), errors))
		{
			if(StringUtils.hasLength(order.getRhprism()))
			{
				validatePrismDirection("rhpd", order.getRhpd(), errors);
			}
		}
		// 填了左棱镜必须要选左底向
		// lhprism
		if(validateNumeric("lhprism", order.getLhprism(), errors))
		{
			if(StringUtils.hasLength(order.getLhprism()))
			{
				validatePrismDirection("lhpd", order.getLhpd(), errors);
			}
		}
		// rbasecurve
		validateInteger("rbasecurve", order.getRbasecurve(), errors);
		// lbasecurve
		validateInteger("lbasecurve", order.getLbasecurve(), errors);
		// rsegheight
		validateNumeric("rsegheight", order.getRsegheight(), errors);
		// lsegheight
		validateNumeric("lsegheight", order.getLsegheight(), errors);
		// rfarpd
		validateNumeric("rfarpd", order.getRfarpd(), errors);
		// lfarpd
		validateNumeric("lfarpd", order.getLfarpd(), errors);
		// rnearpd
		validateNumeric("rnearpd", order.getRnearpd(), errors);
		// lnearpd
		validateNumeric("lnearpd", order.getLnearpd(), errors);
		// rochgt
		validateNumeric("rochgt", order.getRochgt(), errors);
		// lochgt
		validateNumeric("lochgt", order.getLochgt(), errors);
		// 镜片材料至少填写一个
		// material material2
		validateMaterial(order, errors);
		// lensdesign
		validateDesign(order, errors);
		// tinttype
		validateEmpty("tinttype", order.getTinttype(), errors);
		// tintcolor1
		// tintcolor2
		// ar
		// src
		// uv
		// diameter
		validateNumeric("diameter", order.getDiameter(), errors);
		// rboxasize
		validateNumeric("rboxasize", order.getRboxasize(), errors);
		// rboxbsize
		validateInteger("rboxbsize", order.getRboxbsize(), errors);
		// dbl
		validateNumeric("dbl", order.getDbl(), errors);
		// ed
		validateNumeric("ed", order.getEd(), errors);
		// 镜架、镜片尺寸
		validateFrameSize(order, errors);
	}
}
