/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.sapient.coding_exercise.cache.CacheManager;
import com.sapient.coding_exercise.utils.ApplicationContextAwareImpl;

@Configuration
@ComponentScan(basePackages="com.sapient.coding_exercise")
@EnableAspectJAutoProxy
public class AppConfig {

	@Bean
	public CacheManager cacheManager() {
		return new CacheManager();
	}
	
	@Bean
	public LoggingAspect loggingAspect() {
		return new LoggingAspect();
	}
	
	@Bean
	public ApplicationContextAware applicationContext() {
		return new ApplicationContextAwareImpl();
	}
}
