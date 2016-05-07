/**
 * 
 */
package com.sainsbury.service;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sainsbury.exception.SainsburyException;
import com.sainsbury.model.Results;

/**
 * @author Bhakti
 * Service interface to get the JSON output from Java object.
 */
public interface JsonService {
	/**
	 * Method definition to get the JSON output from Java list.
	 * @param results
	 * @return
	 * @throws JSONException
	 * @throws SainsburyException
	 */
	public JSONObject getJsonOutput(Results results) throws JSONException, SainsburyException;
}
