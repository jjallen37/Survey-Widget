package com.paintracking.SurveyWidget.client.detail;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.paintracking.SurveyWidget.client.CategoryOption;
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
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class OptionsCategoryComponent extends Composite implements Detail {

	private PainCategory detailItem;
	private Master masterObject;
	private List<CategoryOption> dataList;
	private DataGrid<CategoryOption> dataGrid;
	private Column<CategoryOption, Number> column;
	private TextColumn<CategoryOption> textColumn;
	private ListDataProvider<CategoryOption> dataProvider;

	public OptionsCategoryComponent() {

		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("226px", "280px");

		Label label = new Label("Category Title");
		flexTable.setWidget(0, 0, label);

		dataGrid = new DataGrid<CategoryOption>();
		flexTable.setWidget(1, 0, dataGrid);
		dataGrid.setSize("100%", "280px");

		column = new Column<CategoryOption, Number>(new NumberCell()) {
			@Override
			public Number getValue(CategoryOption object) {
				return object.getIndex();
			}
		};
		column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		dataGrid.addColumn(column, "Index");
		dataGrid.setColumnWidth(column, "50px");

		textColumn = new TextColumn<CategoryOption>() {
			@Override
			public String getValue(CategoryOption object) {
				return object.getOption();
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
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);

		// Create a data provider.
		dataProvider = new ListDataProvider<CategoryOption>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(dataGrid);

		// Add a selection model to master that pulls up the pain category
		// options in the detail view
		final SingleSelectionModel<CategoryOption> optionSelectionModel = new SingleSelectionModel<CategoryOption>();
		dataGrid.setSelectionModel(optionSelectionModel);
		optionSelectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						// Make the selected option the Pain Category's selected value
						CategoryOption selectedCategory = optionSelectionModel.getSelectedObject();
						detailItem.setActualValue(selectedCategory.toString());
						//Reload the master view
						masterObject.refresh();
					}
				});

		// Add the data to the data provider, which automatically pushes it to
		// the
		// widget.
		// dataList = dataProvider.getList();

		// Old code that iterates through and sets options.
		// String[] options = detailItem.getOptions();
		// //Add options to list.x
		// for (int i=0; i<options.length; i++) {
		// dataList.add(new OptionsCategoryOption(i,options[i]));
		// }

		// TODO Add cell selection
		// // Add a selection model to handle user selection.
		// final SingleSelectionModel<String> selectionModel = new
		// SingleSelectionModel<String>();
		// cellList.setSelectionModel(selectionModel);
		// selectionModel.addSelectionChangeHandler(new
		// SelectionChangeEvent.Handler() {
		// public void onSelectionChange(SelectionChangeEvent event) {
		// String selected = selectionModel.getSelectedObject();
		// if (selected != null) {
		// Window.alert("You selected: " + selected);
		// }
		// }
		// });

	}

	private void configureView() {
		// Set the data
		dataList = detailItem.getOptions();
		dataProvider.setList(dataList);
	}

	// //Detail Interface methods
	@Override
	public void setDetailItem(PainCategory newDetailItem) {
		detailItem = newDetailItem;

		// Configure view with new Data
		configureView();
	}

	@Override
	public void setMasterObject(Master newMasterObject) {
		masterObject = newMasterObject;
	}
}
