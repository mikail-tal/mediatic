package com.dta.mediatic.garbage;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.dta.mediatic.user.model.User;
import com.dta.mediatic.user.repository.UserRepository;
import com.dta.mediatic.user.service.UserService;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
public class AuthenticationIntegrationTest extends IntegrationTest {

	@Autowired
	private WebApplicationContext context;
	
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService service;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	};
	
	@Test
	public void testLogin() throws Exception{
        userRepository.deleteAllInBatch();;
    	 
        /*
         * Create User1
         */
        User user1 = new User();
        user1.setLogin("user1");
        user1.setPassword("password1");
        user1.setName("name user1");
        
        service.create(user1);
		
        /* MockHttpSession session = new MockHttpSession();
        mvc.perform(post("/authenticate")
        		.param("username", "user1")
        		.param("password","password1")
        		.session(session))
        .andReturn();*/
        
        MockHttpSession session = (MockHttpSession) mvc
        		.perform(formLogin("/authenticate")
        				.user("user1")
        				.password("password1"))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn().getRequest().getSession();

        
		mvc.perform(get("/api/authentication/user").session(session)).andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$.authorities[0].authority").value("ROLE_BASIC"));
	}
	
}