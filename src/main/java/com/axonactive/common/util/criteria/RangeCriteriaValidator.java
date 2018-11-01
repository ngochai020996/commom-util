/**
 * 
 */
package com.axonactive.common.util.criteria;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author nvmuon
 *
 */
public class RangeCriteriaValidator implements ConstraintValidator<ValidRangeCriteria, RangeCriteria>{

	@Override
	public void initialize(ValidRangeCriteria constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(RangeCriteria value, ConstraintValidatorContext context) {
		return RangeCriteria.checkValid(value.getFrom(), value.getTo());
	}

}
