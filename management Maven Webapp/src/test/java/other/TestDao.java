package other;


import javax.annotation.Resource;

import org.junit.Test;

import com.se.dao.CourseDao;
import com.se.dao.UserDao;
import com.se.pojo.User;


public class TestDao  extends BaseJunitTest{
	
	@Resource
	CourseDao courseDao;
	@Resource
	UserDao userDao;
	
	@Test
	public void test(){
		User user=new User();
		user.setUser_id("00");
		user.setUser_name("test");
		user.setUser_pwd("111111");
		user.setRole(2);
		userDao.addUser(user);
		
	}
}
