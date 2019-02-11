package com.sapient.cache.strategy.thread;
import java.util.concurrent.TimeUnit;

import com.sapient.cache.strategy.CustomCacheIntf;
 
 
public final class ExpireTaskThread<K, V> implements Runnable {

	private final CustomCacheIntf<K, V> customCacheIntf;

	public ExpireTaskThread(CustomCacheIntf<K, V> customCacheIntf) {
		this.customCacheIntf = customCacheIntf;
	}

	@Override
	public void run() {    
		while(true){
			try {
				TimeUnit.SECONDS.sleep(1);
				customCacheIntf.evictCache();
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}

	}
}
 