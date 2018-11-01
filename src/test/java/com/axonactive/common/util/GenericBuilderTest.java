/**
 * 
 */
package com.axonactive.common.util;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

import com.axonactive.common.util.GenericBuilder;

/**
 * @author nvmuon
 *
 */
public class GenericBuilderTest {

	@Test
	public void shouldReturnedPeronWithFullAtrributeValues() {
		Person value = GenericBuilder.of(Person::new)
	            .with(Person::setName, "Tony Teo").with(Person::setAge, 5).build();
		
		assertThat(value.getName(), Is.is("Tony Teo"));
		assertThat(value.getAge(), Is.is(5));
	}
}
