package com.se.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.se.pojo.Student;
import com.se.pojo.StudentRollCall;

public interface StudentService {
	void addStudentList(Workbook wb,String course_id);
	List<Student> getCourseStudent(String course_id);
	
	Student getStudentById(String student_id);
	void setStudentRollCallListByStudentList(List<StudentRollCall> studentRollCalls);
	List<Student> searchStudent(String info ,String course_id);
	List<List<Map<String, Integer>>> getStudentRollCallListByCourse(String course_id,List<Student> students);
	List<List<Map<String, Object>>> getStudentResultListByCourse(String course_id,
			List<Student> students);

	int CourseMaximumRollCall(String course_id);
	List<Integer> getStudentRollCallListByStudentList(String course_id, int roll_order,List<Student> students);

	List<List<Map<String, Object>>> getStudentResultListByCourseAndRollOrder(String course_id, int roll_order,List<Student> students);
	
	int getStudentCourseGrade(String course_id,String student_id);
	void addStudentCourseGrade(String course_id,String student_id,int grade);
	void updateStudentCourseGrade(String course_id,String student_id,int grade);
}
