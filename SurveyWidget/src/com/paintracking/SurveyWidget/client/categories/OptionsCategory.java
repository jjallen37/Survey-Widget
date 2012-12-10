package com.paintracking.SurveyWidget.client.categories;

import java.util.List;


public class OptionsCategory extends Category{

	private List<CategoryOption> options;
	private CategoryOption selectedOption;
	
	public OptionsCategory(){
		this.categoryType = "options";
	}
	
	public List<CategoryOption> getOptions() {
		return options;
	}

	public void setOptions(List<CategoryOption> options) {
		this.options = options;
	}

	public CategoryOption getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(CategoryOption selectedOption) {
		this.selectedOption = selectedOption;
	}
	
	public String getActualValue(){
		if(selectedOption==null)
			return "";
		return selectedOption.toString();
	}

	@Override
	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
	}

	@Override
	public void clear() {
		selectedOption=null;
	}
	
	

}
