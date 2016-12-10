package other;


import javax.annotation.Resource;

import org.junit.Test;

import com.se.dao.CourseDao;
import com.se.dao.StudentDao;
import com.se.dao.TeamDao;
import com.se.dao.UserDao;
import com.se.pojo.Student;
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
	@Test
	public void test(){
		
		for(Student student :studentService.searchStudent("2013211001", "201310"))
			System.out.println(student.getStudent_name());
		
	}
}
