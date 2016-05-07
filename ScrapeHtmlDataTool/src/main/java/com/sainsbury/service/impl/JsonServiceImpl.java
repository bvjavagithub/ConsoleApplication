/**
 * 
 */
package com.sainsbury.service.impl;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sainsbury.exception.SainsburyException;
import com.sainsbury.model.Product;
import com.sainsbury.model.Results;
import com.sainsbury.service.JsonService;
import com.sainsbury.utility.SainsburyConstants;

/**
 * @author Bhakti
 * Implementation class to get the JSON output from Java object.
 */
public class JsonServiceImpl implements JsonService {

	/**
	 * 
	 */
	public JsonServiceImpl() {
	}
	
	/**
	 * Method implementation to get the JSON output from Java list.
	 */
	public JSONObject getJsonOutput(Results results) throws JSONException, SainsburyException{
		JSONObject jObject = new JSONObject();
		JSONArray jsArray = new JSONArray();
		for(Product product : results.getProductList()){
			JSONObject productObject = new JSONObject();
				productObject.put(SainsburyConstants.TITLE, product.getTitle());
				productObject.put(SainsburyConstants.SIZE, product.getSize());
				productObject.put(SainsburyConstants.UNIT_PRICE, product.getUnitPrice());						
				productObject.put(SainsburyConstants.DESCRIPTION, product.getDescription());
				jsArray.put(productObject);
		}
		jObject.put(SainsburyConstants.RESULTS, jsArray);
		jObject.put(SainsburyConstants.TOTAL, results.getTotal());
		return jObject;
		
	}
}
