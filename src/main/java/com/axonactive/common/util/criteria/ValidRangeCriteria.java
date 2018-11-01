/**
 * 
 */
package com.axonactive.common.util.criteria;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Retention(RUNTIME)
@Target({ TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, TYPE_USE })
@Constraint(validatedBy = RangeCriteriaValidator.class)
public @interface ValidRangeCriteria {
	
	String message() default "{common.until.predicate.constraints.ValidRangeCriteria.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
