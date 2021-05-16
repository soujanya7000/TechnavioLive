package com.freemium.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.freemium.base.Constant;

public class ExcelReader {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	private static MissingCellPolicy xRow;
	final static Logger log = Logger.getLogger(ExcelReader.class);

	// This method is to set the File path and to open the Excel file, Pass Excel
	// Path and Sheetname as Arguments to this method
	public static void setExcelFile(String Path, String SheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(System.getProperty("user.dir")+Path);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			log.info("Excel sheet opened");
		} catch (Exception e) {
			throw (e);
		}
	}

	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			return "";
		}
	}

	// This method is to write in the Excel cell, Row num and Col num are the
	// parameters
	@SuppressWarnings("static-access")
	public static void setCellData(String Result, int RowNum, int ColNum) throws Exception {
		try {
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, xRow.RETURN_NULL_AND_BLANK);

			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
				ExcelWSheet.autoSizeColumn(ColNum);
				CellStyle cs=ExcelWBook.createCellStyle();
				cs.setWrapText(true);
			} else {
				Cell.setCellValue(Result);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(System.getProperty("freemiumExcelPath") + System.getProperty("freemiumTestDataFileName"));
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}

	public static int getRowContains(String sTestCaseName, int colNum) throws Exception {
		int i;
		try {
			int rowCount = ExcelReader.getRowUsed();
			for (i = 0; i < rowCount; i++) {
				if (ExcelReader.getCellData(i, colNum).equalsIgnoreCase(sTestCaseName)) {
					break;
				}
			}
			return i;
		} catch (Exception e) {
			// Log.error("Class ExcelUtil | Method getRowContains | Exception desc : " +
			// e.getMessage());
			throw (e);
		}
	}

	public static int getRowUsed() throws Exception {
		try {
			int RowCount = ExcelWSheet.getLastRowNum();
			log.info("Total number of Row used return as < " + RowCount + " >.");
			return RowCount;
		} catch (Exception e) {
			log.error("Class ExcelUtil | Method getRowUsed | Exception desc : " + e.getMessage());
			throw (e);
		}

	}

}
