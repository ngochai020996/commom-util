/**
 * 
 */
package com.axonactive.common.util.criteria;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author nvmuon
 *
 */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SearchCriteriaBuilder {

	private String field = null;

	private Object value = null;

	private ComparisonExpression expression = null;


	public SearchCriteriaBuilder searchBy(String searchByField) {
		this.field = searchByField;
		return this;
	}

	public SearchCriteriaBuilder eq(Object value) {
		this.expression = ComparisonExpression.EQUAL;
		this.value = value;
		return this;
	}

	public SearchCriteriaBuilder like(String value) {
		this.expression = ComparisonExpression.LIKE;
		this.value = value;
		return this;
	}

	public SearchCriteriaBuilder notEq(Object value) {
		this.expression = ComparisonExpression.NOT_EQUAL;
		this.value = value;
		return this;
	}

	public SearchCriteriaBuilder lessThan(Object value) {
		this.expression = ComparisonExpression.LESS_THAN;
		this.value = value;
		return this;
	}

	public SearchCriteriaBuilder lessThanOrEqual(Object value) {
		this.expression = ComparisonExpression.LESS_THAN_OR_EQUAL;
		this.value = value;
		return this;
	}

	public SearchCriteriaBuilder greaterThan(Object value) {
		this.expression = ComparisonExpression.GREATER_THAN;
		this.value = value;
		return this;
	}

	public SearchCriteriaBuilder greaterThanOrEqual(Object value) {
		this.expression = ComparisonExpression.GREATER_THAN_OR_EQUAL;
		this.value = value;
		return this;
	}

	public SearchCriteria build() {
		if (!isValidSearchCriteria()) {
			throw new IllegalArgumentException("field, expression or value is missing");
		}

		return new SearchCriteria(field, expression, value);
	}
	
	private boolean isValidSearchCriteria() {
		return Objects.nonNull(field) 
				&& Objects.nonNull(expression)
				&& Objects.nonNull(value);
	}
}
