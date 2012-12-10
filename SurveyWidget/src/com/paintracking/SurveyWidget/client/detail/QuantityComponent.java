/**
 * @author James Allen 
 * December 2012
 * Comp 523: Paintracker
 *  
 *  The QuantityComponent is a composite that allows a user to set a number
 *  in a textbox. The user can increment and decrement the quantity with
 *  Up and down buttons next to the field
 *
 */

package com.paintracking.SurveyWidget.client.detail;

import com.google.gwt.user.client.ui.Composite;
import com.paintracking.SurveyWidget.client.Detail;
import com.paintracking.SurveyWidget.client.Master;
import com.paintracking.SurveyWidget.client.categories.Category;
import com.paintracking.SurveyWidget.client.categories.QuantityCategory;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;

public class QuantityComponent extends Composite implements Detail{
	
	private QuantityCategory detailItem;
	private Master masterObject;
	private DoubleBox doubleBox;
	private Label lblTitle;
	private TextBox titleEditTextBox;
	private boolean isEditing; 
	private HorizontalPanel horizontalPanel;
	private Button editButton;
	private FlexTable flexTable;
	
	public QuantityComponent() {
		
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
		
		lblTitle = new Label("Category Title");
		lblTitle.setStyleName("gwt-Label-Title");
		flexTable.setWidget(0, 0, lblTitle);
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		flexTable.setWidget(1, 0, horizontalPanel_1);
		horizontalPanel_1.setWidth("100%");
		
		doubleBox = new DoubleBox();
		doubleBox.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
	            char charCode = event.getCharCode();
	            if(charCode == '\r'){//Enter
	            	detailItem.setQuantity(doubleBox.getValue());
					masterObject.refresh();
	            }
			}
		});
		
		horizontalPanel_1.add(doubleBox);
		doubleBox.setSize("163px", "49px");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		horizontalPanel_1.add(verticalPanel);
		
		Button btnNewButton = new Button("Inc\n");
		btnNewButton.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				detailItem.incQuantity(1);
				doubleBox.setValue(detailItem.getQuantity());
				masterObject.refresh();
			}
		});
		verticalPanel.add(btnNewButton);
		btnNewButton.setSize("50px", "25px");
		
		Button btnNewButton_1 = new Button("Dec");
		btnNewButton_1.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				detailItem.decQuantity(1);
				configureView();
				masterObject.refresh();
			}
		});
		
		verticalPanel.add(btnNewButton_1);
		btnNewButton_1.setSize("50px", "25px");
		
		horizontalPanel = new HorizontalPanel();
		flexTable.setWidget(2, 0, horizontalPanel);
		
		Button button_1 = new Button("Clear");
		button_1.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				detailItem.setQuantity(0.0);
				configureView();
				masterObject.refresh();
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
				//Toggle editing
				setEditing(!isEditing);
			}
		});
		editButton.setStyleName("gwt-Button-SurveyWidgetButton");
		horizontalPanel.add(editButton);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
	}

	@Override
	public void setDetailItem(Category newDetailItem) {
		detailItem = (QuantityCategory)newDetailItem;
		
		configureView();
	}

	@Override
	public void setMasterObject(Master newMasterObject) {
		this.masterObject = newMasterObject;
	}
	
	
	private void configureView() {
		if (isEditing()) {
			configureEditView();
		} else {
			configureNormalView();
		}
		doubleBox.setValue(detailItem.getQuantity());		
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
