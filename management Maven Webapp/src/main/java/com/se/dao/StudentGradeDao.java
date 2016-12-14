package com.se.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.se.pojo.StudentGrade;

@Repository("studentGradeDao")
public interface StudentGradeDao {
	@Insert("insert into student_course_grade(student_id,course_id,grade) values(#{student_id},#{course_id},#{grade})")
	void addStudentGrade(StudentGrade studentGrade);
	@Select("select * from student_course_grade where course_id=#{course_id} and student_id=#{student_id}")
	StudentGrade getStudentCourseGrade(@Param("course_id")String course_id,@Param("student_id")String student_id);
	@Update("update student_course_grade set grade=#{grade} where course_id=#{course_id} and student_id=#{student_id}")
	void updateGrade(@Param("course_id")String course_id,@Param("student_id")String student_id,@Param("grade")int grade);
}
