package com.paintracking.SurveyWidget.client.JsList;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class JsArrayExtend<T extends JavaScriptObject> extends JsArray<T>
{
	protected JsArrayExtend(){};
	
	public final native void add(int index, T element) /*-{ this.splice(index, 0, element) ;}-*/;
	
	public final native int indexOf(JavaScriptObject search) /*-{ return this.indexOf(search); }-*/; 
	
	public final native int lastIndexOf(JavaScriptObject search) /*-{ return this.lastIndexOf(search); }-*/;
	
	public final native JsArrayExtend<T> splice(int begin, int end) /*-{ return this.splice(begin, end);}-*/;
}