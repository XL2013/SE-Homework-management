package com.se.util;

import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class ExcelHelper {
		
	
	public static boolean validateExcel(String filePath){
			if(filePath==null||!(isExcel2003(filePath)||isExcel2007(filePath)))
				return false;
			return true;
	}
	
	public static  boolean isExcel2003(String filePath){
		return filePath.matches("^.+\\.(?i)(xls)$");
	}
	public static boolean isExcel2007(String filePath){
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}
	
	public static Workbook getExCelWorkbook(InputStream is,String filePath){
		try{
		if(isExcel2003(filePath))
			return new HSSFWorkbook(is);
		else if(isExcel2007(filePath)) 
			return new XSSFWorkbook(is);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		}
	
}
