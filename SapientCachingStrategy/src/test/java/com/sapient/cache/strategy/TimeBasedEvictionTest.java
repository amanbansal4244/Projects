package com.sapient.cache.strategy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sapient.cache.strategy.thread.ExpireTaskThread;

public class TimeBasedEvictionTest {
    
    private static final Integer KEY = 1;
    private static final String VALUE = "cachedTestValue1";
    
    CustomCacheIntf<Object, Object> cacheEvictionStrategyIntf = null;
    
    @Before
	public void setup() {
    		cacheEvictionStrategyIntf = CacheEvictionFactory.getCacheStrategy("TIME");
	}

	@After
	public void tearDown() {
		cacheEvictionStrategyIntf = null;
	}
	
	@Test
    public void testSaveAndGet() {
        cacheEvictionStrategyIntf.save(KEY, VALUE);
        String value = (String) cacheEvictionStrategyIntf.get(KEY);
        Assert.assertEquals(VALUE, value);
    }
    
    @Test
    public void testEvictCacheAfterWriteAfterTTLTime() {
        cacheEvictionStrategyIntf.save(KEY, VALUE);
        try {
            TimeUnit.MINUTES.sleep(3);
            cacheEvictionStrategyIntf.evictCache();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String value = (String) cacheEvictionStrategyIntf.get(KEY);
        Assert.assertEquals(null, value);
    }
 
    @Test
    public void testEvictCacheAfterWriteBeforeTTLTime() {
    		cacheEvictionStrategyIntf.save(KEY, VALUE);
        try {
            TimeUnit.MINUTES.sleep(1);
            cacheEvictionStrategyIntf.evictCache();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String value = (String) cacheEvictionStrategyIntf.get(KEY);
        Assert.assertEquals(VALUE, value);
    }
   
   @Test
   public void testEvictCacheAfterAccessBeforeTTLTime() {
   		cacheEvictionStrategyIntf.save(KEY, VALUE);
   		cacheEvictionStrategyIntf.get(KEY);
       try {
           TimeUnit.MINUTES.sleep(1);
           cacheEvictionStrategyIntf.evictCache();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       String value = (String) cacheEvictionStrategyIntf.get(KEY);
       Assert.assertEquals(VALUE, value);
   }
   
   @Test
   public void testEvictCacheAfterAccessAfterTTLTime() {
   		cacheEvictionStrategyIntf.save(KEY, VALUE);
   		cacheEvictionStrategyIntf.get(KEY);
       try {
           TimeUnit.MINUTES.sleep(3);
           cacheEvictionStrategyIntf.evictCache();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       String value = (String) cacheEvictionStrategyIntf.get(KEY);
       Assert.assertEquals(null, value);
   }
   
   @Test
   public void testMultipleSave() {

	   ExecutorService executorService = null;
	   try {
		   executorService = Executors.newFixedThreadPool(1);
		   for(int i =0; i<5; i++) {
			   Runnable expireTaskThread = new Thread(new WorkerThreadTest(cacheEvictionStrategyIntf,i,i));

			   //expireTaskThread.setDaemon(true);
			   executorService.execute(expireTaskThread);
			   Thread.sleep(5);
		   } }catch (Exception e) {
			   e.printStackTrace();
		   } finally {
			   executorService.shutdown();
		   }

	   Assert.assertEquals(5, cacheEvictionStrategyIntf.size());

	   /* Executors.newFixedThreadPool(5).execute(new Runnable() {
		   @Override 
		   public void run() {
			   for(int i=0; i<5; i++) {
				   cacheEvictionStrategyIntf.save(i, i);
			   }
		   }
	   });*/

	   /*Runnable r = new Runnable(){	 //Creating an object of Anonymous class that implemented Runnable interface
			public void run()	 //Anonymous class implementing run() method of Runnable class
			{
			Thread t= Thread.currentThread();
			t.setName("Anonymous Thread");	
			System.out.println("Name of the other thread - " + t.getName());
			for(int i=0;i<3;i++)
			{
				System.out.println("amans" + cacheEvictionStrategyIntf);
				cacheEvictionStrategyIntf.save(i, i);
			}

			}
			};

		Thread t= new Thread(r);  //Passing the object of anonymous class to the constructor of Thread
		t.start();	*/

	   //System.out.println(cacheEvictionStrategyIntf.size());
	   /*ExecutorService executorService = null;
		try {
			executorService = Executors.newFixedThreadPool(5);
			Thread expireTaskThread = new Thread(new Runnable() {
				public void run() {
					for(int i=0; i<5; i++) {
						cacheEvictionStrategyIntf.save(i, i);
					}
				}
			});*/

	   /*	//executorService.execute(expireTaskThread);
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}*/
	   //Assert.assertEquals(5, cacheEvictionStrategyIntf.size());
   }
}