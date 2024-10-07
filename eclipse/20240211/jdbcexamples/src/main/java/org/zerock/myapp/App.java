package org.zerock.myapp;

import java.util.Arrays;
import java.util.Date;

import org.apache.logging.log4j.Level;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class App {
	
	
    public static void main( String[] args ) {
    	log.trace("main({}) invoked.", Arrays.toString(args));
    	
        log.printf(Level.INFO, "Hello World!, %s", new Date());
    } // main
    
} // end class
