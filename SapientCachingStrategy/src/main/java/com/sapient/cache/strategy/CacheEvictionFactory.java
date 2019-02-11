package com.sapient.cache.strategy;

public class CacheEvictionFactory {

	public static CustomCacheIntf<Object, Object> getCacheStrategy(String strategyName){
		CustomCacheIntf<Object, Object>  cacheEvictionStrategyIntf= null;
		
		if (strategyName.equalsIgnoreCase("TIME")) {
			cacheEvictionStrategyIntf = new TimeBasedEviction<Object, Object>();
		}
		else if (strategyName.equalsIgnoreCase("SIZE")) {
			//TODO
		}
		return cacheEvictionStrategyIntf;
	}
}
