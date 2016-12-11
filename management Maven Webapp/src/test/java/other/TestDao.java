package other;


import javax.annotation.Resource;

import org.junit.Test;

import com.se.dao.AssistantDao;
import com.se.dao.CourseDao;
import com.se.dao.StudentDao;
import com.se.dao.TeamDao;
import com.se.dao.TeamHomeworkDao;
import com.se.dao.UserDao;
import com.se.pojo.HomeworkFile;
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
		HomeworkFile file=new HomeworkFile();
		file.setFile_name("tttt");
		file.setFile_path("sss");
		file.setHomework_id("111");
		file.setTeam_id("111");
		teamHomeworkDao.addHomeworkFile(file);
			
	}
}
