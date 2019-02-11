/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise.file_loader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.sapient.coding_exercise.constants.CommonConstants;
import com.sapient.coding_exercise.file_processor.FileProcessor;

@Component(value = "fileLoaderTask")
@PropertySource(value = { "classpath:application.properties" })
public class FileLoaderTask extends TimerTask {

	@Autowired
	FileProcessor fileProcessor;
	@Autowired
	Environment environment;

	private static final Logger LOGGER = LoggerFactory.getLogger(FileLoaderTask.class);

	private static final String DEFAULT_INPUT_FILE_PATH = "";

	@Override
	public void run() {
		String inputFilesDir = environment.getProperty(CommonConstants.INPUT_FILE_DIRECTORY, DEFAULT_INPUT_FILE_PATH);
		long pollingStartTime = new Date().getTime();
		Path path = Paths.get(inputFilesDir);
		path = path.toAbsolutePath();
		try {
			if (Files.exists(path) && Files.isDirectory(path)) {
				fileProcessor.processDirectory(path);
			} else {
				LOGGER.info("input file path does not exists. It will be tried again in next run.");
			}
		} catch (RuntimeException re) {
			LOGGER.info("error occurred while starting file processing - " + re.getMessage());
		}
		long pollingEndTime = new Date().getTime();
		LOGGER.info("Time taken in file polling - " + (pollingEndTime - pollingStartTime));

	}

}
