package com.paintracking.SurveyWidget.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class PainCategoryComposite extends Composite implements PainCategoryView{
	
	PainCategory painCategory;
	private Label lblCategoryName;
	public PainCategoryComposite(PainCategory newPainCategory) {
		painCategory = newPainCategory;
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		
		lblCategoryName = new Label("Category Name");
		flexTable.setWidget(0, 0, lblCategoryName);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void edit() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public PainCategory getPainCategory() {
		return painCategory;
	}
}
