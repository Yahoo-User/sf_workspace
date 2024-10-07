package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.config.RootContextXml;
import org.zerock.myapp.domain.EmployeeVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= { RootContextXml.class })
public class EmployeesMapperTest {
	
//	@Setter(onMethod_= { @Autowired })
//	@Setter(onMethod_= { @Inject })
	
//	@Setter(onMethod_= { @Resource })
//	@Setter(onMethod_= { @Resource(type=EmployeesMapper.class) })
	
	
//	@Setter(onMethod_= @Autowired)
//	@Setter(onMethod_= @Inject)
	
	
//	@Setter(onMethod_= @Resource)
//	@Setter(onMethod_= @Resource(type=EmployeesMapper.class))
	
//	@Inject
//	@Autowired
	
//	@Resource
	@Resource(type = EmployeesMapper.class)
	private EmployeesMapper mapper;
	
	private List<String> names;
	private List<EmployeeVO> employees;
	
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		assertNotNull(mapper);
		
		log.info("\t+ mapper: " + mapper);
		log.info("\t+ type: " + mapper.getClass().getName());
	} // setup
	
	
//	@Test(timeout=1000)
	@Test
	public void testGetAllNamesOfEmployees() {
		log.debug("testGetAllNamesOfEmployees() invoked.");
		
		names = mapper.getAllNamesOfEmployees();
		
//		log.info(names);
		
		names.forEach(log::info);
		
		names.clear();
		names = null;
	} // testGetAllNamesOfEmployees
	
	
	@Test
	public void testGetAllEmployees() {
		log.debug("testGetAllEmployees() invoked.");
		
		employees = mapper.getAllEmployees();
		
//		employees.forEach(e -> {
//			
//			String employee = String.format(
//					"%s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
//					e.getEmployeeId(),
//					e.getFirstName(),
//					e.getLastName(),
//					e.getEmail(),
//					e.getPhoneNumber(),
//					e.getHireDate(),
//					e.getJobId(),
//					e.getSalary(),
//					e.getCommissionPct(),
//					e.getDepartmentId()
//				);
//			
//			log.info(employee);
//			
//		}); // forEach
		
		employees.forEach(log::info);
		
		employees.clear();
		employees = null;
	} // testGetAllEmployees
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
		if(names != null) {
			names.clear();
			names = null;
		} // if
		
		if(employees != null) {
			employees.clear();
			employees = null;
		} // if
	} // tearDown
	
} // end class
