/**
 * @author James Allen 
 * December 2012
 * Comp 523: Paintracker
 *  
 *  Options Category component is the most complicated category component, 
 *  because it has adding features.
 *  The main cell widget is a DataGrid, which is provided CategoryOption data by
 *  the variable dataList. The composite can clear the current selection, 
 *  change options and title text, and add new options.
 */

package com.paintracking.SurveyWidget.client.detail;

import java.util.List;
import com.google.gwt.user.client.ui.Composite;
import com.paintracking.SurveyWidget.client.Detail;
import com.paintracking.SurveyWidget.client.Master;
import com.paintracking.SurveyWidget.client.categories.Category;
import com.paintracking.SurveyWidget.client.categories.CategoryOption;
import com.paintracking.SurveyWidget.client.categories.OptionsCategory;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.user.client.ui.TextBox;

public class OptionsCategoryComponent extends Composite implements Detail {

	private OptionsCategory detailItem;
	private Master masterObject;
	private List<CategoryOption> dataList;
	private DataGrid<CategoryOption> dataGrid;
	private Column<CategoryOption, Number> indexColumn;
	private TextColumn<CategoryOption> textColumn;
	private ListDataProvider<CategoryOption> dataProvider;
	private Column<CategoryOption, String> deleteColumn;

	private boolean isEditing = false;
	private Column<CategoryOption, String> editableTextColumn;
	private Label lblTitle;
	private Button editButton;
	private Button btnAdd;
	private HorizontalPanel horizontalPanel;
	private TextBox titleEditTextBox;
	
	//Max number of options 
	private final int MAX_OPTIONS = 10;
	private FlexTable flexTable;
	

	public OptionsCategoryComponent() {

		flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("250px", "280px");
		
		titleEditTextBox = new TextBox();
		titleEditTextBox.setSize("100%", "25px");
		titleEditTextBox.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
	            char charCode = event.getCharCode();
	            if(charCode == '\r'){//Enter
	            	saveTitleText();
	            }
			}
		});
		
		lblTitle = new Label("Category Title");
		lblTitle.setStyleName("gwt-Label-Title");
		flexTable.setWidget(0, 0, lblTitle);
		lblTitle.setHeight("25px");

		dataGrid = new DataGrid<CategoryOption>();
		flexTable.setWidget(1, 0, dataGrid);
		dataGrid.setSize("100%", "260px");


		horizontalPanel = new HorizontalPanel();
		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(2, 0, horizontalPanel);
		horizontalPanel.setWidth("100%");

		Button button_1 = new Button("Clear");
		button_1.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				detailItem.setActualValue("");
				detailItem.setSelectedOption(null);
				configureView();
			}
		});
		button_1.setStyleName("gwt-Button-SurveyWidgetButton");
		horizontalPanel.add(button_1);

		editButton = new Button("Edit");
		editButton.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				//Is about to stop editing
				if(isEditing){
					//Save the user changes to the title
					saveTitleText();
				}
				
				setEditing(!isEditing);
				
			}
		});
		editButton.setStyleName("gwt-Button-SurveyWidgetButton");
		horizontalPanel.add(editButton);
		
		btnAdd = new Button("Add");
		btnAdd.setStyleName("gwt-Button-SurveyWidgetButton");
		btnAdd.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				//Add new option and refresh if the user doesn't have the max number of options
				if(dataList.size()<MAX_OPTIONS){
					dataList.add(new CategoryOption(dataList.size()+1,""));
					configureView();
				}
			}
		});
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);
		
		indexColumn = new Column<CategoryOption, Number>(new NumberCell()) {
			@Override
			public Number getValue(CategoryOption object) {
				return object.getIndex();
			}
		};
		indexColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		dataGrid.setColumnWidth(indexColumn, "20%");

		textColumn = new TextColumn<CategoryOption>() {
			@Override
			public String getValue(CategoryOption object) {
				return object.getOption();
			}
		};

		//Editable column to delete options
		deleteColumn = new Column<CategoryOption, String>(new ButtonCell()) {
			@Override
			public String getValue(CategoryOption object) {
				return "X";
			}

		};

		editableTextColumn = new Column<CategoryOption, String>(new TextInputCell()) {
			@Override
			public String getValue(CategoryOption object) {
				return object.getOption();
			}
		};
		
		// Add a field updater to be notified when the user enters a new name.
		editableTextColumn.setFieldUpdater(new FieldUpdater<CategoryOption, String>() {
	      public void update(int index, CategoryOption object, String value) {
	    	  //If the currently selected item is changed, make sure the master list is updated.
	    	  if(detailItem.getActualValue().equals(object.toString())){
	    		  detailItem.setActualValue(object.toString());
	    	  }
	    	  
	    	  //Save the edit
	    	  object.setOption(value);
	    	  
	    	  //Refresh everything
	    	  configureView();
	      }
		});
		
		// Create a data provider.
		dataProvider = new ListDataProvider<CategoryOption>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(dataGrid);
		
		//Add Columns
		dataGrid.addColumn(indexColumn);
		dataGrid.addColumn(textColumn);
		dataGrid.addColumn(editableTextColumn);
		dataGrid.addColumn(deleteColumn);

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
						CategoryOption selectedOption = optionSelectionModel
								.getSelectedObject();
						detailItem.setSelectedOption(selectedOption);
//						detailItem.setActualValue(selectedOption.toString());
						// Reload the master view
						configureView();
					}
				});

	}

	private void configureView() {
		if (isEditing()) {
			configureEditView();
		} else {
			configureNormalView();
		}
		
		// Set the data and reload the list
		dataList = detailItem.getOptions();
		dataProvider.setList(dataList);
		
		//Redraw views
		dataGrid.redraw();
		masterObject.refresh();
	}

	private void configureEditView() {
		//Make the Title an editable text box
		titleEditTextBox.setText(detailItem.getCategoryName());
		flexTable.setWidget(0, 0, titleEditTextBox);

		//Hide normal columns
		dataGrid.setColumnWidth(textColumn, "0%");	

		//Show editable columns
		dataGrid.setColumnWidth(deleteColumn, "25%");	
		dataGrid.setColumnWidth(editableTextColumn, "60%");		
		
		//Change edit button title
		editButton.setText("Done");
		horizontalPanel.add(btnAdd);

	}
	
	private void configureNormalView() {
		//Make the title just a label
		lblTitle.setText(detailItem.getCategoryName());
		flexTable.setWidget(0, 0, lblTitle);

		//Show normal columns
		dataGrid.setColumnWidth(textColumn, "75%");	

		//Hide editble Columns
		dataGrid.setColumnWidth(deleteColumn, "0%");	
		dataGrid.setColumnWidth(editableTextColumn, "0%");	
		
		//Change edit button title
		editButton.setText("Edit");
		horizontalPanel.remove(btnAdd);
	}

	// //Detail Interface methods
	@Override
	public void setDetailItem(Category newDetailItem) {
		detailItem = (OptionsCategory)newDetailItem;

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
	
	private void saveTitleText(){
		if(titleEditTextBox.getText().length() == 0)
			return;
		
		detailItem.setCategoryName(titleEditTextBox.getText());
	}
}
