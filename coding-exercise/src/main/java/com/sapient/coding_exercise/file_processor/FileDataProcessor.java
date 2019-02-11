/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise.file_processor;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.sapient.coding_exercise.dto.FileInfoDto;

@Component
public interface FileDataProcessor {
	public void processDataForMtdFile(Collection<FileInfoDto> data);
	public String processDataForDmtdFile(Collection<FileInfoDto> data);
	public String processDataForSmtdFile(Collection<FileInfoDto> data, String sortBy); 
}
