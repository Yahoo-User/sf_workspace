package org.zerock.myapp.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.myapp.domain.Ticket;

import com.google.gson.Gson;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;




@Log4j
@NoArgsConstructor


@WebAppConfiguration

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"
})
public class RequestBodyControllerTests {
	
	@Setter(onMethod_= {@Autowired})
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		Objects.requireNonNull(this.ctx);
		log.info("\t+ ctx: " + this.ctx);
		
		DefaultMockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		this.mockMvc = mockMvcBuilder.build();
		
		Objects.requireNonNull(this.mockMvc);
		log.info("\t+ mockMvc: " + this.mockMvc);
	} // setup
	
	
	@Test
	public void testConvertToTicket() throws Exception {
		log.debug("testConvertToTicket() invoked.");
		
		Ticket ticket = new Ticket();
		ticket.setTno(1000);
		ticket.setOwner("trinity");
		ticket.setGrade("B");
		
		log.info("\t+ ticket: " + ticket);
		
		//-----------------------------------------//
		
		Gson gson = new Gson();
		
		String ticketJSON = gson.toJson(ticket);
		log.info("\t+ ticketJSON: " + ticketJSON);
		
		//-----------------------------------------//
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/sample4/ticket");
		reqBuilder.contentType(MediaType.APPLICATION_JSON_VALUE);
		reqBuilder.content(ticketJSON);
		
		ResultActions resultActions = this.mockMvc.perform(reqBuilder);
		resultActions.andExpect(status().isOk());
	} // testConvertToTicket
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
	} // tearDown

} // end class
