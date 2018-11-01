/**
 * 
 */
package com.axonactive.common.util.search.predicate;

import java.util.function.Predicate;

import com.axonactive.common.util.criteria.SearchByCriteria;

/**
 * @author nvmuon
 *
 */
public abstract class AbstractPredicateBuilder<E> {

	protected abstract Predicate<E> build(SearchByCriteria criteria);
}
