package org.zerock.myapp.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
	private int tno;
	private String grade;
	private double price;

	
	
	public int get1() {
		return this.tno;
	} // get1
	
	public String get2() {
		return this.grade;
	} // get1
	
	public double get3() {
		return this.price;
	} // get1
	
	
} // end class
