package com.conant.order.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.conant.order.vo.OrsOrder;

public class OrderValidator implements Validator
{
	private int minWorkdays;
	
	//@Override
	public boolean supports(Class clazz)
	{
		return OrsOrder.class.isAssignableFrom(clazz);
	}

	public int getMinWorkdays()
	{
		return minWorkdays;
	}

	public void setMinWorkdays(int minWorkdays)
	{
		this.minWorkdays = minWorkdays;
	}

	//@Override
	public void validate(Object obj, Errors errors)
	{
		validateOrder((OrsOrder)obj, errors);
	}

	public void validateOrder(OrsOrder order, Errors errors)
	{
		ValidationUtils.rejectIfEmpty(errors, "user.user_id", "required");
		ValidationUtils.rejectIfEmpty(errors, "telephone", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ordereddate", "required");		
	}
}
