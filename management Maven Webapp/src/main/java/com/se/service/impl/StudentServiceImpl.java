package com.se.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.se.dao.StudentDao;
import com.se.dao.UserDao;
import com.se.pojo.Student;
import com.se.pojo.User;
import com.se.service.StudentService;
import com.se.util.ExcelHelper;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentDao studentDao;
	@Resource
	private UserDao userDao;
	/**
	 * excel表定义:
	 * student_id  | class_id | student_name
	 * @focus:必须如此，否则会出现写入错误的数据的情况
	 * @todo:增加表格验证,可能后续还要增加事物操作，因为是一次性写入数据。
	 */
	public void addStudentList(Workbook wb,String course_id) {		
			//Read the sheets
			for(int sheetNum=0;sheetNum<wb.getNumberOfSheets();sheetNum++){
				Sheet sheet=wb.getSheetAt(sheetNum);
				if(sheet==null) continue;
				//Read the rows,begin from row one
				for(int rowNum=1;rowNum<sheet.getPhysicalNumberOfRows();rowNum++){
					Row row=sheet.getRow(rowNum);
					if(row!=null){
						String student_id=getValue(row.getCell(0));
						String class_id=getValue(row.getCell(1));
						String student_name=getValue(row.getCell(2));
						Student student=new Student();
						student.setStudent_id(student_id);
						student.setClass_id(class_id);
						student.setStudent_name(student_name);
											
						if(studentDao.getStudent(student_id)==null){
							//add student
							studentDao.addStudent(student);
							
							//add user
							User user=new User();
							user.setUser_id(student_id);
							user.setUser_name(student_name);
							user.setUser_pwd(student_id);
							user.setUser_role(3);
							userDao.addUser(user);
						}
						//add constrain
						studentDao.addStudentCourse(getValue(row.getCell(0)), course_id);	
						
					}
				}
			}
		
	}
	public void readStudentInfo(MultipartFile[] files,String course_id){
		HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String path= request.getSession().getServletContext().getRealPath("/upload/studentList/");
		File dirPath=new File(path);
		if(!dirPath.exists())
			dirPath.mkdirs();
		if(files.length!=0){
			for(MultipartFile file : files){
				try {
					File tempFile=new File(dirPath,file.getOriginalFilename());
					if(!ExcelHelper.validateExcel(tempFile.getPath())) continue;
					file.transferTo(tempFile);	
					InputStream is=new FileInputStream(tempFile);
					addStudentList(ExcelHelper.getExCelWorkbook(is,tempFile.getPath()),course_id);
					is.close();
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}				
			}
		}
	}

	@Override
	public List<Student> getCourseStudent(String course_id) {
		return studentDao.getStudentList(course_id); 
		
	}

	private String getValue(Cell cell){
		cell.setCellType(CellType.STRING);
		return cell.getStringCellValue();
	}
	@Override
	public Student getStudentById(String student_id) {
		// TODO Auto-generated method stub
		return studentDao.getStudent(student_id);
	}
	@Override
	public List<Student> searchStudent(String info, String course_id) {
		// TODO Auto-generated method stub
		List<Student> students=new ArrayList<Student>();
		for(String id : studentDao.searchStudent(info, course_id)){
			students.add(studentDao.getStudent(id));
		}
		return students;
	}

}
