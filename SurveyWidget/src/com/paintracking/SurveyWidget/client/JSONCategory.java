package com.paintracking.SurveyWidget.client;

import com.google.gwt.core.client.JavaScriptObject;

public class JSONCategory extends JavaScriptObject {
	  // Overlay types always have protected, zero argument constructors.
	  protected JSONCategory() {}                                              // (2)

	  // JSNI methods to get stock data.
	  public final native String getCategoryType() /*-{ return this.categoryType; }-*/;
	  public final native String getCategoryName() /*-{ return this.categoryName; }-*/; // (3)
	  public final native String getActualValue() /*-{ return this.actualValue; }-*/;
	  public final native String[] getOptions() /*-{ return this.options; }-*/;

}
