package dev.procheck.excel.fusion.fusion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRangeCopier;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class FusionExcelWithHSSF {

	public static void fusionExcel(String path) throws IOException {
		// Read XSL file
		FileInputStream inputStream = new FileInputStream(new File(path));
		// Get the workbook instance for XLS file
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			HSSFSheet sourceSheet = workbook.getSheetAt(i);
			ExcelRemoveMergedRegion.removeMergedRegion(sourceSheet, CellRangeAddress.valueOf("G3:H3"));
			ExcelRemoveMergedRegion.removeMergedRegion(sourceSheet, CellRangeAddress.valueOf("A8:B8"));
			ExcelRemoveMergedRegion.removeMergedRegion(sourceSheet, CellRangeAddress.valueOf("C8:D8"));
		}
		for (int i = 1; i < workbook.getNumberOfSheets(); i++) {
			// get active sheet
			HSSFSheet sourceSheet = workbook.getSheetAt(i);
			// get lastRowNum
			int lastSourceRowCount = sourceSheet.getLastRowNum()+1;
			// range "A11:H"+lastrowCount
			CellRangeAddress cellRangeAddressSource = CellRangeAddress.valueOf("A11:H" + lastSourceRowCount);
			// get sheet number 0
			HSSFSheet destinationSheet0 = workbook.getSheetAt(0);
			// get lastRowNum sheet number 0
			int lastDestinationRowCountSheet0 = destinationSheet0.getLastRowNum()+1;
			// range "A"+lastrowCountSheet0+1
			CellRangeAddress cellRangeAddressDestination = CellRangeAddress
					.valueOf("A" + (lastDestinationRowCountSheet0 +(i!=1 ? 1:0)) + ":H"
							+ (lastDestinationRowCountSheet0 + (lastSourceRowCount - 10 -1)));
			// copy cellRangeAddressSource and paste in cellRangeAddressDestination
			HSSFRangeCopier hssfRangeCopier = new HSSFRangeCopier(sourceSheet, destinationSheet0);
			hssfRangeCopier.copyRange(cellRangeAddressSource, cellRangeAddressDestination, true, true);
//			for (int j = 10; j < lastSourceRowCount; j++) {
//				sourceSheet.removeRow(sourceSheet.getRow(j));
//			}
		}
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			HSSFSheet sourceSheet = workbook.getSheetAt(i);
			sourceSheet.addMergedRegion(CellRangeAddress.valueOf("A8:B8"));
			sourceSheet.addMergedRegion(CellRangeAddress.valueOf("C8:D8"));
			sourceSheet.addMergedRegion(CellRangeAddress.valueOf("G3:H3"));

		}
		System.out.println();
		File file = new File(path);
		file.getParentFile().mkdirs();
		FileOutputStream outFile = new FileOutputStream(file);
		workbook.write(outFile);
		workbook.close();
	}
	
	}