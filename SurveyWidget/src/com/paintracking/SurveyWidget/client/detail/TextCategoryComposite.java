/**
 * @author James Allen 
 * December 2012
 * Comp 523: Paintracker
 *  
 *  The TextCategoryComposite is an object that allows the user to 
 *  save text. It has a title, a text input box, and edit and clear buttons
 *  similar to the other CategoryComposites. 
 *
 */

package com.paintracking.SurveyWidget.client.detail;

import com.google.gwt.user.client.ui.Composite;
import com.paintracking.SurveyWidget.client.Detail;
import com.paintracking.SurveyWidget.client.Master;
import com.paintracking.SurveyWidget.client.categories.Category;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;

public class TextCategoryComposite extends Composite implements Detail {

	private Category detailItem;
	private Master masterObject;
	private TextArea textArea;
	private Label lblCategoryTitle;
	private boolean isEditing;
	private HorizontalPanel horizontalPanel;
	private FlexTable flexTable;
	private TextBox titleEditTextBox;
	private Button editButton;
	
	public TextCategoryComposite() {
		
		flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("226px", "280px");
		
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
		
		lblCategoryTitle = new Label("Category Title");
		lblCategoryTitle.setStyleName("gwt-Label-Title");
		flexTable.setWidget(0, 0, lblCategoryTitle);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		textArea = new TextArea();

		
		flexTable.setWidget(1, 0, textArea);
		textArea.setSize("220px", "200px");
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		
		horizontalPanel = new HorizontalPanel();
		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(2, 0, horizontalPanel);
		horizontalPanel.setWidth("100%");
		
		Button btnSaveText = new Button("Save Text");
		btnSaveText.setStyleName("gwt-Button-SurveyWidgetButton");
		btnSaveText.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				saveText();
			}
		});
		
		horizontalPanel.add(btnSaveText);
		
		Button btnClear = new Button("Clear");
		btnClear.setStyleName("gwt-Button-SurveyWidgetButton");
		btnClear.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				//Clear the text area
				textArea.setText("");
				
				//Update Master
				saveText();
			}
		});
		horizontalPanel.add(btnClear);
		
		editButton = new Button("Edit");
		editButton.setStyleName("gwt-Button-SurveyWidgetButton");
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
		horizontalPanel.add(editButton);
		
	}
	
	private void saveText(){
		detailItem.setActualValue(textArea.getText());
		masterObject.refresh();
	}
	
	////Detail Interface methods
	@Override
	public void setDetailItem(Category newDetailItem) {
		detailItem = newDetailItem;

		//Configure view with new Data
		configureView();	
	}


	@Override
	public void setMasterObject(Master newMasterObject){
		masterObject = newMasterObject;
	}
	
	private void configureView() {
		if (isEditing()) {
			configureEditView();
		} else {
			configureNormalView();
		}
		
		lblCategoryTitle.setText(detailItem.getCategoryName());
		textArea.setText(detailItem.getActualValue());
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
		lblCategoryTitle.setText(detailItem.getCategoryName());
		flexTable.setWidget(0, 0, lblCategoryTitle);
		
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
