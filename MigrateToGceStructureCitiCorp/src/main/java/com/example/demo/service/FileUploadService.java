package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

	public void store(MultipartFile file);
}
