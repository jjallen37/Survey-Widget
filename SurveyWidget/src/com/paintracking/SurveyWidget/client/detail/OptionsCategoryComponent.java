package com.paintracking.SurveyWidget.client.detail;

import com.google.gwt.user.client.ui.Composite;
import com.paintracking.SurveyWidget.client.Master;
import com.paintracking.SurveyWidget.client.PainCategory;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ScrollPanel;

public class OptionsCategoryComponent extends Composite implements Detail{

	
	public static class OptionsCategoryOption{
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
	
	public OptionsCategoryComponent() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("226px", "280px");
		
		Label label = new Label("Category Title");
		flexTable.setWidget(0, 0, label);
		
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
		
	}
	
	////Detail Interface methods
	@Override
	public void setDetailItem(PainCategory newDetailItem) {
		detailItem = newDetailItem;

		//Configure view with new Data
//		configureView();	
	}


	@Override
	public void setMasterObject(Master newMasterObject){
		masterObject = newMasterObject;
	}
}
