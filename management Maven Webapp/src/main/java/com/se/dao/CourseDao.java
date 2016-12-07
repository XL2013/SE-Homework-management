package com.se.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.se.pojo.Course;

@Repository("courseDao")
public interface CourseDao {
	
	@Insert("insert into course_table(course_id,course_name,teacher_id,description) values(#{course_id},#{course_name},#{teacher_id},#{description})")
	void addCourse(Course course);
	
	@Update("update course_table set description=#{description} where course_id = #{course_id}")
	void updateCourse(Course course);
	
	@Delete("delete from course_table where course_id= #{course_id}")
	void deleteCourse(String course_id);
	
	@Select("select * from course_table where teacher_id=#{teacher_id}")
	List<Course> getAllCourse(String teacher_id);
	
	@Select("select * from course_table where course_id=#{course_id}")
	Course getCourse(String course_id);
	
	@Select("select count(*) from course_table where teacher_id=#{teacher_id}")
	int getCourseNum(String teacher_id);
	
	@Select("select course_id from stu_course where student_id=#{student_id}")
	List<String> getStudentCourses(String student_id);
}
