/**
 * 
 */
package com.axonactive.common.util.search.predicate;

import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;

import com.axonactive.common.util.criteria.ComparisonExpression;
import com.axonactive.common.util.criteria.SearchByCriteria;
import com.axonactive.common.util.criteria.SearchCriteria;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * @author nvmuon
 *
 */
@AllArgsConstructor(access=AccessLevel.PRIVATE)
public class JpaPredicateBuilder<E> {

	private CriteriaBuilder criteriaBuilder;

	private Root<E> targetEntity;

	public static final <E> JpaPredicateBuilder<E> createBuilder(CriteriaBuilder criteriaBuilder, Root<E> targetEntity) {
		return new JpaPredicateBuilder<>(criteriaBuilder, targetEntity);
	}

	public Predicate build(SearchByCriteria searchBy) {
		Predicate predicate = null;

		if (SearchByCriteria.hasCriteria(searchBy)) {
			predicate = buildAnd(searchBy.getCriteria());
			predicate = buildOr(predicate, searchBy.getOr());
		}

		return predicate;
	}

	private Predicate buildAnd(List<SearchCriteria> criteria) {
		Predicate predicate = null;

		if (CollectionUtils.isNotEmpty(criteria)) {

			for (SearchCriteria each : criteria) {
				Predicate andPredicate = build(each);
				predicate = (Objects.isNull(predicate))? 
						criteriaBuilder.conjunction() : criteriaBuilder.and(criteriaBuilder.conjunction(), predicate);
						predicate = criteriaBuilder.and(predicate, andPredicate);
			}
		}
		return predicate;
	}

	private Predicate buildOr(Predicate predicate, List<SearchByCriteria> orCriteria) {
		if (CollectionUtils.isNotEmpty(orCriteria)) {
			for (SearchByCriteria each : orCriteria) {
				Predicate orPredicate = build(each);
				if (orPredicate != null) {
					predicate = (Objects.isNull(predicate))?
							criteriaBuilder.disjunction() : criteriaBuilder.or(criteriaBuilder.disjunction(), predicate);
							predicate = criteriaBuilder.or(predicate, orPredicate);
				}
			}
		}
		return predicate;
	}

	private Predicate build(SearchCriteria criteria) {
		String fieldName = criteria.getField();

		// See JPA 2.0 Criteria API for supported criterias
		// comparisons for common operands:
		if (criteria.getExpression().compareTo(ComparisonExpression.EQUAL) == 0) {
			return criteriaBuilder.equal(targetEntity.get(fieldName), criteria.getValue());
		}

		if (criteria.getExpression().compareTo(ComparisonExpression.NOT_EQUAL) == 0) {
			return criteriaBuilder.notEqual(targetEntity.get(fieldName), criteria.getValue());
		}

		// comparisons for string operands:
		if (criteria.getExpression().compareTo(ComparisonExpression.LIKE) == 0) {
			String pattern = (criteria.getValue() == null) ? "" : "%" + criteria.getValue().toString().toUpperCase() + "%";
			return criteriaBuilder.like(criteriaBuilder.upper(targetEntity.<String>get(fieldName)), pattern);
		} 

		else {
			String message = String.format("The expression '%s' is not supported", criteria.getExpression().toString());
			throw new UnsupportedOperationException(message);
		}
	}

}
