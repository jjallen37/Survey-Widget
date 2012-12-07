package com.paintracking.SurveyWidget.client.detail;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Composite;
import com.paintracking.SurveyWidget.client.Master;
import com.paintracking.SurveyWidget.client.categories.Category;

public class BasicPainCategoryComposite extends Composite implements Detail {
	private Label lblCategoryName;
	private Label lblActualValue;
	
	private Category detailItem;
	@SuppressWarnings("unused")
	private Master masterObject;

	public BasicPainCategoryComposite() {
		//Set view here
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("226px", "280px");
		
		lblCategoryName = new Label("Category Name");
		flexTable.setWidget(0, 0, lblCategoryName);
		
		lblActualValue = new Label("ActualValue");
		flexTable.setWidget(1, 0, lblActualValue);
		
	}
	
	private void configureView(){
		lblCategoryName.setText(detailItem.getCategoryName());
		lblActualValue.setText(detailItem.getActualValue());
	}

	@Override
	public void setDetailItem(Category newDetailItem) {
		detailItem = newDetailItem;

		//Configure view with new Data
		configureView();
		
//		//Refresh changes to master
//		masterObject.refresh();
	}


	@Override
	public void setMasterObject(Master newMasterObject){
		masterObject = newMasterObject;
	}

}
