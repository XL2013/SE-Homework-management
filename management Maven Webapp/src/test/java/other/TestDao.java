package other;

import javax.annotation.Resource;

import org.junit.Test;

import com.se.dao.UserDao;

public class TestDao  extends BaseJunitTest{
	
	@Resource
	private UserDao userDao;
	
	@Test
	public void test(){
		System.out.print("test");
	}
}
