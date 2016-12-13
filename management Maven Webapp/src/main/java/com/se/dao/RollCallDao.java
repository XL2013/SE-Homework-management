package com.se.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.se.pojo.RollCallSetting;
import com.se.pojo.StudentRollCall;

/**
 * 这个dao封装了对rollCallSetting和StudentRollcall的sql操作
 * @author zzs
 *
 */
@Repository("rollCallDao")
public interface RollCallDao {
		
		// rollcallsetting 部分dao代码
		@Insert("insert into rollcallSetting(course_id,total) values(#{course_id},#{total})")
		void addRollCallSetting(RollCallSetting rollCallSetting);
		
		@Update("update rollcallSetting set total=#{total} where course_id=#{course_id}")
		void updateRollCallSetting(@Param("course_id")String course_id,@Param("total")int total);
		
		@Select("select * from rollCallSetting where course_id=#{course_id}")
		RollCallSetting getRollCallSetting(String course_id);
		//todo:增加删除操作，以为后期的删除课程做准备
		
		
		//studentRollCall 部分dao代码
		@Insert("insert into student_rollcall(course_id,roll_order,status,student_id) values(#{course_id},#{roll_order},#{status},#{student_id})")
		void addStudentRollCall(StudentRollCall studentrollcall);
		
		@Update("update student_rollcall set status=#{status} where course_id=#{course_id} and roll_order=#{roll_order} and student_id=#{student_id}")
		void setStudentRollCallStatus(@Param("course_id")String course_id,@Param("roll_order")int roll_order,@Param("status")int status,@Param("student_id")String student_id);
		
		@Select("select  status from student_rollcall where course_id=#{course_id} and roll_order=#{roll_order} and student_id=#{student_id}")
		int getStudentRollStatus(@Param("course_id")String course_id,@Param("roll_order")int roll_order,@Param("student_id")String student_id);
		//todo: 增加删除代码
		@Select("select count(*) from student_rollcall where course_id=#{course_id} and student_id=#{student_id} and status=1")
		int getStudentRollCallTimes(@Param("course_id")String course_id,@Param("student_id")String student_id);
		@Select("select count(*) from student_rollcall where course_id=#{course_id} and student_id=#{student_id}")
		int getStudentRollCallTotals(@Param("course_id")String course_id,@Param("student_id")String student_id);
		
}
