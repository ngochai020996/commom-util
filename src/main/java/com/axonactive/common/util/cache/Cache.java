/**
 * 
 */
package com.axonactive.common.util.cache;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author nvmuon
 *
 */
public interface Cache<K, V> {

	/**
	 * Puts the specified value with the specified key into local memory cache.
	 * @param key   key to use
	 * @param value value to store
	 */
	void put(K key, V value) ;

	/**
	 * Returns the value to which the specified key is stored,
     * or {@code null} if this cache contains no mapping for the key.
	 * @param key   key to use
	 * @return the value to which the specified key is stored, or
     * {@code null} if this cache contains no mapping for the key
	 */
	V get(K key);

	/**
	 * check whether key is or is not existed in cache
	 *
	 * @param key  key to use
	 * @return true if key exists, otherwise return false
	 */
	boolean containKey(K key) ;

	/**
	 * Returns a {@link List} view of the values contained in this cache.
	 * @return a {@link List} view of the values contained in this cache.
	 */
	Collection<V> values() ;

	/**
	 * Removes the mapping for a key from this cache if it is present
	 * @param key
	 */
	void remove(K key);

	 /**
     * Removes all of the mappings from this cache (optional operation).
     * The cache will be empty after this call returns.
     */
	void clear();

	/**
	 * Returns a {@link Set} view of the keys contained in this cache.
	 * @return
	 */
	Set<K> keySet();
	
	/**
     * Returns <tt>true</tt> if this cache contains no elements.
     *
     * @return <tt>true</tt> if this cache contains no elements
     */
	default boolean isEmppty() {
		return this.keySet().isEmpty();
	}
}
