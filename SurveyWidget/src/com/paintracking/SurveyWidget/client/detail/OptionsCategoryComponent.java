package com.paintracking.SurveyWidget.client.detail;

import java.util.List;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
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
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;

public class OptionsCategoryComponent extends Composite implements Detail {

	private PainCategory detailItem;
	private Master masterObject;
	private List<CategoryOption> dataList;
	private DataGrid<CategoryOption> dataGrid;
	private Column<CategoryOption, Number> column;
	private TextColumn<CategoryOption> textColumn;
	private ListDataProvider<CategoryOption> dataProvider;
	private Column<CategoryOption, String> deleteColumn;

	private boolean isEditing = false;

	public OptionsCategoryComponent() {

		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("226px", "280px");

		Label label = new Label("Category Title");
		flexTable.setWidget(0, 0, label);

		dataGrid = new DataGrid<CategoryOption>();
		flexTable.setWidget(1, 0, dataGrid);
		dataGrid.setSize("226px", "260px");

		column = new Column<CategoryOption, Number>(new NumberCell()) {
			@Override
			public Number getValue(CategoryOption object) {
				return object.getIndex();
			}
		};
		column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		dataGrid.addColumn(column);
		dataGrid.setColumnWidth(column, "20%");

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
		button_2.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				setEditing(!isEditing);
			}
		});
		button_2.setStyleName("gwt-Button-SurveyWidgetButton");
		horizontalPanel.add(button_2);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);

		// Create a data provider.
		dataProvider = new ListDataProvider<CategoryOption>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(dataGrid);

		deleteColumn = new Column<CategoryOption, String>(new ButtonCell()) {
			@Override
			public String getValue(CategoryOption object) {
				return "X";
			}

		};

		deleteColumn
				.setFieldUpdater(new FieldUpdater<CategoryOption, String>() {
					@Override
					public void update(int index, CategoryOption object,
							String value) {
						// The user clicked on the button for the passed
						// auction.
						dataList.remove(object);

						// Refresh
						configureView();
					}
				});

		// Add a selection model to master that pulls up the pain category
		// options in the detail view
		final SingleSelectionModel<CategoryOption> optionSelectionModel = new SingleSelectionModel<CategoryOption>();
		dataGrid.setSelectionModel(optionSelectionModel);

		optionSelectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						// Make the selected option the Pain Category's selected
						// value
						CategoryOption selectedCategory = optionSelectionModel
								.getSelectedObject();
						detailItem.setActualValue(selectedCategory.toString());
						// Reload the master view
						masterObject.refresh();
					}
				});

	}

	private void configureView() {
		if (isEditing()) {
			// dataGrid.setColumnWidth(deleteColumn,"30px");

			configureEditView();
		} else {
			dataGrid.setColumnWidth(deleteColumn, "1px");

			// Remove editing features if needed.
			// removeButtonColumn(deleteColumn);
		}

		// Set the data and reload the list
		dataList = detailItem.getOptions();
		dataProvider.setList(dataList);
	}

	private void configureEditView() {
		insertButtonColumn(deleteColumn);
	}

	public void insertButtonColumn(Column<CategoryOption, String> column) {
		if (dataGrid.getColumnIndex(column) == -1) {
			dataGrid.addColumn(column, "");
			// dataGrid.setColumnWidth(column,50, Unit.PX);
		}
		dataGrid.setColumnWidth(column, "25%");

	}

	public void removeButtonColumn(Column<CategoryOption, String> column) {
		int index = dataGrid.getColumnIndex(column);
		if (index != -1)
			dataGrid.setColumnWidth(column, "1px");
		// dataGrid.setSize("226px", "260px");

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

	public boolean isEditing() {
		return isEditing;
	}

	public void setEditing(boolean isEditing) {
		this.isEditing = isEditing;

		// Refresh the view based on editing.
		configureView();
	}
}
