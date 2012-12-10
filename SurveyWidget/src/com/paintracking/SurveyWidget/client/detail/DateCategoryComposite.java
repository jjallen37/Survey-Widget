/**
 * @author James Allen 
 * December 2012
 * Comp 523: Paintracker
 *  
 *  The DateCategoryComposite is a GWT composite that contains a date picker.
 *  The date picker is connected to a Category passed from the Master object,
 *  and formatted in "MM/dd/yyyy" format.
 *
 */

package com.paintracking.SurveyWidget.client.detail;

import com.google.gwt.user.client.ui.Composite;
import com.paintracking.SurveyWidget.client.Detail;
import com.paintracking.SurveyWidget.client.Master;
import com.paintracking.SurveyWidget.client.categories.Category;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

import java.util.Date;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;

public class DateCategoryComposite extends Composite implements Detail {

	private Category detailItem;
	private Master masterObject;
	private DatePicker datePicker;
	private Label lblDate;
	private Label lblTitle;
	private TextBox titleEditTextBox;
	private boolean isEditing; 
	private FlexTable flexTable;
	private HorizontalPanel horizontalPanel;
	private Button editButton;

	public DateCategoryComposite() {
		
		flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("226px", "280px");
		
		lblTitle = new Label("Category Title");
		lblTitle.setStyleName("gwt-Label-Title");
		flexTable.setWidget(0, 0, lblTitle);
		
		datePicker = new DatePicker();
		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				//Reference the selected date
		        Date date = event.getValue();
		        //Calculate the date string 
		        String dateString = DateTimeFormat.getFormat("MM/dd/yyy").format(date);
		        //Set the label above the date picker
		        lblDate.setText(dateString);
		        detailItem.setActualValue(dateString);
		        masterObject.refresh();
			}
		});
		
		lblDate = new Label("Date");
		flexTable.setWidget(1, 0, lblDate);
		flexTable.setWidget(2, 0, datePicker);
		
		Label label = new Label("");
		flexTable.setWidget(3, 0, label);
		
		horizontalPanel = new HorizontalPanel();
		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(4, 0, horizontalPanel);
		horizontalPanel.setWidth("100%");
		
		Button button_1 = new Button("Clear");
		button_1.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				detailItem.setActualValue("");
		        lblDate.setText("");
				masterObject.refresh();
			}
		});
		horizontalPanel.add(button_1);
		
		editButton = new Button("Edit");
		editButton.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				//Is about to stop editing
				if(isEditing){
					//Save the user changes to the title
					saveTitleText();
				}
				//Toggle editing
				setEditing(!isEditing);
			}
		});
		horizontalPanel.add(editButton);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_MIDDLE);
	}

	
	@Override
	public void setDetailItem(Category newDetailItem) {
		detailItem = newDetailItem;
		
	}

	@Override
	public void setMasterObject(Master newMasterObject) {
		masterObject = newMasterObject;
	}
	
	private void configureView() {
		if (isEditing()) {
			configureEditView();
		} else {
			configureNormalView();
		}

		masterObject.refresh();
	}

	private void configureEditView() {
		//Make the Title an editable text box
		titleEditTextBox.setText(detailItem.getCategoryName());
		flexTable.setWidget(0, 0, titleEditTextBox);

		//Change edit button title
		editButton.setText("Done");

	}
	
	private void configureNormalView() {
		//Make the title just a label
		lblTitle.setText(detailItem.getCategoryName());
		flexTable.setWidget(0, 0, lblTitle);
		
		//Change edit button title
		editButton.setText("Edit");
	}
	
	private void saveTitleText(){
		if(titleEditTextBox.getText().length() == 0)
			return;
		
		detailItem.setCategoryName(titleEditTextBox.getText());
	}

	public boolean isEditing() {
		return isEditing;
	}

	public void setEditing(boolean isEditing) {
		this.isEditing = isEditing;
		configureView();
	}
	

}
