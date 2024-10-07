package org.zerock.myapp.gson;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.zerock.myapp.domain.Ticket;

import com.google.gson.Gson;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GsonTests {
	
	private Ticket ticket;
	

	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		ticket = new Ticket(1000, "Yoseph", "A");
		log.info("\t+ ticket: " + ticket);
	} // beforeAll
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testTicketToJSON")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testTicketToJSON() {
		log.debug("testTicketToJSON() invoked.");
		
		Objects.requireNonNull(this.ticket);
		
		Gson gson = new Gson();
		String ticketJSON = gson.toJson(this.ticket);
		
		log.info("\t+ ticketJSON: " + ticketJSON);
	} // testTicketToJSON

	

} // end class
