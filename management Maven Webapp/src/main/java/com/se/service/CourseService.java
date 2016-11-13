package com.se.service;

import java.util.List;

import com.se.pojo.Course;

public interface CourseService {
	
		void updateDescription(String course_id,String description);
		List<Course> getTeacherCourse(String teacher_id);
		void deleteCourse(String course_id);
		String addCourse(String course_name,String teacher_id,String description);
}
