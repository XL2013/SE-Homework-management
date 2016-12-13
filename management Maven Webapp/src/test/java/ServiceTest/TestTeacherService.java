package ServiceTest;

import javax.annotation.Resource;

import org.junit.Test;

import com.se.dao.TeamHomeworkDao;
import com.se.service.HomeworkService;

import other.BaseJunitTest;

public class TestTeacherService extends BaseJunitTest{
	@Resource
	private TeamHomeworkDao teamHomeworkDao;
	@Resource
	private HomeworkService homworkService;
	@Test
	public void test(){
		System.out.print(homworkService.getTeamHomeworkViewData("diyici", "", "").get("homework_names").toString());
	}
}
