/**
 * 
 */
package com.sainsbury.model;

import java.util.List;

/**
 * @author Bhakti
 * Model class to represent product list and total price values.
 */
public class Results {
	
	private List<Product> productList;
	private double Total;

	/**
	 * 
	 */
	public Results() {
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public double getTotal() {
		return Total;
	}

	public void setTotal(double total) {
		Total = total;
	}

	

	
}
