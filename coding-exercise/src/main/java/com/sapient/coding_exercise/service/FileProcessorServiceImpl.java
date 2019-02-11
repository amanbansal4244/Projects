/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.sapient.coding_exercise.cache.CacheManager;
import com.sapient.coding_exercise.constants.CommonConstants;
import com.sapient.coding_exercise.dto.FileInfoDto;
import com.sapient.coding_exercise.file_processor.FileDataProcessor;
import com.sapient.coding_exercise.repository.FileProcessorDao;

/**
 * @author virjangi
 *
 */
@Service
@PropertySource(value= {"classpath:application.properties"})
public class FileProcessorServiceImpl implements FileProcessorService{
	@Autowired
	FileProcessorDao<FileInfoDto> dao;
	@Autowired
	FileDataProcessor dataProcessor;
	@Autowired
	Environment environment;
	@Override
	public void add(List<FileInfoDto> data, Path parentDirectory) {
		Collection<FileInfoDto> filteredData = data.stream().filter(
				(file -> CacheManager.getCache(
						file.getFilePath().getName(file.getFilePath().getNameCount()-1)
						)!=null
						)
				).collect(Collectors.toList());
		dataProcessor.processDataForMtdFile(filteredData);
		dao.save(filteredData);
		Map<String, List<FileInfoDto>> pathMappedData = data.parallelStream().collect(
				Collectors.groupingBy(FileInfoDto::getFilePathString, Collectors.toList())
				);
		for(Map.Entry<String,List<FileInfoDto>> entry : pathMappedData.entrySet()) {
			String dmtdFileData = dataProcessor.processDataForDmtdFile(entry.getValue());
			dao.save(dmtdFileData, entry.getKey(),".dmtd");
			String smtedFileData = dataProcessor.processDataForSmtdFile(entry.getValue(), environment.getProperty(CommonConstants.SORT_BY));
			dao.save(smtedFileData, entry.getKey(),".smtd");
		}
	}
}
