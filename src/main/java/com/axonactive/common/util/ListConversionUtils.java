/**
 * 
 */
package com.axonactive.common.util;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author nvmuon
 *
 */
public class ListConversionUtils {
	
	private ListConversionUtils() {}

	
	public static <U, V> List<U> convert(List<V> collection, Function<V, U> convertFunction) {
		return Optional.ofNullable(collection).map(thiz -> {
			return thiz.stream().map(convertFunction).collect(Collectors.toList());
		}).orElse(null);
	}
	
	public static <U, V> List<U> convertAndReturnEmptyIfGivenListIsNull(List<V> collection, Function<V, U> convertFunction) {
		return Optional.ofNullable(collection).map(thiz -> {
			return thiz.stream().map(convertFunction).collect(Collectors.toList());
		}).orElse(Collections.emptyList());
	}
}
