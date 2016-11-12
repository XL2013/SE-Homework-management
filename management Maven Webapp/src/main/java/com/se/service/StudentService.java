package com.se.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.se.pojo.Student;

public interface StudentService {
		
	void addStudentList(Workbook wb);
	List<Student> getCourseStudent(String course_id);
}
