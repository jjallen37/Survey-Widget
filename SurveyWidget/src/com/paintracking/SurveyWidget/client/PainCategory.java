package com.paintracking.SurveyWidget.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JsArray;

public class PainCategory {

	private int surveyID;
	private int categoryID;
	private int userID;
	private int fields;
	private List<CategoryOption> options;
	private int relativeValue;
	private String actualValue;
	private String categoryType;
	private String categoryName;

	public PainCategory(JSONCategory jsonCategory){
		//Make options list
		JsArray<JsonOption> tempOps = jsonCategory.getOptions();
		options = new ArrayList<CategoryOption>();
		
		//Convert the JSON to Category Option wrappers.
		for(int i=0; i<tempOps.length(); i++){
			CategoryOption categoryOption = new CategoryOption(tempOps.get(i));
			options.add(categoryOption);
		}
		
		//Wrap all of the variables
		categoryName = jsonCategory.getCategoryName();
		actualValue = jsonCategory.getActualValue();
		categoryType = jsonCategory.getCategoryType();
//		surveyID = jsonCategory.getSurveyID();
//		categoryID = jsonCategory.getCategoryID();
//		userID = jsonCategory.getUserID();

	}
	
	public PainCategory(){
		
	}
	
	public String getJSON(){
		//TODO write reflective java code to generate json
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
	public List<CategoryOption> getOptions() {
		return options;
	}
	public void setOptions(List<CategoryOption> options) {
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
