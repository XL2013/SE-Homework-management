package other;


import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.apache.ibatis.javassist.expr.Instanceof;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;
import org.mockito.internal.matchers.Not;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.se.dao.AssistantDao;
import com.se.dao.CourseDao;
import com.se.dao.StudentDao;
import com.se.dao.TeamDao;
import com.se.dao.TeamHomeworkDao;
import com.se.dao.UserDao;

import com.se.dao.HomeworkDao;
import com.se.dao.RollCallDao;
import com.se.pojo.Homework;
import com.se.pojo.Student;
import com.se.pojo.TeamHomework;
import com.se.service.impl.StudentServiceImpl;

import oracle.net.aso.i;


public class LiliangTestDao  extends BaseJunitTest{
	
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
		
//		Homework homework = new Homework();
//		homework.setHomework_id("2013101");
//		homework.setCourse_id("201310");
//		homework.setRelease_time("2013-04-06");
//		homework.setDescription("sdfadsf");
//		homework.setRatio(0.5);
//		homework.setUpload_time("2013-04-05");
//		homework.setHomework_name("diyici");
//		
//		homeworkDao.updateHomeworkRatio("2013102", 0.3);
//		teamHomeworkDao.setTeamHomeworkComment("2013101", "20130400","1111");
//		System.out.println(teamHomeworkDao.getHomeworkName("µÚÒ»´Î"));
		//System.out.println(assistantDao.getAssistant(assistantDao.getTeamAssistant("20130400")).getAssistant_name());
//		System.out.println(rollCallDao.getStudentRollStatus("201310", 1, "2013211002"));
//		Object x=rollCallDao.getStudentRollStatus("201310", 1, "2013211001");
//		System.out.println(x == null);
//		Integer y;
//		if(x==null)
//			System.out.println("hehe");
//			
//		else {
//			y = ((BigDecimal)x).intValue();t
//			System.out.println(y.getClass().toString());
//			
//			System.out.println(rollCallDao.getStudentRollStatus("201310", 1, "2013211001"));
//		}
		List<Student> list = studentDao.getStudentList("201310");
		for (Student student : list) {
			System.out.println(student.getStudent_id());
			studentService.addStudentCourseGrade("201310", student.getStudent_id(), 0);
		}
//		System.out.println(rollCallDao.isStudentRollExist("201310", 8, "2013211003"));
	}
}
