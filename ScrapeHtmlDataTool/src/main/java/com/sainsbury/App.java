package com.sainsbury;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sainsbury.dao.OutputHelper;
import com.sainsbury.exception.SainsburyException;
import com.sainsbury.model.Results;
import com.sainsbury.utility.SainsburyConstants;

/** 
 * @author Bhakti
 * 
 * This is the entry point for the application. To make an application specific for particular html link no need to pass an argument from command line.
 * To make this application generic for any html link to scrape data, need to pass a main method argument html link from command line. Need to make sure, the 
 * structure of web page is same for any link, as code looks for specific elements for e.g. div element "productInfo" etc.
 *
 */

public class App 
{
	final static Logger logger = Logger.getLogger(App.class); // Logger to log messages and error.
	
    public static void main( String[] args )
    {
    	logger.info("Start of the application.....");
    	ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {SainsburyConstants.SPRING_BEANS_XML});

    	OutputHelper output = (OutputHelper)context.getBean(SainsburyConstants.OUTPUT_HELPER);
    	Results results = null;
    	
    	try {
    		if(args.length==0){
    			// Scrape html data for a given Sainsbury product link.
    			results = output.getResults("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html");
    		}
    		else{
    			// To make application generic, pass the html link argument from command line. Scrape data for any html link with the same web page structure.
        		 results = output.getResults(args[0]);
    		}    		
			logger.info("Got the product list.... ");
			logger.info("JSON Output: "+output.getJsonOutput(results));
		} catch (IOException | JSONException | SainsburyException ex) {
			logger.error("Error while processing data : ", ex);
		} 
    }
}
