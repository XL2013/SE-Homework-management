package ServiceTest;

import javax.annotation.Resource;

import org.junit.Test;

import com.se.service.impl.HomeworkServiceImpl;

import other.BaseJunitTest;

public class TestService  extends BaseJunitTest {

		@Resource
		private HomeworkServiceImpl homeworkService;
		
		@Test
		public void test(){
			System.out.print(homeworkService.checkHomeworkFile("test.txt", "2013111", "20130100"));
		}
}
