package other;


import javax.annotation.Resource;

import org.junit.Test;

import com.se.dao.AssistantDao;
import com.se.dao.CourseDao;
import com.se.dao.StudentDao;
import com.se.dao.TeamDao;
import com.se.dao.UserDao;

import com.se.dao.HomeworkDao;
import com.se.pojo.Homework;
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
	@Test
	public void test(){
		
//		Homework homework = new Homework();
//		homework.setHomework_id("2013101");
//		homework.setCourse_id("201310");
//		homework.setRelease_time("2013-04-06");
//		homework.setDescription("sdfadsf");
//		homework.setRatio(0.5);
//		homework.setUpload_time("2013-04-05");
//		homework.setHomework_name("diyici");
//		
//		homeworkDao.addHomework(homework);
		homeworkDao.updateHomeworkRatio("2013102", 0.3);
		//System.out.println(assistantDao.getAssistant(assistantDao.getTeamAssistant("20130400")).getAssistant_name());
		
	}
}
