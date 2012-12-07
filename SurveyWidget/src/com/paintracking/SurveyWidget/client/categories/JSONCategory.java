package com.paintracking.SurveyWidget.client.categories;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class JSONCategory extends JavaScriptObject {
	  // Overlay types always have protected, zero argument constructors.
	  protected JSONCategory() {}                                              

	  // JSNI methods to get stock data.
	  public final native String getCategoryType() /*-{ return this.categoryType; }-*/;
	  public final native String getCategoryName() /*-{ return this.categoryName; }-*/;
	  public final native String getActualValue() /*-{ return this.actualValue; }-*/;
	  public final native JsArray<JsonOption> getOptions() /*-{ this.options; }-*/;

}
