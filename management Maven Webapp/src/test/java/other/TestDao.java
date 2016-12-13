package other;


import javax.annotation.Resource;

import org.junit.Test;

import com.se.dao.AssistantDao;
import com.se.dao.CourseDao;
import com.se.dao.StudentDao;
import com.se.dao.TeamDao;
import com.se.dao.TeamHomeworkDao;
import com.se.dao.UserDao;

import com.se.dao.HomeworkDao;
import com.se.dao.RollCallDao;
import com.se.pojo.Homework;
import com.se.pojo.RollCallSetting;
import com.se.pojo.StudentRollCall;
import com.se.pojo.TeamHomework;
import com.se.service.impl.StudentServiceImpl;


public class TestDao  extends BaseJunitTest{
	
	@Resource
	CourseDao courseDao;
	@Resource
	UserDao userDao;
	
	@Resource
	TeamDao teamDao;
	
	@Resource
	StudentDao studentDao;
	
	@Resource
	StudentServiceImpl studentService;
	
	@Resource
	AssistantDao assistantDao;
	
	@Resource

	HomeworkDao homeworkDao ;
	
	@Resource
	TeamHomeworkDao teamHomeworkDao;
	
	@Resource
	RollCallDao rollCallDao;
	@Test
	public void test(){
			
		
	}
}
