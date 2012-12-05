package com.paintracking.SurveyWidget.client.detail;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Button;
import com.paintracking.SurveyWidget.client.Master;
import com.paintracking.SurveyWidget.client.PainCategory;

public class CategoryComponent extends Composite implements Detail{

	private PainCategory detailItem;
	private Master masterObject;
	
	public CategoryComponent() {
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("226px", "280px");
		
		Label label = new Label("Category Title");
		flexTable.setWidget(0, 0, label);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(2, 0, horizontalPanel);
		horizontalPanel.setWidth("100%");
		
		Button button_1 = new Button("Edit");
		horizontalPanel.add(button_1);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}

	@Override
	public void setDetailItem(PainCategory newDetailItem) {
		detailItem = newDetailItem;
	}

	@Override
	public void setMasterObject(Master newMasterObject) {
		masterObject = newMasterObject;
	}

	
}
