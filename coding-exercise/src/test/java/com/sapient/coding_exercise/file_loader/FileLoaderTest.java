package com.sapient.coding_exercise.file_loader;

import org.junit.Test;

public class FileLoaderTest {

	@Test
	public void testLoader() {
		FileLoaderImpl loader = new FileLoaderImpl();
		loader.load();
	}
}
