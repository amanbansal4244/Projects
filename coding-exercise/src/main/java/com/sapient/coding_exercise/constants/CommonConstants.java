/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise.constants;

public class CommonConstants {
	public static final int PARENT_CACHE_SLEEP_TIME = 5000; 
	
	//file loader properties
	public static final String INITIAL_DELAY = "polling-thread-initial-Delay-in-seconds";
	public static final String EXECUTION_INTERVAL = "polling-thread-execution-Delay-in-seconds";
	public static final String INPUT_FILE_DIRECTORY = "input-directory-to-poll-for-file";
	public static final String THREAD_SHUTDOWN_SIGNAL = "polling-thread-shutdown-signal";
	public static final String CACHED_ENTITY_LIVE_TIME = "cached-entity-live-time";
	
	//file processor properties
	public static final char[] SPECIAL_CHAR = { '@', '#', '$', '*','%','&','^'};
	public static final String SPACE_SEPERATOR = " ";
	public static final String COMA_SEPERATOR = ",";
	public static final String SORT_BY = "sort-by-switch-value";
	public static final String SORT_BY_WORD="word";
	public static final String SORT_BY_VOWEL="vowel";
	public static final String SORT_BY_SPECIAL_CHAR = "special character";
}
