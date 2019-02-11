/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise.cache;

public interface Cacheable {
	
	int defaultLiveTime = 6000000;
	
	public boolean isExpired();
	
	public Object getIdentifier();
}
