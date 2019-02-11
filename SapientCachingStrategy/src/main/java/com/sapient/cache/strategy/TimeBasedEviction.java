package com.sapient.cache.strategy;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sapient.cache.strategy.constants.Constant;
import com.sapient.cache.strategy.thread.ExpireTaskThread;

public class TimeBasedEviction<K, V> implements CustomCacheIntf<K, V> {

	private final ConcurrentHashMap<CustomCacheKey<K>, CacheTime<V>> evictionCacheMap = new ConcurrentHashMap<>();;

	public TimeBasedEviction() {
		initCacheCleanUpThread();
	}

	@Override
	public void save(K key, V value) {
		CacheTime<V> cacheTime = evictionCacheMap.get(key);
		if (cacheTime == null) {
			evictionCacheMap.put(new CustomCacheKey<K>(key), new CacheTime<V>(value));
		}
	}

	@Override
	public void update(K key, V value) {
		evictionCacheMap.put(new CustomCacheKey<K>(key), new CacheTime<V>(value));
	}

	@Override
	public V get(K key) {
		Set<CustomCacheKey<K>> keySet = evictionCacheMap.keySet();
		for (CustomCacheKey<K> key1 : keySet) {
			CacheTime<V> cacheTime = evictionCacheMap.get(key1);
			if (cacheTime != null) {
				cacheTime.setLastAccessTimestamp(LocalDateTime.now());
				return cacheTime.getValue();
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public void evictCache() {
		LocalDateTime now = LocalDateTime.now();
		Set<CustomCacheKey<K>> keySet = evictionCacheMap.keySet();
		for (CustomCacheKey<K> key : keySet) {
			CacheTime<V> cacheTime = evictionCacheMap.get(key);
			synchronized (evictionCacheMap) {
				if (cacheTime != null) {
					if (expireAfterAccess(ChronoUnit.MINUTES.between(cacheTime.getLastAccessTimestamp(), now))
							|| expireAfterWrite(ChronoUnit.MINUTES.between(cacheTime.getLastWriteTimestamp(), now))) {
						notifyEviction(key);
						this.remove(key);
						Thread.yield();
					}
				}
			}
		}
	}

	@Override
	public void notifyEviction(CustomCacheKey<K> key) {
		System.out.println("Cache eviction notifications: Key got expired: " + key);

	}

	@Override
	public void remove(CustomCacheKey<K> key) {
		evictionCacheMap.remove(key);

	}

	private boolean expireAfterWrite(long duration) {
		return duration > Constant.EXPIRY_TIME_TO_LIVE;
	}

	private boolean expireAfterAccess(long duration) {
		return duration > Constant.EXPIRY_TIME_TO_LIVE;
	}

	/****
	 * A separate daemon thread “initCacheCleanUpThread” runs every x seconds to remove cache
	 * items that have not been accessed for more than TTL duration. E.g. 10 seconds. This thread invokes the “initCacheCleanUpThread()” method.
	 */
	private void initCacheCleanUpThread() {
		ExecutorService executorService = null;
		try {
			executorService = Executors.newFixedThreadPool(1);
			Thread expireTaskThread = new Thread(new ExpireTaskThread<K, V>(this));
			expireTaskThread.setDaemon(true);
			executorService.execute(expireTaskThread);
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}
	}

	@Override
	public int size() {
		return evictionCacheMap.size();
	}
}
