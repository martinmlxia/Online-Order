package com.conant.order.web.validator;

import org.springframework.validation.Errors;

import com.conant.order.vo.OrsOrder;

public class LensOrderValidator extends OrderValidator
{
	public void validate(Object obj, Errors errors)
	{
		super.validate(obj, errors);
		validateLensDetail((OrsOrder)obj, errors);
	}

	public void validateLensDetail(OrsOrder order, Errors errors)
	{
		//ValidationUtils.rejectIfEmpty(errors, "lensdetail.lensmodel", "required");
		//ValidationUtils.rejectIfEmpty(errors, "lensdetail.lensmodel.id", "required");
		//ValidationUtils.rejectIfEmpty(errors, "lensdetail.diameter", "required");
		//ValidationUtils.rejectIfEmpty(errors, "lensdetail.rsphere", "required");
		//ValidationUtils.rejectIfEmpty(errors, "lensdetail.lsphere", "required");
		//ValidationUtils.rejectIfEmpty(errors, "lensdetail.rcylinder", "required");
		//ValidationUtils.rejectIfEmpty(errors, "lensdetail.lcylinder", "required");
	}
}
