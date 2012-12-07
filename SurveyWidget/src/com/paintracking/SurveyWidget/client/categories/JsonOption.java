package com.paintracking.SurveyWidget.client.categories;

import com.google.gwt.core.client.JavaScriptObject;

public class JsonOption extends JavaScriptObject{
	  // Overlay types always have protected, zero argument constructors.
	  protected JsonOption() {}                                              
	  
	  public final native int getIndex() /*-{ return this.index; }-*/;
	  public final native String getOption() /*-{ return this.option; }-*/;
}
