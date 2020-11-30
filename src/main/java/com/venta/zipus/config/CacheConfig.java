package com.venta.zipus.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Configuration
@EnableCaching
public class CacheConfig {

    /*Customizer*/
    @Component
    public class SimpleCacheCustomizer
            implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

        @Override
        public void customize(ConcurrentMapCacheManager cacheManager) {
            cacheManager.setCacheNames(asList("user", "users", "publications", "userDetails", "userAuthority"));
        }
    }

    /*Config*/
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("user", "users", "publications", "userDetails", "userAuthority", "pubType");
    }

}
