package ControllerTest;

import javax.annotation.Resource;

import org.apache.commons.collections4.map.MultiValueMap;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import other.BaseJunitTest;
@WebAppConfiguration
public class TestControllers extends BaseJunitTest{
	
	@Resource
	private WebApplicationContext wac;
	private MockMvc mocMvc;
	@Before
	public void setUp(){
		this.mocMvc= MockMvcBuilders.webAppContextSetup(wac).build();
	}
	@Test
	public void testLogin() throws Exception{
		
		MvcResult result=mocMvc.perform(MockMvcRequestBuilders.get("/student/team")
			
				).
			andDo(MockMvcResultHandlers.print()).andReturn();
		
	}
}
