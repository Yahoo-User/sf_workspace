package org.zerock.myapp.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@WebListener
public class RequestScopeAttributeListener
	implements ServletRequestAttributeListener {
	
	
	private void printAttrInfo(ServletRequestAttributeEvent event) {
		String name = event.getName();
        Object value = event.getValue();
        
        log.info("\t+ 1. name: " + name);
        log.info("\t+ 2. value: " + value);
	} // printAttrInfo

    
	@Override
    public void attributeRemoved(ServletRequestAttributeEvent event) { 
         log.debug("attributeRemoved(event) invoked.");
         
         this.printAttrInfo(event);
    } // attributeRemoved


	@Override
    public void attributeAdded(ServletRequestAttributeEvent event) {
        log.debug("attributeRemoved(event) invoked.");

        this.printAttrInfo(event);
    } // attributeAdded


	@Override
    public void attributeReplaced(ServletRequestAttributeEvent event) {
        log.debug("attributeRemoved(event) invoked.");

        this.printAttrInfo(event);
    } // attributeReplaced
	
} // end class
