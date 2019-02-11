package com.sapient.global.test.fileType;

import java.io.File;
import java.io.IOException;

import com.sapient.global.test.customExecption.FileTypeNotSupportedException;

public interface FileType {

	public void processInputFileToCalculateTransactionRecord(File file) throws FileTypeNotSupportedException, IOException;

	public void printSummary() throws FileTypeNotSupportedException;

}
