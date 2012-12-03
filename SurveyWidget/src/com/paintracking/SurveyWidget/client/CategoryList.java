package com.paintracking.SurveyWidget.client;

import java.util.List;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.paintracking.SurveyWidget.client.JsList.JsArrayExtend;
import com.paintracking.SurveyWidget.client.JsList.JsList;
import com.paintracking.SurveyWidget.client.detail.Detail;

public class CategoryList extends Composite implements Master{
	private List<JSONCategory> dataList;
	private Detail detailObject;
	private CellList<JSONCategory> categoryCellList;
	
	public CategoryList() {
		//Load dummy data
		loadData();
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("226px", "224px");
		
		setCategoryCellList(new CellList<JSONCategory>(new AbstractCell<JSONCategory>(){
			@Override
			public void render(Context context, JSONCategory value, SafeHtmlBuilder sb) {
				//Show a the Category name and the selected result
				sb.appendHtmlConstant("<table>");
				// Create a table to align the cells correctly
				sb.appendHtmlConstant("<tr>");
				sb.appendHtmlConstant("<td style='font-size:110%;'><b>");
				sb.appendEscaped(value.getCategoryName());
				sb.appendHtmlConstant("</b></td></tr><tr><td>");
				sb.appendEscaped(value.getActualValue());
				sb.appendHtmlConstant("</td></tr></table>");
			}
		}));
		
		//Set table data
		getCategoryCellList().setRowCount(dataList.size(), true);
		getCategoryCellList().setRowData(0, dataList);
		
		Label lblCategories = new Label("Categories");
		flexTable.setWidget(0, 0, lblCategories);
		
		
		flexTable.setWidget(1, 0, getCategoryCellList());
		getCategoryCellList().setSize("226px", "234px");
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}
	
	public void loadData(){
		//TODO Real data	
		

		//Testing JSON
		//Make dummy JSON Data
		String categoryOne = "{\"categoryName\":\"Pain\",\"actualValue\":\"3. A Lot\"}";
		String categoryTwo = "{\"categoryName\":\"Dinner\",\"actualValue\":\"Spagetti\"}";
		String jsonData = "["+categoryOne+","+categoryTwo+"]";
		
		//Evaluate JSON into List
		JsArrayExtend<JSONCategory> jsonCategories = asArrayOfCategories(jsonData);
		dataList = new JsList<JSONCategory>(jsonCategories);
		
//		//Dummy data
//		PainCategory a = new PainCategory();
//		a.setCategoryName("Pain");
//		a.setCategoryType("Options");
//		a.setActualValue("");
//
//		PainCategory b = new PainCategory();
//		b.setCategoryName("Time");
//		b.setCategoryType("time");
//		b.setActualValue("");
//
//		dataList.add(a);
//		dataList.add(b);
	}
	
	  /**
	   * Convert the string of JSON into JavaScript object.
	   */
	  private final native JsArrayExtend<JSONCategory> asArrayOfCategories(String json) /*-{
			return eval('('+json+')');
	  }-*/;

	  
	public void setDetailObject(Detail detailObject) {
		this.detailObject = detailObject;
	}
	  
	///Master interface methods
	@Override
	public Detail getDetailObject() {
		return detailObject;
	}

	@Override
	public void refresh() {
		getCategoryCellList().setRowCount(dataList.size(), true);
		getCategoryCellList().setRowData(0, dataList);
	}

	public CellList<JSONCategory> getCategoryCellList() {
		return categoryCellList;
	}

	public void setCategoryCellList(CellList<JSONCategory> categoryCellList) {
		this.categoryCellList = categoryCellList;
	}


	
	

}
