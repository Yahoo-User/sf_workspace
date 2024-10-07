package org.zerock.myapp.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.myapp.config.RootContextXml;
import org.zerock.myapp.config.ServletContextXml;
import org.zerock.myapp.domain.Ticket;

import com.google.gson.Gson;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


//For JUnit 4
//@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)

//For JUnit 5
@ExtendWith(SpringExtension.class)

@ContextConfiguration(classes = { RootContextXml.class, ServletContextXml.class })


@WebAppConfiguration
public class RequestBodyControllerTests {
	
	@Setter(onMethod_= {@Autowired})
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	

	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		Objects.requireNonNull(this.ctx);
		log.info("\t+ ctx: " + this.ctx);
		
		DefaultMockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		this.mockMvc = mockMvcBuilder.build();
		
		Objects.requireNonNull(this.mockMvc);
		log.info("\t+ mockMvc: " + this.mockMvc);
	} // beforeAll
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testConvertToTicket")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
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

	
	

} // end class
