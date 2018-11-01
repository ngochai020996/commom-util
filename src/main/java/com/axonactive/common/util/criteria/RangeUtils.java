/**
 * 
 */
package com.axonactive.common.util.criteria;

import java.util.List;

/**
 * @author nvmuon
 *
 */
public class RangeUtils {

	
	public static <T> List<T> filter(List<T> list, RangeCriteria range) {
		if (range == null) {
			return list;
		}
		if (list == null || list.isEmpty()) {
			return list;
		}

		Integer from = (range.getFrom() == null)? 0 : range.getFrom();
		Integer to = (range.getTo() == null || range.getTo() > list.size())? list.size() : range.getTo();

		return list.subList(from, to);
	}
}
