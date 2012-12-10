/**
 * @author James Allen 
 * December 2012
 * Comp 523: Paintracker
 *  
 *  Holds a simple category with text.
 * 
 */

package com.paintracking.SurveyWidget.client.categories;

public class TextCategory extends Category{
	
	public TextCategory(){
		this.categoryType = "text";
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
