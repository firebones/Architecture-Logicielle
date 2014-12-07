package ca.ulaval.glo4003.architecture_logicielle.web.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.EmployeeViewModel;


public class ValidatorEmployee implements Validator{
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@Override
	public boolean supports(Class<?> arg0) {
		return EmployeeViewModel.class.equals(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		EmployeeViewModel employee = (EmployeeViewModel) target;
		
		ValidationUtils.rejectIfEmpty(errors, "name", "field.name.required","Ce champ est obligatoire");
		ValidationUtils.rejectIfEmpty(errors, "email", "field.email.required","Ce champ est obligatoire");
		
		if (employee.getName().length() > 0 && VerifyStringContainsNumbers(employee.getName()))
			errors.rejectValue("name", "field.name.required", "Le nom ne peut contenir de nombres.");
		
		if (employee.getEmail().length() > 0 && !VerifyEmailIsValid(employee.getEmail()))
			errors.rejectValue("email", "field.email.required", "L'adresse courriel n'est pas valide.");	
	}
	
	private Boolean VerifyStringContainsNumbers(String string)
	{
		if(string.matches(".*\\d.*")){
		   return true;
		} else{
		   return false;
		}
	}
	
	private Boolean VerifyEmailIsValid(String email)
	{
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
