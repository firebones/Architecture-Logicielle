package ca.ulaval.glo4003.architecture_logicielle.web.controllers;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ca.ulaval.glo4003.architecture_logicielle.model.Employee;



public class ValidatorEmployee implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Employee.class.equals(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "lastName", "field.lastName.required","The field is required");
		ValidationUtils.rejectIfEmpty(errors, "firstName", "field.firstName.required","The field is required");
		ValidationUtils.rejectIfEmpty(errors, "email", "field.email.required","The field is required");
		ValidationUtils.rejectIfEmpty(errors, "address", "field.address.required","The field is required");

	}

}
