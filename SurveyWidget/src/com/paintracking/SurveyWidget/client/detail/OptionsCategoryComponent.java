package com.paintracking.SurveyWidget.client.detail;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.paintracking.SurveyWidget.client.Master;
import com.paintracking.SurveyWidget.client.PainCategory;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.ListDataProvider;

public class OptionsCategoryComponent extends Composite implements Detail{

	
	private static class OptionsCategoryOption{
		private int index;
		private String text;
		public OptionsCategoryOption(int newIndex, String newText){
			setIndex(newIndex);
			setText(newText);
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
	}
	
	private PainCategory detailItem;
	private Master masterObject;
	private List<OptionsCategoryOption> dataList;
	private DataGrid<OptionsCategoryOption> dataGrid;
	private Column<OptionsCategoryOption, Number> column;
	private TextColumn<OptionsCategoryOption> textColumn;
	
	public OptionsCategoryComponent() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("226px", "280px");
		
		Label label = new Label("Category Title");
		flexTable.setWidget(0, 0, label);
		
		dataGrid = new DataGrid<OptionsCategoryOption>();
		flexTable.setWidget(1, 0, dataGrid);
		dataGrid.setSize("100%", "280px");
		
		column = new Column<OptionsCategoryOption, Number>(new NumberCell()) {
			@Override
			public Number getValue(OptionsCategoryOption object) {
				return object.getIndex();
			}
		};
		dataGrid.addColumn(column, "Index");
		
		textColumn = new TextColumn<OptionsCategoryOption>() {
			@Override
			public String getValue(OptionsCategoryOption object) {
				return object.getText();
			}
		};
		dataGrid.addColumn(textColumn, "Option");
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		flexTable.setWidget(2, 0, horizontalPanel);
		horizontalPanel.setWidth("100%");
		
		Button button = new Button("Save Text");
		button.setStyleName("gwt-Button-SurveyWidgetButton");
		horizontalPanel.add(button);
		
		Button button_1 = new Button("Clear");
		button_1.setStyleName("gwt-Button-SurveyWidgetButton");
		horizontalPanel.add(button_1);
		
		Button button_2 = new Button("Edit");
		button_2.setStyleName("gwt-Button-SurveyWidgetButton");
		horizontalPanel.add(button_2);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		// Create a data provider.
	    ListDataProvider<OptionsCategoryOption> dataProvider = new ListDataProvider<OptionsCategoryOption>();
	    
	    // Connect the table to the data provider.
		dataProvider.addDataDisplay(dataGrid);
		
	    // Add the data to the data provider, which automatically pushes it to the
	    // widget.
	    dataList = dataProvider.getList();
	    
	    //Get the options from the detail item
	    String[] options = detailItem.getOptions();
	    
	    //Add options to list.x
	    for (int i=0; i<options.length; i++) {
	      dataList.add(new OptionsCategoryOption(i,options[i]));
	    }

	    
	    //TODO Add cell selection
//	    // Add a selection model to handle user selection.
//	    final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
//	    cellList.setSelectionModel(selectionModel);
//	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//	      public void onSelectionChange(SelectionChangeEvent event) {
//	        String selected = selectionModel.getSelectedObject();
//	        if (selected != null) {
//	          Window.alert("You selected: " + selected);
//	        }
//	      }
//	    });
		
	}
	
	private void configureView(){
		
	}
	
	////Detail Interface methods
	@Override
	public void setDetailItem(PainCategory newDetailItem) {
		detailItem = newDetailItem;

		//Configure view with new Data
		configureView();	
	}


	@Override
	public void setMasterObject(Master newMasterObject){
		masterObject = newMasterObject;
	}
}
