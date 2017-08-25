package com.dta.mediatic.garbage;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.dta.mediatic.MediaticApplication;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = MediaticApplication.class)
//@TestPropertySource("classpath:application-test.properties")
@Transactional
public abstract class IntegrationTest {

	@Autowired
    protected WebApplicationContext wac;
	
	@Autowired
	protected JsonHelper jsonHelper;
	
    protected MockMvc mockMvc;

    @Before
    public void initMockMcv() {
        mockMvc = MockMvcBuilders
        		.webAppContextSetup(wac)
        		.build();
    }	
    
}
