/**
 * @author James Allen 
 * December 2012
 * Comp 523: Paintracker
 *  
 *  CategoryOption is the helper class of OptionsCategory.
 *  CategoryOption is contained within an array of OptionsCategory.
 *  
 * 
 */

package com.paintracking.SurveyWidget.client.categories;

public class CategoryOption{
	private int index;
	private String option;
	    
	public CategoryOption(JsonOption jsonOption){
		this.index = jsonOption.getIndex();
		this.option = jsonOption.getOption();
	}
	
	public CategoryOption(int i, String newOption){
		index = i;
		option = newOption;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String toString(){
		return ""+index+" - "+option;
	}
	
}
