package com.borzdykooa.callservice.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class PhoneNumberConstraintValidator implements ConstraintValidator<PhoneNumberConstraint, String> {

	private static final String PHONE_PATTERN = "^[+]?[0-9\\-() ]*$";
	private static final String NON_DIGITAL_SYMBOLS = "[\\-() ]";
	private static final int NUMBER_OF_ALL_DIGITS = 14;
	private static final int NUMBER_OF_LOCAL_DIGITS = 9;

	public void initialize(PhoneNumberConstraint constraint) {
	}

	public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
		if (phoneNumber == null) {
			return false;
		}
		Pattern pattern = Pattern.compile(PHONE_PATTERN);
		Matcher matcher = pattern.matcher(phoneNumber);
		if (!matcher.find() || wrongBrackets(phoneNumber)) {
			return false;
		}
		phoneNumber = phoneNumber.replace("+", "00");
		phoneNumber = phoneNumber.replaceAll(NON_DIGITAL_SYMBOLS, "");
		if (numberHasWrongNumberOfDigts(phoneNumber) || numberHasWrongCode(phoneNumber)) {
			return false;
		}

		return true;
	}

	private boolean numberHasWrongCode(String phoneNumber) {
		return phoneNumber.length() == NUMBER_OF_ALL_DIGITS && !phoneNumber.substring(0, 2).equals("00");
	}

	private boolean numberHasWrongNumberOfDigts(String phoneNumber) {
		return phoneNumber.length() != NUMBER_OF_ALL_DIGITS && phoneNumber.length() != NUMBER_OF_LOCAL_DIGITS;
	}

	private boolean wrongBrackets(String phoneNumber) {
		return StringUtils.countOccurrencesOf(phoneNumber, "(") != StringUtils.countOccurrencesOf(phoneNumber, ")");
	}
}
