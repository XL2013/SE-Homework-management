package other;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.io.File;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class DataCreate {
	
	public static void createFile(String fileDir){
		File file=new File(fileDir);
		if(file.exists()) return;
		else
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static  void createExcel(String fileDir,String sheetName){
		Workbook workBook=new XSSFWorkbook();
		Sheet sheet1=workBook.createSheet(sheetName);
		String titleRow[]={"学号","班级","姓名"};
		FileOutputStream out=null;
		try{
			
			//创建表头
			Row row=workBook.getSheet(sheetName).createRow(0);
			for(int i=0;i<titleRow.length;i++){
				Cell cell=row.createCell(i);
				cell.setCellValue(titleRow[i]);
			}
			//添加数据
			for(int i=1;i<100;i++){
				Row cRow=workBook.getSheet(sheetName).createRow(i);
				//学号
				Cell cell0=cRow.createCell(0);
				cell0.setCellValue("2013211"+transInt(i));
				//班级
				Cell cell1=cRow.createCell(1);
				cell1.setCellValue("2013211304");
				//姓名
				Cell cell2=cRow.createCell(2);
				cell2.setCellValue(getRandomName());
			}
			out=new FileOutputStream(fileDir);
			workBook.write(out);
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//将数字变为相应的三位字符串
	public static String transInt(int num){
		String prefix="";
		if(num<10) prefix="00";
		else if(num<100) prefix="0";
		
		return prefix+String.valueOf(num);			
	}
	public static String getRandomName(){
		String firstName[]={"周","吴","郑","王","李","丁","陈","翟","蔡","毕"};
		return firstName[RandomUtils.nextInt(0, firstName.length)]+RandomStringUtils.randomAlphabetic(2);
	}
	public static void main(String args[]){
		String fileDir="c:/资源文件/student1.xlsx";
		createFile(fileDir);
		createExcel(fileDir, "sheet1");
	}
}
