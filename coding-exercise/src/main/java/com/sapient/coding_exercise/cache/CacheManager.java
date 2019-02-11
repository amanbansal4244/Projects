/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise.cache;

import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.sapient.coding_exercise.constants.CommonConstants;
import com.sapient.coding_exercise.dto.FileInfoDto;

@PropertySource(value= {"classpath:application.properties"})
public class CacheManager {
	@Autowired
	static
	Environment environment;
	public static Map<Object, Cacheable> cacheMap = new ConcurrentHashMap<>();

	private static final Logger LOGGER = LoggerFactory.getLogger(CacheManager.class);

	static {
		Thread parentCleaner = new Thread(() -> {
			while (true) {
				Set<Object> keySet = cacheMap.keySet();
				keySet.parallelStream().forEach((k) -> {
					Cacheable value = (Cacheable) cacheMap.get(k);
					if (!value.isExpired()) {
						cacheMap.remove(k);
						LOGGER.debug("Parent cleaner thread found an Expired Object in the Cache.");
					}
					;
				});
				try {
					Thread.sleep(CommonConstants.PARENT_CACHE_SLEEP_TIME);
				} catch (InterruptedException e) {
					LOGGER.debug("Exception occurred while parent thread were executing.");
				}
			}
		});
		parentCleaner.setPriority(Thread.MIN_PRIORITY);
		parentCleaner.start();
	}

	public CacheManager() {

	}

	public static void putCache(Cacheable object) {
		cacheMap.put(object.getIdentifier(), object);
	}

	public static Cacheable getCache(Object identifier) {
		try {
		Optional<Map.Entry<Object, Cacheable>> data = cacheMap.entrySet().stream().filter(
				entry -> entry.getKey().toString().equalsIgnoreCase(identifier.toString())
				).findFirst();
		Cacheable value = data.get().getValue();
		if (value == null)
			return null;
		if (!value.isExpired()) {
			cacheMap.remove(identifier);
			return null;
		} else {
			return value;
		}
		}
		catch(NoSuchElementException e) {
			LOGGER.debug("Element does not exists in Cache - "+identifier.toString());
		}
		return null;

	}

	public static Cacheable createDefaultCacheableObject(FileInfoDto cacheObject) {
		return new Cacheable() {
			private long objectCreationTime = new Date().getTime() ;
			@Override
			public boolean isExpired() {
				long currentTime = new Date().getTime();
				//long liveTime = environment.getProperty(CommonConstants.CACHED_ENTITY_LIVE_TIME) != null ? Long.parseLong(environment.getProperty(CommonConstants.CACHED_ENTITY_LIVE_TIME)) : defaultLiveTime;
				if(((objectCreationTime+defaultLiveTime)-currentTime) > 0 ) {
					return true;
				}
				return false;
			}

			@Override
			public Object getIdentifier() {
				return cacheObject.getFilePath().getName(cacheObject.getFilePath().getNameCount()-1);
			}
		};
	}
}
