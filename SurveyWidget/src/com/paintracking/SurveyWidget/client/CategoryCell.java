package com.paintracking.SurveyWidget.client;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.paintracking.SurveyWidget.client.categories.Category;

public class CategoryCell extends AbstractCell<Category> implements Cell<String> {
	public void render(Context context, Category value, SafeHtmlBuilder sb) {
		//Show a the Category name and the selected result
		sb.appendHtmlConstant("<table>");
		// Create a table to align the cells correctly
		sb.appendHtmlConstant("<tr>");
		sb.appendHtmlConstant("<td style='font-size:110%;'><b>");
		sb.appendEscaped(value.getCategoryName());
		sb.appendHtmlConstant("</b></td></tr><tr><td>");
		if(value.getActualValue()!=null && value.getActualValue()!=""){
			sb.appendEscaped(value.getActualValue());
			sb.appendHtmlConstant("</td></tr></table>");
		}else{
			sb.appendHtmlConstant("</td></tr></table>");
			sb.appendHtmlConstant("<br>");
		}
	}

}
