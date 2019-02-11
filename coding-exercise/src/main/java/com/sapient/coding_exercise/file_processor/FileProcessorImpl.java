/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise.file_processor;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.sapient.coding_exercise.dto.FileInfoDto;
import com.sapient.coding_exercise.service.FileProcessorService;
import com.sapient.coding_exercise.utils.FileDirectoryUtils;

@Component
@PropertySource(value= {"classpath:application.properties"})
public class FileProcessorImpl implements FileProcessor {

	@Autowired
	FileProcessorService service;

	@Override
	public void processDirectory(Path path) {
		List<FileInfoDto> fileDtos = FileDirectoryUtils.processDirectory(path);
		service.add(fileDtos, path);
	}
}
