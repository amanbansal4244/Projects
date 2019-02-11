package com.example.demo.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.StructureDetailsRepository;
import com.example.demo.repository.SweepFrequencyRepository;
import com.example.vo.StagingTableDataVO;

/****
 * @Service annotation is a stereotype and is used at class level that makes the
 *          class a service. A service class can act as Business Service Facade
 *          of j2EE pattern. A service class implements business logic using
 *          DAO, utility classes etc. The classes annotated with @Service are
 *          auto detected through class-path scanning. Annotating a class
 *          with @Service gives a logical sense that these classes are services.
 *          If we use @Component annotation on service class instead
 *          of @Service, there is no harm but for better readability, a service
 *          class should be annotated with @Service annotation. @Service
 *          annotation is a specialization of @Component annotation. @Service
 *          annotation has the attribute as value which is the suggestion of
 *          component name as well as a spring bean name for that class.
 *          
 *If we don't use '@Service' and we want to use inject this class to other class using @Autowired annotation then server won't get started and we would get below error:
 *	Field fileUploadDao in com.example.demo.service.FileUploadServiceImpl required a bean of type 'com.example.demo.dao.FileUploadDao' that could not be found.
 *
 */
@Service
public class FileUploadDaoImpl implements FileUploadDao {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadDaoImpl.class.getName());

	/****
	 * Note: If we are using @Autowired on StructureDetailsRepository, StructureDetailsRepository should be annotated with either 
	 * @Component or @Service or @Repository at class level.
	 */
	@Autowired
	private StructureDetailsRepository structureDetailsRepository;

	@Autowired
	private SweepFrequencyRepository sweepFrequencyRepository;

	@Transactional
	@Override
	public void saveInputFileDataIntoStaggingTables(StagingTableDataVO StagingTableDataVO) {
		structureDetailsRepository.saveAll(StagingTableDataVO.getStructureDetailsVOs());
		sweepFrequencyRepository.saveAll(StagingTableDataVO.getSweepFrequencyVOs());
	}
}
