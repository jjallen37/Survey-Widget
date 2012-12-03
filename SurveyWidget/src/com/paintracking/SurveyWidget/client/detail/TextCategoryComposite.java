package com.paintracking.SurveyWidget.client.detail;

import com.google.gwt.user.client.ui.Composite;
import com.paintracking.SurveyWidget.client.JSONCategory;
import com.paintracking.SurveyWidget.client.Master;
import com.paintracking.SurveyWidget.client.PainCategory;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ChangeEvent;

public class TextCategoryComposite extends Composite implements Detail {

	private PainCategory detailItem;
	private Master masterObject;
	private TextArea textArea;
	private Label lblCategoryTitle;
	
	public TextCategoryComposite() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("226px", "280px");
		
		lblCategoryTitle = new Label("Category Title");
		flexTable.setWidget(0, 0, lblCategoryTitle);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		textArea = new TextArea();
		textArea.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				//TODO set data values
				System.out.println(textArea.getText());
//				masterObject.refresh();
			}
		});
		
		flexTable.setWidget(1, 0, textArea);
		textArea.setSize("220px", "200px");
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		flexTable.setWidget(2, 0, horizontalPanel);
		
		Button btnEdit = new Button("Edit");
		horizontalPanel.add(btnEdit);
		
		Button btnClear = new Button("Clear");
		horizontalPanel.add(btnClear);
		
	}
	
	private void configureView(){
		lblCategoryTitle.setText(detailItem.getCategoryName());
		textArea.setText(detailItem.getActualValue());
	}
	
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
