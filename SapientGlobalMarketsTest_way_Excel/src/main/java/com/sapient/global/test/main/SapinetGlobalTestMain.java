package com.sapient.global.test.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.sapient.global.test.customExecption.FileTypeNotSupportedException;
import com.sapient.global.test.fileType.FileType;
import com.sapient.global.test.fileType.FileTypeReaderFactory;

public class SapinetGlobalTestMain {

	private void openAndReadFile(String fileName) throws FileNotFoundException, FileTypeNotSupportedException, IOException  {
		File file = new File(new File("").getAbsolutePath(), "src/main/resources/" + fileName);
		if(file.exists()){
				FileType fileTypeObject = FileTypeReaderFactory.getFileType(fileName.substring(fileName.lastIndexOf('.') + 1));
				fileTypeObject.processInputFileToCalculateTransactionRecord(file);
				fileTypeObject.printSummary();
		}else{
			throw new FileNotFoundException();
		}
	}

	public static void main(String[] args) throws FileTypeNotSupportedException, IOException {
		SapinetGlobalTestMain obj = new SapinetGlobalTestMain();
		obj.openAndReadFile("SampleData1.xlsx");
	}
}
