/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

@Repository
public interface FileProcessorDao<FileInfoDto> {
	public void save(Collection<FileInfoDto> data);
	public void save(String data, String directory, String fileExtension);
}
