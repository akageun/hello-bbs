package kr.geun.o.common.valid.impl;

import kr.geun.o.common.valid.EnumValid;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.TreeSet;

/**
 * Enum Valid
 *
 * @author akageun
 */
public class EnumValidator implements ConstraintValidator<EnumValid, String> {

	private Set<String> targetEnumList = new TreeSet<>();

	@Override
	public void initialize(EnumValid constraintAnnotation) {
		Class<? extends Enum<?>> enumClass = constraintAnnotation.targetEnum();

		Enum[] enumValArr = enumClass.getEnumConstants();
		for (Enum anEnum : enumValArr) {
			targetEnumList.add(StringUtils.upperCase(anEnum.toString()));
		}

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (StringUtils.isBlank(value)) {
			return false;
		}

		return targetEnumList.contains(StringUtils.upperCase(value));
	}
}
