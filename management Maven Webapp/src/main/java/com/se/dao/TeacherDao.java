package com.se.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.se.pojo.Teacher;

@Repository("teacherDao")
public interface TeacherDao {
		@Insert("insert into teacher_table(teacher_id,teacher_name) values(#{teacher_id},#{teacher_name})")
		void addTeacher(Teacher teacher);
		
		@Delete("delete from teacher_table where teacher_id=#{teacher_id}")
		void deleteTeacher(String teacher_id);
		
		@Select("select * from teacher_table where teacher_id=#{teacher_id}")
		Teacher getTeacher(String teacher_id);
}
