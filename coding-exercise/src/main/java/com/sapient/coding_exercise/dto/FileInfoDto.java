/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise.dto;

import java.nio.ByteBuffer;
import java.nio.file.Path;

public class FileInfoDto {
	
	private Path filePath;
	private long wordCount;
	private long vowelCount;
	private long specialCharCount;
	private String mtdData;
	
	public FileInfoDto() {
		
	}
	public FileInfoDto(Path filePath, long wordCount, long vowelCount, long specialCharCount) {
		this.filePath = filePath;
		this.wordCount = wordCount;
		this.vowelCount = vowelCount;
		this.specialCharCount = specialCharCount;
	}
	
	public void persist(ByteBuffer buffer, FileInfoDto data) {
        buffer.putLong( data.getWordCount());
        buffer.putLong( data.getSpecialCharCount());
        buffer.putLong( data.getVowelCount());
    }
	/**
	 * @return the filePath
	 */
	public Path getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(Path filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the wordCount
	 */
	public long getWordCount() {
		return wordCount;
	}
	/**
	 * @param wordCount the wordCount to set
	 */
	public void setWordCount(long wordCount) {
		this.wordCount = wordCount;
	}
	/**
	 * @return the vowelCount
	 */
	public long getVowelCount() {
		return vowelCount;
	}
	/**
	 * @param vowelCount the vowelCount to set
	 */
	public void setVowelCount(long vowelCount) {
		this.vowelCount = vowelCount;
	}
	/**
	 * @return the specialCharCount
	 */
	public long getSpecialCharCount() {
		return specialCharCount;
	}
	/**
	 * @param specialCharCount the specialCharCount to set
	 */
	public void setSpecialCharCount(long specialCharCount) {
		this.specialCharCount = specialCharCount;
	}
	/**
	 * @return the mtdData
	 */
	public String getMtdData() {
		return mtdData;
	}
	/**
	 * @param mtdData the mtdData to set
	 */
	public void setMtdData(String mtdData) {
		this.mtdData = mtdData;
	}
	
	public String getFilePathString() {
		return this.filePath.getParent().toString();
	}
}
