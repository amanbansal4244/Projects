package com.sapient.global.test.fileType;

import com.sapient.global.test.utility.TransactionRecordUtility;

public class FileTypeReaderFactory {
	
	public static FileType getFileType(String inputFileType){
		FileType fileType = null;
		if (inputFileType.equalsIgnoreCase("csv")) {
			fileType = new CsvFileType();
			CsvFileType csvFileType = (CsvFileType) fileType;
			csvFileType.setProcessTransactionRecordUtility(TransactionRecordUtility.getObject());
		}
		else if (inputFileType.equalsIgnoreCase("xml")) {
			fileType = new XMLFileType();
		}
		else if (inputFileType.equalsIgnoreCase("xlsx")) {
			fileType = new ExcelFileType_XLSX_Extenstion();
		}
		
		return fileType;
	}
}
