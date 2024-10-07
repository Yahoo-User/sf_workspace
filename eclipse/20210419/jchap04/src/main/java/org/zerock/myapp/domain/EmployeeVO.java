package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;



@Value
public class EmployeeVO {

//	------------------------------------------------------------ //
//  OK
//	------------------------------------------------------------ //
//	private Integer EMPLOYEE_ID;
//	private String FIRST_NAME;
//	private String LAST_NAME;
//	private String EMAIL;
//	private String PHONE_NUMBER;
//	private Timestamp HIRE_DATE;
//	private String JOB_ID;
//	private Double SALARY;
//	private Double COMMISSION_PCT;
//	private Integer MANAGER_ID;
//	private Integer DEPARTMENT_ID;
	

//	------------------------------------------------------------ //
//  OK
//	------------------------------------------------------------ //
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Timestamp hireDate;
	private String jobId;
	private Double salary;
	private Double commissionPct;
	private Integer managerId;
	private Integer departmentId;
	
	
//	------------------------------------------------------------ //
//	XX
//	------------------------------------------------------------ //
//	Caused by: org.apache.ibatis.reflection.ReflectionException: 
//	Error instantiating class org.zerock.myapp.domain.EmployeeVO 
//	with invalid types (int,String,String,String,String,Timestamp,String,double,double,int,int) or 
//	values (100,Steven,King,SKING,515.123.4567,2003-06-17 00:00:00.0,AD_PRES,24000.0,null,null,90).
//	Cause: java.lang.IllegalArgumentException
//	------------------------------------------------------------ //
	
//	private int employeeId;
//	private String firstName;
//	private String lastName;
//	private String email;
//	private String phoneNumber;
//	private Timestamp hireDate;
//	private String jobId;
//	private double salary;
//	private double commissionPct;
//	private int managerId;
//	private int departmentId;

//	------------------------------------------------------------ //
	

} // end class
