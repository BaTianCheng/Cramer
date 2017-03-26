package com.cw.cramer.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * EXCEL操作工具类
 * @author wicks
 */
public class ExcelUtils {
	
	/**
	 * 读取EXCEL文件
	 */
	public static List<String[]> readeExcel(String path, String filename) {
		List<String[]> excelList = new ArrayList<String[]>();
		try {
			String excelFilePath = path + FileUtils.inputPath + filename;
			File file = new File(excelFilePath);
			FileInputStream fIP = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fIP);
			XSSFSheet spreadsheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = spreadsheet.iterator();
			XSSFRow row;
			String[] strs;
			DecimalFormat df = new DecimalFormat("0");  
			while (rowIterator.hasNext()) {
				row = (XSSFRow) rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				strs = new String[row.getLastCellNum()];
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) 
		            {
		               case Cell.CELL_TYPE_NUMERIC:
		            	   strs[cell.getColumnIndex()] = String.valueOf(df.format(cell.getNumericCellValue()));
		               break;
		               case Cell.CELL_TYPE_STRING:
		            	   strs[cell.getColumnIndex()] = cell.getStringCellValue();
		               break;
		            }
				}
				excelList.add(strs);
			}
			fIP.close();
		} catch (Exception ex) {
			LogUtils.error("读取Excel文件失败，" + filename, ex);
		}
		return excelList;
	}

	/**
	 * 写入EXCEL文件
	 */
	public static String writeExcel(String path, String filename, List<String[]> excelList) {
		try {
			String excelFilePath = path + FileUtils.outputPath + filename;
			if (excelList != null && excelList.size() > 0) {
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet spreadsheet = workbook.createSheet("结果数据");
				XSSFRow row;
				spreadsheet.setDefaultColumnWidth(15);
				int rowid = 0;
				for (String[] strs : excelList) {
					row = spreadsheet.createRow(rowid++);
					int cellid = 0;
					for (String str : strs) {
						Cell cell = row.createCell(cellid++);
						cell.setCellValue(str);
					}
				}
				FileOutputStream out = new FileOutputStream(new File(excelFilePath));
				workbook.write(out);
				out.close();
			}
			return excelFilePath;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
