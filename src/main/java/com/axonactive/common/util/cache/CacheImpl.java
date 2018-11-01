/**
 *
 */
package com.axonactive.common.util.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.infinispan.commons.api.BasicCache;

/**
 * @author nvmuon
 * @param <K> indicates the key of a cached object
 * @param <V> the cached object
 *
 */
public class CacheImpl<K, V> implements Cache<K, V>{

	private BasicCache<K, V> cache;

	/**
	 * @param infinispanBasicCache
	 */
	CacheImpl(BasicCache<K, V> cache) {
		this.cache = cache;
	}

	@Override
	public void put(K key, V value) {
		if (Objects.nonNull(key) && Objects.nonNull(value)) {
			this.cache.put(key, value);
		}
	}

	@Override
	public V get(K key) {
		return Optional.ofNullable(key)
				.map(k -> this.cache.get(k))
				.orElse(null);
	}

	/**
	 * check whether key is or is not existed in cache
	 *
	 * @param key the key
	 * @return true if key exists, otherwise return false
	 */
	@Override
	public boolean containKey(K key) {
		return Optional.ofNullable(key)
				.map(k -> this.cache.containsKey(k))
				.orElse(Boolean.FALSE);
	}

	@Override
	public List<V> values() {
		Collection<V> all = this.cache.values();
		return new ArrayList<>(all);
	}

	@Override
	public void remove(K key) {
		Optional.ofNullable(key)
		.ifPresent(k -> this.cache.remove(k));
	}

	@Override
	public void clear() {
		this.cache.clear();
	}

	@Override
	public Set<K> keySet() {
		Set<K> keySet = this.cache.keySet();
		return new HashSet<>(keySet);
	}


}
