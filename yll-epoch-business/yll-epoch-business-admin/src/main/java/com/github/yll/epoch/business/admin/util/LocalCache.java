package com.github.yll.epoch.business.admin.util;

import org.springframework.cache.Cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地缓存
 *
 * @author luliang_yu
 * @date 2018-11-23
 */

public class LocalCache implements Cache {

    ConcurrentHashMap<Object, Object> local = new ConcurrentHashMap<>();

    public LocalCache(ConcurrentHashMap<Object, Object> local) {
        this.local = local;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public ValueWrapper get(Object o) {
        return null;
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object o, Object o1) {

    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        return null;
    }

    @Override
    public void evict(Object o) {

    }

    @Override
    public void clear() {

    }
}
