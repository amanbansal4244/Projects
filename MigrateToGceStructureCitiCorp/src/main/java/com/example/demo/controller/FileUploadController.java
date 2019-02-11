package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.FileUploadService;

/****
 * @Controller annotation is a stereotype and is used at class level in spring
 *             MVC. It indicates that the class is a web controller. These
 *             classes annotated with @Controller are auto detected through
 *             classpath scanning. @Controller annotation is usually used in
 *             combination with @RequestMapping annotation in spring
 *             MVC. @Controller annotation is a specialization of @Component
 *             annotation. @Controller annotation has an attribute value which
 *             is the suggestion for component name and will also be used as
 *             spring bean name for that class.
 *
 */
@Controller
public class FileUploadController {

	/****
	 * Note: If we are using @Autowired on FileUploadService, implementation of FileUploadService should be annotated with either 
	 * @Component or @Service or @Repository at class level.
	 */
	@Autowired
	private FileUploadService fileUploadService;

	/****
	 * Annotation for mapping HTTP GET requests onto specific handler methods.
	 * Specifically, @GetMapping is a composed annotation that acts as a shortcut
	 * for @RequestMapping(method = RequestMethod.GET).
	 */
	@GetMapping("/")
	public String index() {
		return "multipartfile/uploadform.html";
	}

	/****
	 * It is specialized version of @RequestMapping annotation that acts as a
	 * shortcut for @RequestMapping(method = RequestMethod.POST).
	 * 
	 * @param file
	 * @param model
	 * @return
	 */
	@PostMapping("/upload")
	public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file, Model model) {
		try {
			fileUploadService.store(file);
			model.addAttribute("message", "File " + file.getOriginalFilename() + " uploaded successfully");
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
		}
		return "multipartfile/uploadform.html";

	}

}
