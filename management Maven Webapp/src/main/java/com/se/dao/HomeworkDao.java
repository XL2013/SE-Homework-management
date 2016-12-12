package com.se.dao;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.se.pojo.Homework;

@Repository("homeworkDao")
public interface HomeworkDao {
	

	@Insert("insert into homework_table(course_id,homework_name,homework_id,description,upload_time,release_time,ratio) "
			+ "values(#{course_id},#{homework_name},#{homework_id},#{description},to_date(#{upload_time},'yyyy-mm-dd'),"
			+ "sysdate,#{ratio})")
	void addHomework(Homework homework);
	
	@Select("select * from homework_table where course_id=#{course_id}")
	List<Homework> getHomeworksInfoByCourseID(String course_id);
	
	@Update("update homework_table set ratio=#{ratio} where homework_id=#{homework_id}")
	void updateHomeworkRatio(@Param("homework_id")String homework_id, @Param("ratio")double ratio);
	
	@Select("select * from homework_table where homework_id=#{homework_id}")
	Homework getHomeworkById(String homework_id);

}
