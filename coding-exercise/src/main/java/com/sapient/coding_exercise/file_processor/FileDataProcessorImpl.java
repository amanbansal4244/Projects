/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise.file_processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

import com.sapient.coding_exercise.constants.CommonConstants;
import com.sapient.coding_exercise.dto.FileInfoDto;

@Component
public class FileDataProcessorImpl implements FileDataProcessor {

	private Comparator<FileInfoDto> wordComparator = (FileInfoDto obj1, FileInfoDto obj2) -> {

		if (obj1.getWordCount() > obj2.getWordCount())
			return 1;
		else if (obj1.getWordCount() < obj2.getWordCount())
			return -1;
		return 0;
	};
	private Comparator<FileInfoDto> VowelComparator = (FileInfoDto obj1, FileInfoDto obj2) -> {

		if (obj1.getVowelCount() > obj2.getVowelCount())
			return 1;
		else if (obj1.getVowelCount() < obj2.getVowelCount())
			return -1;
		return 0;
	};
	Comparator<FileInfoDto> specialCharComparator = (FileInfoDto obj1, FileInfoDto obj2) -> {

		if (obj1.getSpecialCharCount() > obj2.getSpecialCharCount())
			return 1;
		else if (obj1.getSpecialCharCount() < obj2.getSpecialCharCount())
			return -1;
		return 0;
	};
	
	@Override
	public void processDataForMtdFile(Collection<FileInfoDto> data) {
		for (FileInfoDto dto : data) {
			StringBuilder builder = new StringBuilder();
			builder.append("Word count - ").append(dto.getWordCount()).append(" | Special Character count - ")
					.append(dto.getSpecialCharCount()).append(" | Vowel count - ").append(dto.getVowelCount())
					.append("\n");
			dto.setMtdData(builder.toString());
		}
	}

	@Override
	public String processDataForDmtdFile(Collection<FileInfoDto> data) {
		long totalWordCount = 0;
		long totalVowelCount = 0;
		long totalSpecialCharCount = 0;
		StringBuilder builder = new StringBuilder();
		for (FileInfoDto object : data) {
			totalWordCount = totalWordCount + object.getWordCount();
			totalVowelCount = totalVowelCount + object.getVowelCount();
			totalSpecialCharCount = totalSpecialCharCount + object.getSpecialCharCount();
		}
		builder.append("Total word count - ").append(totalWordCount).append(" | Total Vowel count - ")
				.append(totalVowelCount).append(" | Total Special Character count - ").append(totalSpecialCharCount)
				.append("\n");
		return builder.toString();
	}

	@Override
	public String processDataForSmtdFile(Collection<FileInfoDto> data, String sortBy) {
		ArrayList<FileInfoDto> list = Lists.newArrayList(data);
		StringBuilder builder = new StringBuilder();
		if (sortBy.equalsIgnoreCase(CommonConstants.SORT_BY_WORD)) {
			Collections.sort(list, wordComparator);
			list.forEach((dto) -> {
				dto = (FileInfoDto) dto;
				builder.append("File Name - ").append(dto.getFilePath().getName(dto.getFilePath().getNameCount()-1)).append(" | Word Count - ").append(dto.getWordCount());
			});
		} else if (sortBy.equalsIgnoreCase(CommonConstants.SORT_BY_VOWEL)) {
			Collections.sort(list, VowelComparator);
			list.forEach((dto) -> {
				dto = (FileInfoDto) dto;
				builder.append("File Name - ").append(dto.getFilePath().getName(dto.getFilePath().getNameCount()-1)).append(" | Vowel Count - ").append(dto.getVowelCount());
			});
		} else  {
			Collections.sort(list, specialCharComparator);
			list.forEach((dto) -> {
				dto = (FileInfoDto) dto;
				builder.append("File Name - ").append(dto.getFilePath().getName(dto.getFilePath().getNameCount()-1)).append(" | Special character Count - ").append(dto.getSpecialCharCount());
			});
		}
		
		return builder.toString();
	}

}
