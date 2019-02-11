/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sapient.coding_exercise.file_loader.FileLoader;

@Component
public class FileProcessorControllerImpl implements FileProcessorController{

	@Autowired
	FileLoader loader;
	
	/**
	 * This method starts file loading process.
	 */
	@Override
	public void runApplicationFlow() {
		loader.load();
	}

}
