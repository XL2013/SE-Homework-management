package other;


import javax.annotation.Resource;

import org.junit.Test;

import com.se.dao.CourseDao;
import com.se.dao.TeamDao;
import com.se.dao.UserDao;
import com.se.pojo.User;


public class TestDao  extends BaseJunitTest{
	
	@Resource
	CourseDao courseDao;
	@Resource
	UserDao userDao;
	
	@Resource
	TeamDao teamDao;
	@Test
	public void test(){
		teamDao.getTeamId("2013");
		
	}
}
