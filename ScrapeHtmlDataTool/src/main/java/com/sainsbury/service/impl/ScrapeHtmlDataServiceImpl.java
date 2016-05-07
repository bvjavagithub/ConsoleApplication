/**
 * 
 */
package com.sainsbury.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sainsbury.exception.SainsburyException;
import com.sainsbury.model.Product;
import com.sainsbury.model.Results;
import com.sainsbury.service.ScrapeHtmlDataService;
import com.sainsbury.utility.SainsburyConstants;

/**
 * @author Bhakti
 * 
 * Implementation class to scrape the html data from the web page.
 *
 */
public class ScrapeHtmlDataServiceImpl implements ScrapeHtmlDataService {

	/**
	 * 
	 */
	public ScrapeHtmlDataServiceImpl() {
	}
	/**
	 * Method implementation to retrieve the html data for all the sainsbury products on the given page.
	 */
	public Results getResults(String htmlLink) throws IOException, SainsburyException{
		Results results = new Results(); // Model class to hold the Product list and total price count.
		
		List<Product> productList = new ArrayList<Product>(); // List of all products on the given page.
		
		Product product;
		double total=0;
		
		Document doc = Jsoup.connect(htmlLink).get(); // Jsoup functionality to get document obj for a given link page.
		Elements elements = doc.getElementsByClass(SainsburyConstants.PRODUCT_INFO); // Get all the div elements of class "productInfo".
		
		for(int i=0; i<elements.size();i++){
			Element element = elements.get(i);
			Elements anchorTagList = element.getElementsByTag(SainsburyConstants.ANCHOR_TAG); // Get the list of all <a> tags within div "productInfo".
			if(null != anchorTagList && anchorTagList.size()==1){
				product = new Product();
				Element anchorTag = anchorTagList.get(0);
				doc = Jsoup.connect(anchorTag.attr(SainsburyConstants.HREF)).get();
				
				product.setSize(getProductSize(doc));
				product.setTitle(getProductTitle(doc));
				product.setDescription(getProductDescription(doc));
				
				double unitPrice = getProductPricePerUnit(doc);
				product.setUnitPrice(unitPrice);
				
				productList.add(product);
				total = total+unitPrice;
			}
		}
		
		results.setProductList(productList);
		results.setTotal(total);
		
		return results;
	}
	
	/**
	 * Helper method to get the size of linked html.
	 * @param doc
	 * @return
	 */
	public String getProductSize(Document doc){
		return (doc.html().length())/1024+SainsburyConstants.KB;
	}

	/**
	 * Helper method to get the title of product.
	 * @param doc
	 * @return
	 */
	public String getProductTitle(Document doc){
		Elements headerList = doc.getElementsByTag(SainsburyConstants.HEADER_1);
		if(null != headerList && headerList.size()==1){
			Element headerTag = headerList.get(0);
			String title = headerTag.text();
			return title;
		}
		return null;
	}
	
	/**
	 * Helper method to get the unit price of product.
	 * @param doc
	 * @return
	 */
	public double getProductPricePerUnit(Document doc){
		Elements elements = doc.getElementsByClass(SainsburyConstants.PRODUCT_SUMMARY);
		double unitPrice=0;
		if(null != elements && elements.size()==1){
			Element divElement = elements.get(0);
			Elements productSummaryElements = divElement.getElementsByClass(SainsburyConstants.PRICE_PER_UNIT);
			if(null != productSummaryElements && productSummaryElements.size()==1){
				Element pricePerUnitElement = productSummaryElements.get(0);
				String unitPriceStr = pricePerUnitElement.text();
			    unitPrice = Double.parseDouble(unitPriceStr.substring(1, unitPriceStr.indexOf(SainsburyConstants.UNIT)-1));
			}
		}
		return unitPrice;
	}
	
	/**
	 * Helper method to get the description of product.
	 * @param doc
	 * @return
	 */
	public String getProductDescription(Document doc){
		int descrIndex = doc.html().indexOf(SainsburyConstants.PRODUCT_DATA_ITEM_HEADER);
		int startParaIdx = doc.html().indexOf(SainsburyConstants.START_PARA_TAG, descrIndex);
		int endParaIdx = doc.html().indexOf(SainsburyConstants.END_PARA_TAG, descrIndex);
		String description = doc.html().substring(startParaIdx+3, endParaIdx);
		return description;
	}

}
