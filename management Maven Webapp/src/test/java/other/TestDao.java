package other;


import javax.annotation.Resource;

import org.junit.Test;

import com.se.dao.CourseDao;


public class TestDao  extends BaseJunitTest{
	
	@Resource
	CourseDao courseDao;

	
	@Test
	public void test(){
		System.out.print(courseDao.getCourse("201310").getCourse_name());
	}
}
