package com.sapient.global.test.fileType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.sapient.global.test.Thread.TransactionRecordThread;
import com.sapient.global.test.constants.TransactionRecordPriorityBasedTypeConstant;
import com.sapient.global.test.customExecption.FileTypeNotSupportedException;
import com.sapient.global.test.model.TransactionRecord;
import com.sapient.global.test.model.TransactionRecordClientIdSorting;
import com.sapient.global.test.model.TransactionRecordDateSorting;
import com.sapient.global.test.model.TransactionRecordPrioritySorting;
import com.sapient.global.test.model.TransactionRecordTypeSorting;
import com.sapient.global.test.utility.TransactionRecordSortingUtility;
import com.sapient.global.test.utility.TransactionRecordUtility;

public class ExcelFileType_XLSX_Extenstion implements FileType {

	protected TransactionRecordUtility transactionRecordUtility;

	public void setProcessTransactionRecordUtility(TransactionRecordUtility transactionRecordUtility) {
		this.transactionRecordUtility = transactionRecordUtility;
	}

	@Override
	public void processInputFileToCalculateTransactionRecord(File file) throws FileTypeNotSupportedException, IOException {
		try {
			/****
			 * . The Apache API Basics
			There are two main prefixes which you will encounter when working with Apache POI:
			HSSF: denotes the API is for working with Excel 2003 and earlier.
			XSSF: denotes the API is for working with Excel 2007 and later.
			And to get started the Apache POI API, you just need to understand and use the following 4 interfaces:
			Workbook: high level representation of an Excel workbook. Concrete implementations are: HSSFWorkbook and XSSFWorkbook.
			Sheet: high level representation of an Excel worksheet. Typical implementing classes are HSSFSheet and XSSFSheet.
			Row: high level representation of a row in a spreadsheet. HSSFRow and XSSFRow are two concrete classes.
			Cell: high level representation of a cell in a row. HSSFCell and XSSFCell are the typical implementing classes.
			 */
			FileInputStream fileInputStream = new FileInputStream(file);

			// Create Workbook instance holding .xls file
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			// Get the first worksheet or Get first/desired sheet from the workbook
			XSSFSheet datatypeSheet = workbook.getSheetAt(0);
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = datatypeSheet.iterator();

			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				// For each row, iterate through all the columns.
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				int colIndex = 0;
				
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					String fieldValue = currentCell.toString();
					String colName = datatypeSheet.getRow(0).getCell(colIndex).toString();
					String fieldName = "";
					if(colName.indexOf("_") != 1) {
						String[] lst = colName.split("_");
						fieldName = lst[0].toLowerCase();
						new TransactionRecordUtility().setTransactionRecordThroughReflection(fieldName,fieldValue, new TransactionRecord() );
					}
					colIndex = colIndex + 1;
					//getCellTypeEnum shown as deprecated for version 3.15
					//getCellTypeEnum will be renamed to getCellType starting from version 4.0
					/*if (currentCell.getCellTypeEnum() == CellType.STRING) {
						System.out.print(currentCell.getStringCellValue() + "--");
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						System.out.print(currentCell.getNumericCellValue() + "--");
					}else if (currentCell.getCellTypeEnum() == CellType.BOOLEAN) {
						System.out.print(currentCell.getNumericCellValue() + "--");
					}
					System.out.print(" - ");*/
				}

			}


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void printSummary() throws FileTypeNotSupportedException {
		List<TransactionRecord> list = transactionRecordUtility.getAllTransactionRecord();
		Collections.sort(list, new TransactionRecordSortingUtility(new TransactionRecordClientIdSorting(), new TransactionRecordTypeSorting(), new TransactionRecordDateSorting(), new TransactionRecordPrioritySorting()));
		
		System.out.println("Client Id: | Transaction Type:     | Transaction Date:                | Priority: | Processing Fee:");
		for(int i=0; i<list.size();i++){
			System.out.println(list.get(i).getClientId() + "         |   " + list.get(i).getTransactionType() + 
					"                | " + list.get(i).getTransactionDate() + "      | " + ((list.get(i).getPriorityFlag().equals(TransactionRecordPriorityBasedTypeConstant.HIGH_PRIORITY)) ? "High" : "Normal") +
					"      | " + list.get(i).getProcessingFees() + "\t");
		}
	}

	
}
