package org.zerock.myapp.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@WebListener
public class ApplicationScopeAttributeListener 
	implements ServletContextAttributeListener {
	
	
	private void printAttrInfo(ServletContextAttributeEvent event) {
		String name = event.getName();
        Object value = event.getValue();
        
        log.info("\t+ 1. name: " + name);
        log.info("\t+ 2. value: " + value);
	} // printAttrInfo

    
	@Override
    public void attributeAdded(ServletContextAttributeEvent event) { 
         log.debug("attributeAdded(event) invoked.");
         
         this.printAttrInfo(event);
    } // attributeAdded

	
	@Override
    public void attributeRemoved(ServletContextAttributeEvent event) {  
        log.debug("attributeRemoved(event) invoked.");

        this.printAttrInfo(event);
    } // attributeRemoved

	
	@Override
    public void attributeReplaced(ServletContextAttributeEvent event) { 
        log.debug("attributeReplaced(event) invoked.");

        this.printAttrInfo(event);
    } // attributeReplaced
	
} // end class
