package com.paintracking.SurveyWidget.client;

public class PainCategory {

	private int surveyID;
	private int categoryID;
	private int userID;
	private int fields;
	private String[] options;
	private int relativeValue;
	private String actualValue;
	private String categoryType;
	private String categoryName;

	public PainCategory(JSONCategory jsonCategory){
		//Wrap all of the variables
		categoryName = jsonCategory.getCategoryName();
		actualValue = jsonCategory.getActualValue();
	}
	
	public String getJSON(){
		return "{\"categoryName\":\""+categoryName+"\", \"actualValue\":\""+actualValue+"\"}";
	}
	
	public int getSurveyID() {
		return surveyID;
	}
	public void setSurveyID(int surveyID) {
		this.surveyID = surveyID;
	}
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getFields() {
		return fields;
	}
	public void setFields(int fields) {
		this.fields = fields;
	}
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) {
		this.options = options;
	}
	public String getActualValue() {
		return actualValue;
	}
	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
	}
	public int getRelativeValue() {
		return relativeValue;
	}
	public void setRelativeValue(int relativeValue) {
		this.relativeValue = relativeValue;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
