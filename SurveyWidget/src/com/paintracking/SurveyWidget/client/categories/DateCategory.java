package com.paintracking.SurveyWidget.client.categories;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class DateCategory extends Category {
	Date today;
	String dateOut;
	DateFormat dateFormatter;

	public DateCategory(){
		dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.US);
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
