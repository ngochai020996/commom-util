/**
 * 
 */
package com.axonactive.common.util.cache;

import java.util.Objects;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;
import org.infinispan.commons.api.BasicCache;

/**
 * @author nvmuon
 *
 */
@Singleton
public class CacheProducer {

	@EJB
	CacheProvider cacheProvider;

	@Produces
	public <K, V> Cache<K, V> produceCache(InjectionPoint ip) {

		Annotated annotated = ip.getAnnotated();
		CacheName cacheName = annotated.getAnnotation(CacheName.class);

		String cachNameValue = Objects.nonNull(cacheName) ? cacheName.value() : null;

		BasicCache<K, V> cache = StringUtils.isNotBlank(cachNameValue) ? cacheProvider.getCache(cachNameValue)
				: cacheProvider.getDefaultCache();

		return new CacheImpl<>(cache);

	}
}
