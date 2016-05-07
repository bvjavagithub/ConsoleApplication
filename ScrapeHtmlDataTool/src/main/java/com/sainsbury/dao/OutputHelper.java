/**
 * 
 */
package com.sainsbury.dao;

import java.io.IOException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sainsbury.exception.SainsburyException;
import com.sainsbury.model.Results;
import com.sainsbury.service.JsonService;
import com.sainsbury.service.ScrapeHtmlDataService;

/**
 * @author Bhakti
 *
 * This class represents Spring dependency injection principle. Loosely coupled classes.
 */
public class OutputHelper {

	ScrapeHtmlDataService scrapeHtmlData; // Object to access the ScrapeHtmlData tool.
	JsonService jsonService; // Object to access the JsonService tool.
	
	/**
	 * 
	 */
	public OutputHelper() {
	}

	public Results getResults(String htmlLink) throws IOException, SainsburyException{
		return scrapeHtmlData.getResults(htmlLink);
	}
	
	public JSONObject getJsonOutput(Results results) throws JSONException, SainsburyException{
		return jsonService.getJsonOutput(results);
	}

	public void setScrapeHtmlData(ScrapeHtmlDataService scrapeHtmlData) {
		this.scrapeHtmlData = scrapeHtmlData;
	}

	public void setJsonService(JsonService jsonService) {
		this.jsonService = jsonService;
	}
	
	
}
