package com.example.demo.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.entity.StructureDetails;
import com.example.demo.entity.SweepFrequency;
import com.example.demo.mid.MIDConstants;
import com.example.vo.StagingTableDataVO;
import com.example.vo.StructureDetailsVO;
import com.example.vo.SweepFrequencyVO;

public class ReadInputFile {
	private static final int SHEET_IDX_SWEEP = 1;
	private static final int SHEET_IDX_STRUCTURE = 0;
	private static final int SKIP_HEADER_INDEX = 0;

	private static String CLASSNAME = ReadInputFile.class.getName().toString();
	private static final Logger logger = LoggerFactory.getLogger(CLASSNAME);

	static {
		MIDConstants.SHEET_INDEX_CLASS_MAP.put(SHEET_IDX_STRUCTURE, StructureDetailsVO.class.getName());
		MIDConstants.SHEET_INDEX_CLASS_MAP.put(SHEET_IDX_SWEEP, SweepFrequencyVO.class.getName());

	}


	private ReadInputFile() {

	}

	public static StagingTableDataVO readInputFile(final InputStream fileInputStream, String fileName) {

		List<StructureDetails> structureDetailsVOs = new ArrayList<StructureDetails>();
		List<SweepFrequency> sweepFrequencyVOs = new ArrayList<SweepFrequency>();

		BufferedInputStream bis = new BufferedInputStream(fileInputStream);

		StagingTableDataVO stagingTableDataVO = new StagingTableDataVO();

		try(Workbook workbook = WorkbookFactory.create(bis)){
			logger.debug("Workbook has:" + workbook.getNumberOfSheets() + " Sheets");
			for(Integer sheetIndex : MIDConstants.SHEET_INDEX_CLASS_MAP.keySet()) {
				//Note : 0 index means sheet 1 in excel
				Sheet sheet = workbook.getSheetAt(sheetIndex);
				logger.debug("Sheet name:" + sheet.getSheetName());
				if(sheetIndex == SHEET_IDX_STRUCTURE ) {
					loadStructureDetailsVO(structureDetailsVOs, sheet, fileName);
				}else if(sheetIndex == SHEET_IDX_SWEEP ) {
					loadSweepFrequencyVO(sweepFrequencyVOs, sheet, fileName);
				}
			}
		}catch(Exception e) {
			logger.error("Error while reading input file. Caused By : " + e.getMessage());
			throw new RuntimeException();
		}

		stagingTableDataVO.setStructureDetailsVOs(structureDetailsVOs);
		stagingTableDataVO.setSweepFrequencyVOs(sweepFrequencyVOs);

		return stagingTableDataVO;

	}

	private static void loadStructureDetailsVO(final List<StructureDetails> structureDetailsVOs, Sheet sheet, String fileName) {
		DataFormatter dataFormatter = new DataFormatter();
		
		Row firstRow = sheet.getRow(0);
		// Iterate through each rows one by one
		for(int i=1; i<=sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			// For each row, iterate through all the columns.
			StructureDetails structureDetailsVO = new StructureDetails();
			structureDetailsVO.setFileName(fileName);
			structureDetailsVO.setBrn(dataFormatter.formatCellValue(row.getCell(0)));
			structureDetailsVO.setAcc(dataFormatter.formatCellValue(row.getCell(1)));
			structureDetailsVO.setCcy(dataFormatter.formatCellValue(row.getCell(2)));
			
			structureDetailsVOs.add(structureDetailsVO);
			
		}

	}
	

	private static void loadSweepFrequencyVO(final List<SweepFrequency> sweepFrequencyVOs, Sheet sheet, String fileName) {
		DataFormatter dataFormatter = new DataFormatter();
		// Iterate through each rows one by one
		for(int i=1; i<=sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			SweepFrequency sweepFrequencyVO = new SweepFrequency();
			sweepFrequencyVO.setFileName(fileName);
			sweepFrequencyVO.setBrn(dataFormatter.formatCellValue(row.getCell(0)));
			sweepFrequencyVO.setAcc(dataFormatter.formatCellValue(row.getCell(1)));
			sweepFrequencyVO.setCcy(dataFormatter.formatCellValue(row.getCell(2)));

			sweepFrequencyVOs.add(sweepFrequencyVO);

		}

	}


}

