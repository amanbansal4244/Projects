package com.example.demo.service;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.FileUploadDao;
import com.example.demo.exception.InvalidFileException;
import com.example.demo.mid.MIDConstants;
import com.example.demo.util.ReadInputFile;
import com.example.vo.StagingTableDataVO;

/****
 * @Component indicates that a class might be a candidate for creating a bean.
 *            Its like putting a hand up and it will also be considered as
 *            spring bean name.
 * @Component is the most generic Spring annotation. A Java class decorated
 *            with @Component is found during class-path scanning and registered
 *            in the context as a Spring bean. @Service, @Repository,
 *            and @Controller are specializations of @Component, which are used
 *            for more specific cases.n java configuration, @ComponentScan
 *            annotation is used to auto detect the component and in spring
 *            application context XML, component-scan tag is used for
 *            auto-detection through class-path.
 * 
 * @ComponentScan ensures that the classes decorated with @Component are found
 *                and registered as Spring beans.
 * @ComponentScan is automatically included with @SpringBootApplication.
 * 
 * @Bean servers a similar purpose as @Component. It is not auto-detected.
 *       Methods decorated with @Bean produce a bean to be managed by the Spring
 *       container during configuration stage.
 */
@Component
public class FileUploadServiceImpl implements FileUploadService {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class.getName());

	/****
	 * Note: If we are using @Autowired on FileUploadDao, implementation of FileUploadDao should be annotated with either 
	 * @Component or @Service or @Repository at class level.
	 */
	@Autowired
	private FileUploadDao fileUploadDao;

	@Override
	public void store(MultipartFile file) {
		String fileName = file.getOriginalFilename();

		try (InputStream uploadFileInputStream = file.getInputStream()) {
			if (file != null && !file.isEmpty()
					&& (fileName.toLowerCase().endsWith(MIDConstants.INPUT_FILE_EXTENSION_XLS)
							|| fileName.toLowerCase().endsWith(MIDConstants.INPUT_FILE_EXTENSION_XLSX))) {

				StagingTableDataVO stagingTableDataVO = ReadInputFile.readInputFile(uploadFileInputStream, fileName);

				fileUploadDao.saveInputFileDataIntoStaggingTables(stagingTableDataVO);

				logger.info("File " + fileName + " uploaded successfully");
			} else {
				throw new InvalidFileException("Bad file " + fileName + "!! Please choose a valid file");
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/*
	 * private void filterStagingData(StagingTableDataVO stagingTableDataVO) {
	 * 
	 * stagingTableDataVO.getStructureDetailsVOs() .removeIf(structureDetails ->
	 * "".equals(structureDetails.getParentAcc()));
	 * 
	 * stagingTableDataVO.getSweepFrequencyVOs().removeIf(sweepFreq ->
	 * "".equals(sweepFreq.getParentAcc()) ||
	 * sweepFreq.getFreq().equalsIgnoreCase("M") ||
	 * sweepFreq.getFreq().equalsIgnoreCase("W"));
	 * 
	 * }
	 */
}
