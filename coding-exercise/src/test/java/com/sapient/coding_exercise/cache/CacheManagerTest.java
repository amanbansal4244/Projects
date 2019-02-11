package com.sapient.coding_exercise.cache;

import static org.junit.Assert.assertNotNull;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.sapient.coding_exercise.dto.FileInfoDto;

public class CacheManagerTest {

	
	@Test
	public void test() {
		Path path = Paths.get("D:\\kridangan\\coding-exercise\\src\\main\\resources\\inputFileSource\\test.csv");
		FileInfoDto object = new FileInfoDto(path, 1, 1, 1);
		CacheManager.putCache(CacheManager.createDefaultCacheableObject(object));
		assertNotNull(CacheManager.getCache(object.getFilePath().getFileName()));
	}
}
