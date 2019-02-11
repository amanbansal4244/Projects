package com.sapient.coding_exercise;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {
	
	@Before("within(com.sapient.coding_exercise.controller.*) ||  within(com.sapient.coding_exercise.service.*) || within(com.sapient.coding_exercise.repository.*) ||"
			+ "within(com.sapient.coding_exercise.file_loader.*) || within(com.sapient.coding_exercise.file_loader.*)")
	public void logBefore(JoinPoint joinPoint) {
		final Logger logger = LoggerFactory.getLogger(joinPoint.getClass());
		logger.info("Method called from  " + joinPoint.getTarget().getClass()+" -- "+joinPoint.getSignature().getName());
	}

	@After("within(com.sapient.coding_exercise.controller.*) ||  within(com.sapient.coding_exercise.service.*) || within(com.sapient.coding_exercise.repository.*) ||"
			+ "within(com.sapient.coding_exercise.file_loader.*) || within(com.sapient.coding_exercise.file_loader.*)")
	public void logAfter(JoinPoint joinPoint) {
		final Logger logger = LoggerFactory.getLogger(joinPoint.getClass());
		logger.info("Method called from  " + joinPoint.getTarget().getClass()+" -- "+joinPoint.getSignature().getName());
	}
}
