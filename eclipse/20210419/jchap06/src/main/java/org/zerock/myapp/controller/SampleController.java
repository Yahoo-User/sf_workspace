package org.zerock.myapp.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.apache.catalina.tribes.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.SampleDTO;
import org.zerock.myapp.domain.SampleDTOList;
import org.zerock.myapp.domain.TodoDTO;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor(access=AccessLevel.PUBLIC)

@RequestMapping("/sample/*")
@Controller
public class SampleController {
	
	
	
	//-------------------------------------------//
	// 1. @RequestMapping or @RequestMapping("")
	//-------------------------------------------//
	// To determine HTTP status code of the response.
//	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ResponseStatus(HttpStatus.OK)
	
	@RequestMapping("")			// OK: 	/sample/ with GET & POST	---> /WEB-INF/views/sample.jsp
//	@RequestMapping				// OK:	/sample/ with GET & POST	---> /WEB-INF/views/sample.jsp
	public String basic() {
		log.debug("basic() invoked.");
		
		return "sample";
	} // basic
	
	
	//-------------------------------------------//
	// 2. @RequestMapping(path, method)
	//-------------------------------------------//
	// To determine HTTP status code of the response.
//	@ResponseStatus(HttpStatus.OK)
	
	@RequestMapping(
//		path= {"/basicGet"},						//---> /WEB-INF/views/sample/basicGET.jsp
//		method= {RequestMethod.GET}
		
		path= "/basicGet",							//---> /WEB-INF/views/sample/basicGET.jsp
		method= GET
	)
	public String basicGet() {
		log.debug("basicGet() invoked.");
		
		return "sample";
	} // basicGET
	
	
	//-------------------------------------------//
	// 3. @RequestMapping(path, method)
	//-------------------------------------------//
	// To determine HTTP status code of the response.
//	@ResponseStatus(HttpStatus.OK)
	
	@RequestMapping(
		path= { "/basicGetPost" },
		method = { GET, POST }
	)
	public String basicGetPost() {
		log.debug("basicGetPost() invoked.");
		
		return "sample";
	} // basicGETPOST
	
	
	//-------------------------------------------//
	// 4. @GetMapping(path)
	//-------------------------------------------//
	// To determine HTTP status code of the response.
//	@ResponseStatus(HttpStatus.OK)
	
	@GetMapping("/basicOnlyGet")
	public String basicOnlyGet() {
		log.debug("basicOnlyGet() invoked.");
		
		return "sample";
	} // basicOnlyGet
	
	
	//-------------------------------------------//
	// 5. @PostMapping(path)
	//-------------------------------------------//
	// To determine HTTP status code of the response.
//	@ResponseStatus(HttpStatus.OK)
	
	@PostMapping("/basicOnlyPost")
	public String basicOnlyPost() {
		log.debug("basicOnlyPost() invoked.");
		
		return "sample";
	} // basicOnlyPost
	
	
	//-------------------------------------------//
	// 6. @GetMapping(path) with DTO parameter
	//-------------------------------------------//
	// To determine HTTP status code of the response.
//	@ResponseStatus(HttpStatus.OK)
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.debug("ex01(dto) invoked.");
		
		log.info("\t+ dto: "+dto);
		
		return "sample";
	} // chap06
	
	
	//-------------------------------------------//
	// 7. @GetMapping(path) with some primitive types's parameters
	//-------------------------------------------//
	// To determine HTTP status code of the response.
//	@ResponseStatus(HttpStatus.OK)
	
	@GetMapping("/ex02")
	
/*	public String ex02(String name,  int age) {
		log.debug("ex02(name, age) invoked.");
	
		log.info("\t+ name: " + name);
		log.info("\t+ age: " + age);
		
		return "sample";
	} // ex02
*/	
	
	public String ex02(String recvName,  int currentAge) {	// XX: java.lang.IllegalStateException
		log.debug("ex02(recvName, currentAge) invoked.");
		
		log.info("\t+ recvName: " + recvName);
		log.info("\t+ currentAge: " + currentAge);
		
		return "sample";
	} // ex02

	
//	public String ex02(										// OK
//			 @RequestParam("name") String recvName, 
//			 @RequestParam("age") int currentAge
//		) {
//		log.debug("ex02(recvName, currentAge) invoked.");
//		
//		log.info("\t+ recvName: " + recvName);
//		log.info("\t+ currentAge: " + currentAge);
//		
//		return "sample";
//	} // ex02
	
	
	//-------------------------------------------//
	// 8. @GetMapping(path) with some List types's parameter
	//-------------------------------------------//
	// To determine HTTP status code of the response.
//	@ResponseStatus(HttpStatus.OK)
	
	@GetMapping("/ex02List")
	
//	public String ex02List(ArrayList<String> ids) {		// OK but ids - [] Empty List
	
//	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {	// OK : ids - [10, 20, 30, 40]
	
// 	XX: java.lang.IllegalStateException: No primary or single public constructor found 
//	for interface java.util.List - and no default constructor found either
//	public String ex02List(List<String> ids) {							// XX: java.lang.IllegalStateException
	
	// java.util.ArrayList created automatically
	public String ex02List(@RequestParam("ids") List<String> ids) {		// OK : ids - [10, 20, 30, 40]
		log.debug("ex02List(ids) invoked.");
		
		log.info("\t+ ids: " + ids);
		log.info("\t+ type: " + ids.getClass().getName());
		
		return "sample";
	} // ex02List
	
	
	//-------------------------------------------//
	// 9. @GetMapping(path) with Array types's parameter
	//-------------------------------------------//
	// To determine HTTP status code of the response.
//	@ResponseStatus(HttpStatus.OK)
	
	@GetMapping("/ex02Array")
	
