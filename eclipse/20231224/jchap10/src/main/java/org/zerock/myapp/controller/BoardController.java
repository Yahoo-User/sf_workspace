package org.zerock.myapp.controller;

import java.util.Objects;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@AllArgsConstructor

@RequestMapping("/board/*")
@Controller("boardController")
public class BoardController
	implements InitializingBean, DisposableBean {
	
//	@Setter(onMethod_= { @Autowired })
//	@Setter(onMethod_= { @Inject })
	
//	@Setter(onMethod_= { @Resource })
//	@Setter(onMethod_= { @Resource(type=BoardService.class) })
	
//	@Setter(onMethod_= @Autowired)
//	@Setter(onMethod_= @Inject)
	
//	@Setter(onMethod_= @Resource)
//	@Setter(onMethod_= @Resource(type=BoardService.class))
	
//	@Inject
//	@Autowired
	
//	@Resource
//	@Resource(type = BoardService.class)
	
	// Since Spring v4.3, One field injected by a constructor with that field.
	private BoardService service;

	
	@GetMapping("/list")
	public void list(Model model) {
		log.debug("list(model) invoked.");
		
		Objects.requireNonNull(service);
		model.addAttribute("list", service.getList());
	} // list
	
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttrs) {
		log.debug("register(board, rttrs) invoked.");
		
		log.info("\t+ board: " + board);
		
		Objects.requireNonNull(service);
		service.register(board);
		
		rttrs.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
	} // register
	
	
	@GetMapping("/get")
	public void get(@RequestParam("bno") Integer bno, Model model) {
		log.debug("get(bno, model) invoked.");
		
		log.info("\t+ bno: " + bno);
		
		Objects.requireNonNull(service);
		BoardVO board = service.get(bno);
		log.info("\t+ board: " + board);
		
		model.addAttribute("board", board);
	} // get
	
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttrs) {
		log.debug("modify(board, rttrs) invoked.");
		
		log.info("\t+ board: "+board);
		
		Objects.requireNonNull(service);
		if(service.modify(board)) {
			rttrs.addFlashAttribute("result", "success");
		} // if
		
		return "redirect:/board/list";
	} // modify
	
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Integer bno, RedirectAttributes rttrs) {
		log.debug("modify(board, rttrs) invoked.");
		
		log.info("\t+ bno: " + bno);
		
		Objects.requireNonNull(service);
		if(service.remove(bno)) {
			rttrs.addFlashAttribute("result", "success");
		} // if
		
		return "redirect:/board/list";
	} // remove
	
	
	//===================================================//

	@Override
	public void destroy() throws Exception {
		log.debug("destroy() invoked.");
		
	} // destroy

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("afterPropertiesSet() invoked.");
		
	} // afterPropertiesSet
	

} // end class
