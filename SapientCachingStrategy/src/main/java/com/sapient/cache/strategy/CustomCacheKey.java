package com.sapient.cache.strategy;
import java.util.Random;
 
public class CustomCacheKey<K> {
 
    private final K key;
    private int userID;
 
    public CustomCacheKey(K key) {
        this.key = key;
        Random rand = new Random(); 
        userID = rand.nextInt(50);
    }

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public K getKey() {
		return key;
	}
    
	public int hashCode(){
        int hashcode = 0;
        hashcode = userID*13;
        hashcode += key.hashCode();
        return hashcode;
    }
     
	public boolean equals(Object obj){
		if(obj==null) 
			return false;
		if (obj instanceof CustomCacheKey) {
			@SuppressWarnings("unchecked")
			CustomCacheKey<Object> customCacheKey = (CustomCacheKey<Object>) obj;
			return (customCacheKey.key.equals(this.key) && customCacheKey.userID == this.userID);
		} else {
			return false;
		}
	}
}