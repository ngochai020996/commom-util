/**
 * 
 */
package com.axonactive.common.util.cache;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author nvmuon
 *
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface CacheName {

	String value();
}
