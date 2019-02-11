package com.sapient.cache.strategy;
import java.time.LocalDateTime;
 
public class CacheTime<V> {
 
    private final V value;
    private LocalDateTime lastAccessTimestamp;
    private LocalDateTime lastWriteTimestamp;
 
    public CacheTime(V value) {
        this.value = value;
        this.lastAccessTimestamp = LocalDateTime.now();
        this.lastWriteTimestamp = LocalDateTime.now();
    }
    
    public LocalDateTime getLastAccessTimestamp() {
        return lastAccessTimestamp;
    }
 
    public void setLastAccessTimestamp(LocalDateTime lastAccessTimestamp) {
        this.lastAccessTimestamp = lastAccessTimestamp;
    }
 
    
    public LocalDateTime getLastWriteTimestamp() {
		return lastWriteTimestamp;
	}

	public void setLastWriteTimestamp(LocalDateTime lastWriteTimestamp) {
		this.lastWriteTimestamp = lastWriteTimestamp;
	}

	public V getValue() {
        return this.value;
    }
	
	 public int hashCode(){
	    	int hashcode = 0;
	    	hashcode = value.hashCode()*13;
	    	hashcode = hashcode + lastAccessTimestamp.hashCode();
	    	hashcode = hashcode + lastWriteTimestamp.hashCode();
			return hashcode;	
	    }
     
	public boolean equals(Object obj){
		if(obj==null) 
			return false;
		if (obj instanceof CacheTime) {
			@SuppressWarnings("unchecked")
			CacheTime<Object> cacheTime = (CacheTime<Object>) obj;
			return (cacheTime.value.equals(this.value) && cacheTime.lastAccessTimestamp == this.lastAccessTimestamp
						&& cacheTime.lastWriteTimestamp == this.lastWriteTimestamp);
		} else {
			return false;
		}
	}
}