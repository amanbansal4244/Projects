package com.sgm.transaction.management.main.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
/**
*This is an entry point to run Transaction management assignment.
*
* @author Aman Bansal
* @version   1.0
**/
public class TestRunner {

	public static void main(String[] args) 
	{
		Result resultObj = JUnitCore.runClasses(JunitTestSuite.class);
		for(Failure failure : resultObj.getFailures())
		{
			System.out.println(failure.toString());
		}
		System.out.println(resultObj.wasSuccessful());
	}
}