/**
 * 
 */
package com.axonactive.common.util.search;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.axonactive.common.util.criteria.OrderByCriteria;
import com.axonactive.common.util.criteria.RangeCriteria;
import com.axonactive.common.util.criteria.SearchByCriteria;
import com.axonactive.common.util.search.predicate.JpaPredicateBuilder;

/**
 * @author nvmuon
 *
 */
public class JpaSearcher<E> extends AbstractSearcher<E> {

	private EntityManager em;
	
	private Class<E> target;



	JpaSearcher(EntityManager em) {
		this.em = em;
	}
	
	public void searchOn(Class<E> targetEntity) {
		this.target = targetEntity;
	}

	/**
	 * Additional generic method with searchBy, sortBy and range query criterias

	 *
	 * @param searchBy
	 * @param orderBy
	 * @param range

	 * @return
	 */
	public List<E> execute() {
		
		validateRequiredFields(); 
		
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(target);
		Root<E> root = cq.from(target);
		cq.select(root);
		cq.distinct(true);

		if (SearchByCriteria.hasCriteria(searchBy)) {
			JpaPredicateBuilder<E> builder = JpaPredicateBuilder.createBuilder(cb, root);
			cq.where(builder.build(searchBy));
		}

		if (OrderByCriteria.hasCriteria(orderBy)) {
			cq.orderBy(buildJpaOrders(cb, root, orderBy));
		}

		javax.persistence.TypedQuery<E> q = this.em.createQuery(cq);


		if (Objects.nonNull(range) 
				&& RangeCriteria.checkValid(range.getFrom(), range.getTo())) {
			q.setMaxResults(range.getTo() - range.getFrom());
			q.setFirstResult(range.getFrom());
		}

		return q.getResultList();
	}

	private void validateRequiredFields() {
		if (Objects.isNull(this.em)) {
			throw new IllegalArgumentException("The EntityManager (em) should not be null");
		}
		
		if (Objects.isNull(this.target)) {
			throw new IllegalArgumentException("The target entity should not be null");
		}
	}

	private List<Order> buildJpaOrders(CriteriaBuilder cb, Root<E> root, OrderByCriteria orderBy) {
		List<Order> orders = null;
		if (OrderByCriteria.hasCriteria(orderBy)) {
			orders  = orderBy.getOrder().stream()
					.map(each -> (each.isDescending()? cb.desc(root.get(each.getField())) : cb.asc(root.get(each.getField()))))
					.collect(Collectors.toList());
		}
		return orders;
	}
}
