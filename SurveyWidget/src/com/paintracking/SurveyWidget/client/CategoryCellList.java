package com.paintracking.SurveyWidget.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.paintracking.SurveyWidget.client.detail.Detail;
import com.paintracking.SurveyWidget.client.detail.OptionsCategory;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.cellview.client.CellList;
import com.paintracking.SurveyWidget.client.categories.Category;
import com.paintracking.SurveyWidget.client.categories.CategoryOption;
import com.paintracking.SurveyWidget.client.categories.DateCategory;
import com.paintracking.SurveyWidget.client.categories.QuantityCategory;
import com.paintracking.SurveyWidget.client.categories.TextCategory;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class CategoryCellList extends Composite implements Master, CategoryList{
	private List<Category> dataList = new ArrayList<Category>();
	private CellTable<Category> cellTable;
	private ListDataProvider<Category> dataProvider;
	private Detail detailObject;

	public CategoryCellList() {

		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("300px", "385px");
		
		Label label = new Label("Categories");
		flexTable.setWidget(0, 0, label);
		
		setCellTable(new CellTable<Category>());
		flexTable.setWidget(1, 0, getCellTable());
		cellTable.setSize("100%", "100%");
		
		

		//Load the dummy data
		loadData();
		// Create a data provider for the cell table
		dataProvider = new ListDataProvider<Category>();
		// Connect the table to the data provider.
		dataProvider.addDataDisplay(getCellTable());
		dataProvider.setList(dataList);
		

		
		TextColumn<Category> categoryTextColumn = new TextColumn<Category>() {
			@Override
			public String getValue(Category object) {
				return object.getCategoryName();
			}
		};
		
		getCellTable().addColumn(categoryTextColumn, "Category");
		cellTable.setColumnWidth(categoryTextColumn, "100px");
		
		TextColumn<Category> valueTextColumn = new TextColumn<Category>() {
			@Override
			public String getValue(Category object) {
				return object.getActualValue();
			}
		};
		getCellTable().addColumn(valueTextColumn, "Value");
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		flexTable.setWidget(2, 0, horizontalPanel);
		horizontalPanel.setSize("100%", "50\n");
		
		Button button = new Button("Submit");
		button.setStyleName("gwt-Button-SurveyWidgetButton");
		horizontalPanel.add(button);
		
		Button button_1 = new Button("Clear");
		button_1.setStyleName("gwt-Button-SurveyWidgetButton");
		horizontalPanel.add(button_1);
		
		Button button_2 = new Button("Edit");
		button_2.setStyleName("gwt-Button-SurveyWidgetButton");
		horizontalPanel.add(button_2);
		
		Button button_3 = new Button("Add\n");
		button_3.setStyleName("gwt-Button-SurveyWidgetButton");
		horizontalPanel.add(button_3);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Detail getDetailObject() {
		return detailObject;
	}
	
	@Override
	public void setDetailObject(Detail detailObject) {
		this.detailObject = detailObject;
	}
	  
	@Override
	public void refresh() {
		dataProvider.setList(dataList);
		cellTable.redraw();
	}

	public CellTable<Category> getCellTable() {
		return cellTable;
	}

	public void setCellTable(CellTable<Category> cellTable) {
		this.cellTable = cellTable;
	}

	
	
	///Dummy data
	public void loadData(){
		//Dummy data before I added JSON data
		dataList = new ArrayList<Category>();
		
		OptionsCategory a = new OptionsCategory();
		a.setCategoryName("Pain");
		a.setCategoryType("options");
		a.setActualValue("");
		ArrayList<CategoryOption> optionsA = new ArrayList<CategoryOption>();
		optionsA.add(new CategoryOption(1,"NoneNoneNoneNoneNoneNoneNoneNoneNoneNoneNoneNoneNoneNoneNoneNoneNone" +
				"NoneNoneNoneNoneNoneNoneNoneNoneNoneNoneNoneNoneNoneNoneNoneNoneNoneNoneNoneNone"));
		optionsA.add(new CategoryOption(2,"Some"));
		optionsA.add(new CategoryOption(3,"A lot"));
		a.setOptions(optionsA);
		
		TextCategory b = new TextCategory();
		b.setCategoryName("Mood");
		b.setCategoryType("text");
		b.setActualValue("");
		
		//Date
		DateCategory c = new DateCategory();
		c.setCategoryName("Date Submitted");
		c.setCategoryType("date");
		c.setActualValue("");
		
		QuantityCategory d = new QuantityCategory();
		d.setCategoryName("Pushups");
		d.setCategoryType("quantity");
		d.setActualValue("");
		
		dataList.add(a);
		dataList.add(b);
		dataList.add(c);
		dataList.add(d);
	}
}
