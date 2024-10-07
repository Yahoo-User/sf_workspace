package org.zerock.myapp;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class DOM4jTests {
	
	
	@BeforeAll
	static void beforeAll() {
		log.trace("beforeAll() invoked.");
		
	} // beforeAll
	
	@AfterAll
	static void afterAll() {
		log.trace("afterAll() invoked.");
		
	} // afterAll
	
	@BeforeEach
	void beforeEach() {
		log.trace("beforeEach() invoked.");
		
	} // beforeEach
	
	@AfterEach
	void afterEach() {
		log.trace("afterEach() invoked.");
		
	} // afterEach
	
	@Test
	@Order(1)
	@DisplayName("1. Read a XML document with DOM4j")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void readXML() throws DocumentException {
		log.trace("dom4jXMLRead() invoked.");
		
//		-------
		
		SAXReader reader = new SAXReader();
		log.info("\t+ reader: {}", reader);
		
//		-------
		
		Document doc = reader.read("src/main/resources/tutorials.xml");
		log.info("\t+ doc: {}", doc);
		
//		-------
		
		log.info("\t+ doc.getDocType: {}", doc.getDocType());
		log.info("\t+ doc.getName: {}", doc.getName());		// XML path
		log.info("\t+ doc.getXMLEncoding: {}", doc.getXMLEncoding());
		
//		-------
		
		Element root = doc.getRootElement();
		
		log.info("\t+ root: {}", root);
		log.info("\t+ root.getName: {}", root.getName());
		log.info("\t+ root.getNodeTypeName: {}", root.getNodeTypeName());
//		log.info("\t+ root.getText: {}", root.getText());
		
//		-------

		@Cleanup("clear")
		List<Element> tutorials = root.elements("tutorial");
		
		log.info("-------------------------------");
		tutorials.forEach(log::info);
		
		tutorials.forEach(t -> {
			String seq = t.attribute("seq").getValue();
			log.printf(Level.TRACE, "seq: %02d", Integer.parseInt(seq));
			
			Element title = t.element("title");
			Element desc = t.element("description");
			Element date = t.element("date");
			Element author = t.element("author");
			
			log.info("[{}]. title: {}, desc: {}, date: {}, author: {}",
					seq, title.getStringValue(), desc.getStringValue(), date.getStringValue(), author.getStringValue());
			
		});	// .forEach
		
//		-------
		
//		tutorials.clear();
	} // contextLoads
	
	
	@Test
	@Order(2)
	@DisplayName("2. Filtering wanted elements.")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void filterElements() throws MalformedURLException, DocumentException {
		log.trace("filterElements() invoked.");
		
		SAXReader reader = new SAXReader();
		Document doc = reader.read("src/main/resources/tutorials.xml");
		
		// `Jaxen` is a universal `XPath Engine` for JAVA. (***)
		String xpath = "/tutorials/tutorial";
		
		@Cleanup("clear")
		List<Node> tutorials = doc.selectNodes(xpath);
		
		tutorials.forEach(log::info);
		
		tutorials.forEach(n -> {
			Element t = (Element) n;

			int seq = Integer.parseInt(t.attributeValue("seq"));				// OK : 1
//			int seq = Integer.parseInt(t.attribute("seq").getValue());			// OK : 2
//			int seq = Integer.parseInt(t.attribute("seq").getStringValue());	// OK : 3
			
//			int attrIndex = 0;
//			int seq = Integer.parseInt(t.attribute(attrIndex).getText());		// OK : 4
			
			Element title = t.element("title");
			Element desc = t.element("description");
			Element date = t.element("date");
			Element author = t.element("author");
			
			log.info("[{}]. title: {}, desc: {}, date: {}, author: {}",
					seq, title.getStringValue(), desc.getStringValue(), date.getStringValue(), author.getStringValue());
			
		});	// .forEach

	} // filterElements
	
	@Test
	@Order(3)
	@DisplayName("3. Read wanted elements by XPath expression.")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void filterAuthorsByXPath() throws DocumentException {
		log.trace("filterAuthorsByXPath() invoked.");
		
//		String xpath = "/tutorials/tutorial/author";	// OK
		
		// Search all <author> nodes in the document in the current node,
		// regardless of location. (***)
		String xpath = "//author";						// OK
		
		// Wild card character * == all matchs. (***)
//		String xpath = "/*/*/author";					// OK
		
//		String xpath = "/*/*/auth*";					// XX
//		String xpath = "/*/*/auth??";					// XX
		
		SAXReader reader = new SAXReader();
		Document doc = reader.read("src/main/resources/tutorials.xml");
		
		@Cleanup("clear")
		List<Node> authors = doc.selectNodes(xpath);
		
		authors.forEach(log::info);
		
		log.info("-------------------------");
		
		authors.forEach(n -> {
			Element e = (Element) n;
			
			String author = e.getStringValue();
			
			String type = e.attributeValue("type");					// OK : 1
//			String type = e.attribute("type").getValue();			// OK : 2
//			String type = e.attribute("type").getStringValue();		// OK : 3
//			int attrIndex = 0;
//			String type = e.attribute(attrIndex).getValue();		// OK : 4
			
			log.info("author: {}, type: {}", author, type);
		});	// .forEach
		
	} // filterAuthorsByXPath
	
	
	@Test
	@Order(4)
	@DisplayName("4. Update XML document.")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void updateXML() throws DocumentException, IOException {
		log.printf(Level.TRACE, "updateXML() invoked.");
		
		SAXReader read = new SAXReader();
		Document doc = read.read("src/main/resources/tutorials.xml");
		
		String xpath = "/tutorials/*/author";
		
		@Cleanup("clear")
		List<Node> authors = doc.selectNodes(xpath);
		authors.forEach(log::info);
		
		authors.forEach(n -> {
			Element author = (Element) n;
			
			author.addAttribute("attr1", "value1");		// 1. Add new attribute.
//			author.setAttributeValue("", "");			// Deprecated.
			
//			---
			
			author.setText("NEW NAME");					// 2. Change Text Content.
			
//			---
			
			Attribute attr1 = author.attribute("attr1");
			author.remove(attr1);						// 3. Remove the specified attribute.
			
//			---
			
			author.detach();							// 4. Remove this element.
		});	// .forEach

//		----
		
		// 5. Add New Element.
		Element root = doc.getRootElement();
		
		List<Element> tutorials = root.elements("tutorial");
		tutorials.forEach(log::info);
		
		tutorials.forEach(tutorial -> {
			Element newAuthor = tutorial.addElement("author");
			
			newAuthor.setText("NEW AUTHOR");
			
			newAuthor.addAttribute("attr1", "val1");
			newAuthor.addAttribute("attr2", "val2");
		});	// .forEach

//		----
		
		@Cleanup FileWriter fw = new FileWriter("src/main/resources/tutorials.xml");
		@Cleanup XMLWriter writer = new XMLWriter(fw);
		
		writer.write(doc);
		writer.flush();
		
	} // updateXML
	
} // end class
