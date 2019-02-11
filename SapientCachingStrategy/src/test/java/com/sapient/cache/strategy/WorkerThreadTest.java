package com.sapient.cache.strategy;
import java.util.concurrent.TimeUnit;

import com.sapient.cache.strategy.CustomCacheIntf;
 
 
public final class WorkerThreadTest<K, V> implements Runnable {

	private final CustomCacheIntf<K, V> customCacheIntf;
	private K key;
	private V value;
	
	public WorkerThreadTest(CustomCacheIntf<K, V> customCacheIntf, K key, V value) {
		this.customCacheIntf = customCacheIntf;
		this.key = key;
		this.value = value;
	}

	@Override
	public void run() {    
			try {
				TimeUnit.SECONDS.sleep(1);
				customCacheIntf.save(key, value);
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
	}
}
