/**
 * 
 */
package com.axonactive.common.util.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.axonactive.common.util.criteria.Criteria;
import com.axonactive.common.util.criteria.OrderByCriteria;
import com.axonactive.common.util.criteria.OrderCriteria;
import com.axonactive.common.util.criteria.RangeCriteria;
import com.axonactive.common.util.criteria.SearchByCriteria;
import com.axonactive.common.util.criteria.SearchCriteria;

/**
 * @author nvmuon
 *
 */
public abstract class AbstractSearcher<E> {

	protected SearchByCriteria searchBy;

	protected OrderByCriteria orderBy;

	protected RangeCriteria range;

	public abstract List<E> execute();

	public AbstractSearcher<E> where(SearchByCriteria searchBy) {
		this.searchBy = searchBy;
		return this;
	}

	public AbstractSearcher<E> where(SearchCriteria criteria) {
		if (Objects.isNull(this.searchBy)) {
			this.searchBy = new SearchByCriteria();
			this.searchBy.setCriteria(new ArrayList<>());
		}
		this.searchBy.getCriteria().add(criteria);
		return this;
	}

	public AbstractSearcher<E> and(SearchCriteria criteria) {
		if (Objects.isNull(this.searchBy)) {
			this.searchBy = new SearchByCriteria();
		}
		this.searchBy.addAnd(criteria);
		return this;
	}

	public AbstractSearcher<E> or(SearchCriteria criteria) {
		if (Objects.isNull(this.searchBy)) {
			this.searchBy = new SearchByCriteria();
		}
		this.searchBy.addOr(criteria);
		return this;
	}

	public  AbstractSearcher<E> orderBy(OrderCriteria criteria) {
		if (Objects.isNull(this.orderBy)) {
			this.orderBy = new OrderByCriteria();
		}
		if (Objects.nonNull(criteria)) {
			this.orderBy.addSortCriteria(criteria);
		}
		return this;
	}
	
	public  AbstractSearcher<E> orderBy(OrderByCriteria orderBy) {
		this.orderBy = orderBy;
		return this;
	}
	
	public  AbstractSearcher<E> range(RangeCriteria range) {
		this.range = range;
		return this;
	} 

	public  AbstractSearcher<E> range(int from, int to) {
		this.range = Criteria.createRangeCriteriaBuilder().from(from).to(to).build();
		return this;
	}

	public AbstractSearcher<E> max(int max) {
		if (max <= 0) {
			throw new IllegalArgumentException("the value must be greater than 0");
		}
		this.range = Criteria.createRangeCriteriaBuilder().from(0).to(max).build();
		return this;
	}
}