//	public String ex02Array(String[] ids) {			// OK but ids - {} empty array
		
	public String ex02Array(@RequestParam("ids") String[] ids) {	// OK : ids - {10, 20, 30, 40}
		log.debug("ex02Array(ids) invoked.");
		
		log.info("\t+ ids: " + Arrays.toString(ids));
		
		return "sample";
	} // ex02Array
	
	
	//-------------------------------------------//
	// 10. @GetMapping(path) with DTO List types's parameter
	//-------------------------------------------//
	// To determine HTTP status code of the response.
//	@ResponseStatus(HttpStatus.OK)
	
	@GetMapping("/ex02Bean")
	
	// (1) if /sample/ex02Bean?list[0].name=NAME_1&list[0].age=1&list[1].name=NAME_2&list[1].age=2
	// 
	// java.lang.IllegalArgumentException: 요청 타겟에서 유효하지 않은 문자가 발견되었습니다.
	// 유효한 문자들은 RFC 7230과 RFC 3986에 정의되어 있습니다.
	
	// (2) if /sample/ex02Bean?list%5B0%5D.name=NAME_1&list%5B0%5D.age=1&list%5B1%5D.name=NAME_2&list%5B1%5D.age=2
	//
	// list: SampleDTOList(list=[SampleDTO(name=NAME_1, age=1), SampleDTO(name=NAME_2, age=2)])
	public String ex02Bean(SampleDTOList list) {
		log.debug("ex02Bean(list) invoked.");
		
		log.info("\t+ list: " + list);
		
		return "sample";
	} // ex02Bean
	
	
	//-------------------------------------------//
	// 11. @InitBinder
	//-------------------------------------------//
	// automatically called back when binding parameters
	//-------------------------------------------//
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		log.debug("initBinder(binder) invoked.");
//		
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
//	} // initBinder
	
	// To determine HTTP status code of the response.
//	@ResponseStatus(HttpStatus.OK)
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO dto) {
		log.debug("ex03(dto) invoked.");
		
		log.info("\t+ dto: " + dto);
		
		
		return "sample";
	} // ex03
	
	
	//-------------------------------------------//
	// 12. @DateTimeFormat
	//-------------------------------------------//
	// No need @InitBinder applied to the member of the DTO.
	//-------------------------------------------//
	// To determine HTTP status code of the response.
//	@ResponseStatus(HttpStatus.OK)
	
	@GetMapping("/ex04")
	public String ex04(TodoDTO dto) {
		log.debug("ex04(dto) invoked.");
		
		log.info("\t+ dto: " + dto);
		
		
		return "sample";
	} // ex04
	
	
	//-------------------------------------------//
	// 13. Predefined Model parameter 
	//-------------------------------------------//
	// to transfer request parameters into the View.
	//-------------------------------------------//
	// To determine HTTP status code of the response.
//	@ResponseStatus(HttpStatus.OK)
	
	@GetMapping("/ex05")
	public String ex05(String name, int age, int page, Model model) {
		log.debug("ex05(name, age, page) invoked.");
		
		log.info(String.format("\t+ name: %s, age: %d, page: %d", name, age, page));
		log.info("\t+ model: " + model.getClass().getName());
		
		SampleDTO dto = new SampleDTO();
		dto.setName(name);
		dto.setAge(age);
		
		model.addAttribute("sampleDTO", dto);
		model.addAttribute("page", page);
		
		return "commandObject";
	} // ex05
	
	
	//-------------------------------------------//
	// 14. @ModelAttribute(key) to transfer data into the View.
	//-------------------------------------------//
	// (*Caution*) Command Object automatically transfered into the VIEW by Spring MVC.
	//	So, No need Model object if use Command Object in the VIEW.
	//	at this time, the name of the Command Object started with a lower-case of 
	//	the first character of the Command Object class name.
	//-------------------------------------------//
	// To determine HTTP status code of the response.
//	@ResponseStatus(HttpStatus.OK)
	
	@PostMapping("/ex06")
	
//	public String ex06(SampleDTO dto, int page) {
	
	public String ex06(SampleDTO dto, @ModelAttribute("page") int page) {
		log.debug("ex06(dto) invoked.");
		
		log.info("\t+ dto: " + dto);
		log.info("\t+ page: " + page);
		
		
		return "commandObject";
	} // ex06
	
	
	//-------------------------------------------//
	// 15. Predefined RedirectAttributes
	//-------------------------------------------//
	//	to redirect request into the other url.
	//-------------------------------------------//
	// To determine HTTP status code of the response.
//	@ResponseStatus(HttpStatus.OK)
	
	@GetMapping("/ex07")
	public String ex07(String name, int age, RedirectAttributes rttrs) {
		log.debug("ex07(rttrs) invoked.");
		
		log.info(String.format("\t+ name: %s, age: %d", name, age));
		
		//--------------------------------------------//
		// 1. added flash attributes into the header 
		//--------------------------------------------//
		//	"Referer: http://vfx-lenovo:8090/sample/ex07?name=YOSEPH&age=23"
		//--------------------------------------------//
//		rttrs.addFlashAttribute("name", name);
//		rttrs.addFlashAttribute("age", age);

		//--------------------------------------------//
		// 2. added flash attributes into the request line and Referer header: 
		//--------------------------------------------//
		// 	GET /?name=YOSEPH&age=23 HTTP/1.1
		//	Referer: http://vfx-lenovo:8090/sample/ex07?name=YOSEPH&age=23
		//--------------------------------------------//
		rttrs.addAttribute("name", name);
		rttrs.addAttribute("age", age);

//		return "redirect:http://localhost:8008/";		// To view request parameters using Netcat
		return "redirect:/sample/ex01";
	} // ex07

} // end class
