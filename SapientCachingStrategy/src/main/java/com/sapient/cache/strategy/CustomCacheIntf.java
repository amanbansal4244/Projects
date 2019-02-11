package com.sapient.cache.strategy;

/**
*
* @description : This interface can be used to all custom cache API. 
*        
* @author aman bansal
*/
public interface CustomCacheIntf<K, V> {

	/****
	 * @description : This helps to save the key-value pair in cache.
	 * @param K key
	 * @param V value
	 */
	public void save(K key, V value);
	
	/****
	 * @description : This helps to update the key-value pair in cache.
	 * @param K key
	 * @param V value
	 */
	public void update(K key, V value);
	
	/****
	 * @description : This helps to get the value against given key from cache.
	 * @param K key
	 */
	public V get(K key);
	
	/****
	 * @description : This returns the size of the given key from cache.
	 */
	public int size();
	
	/****
	 * @description : This helps to remove the key from cache.
	 * @param CustomCacheKey key
	 */
	public void remove(CustomCacheKey<K> key);
	
	/****
	 * @description : This helps to remove the key from cache based on the strategy.
	 */
    public void evictCache();
    
    /****
	 * @description : This helps to notify the user when key is evicted from cache.
	 * @param CustomCacheKey key
	 */
    public void notifyEviction(CustomCacheKey<K> key);
    
}
