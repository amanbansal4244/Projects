/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import com.sapient.coding_exercise.constants.CommonConstants;
import com.sapient.coding_exercise.constants.FileTypes;
import com.sapient.coding_exercise.dto.FileInfoDto;

public class FileIOUtil {

	public static FileInfoDto processFile(Path path, FileTypes extension) {
		long wordCount = 0;
		long vowelCount = 0;
		long specialCharCount =0 ;
		try {
			List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));
				for(String data: lines) {
					if (extension.toString().equals(FileTypes.CSV_TYPE.toString())) {
						wordCount = wordCount + getWordCount(data, CommonConstants.COMA_SEPERATOR);
					} else {
						wordCount = wordCount + getWordCount(data, CommonConstants.SPACE_SEPERATOR);
					}
					vowelCount =  vowelCount + getVowelCount(data);
					specialCharCount =  specialCharCount + getSpecialCharCount(data, CommonConstants.SPECIAL_CHAR);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new FileInfoDto(path,wordCount,vowelCount,specialCharCount);
	}
	
	public static void writeDataToFile(Path path, String data) {
				try {
					Files.write(path, data.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	private static long getWordCount(String data, String delemiter) {
		if (data != null && !data.trim().isEmpty()) {
			return data.trim().split(delemiter).length;
		}
		return 0;
	}

	private static long getSpecialCharCount(String data, char[] specialCharacters) {
		int count = 0;
		if (data != null && !data.trim().isEmpty()) {
			for (Character c : specialCharacters) {
				count = count + data.length() - data.replaceAll("\\" + c.toString(), "").length();
			}
		}
		return count;
	}

	private static long getVowelCount(String data) {
		if (data != null && !data.trim().isEmpty()) {
			data = data.toLowerCase();
			int count = 0;
			for (int i = 0; i < data.length(); i++) {
				if (data.charAt(i) == 'a' || data.charAt(i) == 'e' || data.charAt(i) == 'i' || data.charAt(i) == 'o'
						|| data.charAt(i) == 'u') {
					count++;
				}
			}
			return count;
		}
		return 0;
	}
}
