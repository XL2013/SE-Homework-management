package com.se.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.se.pojo.Student;
import com.se.service.impl.HomeworkServiceImpl;
import com.se.service.impl.StudentServiceImpl;

@Controller
public class FileController {

	@Resource
	HomeworkServiceImpl homeworkService;
	@Resource
	StudentServiceImpl studentService;
	
	@GetMapping("file/download")
	@ResponseBody
	public void teamHomeworkfileDownload(String team_id,String homework_id,String file_name) throws UnsupportedEncodingException{
		HttpServletResponse response=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		HttpServletRequest request =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String path= request.getSession().getServletContext().getRealPath("/WEB-INF/upload/studentHomework/");
		path=path+"\\"+homeworkService.getHomework(homework_id).getCourse_id()+"\\"+homework_id+"\\"+team_id+"\\";
		String file_name1=file_name;
		file_name=new String(file_name.getBytes("iso8859-1"),"utf-8");	//解决中文编码问题

        InputStream inputStream = null;  
        OutputStream outputStream = null;
        File file=new File(path,file_name);
        try{
        	inputStream = new FileInputStream(file);  
        	outputStream = new BufferedOutputStream(response.getOutputStream());   
          
            byte[] buffer = new byte[inputStream.available()];  
            inputStream.read(buffer);
            inputStream.close();
            
            response.reset();
         // 设置response的Header  
        	//1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
            response.setContentType("multipart/form-data");  
            response.addHeader("Content-Disposition","attachment;filename=" + file_name1);  
            response.addHeader("Content-Length", "" + file.length());  
            
            outputStream.write(buffer);
            outputStream.close();
            outputStream.flush();
        	
        }catch(Exception e){
        	e.printStackTrace();
        }
				
	}
	
	@GetMapping(value="file/courseGradeFile")
	@ResponseBody
	public void gradeFileDownload(String course_id){
		HttpServletResponse response=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String path= request.getSession().getServletContext().getRealPath("/WEB-INF/courseGradeFiles/");
		InputStream inputStream = null;  
        OutputStream outputStream = null;
		File gradeFile=new File(path,course_id+".xlsx");
		 try{
	        	inputStream = new FileInputStream(gradeFile);  
	        	outputStream = new BufferedOutputStream(response.getOutputStream());   
	          
	            byte[] buffer = new byte[inputStream.available()];  
	            inputStream.read(buffer);
	            inputStream.close();
	            
	            response.reset();
	         // 设置response的Header  
	        	//1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
	            response.setContentType("multipart/form-data");  
	            response.addHeader("Content-Disposition","attachment;filename=" + course_id+".xlsx");  
	            response.addHeader("Content-Length", "" + gradeFile.length());  
	            
	            outputStream.write(buffer);
	            outputStream.close();
	            outputStream.flush();
	        	
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	}
	
	@PostMapping(value="file/getCourseTotalGrade")
	@ResponseBody
	public Map<String, Object> getCourseTotalGrade(String course_id){
		Map<String,Object> data=new HashMap<String, Object>();
		List<Student> students=studentService.getCourseStudent(course_id);
		studentService.getStudentCourseTotalGradeByStudentList(course_id,students);
		createExcel(course_id);
		data.put("course_id", course_id);
		return data;
	}
	public void createExcel(String course_id){
		//创建空的EXCEL文件
		HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String path= request.getSession().getServletContext().getRealPath("/WEB-INF/courseGradeFiles/");
		File dirPath=new File(path);
		if(!dirPath.exists())
			dirPath.mkdirs();
		File gradeFile=new File(dirPath,course_id+".xlsx");
		
		Workbook workBook=new XSSFWorkbook();
		Sheet sheet1=workBook.createSheet("sheet1");
		String titleRow[]={"学号","班级","姓名","期末成绩"};
		FileOutputStream out=null;
		try{
			
			//创建表头
			Row row=workBook.getSheet("sheet1").createRow(0);
			for(int i=0;i<titleRow.length;i++){
				Cell cell=row.createCell(i);
				cell.setCellValue(titleRow[i]);
			}
			//添加数据
			int i=1;
			for(Student student : studentService.getCourseStudent(course_id)){
				Row cRow=workBook.getSheet("sheet1").createRow(i++);
				//学号
				Cell cell0=cRow.createCell(0);
				cell0.setCellValue(student.getStudent_id());
				//班级
				Cell cell1=cRow.createCell(1);
				cell1.setCellValue(student.getClass_id());
				//姓名
				Cell cell2=cRow.createCell(2);
				cell2.setCellValue(student.getStudent_name());
				//成绩
				Cell cell3=cRow.createCell(3);
				cell3.setCellValue(studentService.getStudentCourseGrade(course_id, student.getStudent_id()));
			}
			out=new FileOutputStream(gradeFile);
			workBook.write(out);
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
