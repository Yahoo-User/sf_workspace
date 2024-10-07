package org.zerock.myapp.mapper2;

import java.util.Date;

import org.apache.ibatis.annotations.Select;



// Mapper Interface 방식의 마이바이스 사용
public interface TimeMapper2 {
	

	@Select("SELECT current_date FROM dual")
	public abstract Date getTime3();

} // end class
