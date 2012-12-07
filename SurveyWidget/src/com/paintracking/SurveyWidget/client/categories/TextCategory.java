package com.paintracking.SurveyWidget.client.categories;


public class TextCategory extends Category{
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
