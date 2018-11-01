/**
 * 
 */
package com.axonactive.common.util.criteria;

/**
 * @author nvmuon
 *
 */
public class Criteria {

	public static final SearchCriteriaBuilder createSearchCriteriaBuilder() {
		return new SearchCriteriaBuilder();
	}
	
	public static final SearchByCriteriaBuilder createSearchByCriteriaBuilder() {
		return new SearchByCriteriaBuilder();
	}

	public static SortByCriteriaBuilder creatSortByCriteriaBuilder() {
		return new SortByCriteriaBuilder();
	}

	public static RangeCriteriaBuilder createRangeCriteriaBuilder() {
		return new RangeCriteriaBuilder();
	}
}
