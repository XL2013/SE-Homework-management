package com.se.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.se.dao.CourseDao;
import com.se.pojo.Course;
import com.se.service.CourseService;

@Service("courseService")
public class CourseServiceImpl implements CourseService{

	@Resource
	private CourseDao courseDao;
	
	public void updateDescription(String course_id, String description) {
			Course course=  courseDao.getCourse(course_id);
			course.setDescription(description);
			courseDao.updateCourse(course);		
	}

	@Override
	public List<Course> getTeacherCourse(String teacher_id) {
		return courseDao.getAllCourse(teacher_id);
	}

	@Override
	public void deleteCourse(String course_id) {
		courseDao.deleteCourse(course_id);		
	}
	
	/**
	 * course_id 这里设定为根据老师的id生成，先暂定不能删除课程
	 * @return 返回课程id
	 */
	public String addCourse(String course_name, String teacher_id, String description) {		
		Course course=new Course();
		String course_id=getCourse_id(teacher_id); 
		course.setCourse_id(course_id);
		course.setCourse_name(course_name);
		course.setDescription(description);
		course.setTeacher_id(teacher_id);
		courseDao.addCourse(course);
		return course_id;
	}
	
	/**
	 * 这个算法可以后期修改，现在暂且这样写
	 * @param teacher_id
	 * @return 根据一定规则生成的课程id
	 */
	private String getCourse_id(String teacher_id){
		return teacher_id+String.valueOf(courseDao.getCourseNum(teacher_id));
	}

	@Override
	public Course getCourse(String course_id) {
		// TODO Auto-generated method stub
		return courseDao.getCourse(course_id);
	}

}
