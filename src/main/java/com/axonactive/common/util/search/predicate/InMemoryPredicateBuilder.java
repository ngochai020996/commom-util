/**
 * 
 */
package com.axonactive.common.util.search.predicate;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import com.axonactive.common.util.criteria.ComparisonExpressionEvaluator;
import com.axonactive.common.util.criteria.SearchByCriteria;
import com.axonactive.common.util.criteria.SearchCriteria;

/**
 * @author nvmuon
 *
 */
public class InMemoryPredicateBuilder<E> extends AbstractPredicateBuilder<E>{

	private InMemoryPredicateBuilder() {
	}

	public static final <E> InMemoryPredicateBuilder<E> createBuilder() {
		return new InMemoryPredicateBuilder<>();
	}

	public static final <E> Predicate<E> buildAlwaysRightPredicate() {
		return obj -> true;
	}

	public Predicate<E> build(SearchByCriteria criteria) {

		Predicate<E> predicate = null;

		if (SearchByCriteria.hasCriteria(criteria)) {
			predicate = buildAnd(criteria.getCriteria());
			buildOr(predicate, criteria.getOr());
		}
		return predicate;
	}


	private Predicate<E> buildAnd(List<SearchCriteria> andCriteria) {
		Predicate<E> predicate = null;
		if (CollectionUtils.isNotEmpty(andCriteria)) {
			for (SearchCriteria each : andCriteria) {
				Predicate<E> eachPredicate = createPredicate(each);
				predicate = Objects.isNull(predicate)? 
						eachPredicate: predicate.and(eachPredicate);
			}
		}
		return predicate;
	}

	private void buildOr(Predicate<E> predicate, List<SearchByCriteria> orCriteria) {
		if(CollectionUtils.isNotEmpty(orCriteria)) {
			orCriteria.forEach(each -> {
				Predicate<E> orPredicate = build(each);
				if (Objects.nonNull(predicate) && Objects.nonNull(orPredicate)) {
					predicate.or(orPredicate);
				}
			});
		}
	}


	private Predicate<E> createPredicate(SearchCriteria criteria) {
		return obj -> {
			try {
				Object value = FieldUtils.readField(obj, criteria.getField(), true);
				return ComparisonExpressionEvaluator.evaluate(value, criteria.getExpression(), criteria.getValue());
			} catch (IllegalAccessException |IllegalArgumentException e) {
				String message = String.format("Could not evaluate the criteria '%s'", criteria);
				throw new UnsupportedOperationException(message, e);
			}
		};
	}

}
