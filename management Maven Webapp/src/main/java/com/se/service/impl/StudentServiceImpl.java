package com.se.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.se.dao.HomeworkDao;
import com.se.dao.RollCallDao;
import com.se.dao.StudentDao;
import com.se.dao.StudentGradeDao;
import com.se.dao.TeamDao;
import com.se.dao.TeamHomeworkDao;
import com.se.dao.UserDao;
import com.se.pojo.RollCallSetting;
import com.se.pojo.Student;
import com.se.pojo.TeamHomework;
import com.se.pojo.StudentGrade;
import com.se.pojo.User;
import com.se.service.StudentService;
import com.se.util.ExcelHelper;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentDao studentDao;
	@Resource
	private UserDao userDao;
	
	@Resource
	private TeamDao teamDao;
	
	@Resource
	private TeamHomeworkDao teamHomeworkDao;
	@Resource
	private RollCallDao rollCallDao;
	private StudentGradeDao studentGradeDao;
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
					tempFile.delete();
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
			if(teamDao.searchTeamBySC(id, course_id)==null)
			students.add(studentDao.getStudent(id));
		}
		return students;
	}
	
	@Override
	public List<List<Map<String,Integer>>> getStudentRollCallListByCourseAndRollOrder(String course_id, int roll_order, List<Student> students) {
		// TODO Auto-generated method stub
		List<List<Map<String, Integer>>> list = new ArrayList<>();
		for (Student student : students) {
			RollCallSetting rollCallSetting = rollCallDao.getRollCallSetting(course_id);
			int roll_call_time=rollCallSetting.getTotal();
			List<Map<String, Integer>> list2 = new ArrayList<>();
			for(int i=0;i<roll_call_time;i++){
				int status = rollCallDao.getStudentRollStatus(course_id, i, student.getStudent_id());
				Map<String, Integer> map = new HashMap<>();
				map.put("rollcall_ID", i+1);
				map.put("rollcall_state", status);
				list2.add(map);
			}
			list.add(list2);
		}
		return list;
	}
	
	@Override
	public List<List<Map<String, Object>>> getStudentResultListByCourseAndRollOrder(String course_id, int roll_order,
			List<Student> students) {
		// TODO Auto-generated method stub
		List<List<Map<String, Object>>> list=new ArrayList<>();
		for (Student student : students) {
			String team_id=teamDao.searchTeamBySC(student.getStudent_id(), course_id);
			List<TeamHomework> teamHomeworks = teamHomeworkDao.getTeamHomeWorks(team_id);
			List<Map<String, Object>> list2 = new ArrayList<>();
			
			for (TeamHomework teamHomework : teamHomeworks) {
				String homework_id = teamHomework.getHomework_id();
				int status = teamHomework.getStatus();
				String homework_name=teamHomeworkDao.getHomeworkName(homework_id);
				int grade = teamHomework.getGrade();
				Map<String, Object> map=new HashMap<>();
				map.put("homework_name", homework_name);
				map.put("grade", grade);
				map.put("status", status);
				list2.add(map);
			}
			list.add(list2);
		}
		return list;
	@Override
	public int getStudentCourseGrade(String course_id, String student_id) {
			return studentGradeDao.getStudentCourseGrade(course_id, student_id);
	}
	@Override
	public void addStudentCourseGrade(String course_id, String student_id, int grade) {
		StudentGrade studentGrade =new StudentGrade();
		studentGrade.setCourse_id(course_id);
		studentGrade.setGrade(grade);
		studentGrade.setStudent_id(student_id);
		studentGradeDao.addStudentGrade(studentGrade);
				
	}
	@Override
	public void updateStudentCourseGrade(String course_id, String student_id, int grade) {
		studentGradeDao.updateGrade(course_id, student_id, grade);
		
	}

}
