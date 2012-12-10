/**
 * @author James Allen 
 * December 2012
 * Comp 523: Paintracker
 *  
 *  Holds a category's title and date.
 * 
 */


package com.paintracking.SurveyWidget.client.categories;

public class DateCategory extends Category {
//	Date today;
	String dateOut;

	public DateCategory(){
		this.categoryType = "date";
	}

	@Override
	public String getActualValue() {
		return this.actualValue;
	}

	@Override
	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
	}
	
	@Override
	public void clear() {
		this.actualValue = "";
	}
	
}
