package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@AllArgsConstructor

@Service("boardService")
public class BoardServiceImpl
	implements BoardService, InitializingBean, DisposableBean {
	
//	@Setter(onMethod_= { @Autowired })
//	@Setter(onMethod_= { @Inject })
	
//	@Setter(onMethod_= { @Resource })
//	@Setter(onMethod_= { @Resource(type=BoardMapper.class) })
	
	
//	@Setter(onMethod_= @Autowired)
//	@Setter(onMethod_= @Inject)
	
	
//	@Setter(onMethod_= @Resource)
//	@Setter(onMethod_= @Resource(type=BoardMapper.class))
	
//	@Inject
//	@Autowired
	
//	@Resource
//	@Resource(type = BoardMapper.class)
	private BoardMapper mapper;
	

	@Override
	public boolean register(BoardVO board) {
		log.debug("register(board) invoked.");
		
		Objects.requireNonNull(mapper);
		
//		int affectedRows = mapper.insertSelectKey(board);
		int affectedRows = mapper.insert(board);

		return (affectedRows == 1)? true : false;
	} // register
	

	@Override
	public boolean modify(BoardVO board) {
		log.debug("modify(board) invoked.");
		
		Objects.requireNonNull(mapper);
		int affectedRows = mapper.update(board);

		return (affectedRows == 1)? true : false;
	} // modify
	

	@Override
	public boolean remove(Integer bno) {
		log.debug("remove(bno) invoked.");
		
		Objects.requireNonNull(mapper);
		
		int affectedRows = mapper.delete(bno);

		return (affectedRows == 1) ? true :  false;
	} // remove
	

	@Override
	public BoardVO get(Integer bno) {
		log.debug("get(bno) invoked.");
		
		Objects.requireNonNull(mapper);

		return mapper.read(bno);
	} // get
	

	@Override
	public List<BoardVO> getList() {
		log.debug("getList() invoked.");
		
		Objects.requireNonNull(mapper);

		return mapper.getList();
	} // getList


	@Override
	public List<BoardVO> getListPerPage(Criteria cri) {
		log.debug("getListPerPage(cri) invoked.");

		log.info("\t+ cri: " + cri);
		
		Objects.requireNonNull(mapper);
		
		return mapper.getListWithPaging(cri);
	} // getListPerPage


	@Override
	public int getTotal(Criteria cri) {
		log.debug("getTotal(cri) invoked.");
		
		log.info("\t+ cri: " + cri);
		
		Objects.requireNonNull(mapper);
		
		return mapper.getTotalCount(cri);
	} // getTotal
	
	
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
