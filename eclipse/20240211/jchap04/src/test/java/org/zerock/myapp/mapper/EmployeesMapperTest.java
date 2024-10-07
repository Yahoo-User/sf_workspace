package org.zerock.myapp.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.jupiter.api.AfterAll;
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
import org.zerock.myapp.config.RootContextXml;
import org.zerock.myapp.domain.EmployeeVO;

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
	

	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(mapper);
		
		log.info("\t+ mapper: " + mapper);
		log.info("\t+ type: " + mapper.getClass().getName());
	} // beforeAll
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testGetAllNamesOfEmployees")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testGetAllNamesOfEmployees() {
		log.debug("testGetAllNamesOfEmployees() invoked.");
		
		names = mapper.getAllNamesOfEmployees();
		
//		log.info(names);
		
		names.forEach(log::info);
		
		names.clear();
		names = null;
	} // testGetAllNamesOfEmployees
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testGetAllEmployees")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
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
	
	
	@AfterAll
	void afterAll() {
		log.trace("afterAll() invoked.");
		
		if(names != null) {
			names.clear();
			names = null;
		} // if
		
		if(employees != null) {
			employees.clear();
			employees = null;
		} // if
	} // afterAll
	
} // end class
