package com.borzdykooa.callservice.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PhoneNumberConstraintValidatorTest {

	private PhoneNumberConstraintValidator validator = new PhoneNumberConstraintValidator();

	@Test
	public void isValid() {
		assertTrue(validator.isValid("+(420) 111 222 333", null));
	}

	@Test
	public void isValid2() {
		assertTrue(validator.isValid("+(420)-111222333", null));
	}

	@Test
	public void isValid3() {
		assertTrue(validator.isValid("+420111222333", null));
	}

	@Test
	public void isValid4() {
		assertTrue(validator.isValid("00420111222333", null));
	}

	@Test
	public void isValid5() {
		assertTrue(validator.isValid("(111) 222 (333)", null));
	}

	@Test
	public void isValid6() {
		assertTrue(validator.isValid("123456789", null));
	}

	@Test
	public void isInvalid() {
		assertFalse(validator.isValid("420 111 222 333", null));
	}

	@Test
	public void isInvalid2() {
		assertFalse(validator.isValid("-+(420)-111222333", null));
	}

	@Test
	public void isInvalid3() {
		assertFalse(validator.isValid("+420111222", null));
	}
}
