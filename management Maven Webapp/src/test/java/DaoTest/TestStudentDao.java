package DaoTest;

import javax.annotation.Resource;

import org.junit.Test;
import com.se.dao.StudentDao;
import com.se.pojo.Student;

import other.BaseJunitTest;


public class TestStudentDao extends BaseJunitTest{
	@Resource
	private StudentDao studentDao;
	
	@Test
	public void testAdd(){
		Student student=new Student();
		student.setStudent_id("2013211261");
		student.setClass_id("2013211304");
		student.setStudent_name("zzs");
		studentDao.addStudent(student);
	}


}
