package org.zerock.myapp.servlet.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebListener
public class RequestScopeAttributesListener
	implements ServletRequestAttributeListener {


	@Override
    public void attributeAdded(ServletRequestAttributeEvent event) {
    	if(
			event.getName().startsWith("org.springframework") || 
			event.getName().startsWith("org.apache") || 
			event.getName().startsWith("javax.servlet") || 
			event.getName().endsWith(".FILTERED")
		) {
    		return;
    	} // if
    	
    	log.debug("attributeAdded(event) invoked.");
    	
    	String name = event.getName();
    	Object value = event.getValue();
    	
    	log.info("\t+ name: " + name);
    	log.info("\t+ value: " + value);
    } // attributeAdded
	
	
	@Override
    public void attributeRemoved(ServletRequestAttributeEvent event) {
    	if(
			event.getName().startsWith("org.springframework") || 
			event.getName().startsWith("org.apache") || 
			event.getName().startsWith("javax.servlet") || 
			event.getName().endsWith(".FILTERED")
		) {
    		return;
    	} // if
    	
    	log.debug("attributeRemoved(event) invoked.");
    	
    	String name = event.getName();
    	Object value = event.getValue();
    	
    	log.info("\t+ name: " + name);
    	log.info("\t+ value: " + value);
    } // attributeRemoved


	@Override
    public void attributeReplaced(ServletRequestAttributeEvent event) {
    	if(
			event.getName().startsWith("org.springframework") || 
			event.getName().startsWith("org.apache") || 
			event.getName().startsWith("javax.servlet") || 
			event.getName().endsWith(".FILTERED")
		) {
    		return;
    	} // if
    	
    	log.debug("attributeReplaced(event) invoked.");
    	
    	String name = event.getName();
    	Object value = event.getValue();
    	
    	log.info("\t+ name: " + name);
    	log.info("\t+ value: " + value);
    } // attributeReplaced
	
} // end class
