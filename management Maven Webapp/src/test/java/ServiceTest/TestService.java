package ServiceTest;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.se.pojo.Student;
import com.se.service.impl.HomeworkServiceImpl;
import com.se.service.impl.StudentServiceImpl;

import other.BaseJunitTest;

public class TestService  extends BaseJunitTest {

		@Resource
		private HomeworkServiceImpl homeworkService;
		@Resource
		private StudentServiceImpl studentServiceImpl;
		
		@Test
		public void test(){
			List<Student> students=studentServiceImpl.getCourseStudent("201312");
			studentServiceImpl.getStudentCourseTotalGradeByStudentList("201312", students);
		}
}
