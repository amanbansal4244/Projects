/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise.file_loader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.sapient.coding_exercise.cache.CacheManager;
import com.sapient.coding_exercise.constants.CommonConstants;

@Component
@PropertySource(value = { "classpath:application.properties" })
public class FileLoaderImpl implements FileLoader {

	private int initialDelay;
	private int repeatInterval;

	@Autowired
	@Qualifier(value = "fileLoaderTask")
	TimerTask task;
	@Autowired
	Environment environment;

	private static final Logger LOGGER = LoggerFactory.getLogger(CacheManager.class);

	/**
	 * Scheduling file polling after configured time interval
	 */
	@Override
	public void load() {
		try {
			this.loadLoaderProperties();
			ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
			scheduler.scheduleAtFixedRate(task, initialDelay, repeatInterval, TimeUnit.MILLISECONDS);
			/*Timer timer = new Timer(true);
			timer.schedule(task, initialDelay, repeatInterval);*/
			// checking if signal is on to cancel the scheduler. If yes then cancel or go on.
			if (checkShutdownSignal()) {
				//task.cancel();
				scheduler.shutdown();
			}
		} catch (RuntimeException | InterruptedException e) {
			LOGGER.info("Error while scheduling the loader."+e.getMessage());
		}
	}

	private void loadLoaderProperties() {
		this.setInitialDelay(Integer.parseInt(environment.getProperty(CommonConstants.INITIAL_DELAY)));
		this.setRepeatInterval(Integer.parseInt(environment.getProperty(CommonConstants.EXECUTION_INTERVAL)));
	}

	/**
	 * this method checks for lock.txt file. If this files exists then it returns
	 * true and deletes the file.
	 * 
	 * @return true/false
	 */
	private boolean checkShutdownSignal() throws InterruptedException{
		String signalFilePath = environment.getProperty(CommonConstants.THREAD_SHUTDOWN_SIGNAL);
		Path path = Paths.get(signalFilePath);
		path = path.toAbsolutePath();
		try {
			while (Files.exists(path)) {
				List<Path> files = Files.list(path).collect(Collectors.toList());
				if (files.size()>0 && files.get(0).endsWith("lock.txt")) {
					Files.delete(new File(files.get(0).toString()).toPath());
					return true;
				}
				LOGGER.info("Parent thread sleeping for " + CommonConstants.PARENT_CACHE_SLEEP_TIME + " mili seconds.");
				Thread.sleep(CommonConstants.PARENT_CACHE_SLEEP_TIME);
			}
		} catch (IOException e1) {
			LOGGER.info("Error occurred while deleting the shutdown signal file." + e1.getMessage());
		}
		return false;
	}

	/**
	 * @param initialDelay
	 *            the initialDelay to set
	 */
	public void setInitialDelay(int initialDelay) {
		this.initialDelay = initialDelay;
	}

	/**
	 * @param repeatInterval
	 *            the repeatInterval to set
	 */
	public void setRepeatInterval(int repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

}
