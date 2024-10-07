package org.zerock.myapp.domain;

import lombok.Data;


// DTO(Data Tranfer Object) 패턴:
// 클라이언트가 전송한 전송파라미터들을 객체의 필드로 모아서, 웹3계층의 뒷부분으로
// 전달하는 용도의 객체를 만드는 패턴

@Data
public class EmpDTO {	
	
	private Integer empno;
	
	
	

} // end class
