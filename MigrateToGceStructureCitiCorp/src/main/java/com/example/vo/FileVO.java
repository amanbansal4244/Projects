package com.example.vo;

public class FileVO {
	private String fileName;
	private String url;
	
	public FileVO(String fileName, String url) {
		super();
		this.fileName = fileName;
		this.url = url;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
