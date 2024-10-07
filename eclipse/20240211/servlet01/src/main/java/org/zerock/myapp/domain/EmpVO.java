package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;


@Value	// for VO (Value Object)
public class EmpVO {	
	// 각 컬럼에 대응하는 필드의 타입은 기본타입을 사용해서는 안되고, 
	// 참조타입으로 사상(mapping)시켜야 함 - 이 부분이 어려운 것임.
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private Timestamp hireDate;
	private Double sal;
	private Double comm;
	private Integer deptno;
	

} // end class
