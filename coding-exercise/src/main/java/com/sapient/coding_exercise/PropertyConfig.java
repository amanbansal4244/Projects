/**
 * @author virjangi
 *
 *//*
package com.sapient.coding_exercise;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.sapient.coding_exercise.constants.CommonConstants;

@Component
@PropertySource(value= {"classpath:application.properties"})
public class PropertyConfig {
	@Autowired
	Environment environment;
	private static Properties prop;
	private PropertyConfig(){
		prop = new Properties();
		InputStream input;
		try {
			input = new FileInputStream(".\\resources\\"+CommonConstants.PROPERTY_FILE_PATH);
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	
	public static Properties propertyConfig() {
		return prop;
	}
}
*/