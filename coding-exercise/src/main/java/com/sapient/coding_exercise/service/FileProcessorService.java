/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise.service;

import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.coding_exercise.dto.FileInfoDto;

@Service
public interface FileProcessorService {
	public void add(List<FileInfoDto> data, Path parentDirectory);
}
