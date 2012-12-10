/**
 * @author James Allen 
 * December 2012
 * Comp 523: Paintracker
 *  
 *  Holds a category's numerical quantity, and has code
 *  to increment and decrement the stored value
 * 
 */

package com.paintracking.SurveyWidget.client.categories;

public class QuantityCategory extends Category{

	private double quantity; 
	
	public QuantityCategory(){
		this.categoryType = "quantity";
	}
	
	@Override
	public String getActualValue() {
		return ""+this.quantity;
	}

	@Override
	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	public void incQuantity(double inc){
		quantity+=inc;
	}
	
	public void decQuantity(double dec){
		quantity-=dec;
	}

	public void clear() {
		setQuantity(0.0);
	}
	
	
	
	

}
