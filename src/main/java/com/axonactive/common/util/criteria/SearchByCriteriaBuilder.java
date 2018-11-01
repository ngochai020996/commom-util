/**
 * 
 */
package com.axonactive.common.util.criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author nvmuon
 *
 */
public class SearchByCriteriaBuilder {

	private List<SearchCriteria> and = null;

	// Support only OR, AND was removed to simplify the search criterias
	private List<SearchByCriteria> or = null;

	SearchByCriteriaBuilder() {
		this.and = new ArrayList<>();
		this.or = new ArrayList<>();
	}

	public SearchByCriteriaBuilder add(SearchCriteria criteria) {
		if (Objects.isNull(criteria)) {
			throw new IllegalArgumentException("criteria is missing or invalid");
		}
		this.and.add(criteria);
		return this;
	}


	public SearchByCriteriaBuilder add(String field, ComparisonExpression expression, Object value) {
		return add(new SearchCriteria(field, expression, value));
	}

	public SearchByCriteriaBuilder or(SearchByCriteria searchBy) {
		if (Objects.isNull(searchBy)) {
			throw new IllegalArgumentException("OR criteria is invalid");
		}

		this.or.add(searchBy);
		return this;
	}

	public SearchByCriteria build() {
		
		
		if (!isValidSearchByCriteria()) {
			throw new IllegalArgumentException("criteria is missing or invalid");
		}
		return  new SearchByCriteria(and, or);
	}
	
	private boolean isValidSearchByCriteria() {
		return (Objects.nonNull(and) && !and.isEmpty())
				|| (Objects.nonNull(or) && !or.isEmpty());
	}

}
