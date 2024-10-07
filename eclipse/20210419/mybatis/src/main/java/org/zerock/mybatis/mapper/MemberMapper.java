package org.zerock.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.mybatis.domain.MemberVO;



public interface MemberMapper {
	
	
	@Select("SELECT\r\n"
			+ "      /*+ INDEX_DESC(tbl_member) */\r\n"
			+ "      *\r\n"
			+ "FROM\r\n"
			+ "      tbl_member")
	public abstract List<MemberVO> selectAllMembers();
	
	
	@Select("SELECT\r\n"
			+ "      *\r\n"
			+ "FROM\r\n"
			+ "      tbl_member\r\n"
			+ "WHERE\r\n"
			+ "      userid = #{TheUserId}")
	public abstract MemberVO selectMember(@Param("TheUserId") String userid);

} // end interface
