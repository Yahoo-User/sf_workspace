package org.zerock.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.mybatis.domain.BoardVO;



public interface BoardMapper {
	
	
	@Select("SELECT\r\n"
			+ "      /*+ INDEX_DESC(tbl_board) */\r\n"
			+ "      *\r\n"
			+ "FROM\r\n"
			+ "      tbl_board")
	public abstract List<BoardVO> selectAllBoards();
	
	
	@Select("SELECT\r\n"
			+ "      *\r\n"
			+ "FROM\r\n"
			+ "      tbl_board\r\n"
			+ "WHERE\r\n"
			+ "      bno = #{TheBno}")
	public abstract BoardVO selectBoard(@Param("TheBno") int bno);

} // end interface
