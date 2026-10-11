package com.cache.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CacheInspectionService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private CacheManager cacheManager;

    public void printCacheContents(String cacheName){
        Cache cache = cacheManager.getCache(cacheName);
        if(cache!=null){
            System.out.println("Cache Contents for CacheName: "+cacheName+" are : ");
            System.out.print(Objects.requireNonNull(cache.getNativeCache()).toString());
            System.out.println();
        }else{
            System.out.println("No Such Cache : "+cacheName);
        }
    }

    //storing them as hash entries under a single cache variable like "cacheVariable1={cachekey1:value1, cacheKey2:value2}"
    //methods below use Redis Hash operations to store multiple key-value pairs under a single session key
    public void setSessionVariable(String sessionId, String key, String value) {
        redisTemplate.opsForHash().put(sessionId, key, value);
    }
    public String getSessionVariable(String sessionId, String key) {
        Object value = redisTemplate.opsForHash().get(sessionId, key);
        return value != null ? value.toString() : null;
    }
}
