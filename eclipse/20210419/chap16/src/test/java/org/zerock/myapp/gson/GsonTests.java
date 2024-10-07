package org.zerock.myapp.gson;

import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zerock.myapp.domain.Ticket;

import com.google.gson.Gson;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor
public class GsonTests {
	
	private Ticket ticket;
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		ticket = new Ticket(1000, "Yoseph", "A");
		log.info("\t+ ticket: " + ticket);
	} // setup
	
	
	@Test
	public void testTicketToJSON() {
		log.debug("testTicketToJSON() invoked.");
		
		Objects.requireNonNull(this.ticket);
		
		Gson gson = new Gson();
		String ticketJSON = gson.toJson(this.ticket);
		
		log.info("\t+ ticketJSON: " + ticketJSON);
	} // testTicketToJSON
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
	} // tearDown

} // end class
