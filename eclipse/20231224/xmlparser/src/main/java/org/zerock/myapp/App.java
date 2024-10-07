package org.zerock.myapp;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;


/**
 * Hello world!
 *
 */
@Log4j2
public class App 
{
    public static void main( String[] args )
    {
    	log.trace("main({}) invoked.", Arrays.toString(args));
    	log.info("type of args: {}", args.getClass().getName());
    	
//        System.out.println( "Hello World!" );
    	log.info("Hello World!");
    }
} // end class
