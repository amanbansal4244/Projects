/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sapient.coding_exercise.cache.CacheManager;
import com.sapient.coding_exercise.constants.FileTypes;
import com.sapient.coding_exercise.dto.FileInfoDto;

public class FileDirectoryUtils {

	private static Logger logger = LoggerFactory.getLogger(FileDirectoryUtils.class);
	
	public static List<FileInfoDto> processDirectory(Path directoryPath) {
		List<FileInfoDto> fileDtos = Lists.newArrayList();
		try {
			Files.list(directoryPath).parallel().forEach((path) -> {
				if (Files.isDirectory(path) && Files.isReadable(path)) {
					// recursively processing sub directories
					fileDtos.addAll(processDirectory(path));
				} else {
					File file = new File(path.toString());
					String strPath = file.getName();
					String[] arr = strPath.split("\\.");
					String ext = arr[1];
					if (ext.equals(FileTypes.CSV_TYPE.getName()) && Files.isReadable(path)) {
						//checking if file is already processed
						if(null != CacheManager.getCache(path)) {
							FileInfoDto dto = FileIOUtil.processFile(path, FileTypes.CSV_TYPE);
							fileDtos.add(dto);
							CacheManager.putCache(CacheManager.createDefaultCacheableObject(dto));		
						}
					}
					if (ext.equals(FileTypes.TEXT_TYPE.getName()) && Files.isReadable(path)) {
						FileInfoDto dto = FileIOUtil.processFile(path, FileTypes.TEXT_TYPE);
						fileDtos.add(dto);
						CacheManager.putCache(CacheManager.createDefaultCacheableObject(dto));		
					}
				}
			});
		} catch (IOException e) {
			logger.debug("Error while recursively  getting data from directory - "+e.getMessage());
		}
		return fileDtos;
	}
}
