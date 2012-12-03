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
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;

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

		
		flexTable.setWidget(1, 0, textArea);
		textArea.setSize("220px", "200px");
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		flexTable.setWidget(2, 0, horizontalPanel);
		
		Button btnSaveText = new Button("Save Text");
		btnSaveText.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				saveText();
			}
		});
		
		horizontalPanel.add(btnSaveText);
		
		Button btnClear = new Button("Clear");
		btnClear.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				//Clear the text area
				textArea.setText("");
				
				//Update Master
				saveText();
			}
		});
		horizontalPanel.add(btnClear);
		
		Button btnEdit = new Button("Edit");
		horizontalPanel.add(btnEdit);
		
	}
	
	private void saveText(){
		detailItem.setActualValue(textArea.getText());
		masterObject.refresh();
	}
	
	private void configureView(){
		lblCategoryTitle.setText(detailItem.getCategoryName());
		textArea.setText(detailItem.getActualValue());
	}
	
	////Detail Interface methods
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
