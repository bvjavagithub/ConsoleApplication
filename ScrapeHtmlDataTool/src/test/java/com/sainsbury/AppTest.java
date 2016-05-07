package com.sainsbury;

import java.io.IOException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;import org.junit.Before;
import org.junit.Test;

import com.sainsbury.exception.SainsburyException;
import com.sainsbury.model.Results;
import com.sainsbury.service.impl.JsonServiceImpl;
import com.sainsbury.service.impl.ScrapeHtmlDataServiceImpl;

import static org.junit.Assert.*;

/**
 * Unit tests for project ScrapeHtmlDataTool.
 */
public class AppTest 
    
{
   String htmlLink="http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";	// Sainsbury product link given in assignment to test.
   Document doc;
   ScrapeHtmlDataServiceImpl scrapeHtmlDataImpl;
   JsonServiceImpl jsonServiceImpl;
   
   /**
    * This is set up method to create objects once to use in all the tests.
    * @throws IOException
    */
   @Before
   public void setUp() throws IOException{
	   doc = Jsoup.connect(htmlLink).get();
	   scrapeHtmlDataImpl = new ScrapeHtmlDataServiceImpl();
	   jsonServiceImpl = new JsonServiceImpl();
   }
  
   /**
    * To test the loaded document object is not null.
    */
   @Test
   public void testDocumentNotNull(){
	  assertNotNull(doc);
   }

   /**
    * Test the product title.
    * @throws IOException
    */
   @Test
   public void testProductTitle() throws IOException{
		doc = Jsoup.connect("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-apricot-ripe---ready-320g.html").get();
		String title = scrapeHtmlDataImpl.getProductTitle(doc);
		assertNotNull(title);
		assertEquals(title, "Sainsbury's Apricot Ripe & Ready x5");
   }   
  
   /**
    * Test the product unit price.
    * @throws IOException
    */
   @Test
   public void testProductUnitPrice() throws IOException{
		doc = Jsoup.connect("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-golden-kiwi--taste-the-difference-x4-685641-p-44.html").get();
		double unitPrice = scrapeHtmlDataImpl.getProductPricePerUnit(doc);
		String unitPriceStr=String.valueOf(unitPrice);
		assertEquals(unitPriceStr, "1.8");
   }
   
   /**
    * Test the product description.
    * @throws IOException
    */
   @Test
   public void testProductDescription() throws IOException{
		doc = Jsoup.connect("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-conference-pears--ripe---ready-x4-%28minimum%29.html").get();
		String description = scrapeHtmlDataImpl.getProductDescription(doc);
		assertNotNull(description);
		assertEquals(description, "Conference");
   }
   
   /**
    * Test the size of linked html.
    * @throws IOException
    */
   @Test
   public void testLinkedHTMLSize() throws IOException{
		doc = Jsoup.connect("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-conference-pears--ripe---ready-x4-%28minimum%29.html").get();
		String size = scrapeHtmlDataImpl.getProductSize(doc);
		assertNotNull(size);
		assertEquals(size, "35kb");
   }
   
   /**
    * Test the results object is not null.
    * @throws IOException
    * @throws SainsburyException
    */
   @Test
   public void testResultsNotNull() throws IOException, SainsburyException{
	   assertNotNull(scrapeHtmlDataImpl.getResults(htmlLink));
   }
   
   /**
    * Test the total unit price.
    * @throws IOException
    * @throws SainsburyException
    */
   @Test
   public void testTotalUnitPrice() throws IOException, SainsburyException{
	 Results results = scrapeHtmlDataImpl.getResults(htmlLink);
	 String totalStr = String.valueOf(results.getTotal());
	 assertEquals(totalStr, "15.100000000000001");
   }
   
   /**
    * Test the final JSON output.
    * @throws IOException
    * @throws JSONException
    * @throws SainsburyException
    */
   @Test
   public void testJSONOutputNotNull() throws IOException, JSONException, SainsburyException{
	   Results results = scrapeHtmlDataImpl.getResults(htmlLink);
	   JSONObject jObject = jsonServiceImpl.getJsonOutput(results);
	   assertNotNull(jObject);
   }
   
   
   
   
   

   
}
