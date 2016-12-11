package other;


import javax.annotation.Resource;

import org.junit.Test;

import com.se.dao.AssistantDao;
import com.se.dao.CourseDao;
import com.se.dao.StudentDao;
import com.se.dao.TeamDao;
import com.se.dao.TeamHomeworkDao;
import com.se.dao.UserDao;
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
	TeamHomeworkDao teamHomeworkDao;
	@Test
	public void test(){
		TeamHomework homework=new TeamHomework();
		homework.setHomework_id("2013102");
		homework.setTeam_id("20130400");
		homework.setStatus(1);
		homework.setSubmit_time("");
		homework.setSubmitter("");
		homework.setCorrectInfo("");
		homework.setStudent_comment("");
		teamHomeworkDao.updateHomework(homework);
			
	}
}
