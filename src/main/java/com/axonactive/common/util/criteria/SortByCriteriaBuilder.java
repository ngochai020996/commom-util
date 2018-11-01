/**
 * 
 */
package com.axonactive.common.util.criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author nvmuon
 *
 */

public class SortByCriteriaBuilder {

	private List<OrderCriteria> order = null;


	SortByCriteriaBuilder() {
		this.order = new ArrayList<>();
	}

	public SortByCriteriaBuilder add(OrderCriteria sortCriteria) {
		if (Objects.isNull(sortCriteria) || !Objects.nonNull(sortCriteria.getField())) {
			throw new IllegalArgumentException("sort criteria is missing or invalid");
		}

		boolean hasConFlictCriteria = this.order.stream().anyMatch(each -> (
				StringUtils.equalsIgnoreCase(each.getField(), sortCriteria.getField())
				&& each.isDescending() != sortCriteria.isDescending()));
		if (hasConFlictCriteria) {
			throw new IllegalArgumentException("the sort criteria is conflicted with existing one from the list");
		}

		this.order.add(sortCriteria);
		return this;
	}

	public SortByCriteriaBuilder add(String field, boolean descending) {
		return add(new OrderCriteria(field, descending));
	}

	public SortByCriteriaBuilder addDescending(String field) {
		return add(new OrderCriteria(field, true));
	}

	public SortByCriteriaBuilder addAscending(String field) {
		return add(new OrderCriteria(field, false));
	}

	public OrderByCriteria build() {

		if (CollectionUtils.isEmpty(order)) {
			throw new IllegalArgumentException("the sort criteria is invalid");
		}

		return new OrderByCriteria(order);
	}
}
