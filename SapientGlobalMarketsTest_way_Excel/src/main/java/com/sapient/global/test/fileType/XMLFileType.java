package com.sapient.global.test.fileType;

import java.io.File;
import java.io.IOException;

import com.sapient.global.test.customExecption.FileTypeNotSupportedException;

public class XMLFileType implements FileType {

	@Override
	public void processInputFileToCalculateTransactionRecord(File file) throws FileTypeNotSupportedException, IOException {
		throw new FileTypeNotSupportedException("In processInputFileToCalculateTransaction() method. XML format is not supported.");
	}

	@Override
	public void printSummary() throws FileTypeNotSupportedException {
		throw new FileTypeNotSupportedException("In printSummary() method . XML format is not supported.");
	}

}
