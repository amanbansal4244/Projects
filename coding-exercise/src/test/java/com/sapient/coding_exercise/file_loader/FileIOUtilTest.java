package com.sapient.coding_exercise.file_loader;

import static org.junit.Assert.assertNotNull;

import java.nio.file.Paths;

import org.junit.Ignore;
import org.junit.Test;

import com.sapient.coding_exercise.constants.FileTypes;
import com.sapient.coding_exercise.dto.FileInfoDto;
import com.sapient.coding_exercise.utils.FileIOUtil;

public class FileIOUtilTest {

	@Ignore
	@Test
	public void testRead() {
		FileInfoDto dto = FileIOUtil.processFile(Paths.get(".\\resources\\inputFileSource\\test.txt"), FileTypes.TEXT_TYPE);
		assertNotNull(dto);
		System.out.println(dto.getFilePath());
		System.out.println(dto.getWordCount());
		System.out.println(dto.getVowelCount());
		System.out.println(dto.getSpecialCharCount());
	}
	
	@Test
	public void testWrite() {
		String testData = "Word count - 10";
		FileIOUtil.writeDataToFile(Paths.get(".\\resources\\output-files\\output.mtd"), testData);
	}
}
