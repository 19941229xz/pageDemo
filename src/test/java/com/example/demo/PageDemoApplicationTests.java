package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PageDemoApplicationTests {
	 
//	private MockMvc mvc;
//
//    @Before
//    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(new UserController(), new UserController()).build();
//    }
//
//	@Test
//	public void contextLoads() throws Exception {
//		
//		String requestBody = "{\"page\":1, \"size\":2}";  
//		mvc.perform(post("/user")  
//		            .contentType(MediaType.APPLICATION_JSON).content(requestBody)  
//		            .accept(MediaType.APPLICATION_JSON)) //执行请求  
//		        .andExpect(content().contentType(MediaType.APPLICATION_JSON)) //验证响应contentType  
//		        .andExpect(jsonPath("$.id").value(1)); //使用Json path验证JSON 请参考http://goessner.net/articles/JsonPath/  
//	}

}

