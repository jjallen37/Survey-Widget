package com.paintracking.SurveyWidget.client.categories;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class JSONCategory extends JavaScriptObject {
	  // Overlay types always have protected, zero argument constructors.
	  protected JSONCategory() {}                                              

	  // JSNI methods to get stock data.
	  public final native String getCategoryType() /*-{ return this.categoryType; }-*/;
	  public final native String getCategoryName() /*-{ return this.categoryName; }-*/;
	  public final native String getUserid() /*-{ this.userid; }-*/;
	  public final native String getSurveyId() /*-{ this.surveyId; }-*/;
	  public final native String getCategoryId() /*-{ this.categoryId; }-*/;
	  public final native String getFields() /*-{ this.fields; }-*/;
	  public final native JsArray<JsonOption> getOptions() /*-{ this.options; }-*/;
	  
	  public final String asString(){
		  return "name:"+getCategoryName()+
				  "\ntype:"+getCategoryType()+
				  "\nuser:"+getUserid()+
				  "\nfields:"+getFields()+
				  "\ntype:"+getCategoryType();
	  }
}
