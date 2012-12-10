package com.paintracking.SurveyWidget.client.detail;

import com.google.gwt.user.client.ui.Composite;
import com.paintracking.SurveyWidget.client.Master;
import com.paintracking.SurveyWidget.client.categories.Category;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

//import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.gwt.event.logical.shared.ValueChangeEvent;

public class DateCategoryComposite extends Composite implements Detail {

	private Category detailItem;
	private Master masterObject;
	private DatePicker datePicker;
	private Label lblDate;
	private Label lblTitle;
	/*

	public DateCategoryComposite() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("226px", "280px");
		
		lblTitle = new Label("Category Title");
		flexTable.setWidget(0, 0, lblTitle);
		
		datePicker = new DatePicker();
		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				//Reference the selected date
		        Date date = event.getValue();
		        //Calculate the date string 
		        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		        //Set the label above the date picker
		        lblDate.setText(dateFormat.format(date));
		        detailItem.setActualValue(dateFormat.format(date));
		        masterObject.refresh();
			}
		});
		
		lblDate = new Label("Date");
		flexTable.setWidget(1, 0, lblDate);
		flexTable.setWidget(2, 0, datePicker);
		
		Label label = new Label("");
		flexTable.setWidget(3, 0, label);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(4, 0, horizontalPanel);
		horizontalPanel.setWidth("100%");
		
		Button button_1 = new Button("Clear");
		horizontalPanel.add(button_1);
		
		Button button_2 = new Button("Edit");
		horizontalPanel.add(button_2);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_MIDDLE);
	}
*/
	
	@Override
	public void setDetailItem(Category newDetailItem) {
		detailItem = newDetailItem;
		
	}

	@Override
	public void setMasterObject(Master newMasterObject) {
		masterObject = newMasterObject;
	}

}
