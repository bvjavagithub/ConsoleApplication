/**
 * 
 */
package com.sainsbury.service;

import java.io.IOException;

import com.sainsbury.exception.SainsburyException;
import com.sainsbury.model.Results;

/**
 * @author Bhakti
 *
 * Service interface to scrape the html data from the web page.
 */
public interface ScrapeHtmlDataService {
	/**
	 * Method definition to retrieve the html data for all the sainsbury products on the given page.
	 * @param htmlLink
	 * @return
	 * @throws IOException
	 * @throws SainsburyException
	 */
	public Results getResults(String htmlLink) throws IOException, SainsburyException;
}
