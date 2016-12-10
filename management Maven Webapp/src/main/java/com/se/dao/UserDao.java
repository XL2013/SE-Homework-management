package com.se.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.se.pojo.User;

@Repository("userDao")
public interface UserDao {
	@Select("select * from user_table where user_id=#{user_id}")
	User getUser(@Param("user_id")  String user_id);
	
	@Select("select * from user_table where user_role=#{user_role}")
	List<User> getUserList(@Param("user_role") int user_role);
	
	@Select("select * from user_table where user_id=#{user_id} and user_pwd= #{password}")
	User checkUser(@Param("user_id")  String user_id,@Param("password") String password);
	
	@Insert("insert into user_table(user_id,user_name,user_pwd,user_role) values(#{user_id},#{user_name}"
			+ ",#{user_pwd},#{user_role})")
	void addUser(User user);
	
	@Update("update user_table set user_name=#{user_name},user_pwd=#{user_pwd},user_role=#{user_role} where user_id=#{user_id}")
	void updateUser(User user);
	
	@Delete("delete from user_table where user_id=#{user_id}")
	void deleteUser(@Param("user_id")String user_id);

}
