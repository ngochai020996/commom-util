/**
 * 
 */
package com.axonactive.common.util.search;

import java.util.Collection;

import javax.persistence.EntityManager;

/**
 * @author nvmuon
 *
 */
public class SearchEngine {

	
	public static <E> JpaSearcher<E> createJpaSearcher(EntityManager em) {
		return new JpaSearcher<>(em);
	}
	
	public static <E> InMemorySearcher<E> createInMemorySearcher(Collection<E> inMemoryData) {
		return new InMemorySearcher<>(inMemoryData);
	}
}
