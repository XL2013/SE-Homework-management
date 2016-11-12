package com.se.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.se.pojo.Student;

@Repository("studentDao")
public interface StudentDao {
		
		/**或者这样写： select * from student_table left out join stu_course on student_table.student_id=stu_course.student_id where
		 *  course_id=#{course_id}
		 */
		@Select("select * from student_table where student_id in "
				+ "(select student_id from stu_course where course_id=#{course_id})")
		List<Student> getStudentList(String course_id);
		
		@Select("select * from student_table where student_id=#{student_id}")
		Student getStudent(String student_id);
		
		@Insert("insert into student_table(student_id,student_name,class_id) values(#{student_id},#{student_name},#{class_id})")
		void addStudent(Student student);
		
		@Insert("insert into stu_course(student_id,course_id) values(#{student_id},#{course_id})")
		void addStudentCourse(@Param("student_id")String student_id,@Param("course_id") String course_id);
		
		@Delete("delete from stu_course where student_id=#{student_id} and course_id=#{course_id}")
		void deleteStudentCourse(@Param("student_id")String student_id,@Param("course_id") String course_id);
}
