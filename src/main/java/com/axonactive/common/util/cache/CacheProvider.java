/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.axonactive.common.util.cache;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;

import org.infinispan.commons.api.BasicCache;
import org.infinispan.commons.api.BasicCacheContainer;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.util.concurrent.IsolationLevel;

/**
 *
 * @author nvmuon
 */
@Singleton
public class CacheProvider {

    BasicCacheContainer cacheContainer;

    @PostConstruct
    public void initCacheContainer() {
        if (cacheContainer == null) {
            GlobalConfiguration glob = new GlobalConfigurationBuilder()
                    .nonClusteredDefault() //Helper method that gets you a default constructed GlobalConfiguration, preconfigured for use in LOCAL mode
                    .globalJmxStatistics().allowDuplicateDomains(true)
                    .build(); //Builds  the GlobalConfiguration object
            Configuration loc = new ConfigurationBuilder()
                    .clustering().cacheMode(CacheMode.LOCAL) //Set Cache mode to LOCAL - Data is not replicated.
                    .locking().isolationLevel(IsolationLevel.REPEATABLE_READ) //Sets the isolation level of locking
                    .persistence().passivation(false)//.addSingleFileStore().purgeOnStartup(true) //Disable passivation and adds a SingleFileStore that is Purged on Startup
                    .build(); //Builds the Configuration object
            cacheContainer = new DefaultCacheManager(glob, loc, true);
        }
    }

    public <K, V> BasicCache<K, V> getCache(String name) {
        return this.cacheContainer.getCache(name);
    }
    
    public <K, V> BasicCache<K, V> getDefaultCache() {
        return this.cacheContainer.getCache();
    }

    @PreDestroy
    public void cleanUp() {
        cacheContainer.stop();
        cacheContainer = null;
    }

}
