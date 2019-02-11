package com.sapient.cache.strategy;

public class SizeBasedEviction<K, V> implements CustomCacheIntf<K, V>{

	@Override
	public void save(K key, V value) {
		// TODO
	}

	@Override
	public V get(K key) {
		// TODO
		return null;
	}

	@Override
	public void evictCache() {
		// TODO
	}

	@Override
	public void remove(CustomCacheKey<K> key) {
		// TODO
		
	}

	@Override
	public void notifyEviction(CustomCacheKey<K> key) {
		// TODO
	}

	@Override
	public void update(K key, V value) {
		// TODO
		
	}

	@Override
	public int size() {
		// TODO 
		return 0;
	}
}
