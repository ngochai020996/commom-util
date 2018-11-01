/**
 * 
 */
package com.axonactive.common.util.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import com.axonactive.common.util.criteria.OrderByCriteria;
import com.axonactive.common.util.criteria.OrderCriteria;
import com.axonactive.common.util.criteria.RangeUtils;
import com.axonactive.common.util.criteria.SearchByCriteria;
import com.axonactive.common.util.search.predicate.InMemoryPredicateBuilder;

/**
 * @author nvmuon
 *
 */
public class InMemorySearcher<E> extends AbstractSearcher<E> {

	private Collection<E> inMemoryData;

	protected InMemorySearcher(Collection<E> inMemoryData) {
		this.inMemoryData = inMemoryData;
	}

	@Override
	public List<E> execute() {
		List<E> resultList = new ArrayList<>();
		
		if (CollectionUtils.isNotEmpty(inMemoryData)) {
			
			Predicate<E> pridicate = InMemoryPredicateBuilder.buildAlwaysRightPredicate();
			if (SearchByCriteria.hasCriteria(searchBy)) {
				InMemoryPredicateBuilder<E> builder = InMemoryPredicateBuilder.createBuilder();
				pridicate = builder.build(searchBy);
			}
			
			resultList = inMemoryData.stream()
					.filter(pridicate)
					.sorted(createComparator(orderBy))
					.collect(Collectors.toList());

			resultList = RangeUtils.filter(resultList, range);
		}

		return resultList;
	}
	
	private Comparator<E> createComparator(OrderByCriteria orderBy) {
		return (thiz, that) -> {
			int result = 0;
			if (OrderByCriteria.hasCriteria(orderBy)) {
				List<OrderCriteria> orders = new ArrayList<>(orderBy.getOrder());
				result = compare(thiz, that, orders);
			}
			return result;
		};
	}

	private int compare(E thiz, E that, List<OrderCriteria> orderCriterias) {

		OrderCriteria orderCriteria = orderCriterias.remove(0);
		int result = compare(thiz, that, orderCriteria);
		if (orderCriterias.isEmpty() || result != 0) {
			return result;
		}
		return compare(thiz, that, orderCriterias);
	}

	@SuppressWarnings("unchecked")
	private int compare(E thiz, E that, OrderCriteria orderCriteria) {
		try {
			Object thisFieldValue = FieldUtils.readField(thiz, orderCriteria.getField(), true);
			Object thatFieldValue = FieldUtils.readField(that, orderCriteria.getField(), true);

			if(orderCriteria.isDescending()) {
				return ComparatorUtils.naturalComparator().compare(thatFieldValue, thisFieldValue);
			}
			else {
				return ComparatorUtils.naturalComparator().compare(thisFieldValue, thatFieldValue);
			}
		} catch (IllegalAccessException e) {
			throw new UnsupportedOperationException(e);
		}
	}
}
