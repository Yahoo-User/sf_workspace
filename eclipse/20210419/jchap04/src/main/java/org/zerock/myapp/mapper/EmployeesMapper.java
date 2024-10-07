package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.EmployeeVO;


public interface EmployeesMapper {
	

	@Select("SELECT last_name FROM employees")
	public abstract List<String> getAllNamesOfEmployees();		//--1. Using MyBatis Annotation
	
	@Select("SELECT * FROM employees")
	public abstract List<EmployeeVO> getAllEmployees();			//--1. Using MyBatis Annotation

} // end interface
