package ca.ulaval.glo4003.architecture_logicielle.web.controllers;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.EmployeeViewModel;





public class ValidatorEmployee implements Validator{
	
	@Override
	public boolean supports(Class<?> arg0) {
		return EmployeeViewModel.class.equals(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "name", "field.name.required","The field is required");
		ValidationUtils.rejectIfEmpty(errors, "email", "field.email.required","The field is required");
		ValidationUtils.rejectIfEmpty(errors, "role", "field.role.required","The field is required");
		
	}


}
