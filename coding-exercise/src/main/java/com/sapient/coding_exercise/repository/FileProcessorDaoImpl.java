/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise.repository;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.sapient.coding_exercise.dto.FileInfoDto;
import com.sapient.coding_exercise.utils.FileIOUtil;

@Repository
public class FileProcessorDaoImpl implements FileProcessorDao<FileInfoDto> {

	@Override
	public void save(Collection<FileInfoDto> data) {
			for (FileInfoDto dto : data) {
				Path path = dto.getFilePath();
				String fileName = getFileName(path.toString(),".mtd");
				FileIOUtil.writeDataToFile(Paths.get(fileName), dto.getMtdData());
			}
	}

	@Override
	public void save(String data, String directory,String fileExt) {
		FileIOUtil.writeDataToFile(Paths.get(getDirName(directory, fileExt)), data);
	}
	
	private String getFileName(String filePath, String fileExt) {
		String[] arr = filePath.split("\\.");
		String name = arr[0]+arr[1]+fileExt;
		return name;
	}
	
	private String getDirName(String filePath, String fileExt) {
		Path path = Paths.get(filePath);
		String name =path.toString()+"\\"+path.getFileName()+fileExt;
		return name;
	}

}
