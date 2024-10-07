package org.zerock.myapp.controller;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.myapp.config.RootContextXml;
import org.zerock.myapp.config.ServletContextXml;

import lombok.NoArgsConstructor;
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

@ContextConfiguration(classes= { RootContextXml.class, ServletContextXml.class })


// Annotation for testing the controller.
@WebAppConfiguration
public class BoardControllerTests {
	
//	@Setter(onMethod_= { @Autowired })
//	@Setter(onMethod_= { @Inject })
	
//	@Setter(onMethod_= { @Resource })
//	@Setter(onMethod_= { @Resource(type=WebApplicationContext.class) })
	
//	@Setter(onMethod_= @Autowired)
//	@Setter(onMethod_= @Inject)
	
//	@Setter(onMethod_= @Resource)
//	@Setter(onMethod_= @Resource(type=WebApplicationContext.class))
	
//	@Inject
//	@Autowired
	
//	@Resource
	@Resource(type = WebApplicationContext.class)
	private WebApplicationContext ctx;				// Spring Context (Bean Container)
	
//	private MockMvc mockMvc;
	

	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		Objects.requireNonNull(ctx);
		log.info("\t+ ctx: " + ctx);
	} // beforeAll
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testList")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testList() throws Exception {
		log.debug("testList() invoked.");
		
		//----------------------------------------------------------------------//
		
//		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
//		log.info("\t(1) mockMvcBuilder: " + mockMvcBuilder);
//		
//		MockMvc mockMvc = mockMvcBuilder.build();
//		log.info("\t(2) mockMvc: " + mockMvc);
//
//		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/board/list");
//		log.info("\t(3) reqBuilder: " + reqBuilder);
//		
//		ResultActions resultActions = mockMvc.perform(reqBuilder);
//		log.info("\t(4) resultActions: " + resultActions);
//		
//		MvcResult mvcResult = resultActions.andReturn();
//		log.info("\t(5) mvcResult: " + mvcResult);
//		
//		ModelAndView modelAndView = mvcResult.getModelAndView();
//		log.info("\t(6) modelAndView: " + modelAndView);
//		
//		Map<String, Object> model = modelAndView.getModel();
//		log.info("\t(7) model: " + model);
//		
//		String viewName = modelAndView.getViewName();
//		log.info("\t(8) viewName: " + viewName);
//		
//		View view = modelAndView.getView();
//		log.info("\t(9) view: " + view);
		
		//----------------------------------------------------------------------//
		
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/board/list");
		
		// ModelMap => LinkedHashMap<String, ArrayList>
		ModelMap modelMap = 
			mockMvc.
				perform(reqBuilder).
				andReturn().
				getModelAndView().
				getModelMap();
		
		//----------------------------------------------------------------------//
		
		modelMap.forEach((t, u) -> {
			log.info("\t+ t: " + t);
			log.info("\t+ u: " + u);
		}); // forEach
		
		modelMap.clear();
		modelMap = null;
	} // testList
	

//	@Disabled
	@Test
	@Order(2)
	@DisplayName("testListPerPage")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testListPerPage() throws Exception {
		log.debug("testListPerPage() invoked.");
		
		//----------------------------------------------------------------------//
		
//		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
//		log.info("\t(1) mockMvcBuilder: " + mockMvcBuilder);
//		
//		MockMvc mockMvc = mockMvcBuilder.build();
//		log.info("\t(2) mockMvc: " + mockMvc);
//
//		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/board/list");
//		log.info("\t(3) reqBuilder: " + reqBuilder);
//		
//		ResultActions resultActions = mockMvc.perform(reqBuilder);
//		log.info("\t(4) resultActions: " + resultActions);
//		
//		MvcResult mvcResult = resultActions.andReturn();
//		log.info("\t(5) mvcResult: " + mvcResult);
//		
//		ModelAndView modelAndView = mvcResult.getModelAndView();
//		log.info("\t(6) modelAndView: " + modelAndView);
//		
//		Map<String, Object> model = modelAndView.getModel();
//		log.info("\t(7) model: " + model);
//		
//		String viewName = modelAndView.getViewName();
//		log.info("\t(8) viewName: " + viewName);
//		
//		View view = modelAndView.getView();
//		log.info("\t(9) view: " + view);
		
		//----------------------------------------------------------------------//
		
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/board/listPerPage");
		reqBuilder.param("currPage", "1");
		reqBuilder.param("amount", "20");
		reqBuilder.param("pagesPerPage", "10");
		
		// ModelMap => LinkedHashMap<String, ArrayList>
		ModelMap modelMap = 
			mockMvc.
				perform(reqBuilder).
				andReturn().
				getModelAndView().
				getModelMap();
		
		//----------------------------------------------------------------------//
		
		modelMap.forEach((t, u) -> {
			log.info("\t+ t: " + t);
			log.info("\t+ u: " + u);
		}); // forEach
		
		modelMap.clear();
		modelMap = null;
	} // testListPerPage
	

//	@Disabled
	@Test
	@Order(3)
	@DisplayName("testRegister")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testRegister() throws Exception {
		log.debug("testResult() invoked.");
		
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/board/register");
		reqBuilder.param("title", "TITLE_NEW");
		reqBuilder.param("content", "CONTENT_NEW");
		reqBuilder.param("writer", "WRITER_NEW");
		
		String viewName = 
			mockMvc.
				perform(reqBuilder).
				andReturn().
				getModelAndView().
				getViewName();
		
		log.info("\t+ viewName: " + viewName);
	} // testRegister
	

//	@Disabled
	@Test
	@Order(4)
	@DisplayName("testGet")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testGet() throws Exception {
		log.debug("testGet() invoked.");
		
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/board/get");
		reqBuilder.param("bno", "84");
		
		ModelMap modelMap =
			mockMvc.
				perform(reqBuilder).
				andReturn().
				getModelAndView().
				getModelMap();
		
		//----------------------------------------------------------------------//
		
		modelMap.forEach((t, u) -> {
			log.info("\t+ t: " + t);
			log.info("\t+ u: " + u);
		}); // forEach
		
		modelMap.clear();
		modelMap = null;
	} // testGet
	

//	@Disabled
	@Test
	@Order(5)
	@DisplayName("testModify")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testModify() throws Exception {
		log.debug("testModify() invoked.");
		
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/board/modify");
		reqBuilder.param("bno", "84");
		reqBuilder.param("title", "TITLE_MODIFIED");
		reqBuilder.param("content", "CONTENT_MODIFIED");
		reqBuilder.param("writer", "WRITER_MODIFIED");
		
		String viewName =
			mockMvc.
				perform(reqBuilder).
				andReturn().
				getModelAndView().
				getViewName();
		
		log.info("\t+ viewName: " + viewName);
	} // testModify
	

//	@Disabled
	@Test
	@Order(6)
	@DisplayName("testRemove")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testRemove() throws Exception {
		log.debug("testModify() invoked.");
		
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/board/remove");
		reqBuilder.param("bno", "84");
		
		String viewName =
				mockMvc.
					perform(reqBuilder).
					andReturn().
					getModelAndView().
					getViewName();
		
		log.info("\t+ viewName: " + viewName);
	} // testRemove

	

} // end class
