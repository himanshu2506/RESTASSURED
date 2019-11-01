package com.RestAssured.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLib 
{
	private static Workbook wb;
	private static Sheet sh;
	private static FileInputStream fis;
	private static FileOutputStream fos;
	private static Row r;
	private static Cell c;

	static String excelPath = LoadProperty.getVar("excelpath", "config");
	static String Path = System.getProperty("user.dir")+excelPath;
	
	
	public String getExcelData(String sheetName, int rowNum, int colNum) throws InvalidFormatException, IOException 
	{
		//String Path = System.getProperty("user.dir")+excelPath;
		fis = new FileInputStream(Path);
		wb = WorkbookFactory.create(fis);
		sh = wb.getSheet(sheetName);
		r = sh.getRow(rowNum);
		c = r.getCell(colNum);
		c.setCellType(Cell.CELL_TYPE_STRING);
		String data = c.getStringCellValue();
		data = data.toString();
		return data;
	}
	/*public static void main(String [] args)
	{
		System.out.println(Path);
	}*/

}
