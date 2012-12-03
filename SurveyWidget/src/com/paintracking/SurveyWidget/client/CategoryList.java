package com.paintracking.SurveyWidget.client;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.paintracking.SurveyWidget.client.JsList.JsArrayExtend;
import com.paintracking.SurveyWidget.client.JsList.JsList;
import com.paintracking.SurveyWidget.client.detail.Detail;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;

public class CategoryList extends Composite implements Master{
	private List<PainCategory> dataList = new ArrayList<PainCategory>();
	private Detail detailObject;
	private CellList<PainCategory> categoryCellList;
	
	public CategoryList() {
		//Load dummy data
		loadData();
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("226px", "224px");
		
		setCategoryCellList(new CellList<PainCategory>(new AbstractCell<PainCategory>(){
			@Override
			public void render(Context context, PainCategory value, SafeHtmlBuilder sb) {
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
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		flexTable.setWidget(2, 0, horizontalPanel);
		horizontalPanel.setWidth("100%");
		
		Button btnSubmit = new Button("Submit");
		btnSubmit.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				System.out.println(asJSONData());
			}
		});
		btnSubmit.setStyleName("gwt-Button-SurveyWidgetButton");
		horizontalPanel.add(btnSubmit);
		
		Button btnClear = new Button("Clear");
		btnClear.setStyleName("gwt-Button-SurveyWidgetButton");
		horizontalPanel.add(btnClear);
		
		Button btnEdit = new Button("Edit");
		btnEdit.setStyleName("gwt-Button-SurveyWidgetButton");
		horizontalPanel.add(btnEdit);
		
		Button btnAddCategory = new Button("Add\n");
		btnAddCategory.setStyleName("gwt-Button-SurveyWidgetButton");
		horizontalPanel.add(btnAddCategory);
	}
	
	public void loadData(){
		//TODO Real data	
		

		//Testing JSON
		//Make dummy JSON Data
		String categoryOne = "{\"categoryName\":\"Pain\",\"actualValue\":\"3. A Lot\"}";
		String categoryTwo = "{\"categoryName\":\"Dinner\",\"actualValue\":\"Spagetti\"}";
		String jsonData = "["+categoryOne+","+categoryTwo+"]";
		
		//Parse jsonData into a JsArray
		JsArray<JSONCategory> jsCategoryArray = asArrayOfCategories(jsonData);
		
		//Convert JsArray into an ArrayList to create a mutable copy with a list interface for editing.
		for(int i=0; i<jsCategoryArray.length(); i++){
			dataList.add(new PainCategory(jsCategoryArray.get(i)));
		}
		
		
		//Before I realized JSON Category was a final object
//		JsArrayExtend<JSONCategory> jsonCategories = asArrayOfCategories(jsonData);
//		dataList = new JsList<JSONCategory>(jsonCategories);
		
		
		
//		//Dummy data before I added JSON data
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
	  private final native JsArray<JSONCategory> asArrayOfCategories(String json) /*-{
			return eval('('+json+')');
	  }-*/;

	  
	private String asJSONData(){
		String jsonData = "[";
		for(PainCategory category : dataList){
			jsonData += category.getJSON() + ",";
		}
		jsonData = jsonData.substring(0,jsonData.length()-1);//Take off last comma
		jsonData += "]";//Add ending
		return jsonData;
	}
	  
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

	public CellList<PainCategory> getCategoryCellList() {
		return categoryCellList;
	}

	public void setCategoryCellList(CellList<PainCategory> categoryCellList) {
		this.categoryCellList = categoryCellList;
	}


	
	

}
