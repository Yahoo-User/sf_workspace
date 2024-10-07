package org.zerock.myapp.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@WebListener
public class SessionScopeAttributeListener
	implements HttpSessionAttributeListener {
	
	
	private void printAttrInfo(HttpSessionBindingEvent event) {        
        String name = event.getName();
        Object value = event.getValue();
        HttpSession session = event.getSession();
        
        log.info("\t+1. name: " + name);
        log.info("\t+2. value: " + value);
        log.info("\t+3. Session ID: " + session.getId());
	} // printAttrInfo
	
	
	@Override
    public void attributeAdded(HttpSessionBindingEvent event)  { 
         log.debug("attributeAdded(event) invoked.");
         
         this.printAttrInfo(event);
    } // attributeAdded


	@Override
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
        log.debug("attributeRemoved(event) invoked.");

        this.printAttrInfo(event);
    } // attributeRemoved


	@Override
    public void attributeReplaced(HttpSessionBindingEvent event)  {
        log.debug("attributeReplaced(event) invoked.");

        this.printAttrInfo(event);
    } // attributeReplaced
	
} // end class
