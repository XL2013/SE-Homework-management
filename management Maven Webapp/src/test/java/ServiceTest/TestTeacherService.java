package ServiceTest;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.se.dao.StudentDao;
import com.se.dao.TeamHomeworkDao;
import com.se.pojo.Student;
import com.se.service.HomeworkService;
import com.se.service.StudentService;

import other.BaseJunitTest;

public class TestTeacherService extends BaseJunitTest{
	@Resource
	private TeamHomeworkDao teamHomeworkDao;
	@Resource
	private HomeworkService homworkService;
	@Resource
	private StudentService studentService;
	@Resource
	private StudentDao studentDao;
	@Test
	public void test(){
		List<Student> list=studentDao.getStudentList("201310");
		studentService.getStudentResultListByCourse("201310", list);
//		studentService.getStudentRollCallListByCourse("201310", list);
//		teamHomeworkDao.getHomeworkName("20130402");
//		System.out.print(homworkService.getTeamHomeworkViewData("diyici", "", "").get("homework_names").toString());
	}
}
