/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise.file_processor;

import java.nio.file.Path;

import org.springframework.stereotype.Component;

@Component
public interface FileProcessor {
	public void processDirectory(Path path);
}
