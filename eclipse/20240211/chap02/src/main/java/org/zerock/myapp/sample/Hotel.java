package org.zerock.myapp.sample;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


//@Log4j2
@ToString
@Getter(AccessLevel.PUBLIC)
//@AllArgsConstructor							// *** 단일 생성자를 통한 묵시적 주입1
@RequiredArgsConstructor					// *** 단일 생성자를 통한 묵시적 주입3

@Component("hotel")
public class Hotel {
	
	// *** 단일 생성자를 통한 묵시적 주입1
//	private Chef chef;
	
	// *** 단일 생성자를 통한 묵시적 주입3
	private @NonNull Chef chef;
	
	
//	public Hotel(@Autowired Chef chef) {	// 생성자를 통한 명시적 주입 using @Autowired
//	public Hotel(Chef chef) {				// 단일 생성자를 통한 묵시적 주입2
//		log.debug("Contructor invoked.");
//		log.debug("\t+ chef: " + chef);
//		
//		this.chef = chef;
//	} // Constructor

} // end class
