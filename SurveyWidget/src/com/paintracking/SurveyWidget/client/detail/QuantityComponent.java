package com.paintracking.SurveyWidget.client.detail;

import com.google.gwt.user.client.ui.Composite;
import com.paintracking.SurveyWidget.client.Master;
import com.paintracking.SurveyWidget.client.categories.Category;
import com.paintracking.SurveyWidget.client.categories.QuantityCategory;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.cellview.client.DataGrid;
import com.paintracking.SurveyWidget.client.categories.CategoryOption;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;

public class QuantityComponent extends Composite implements Detail{
	
	private QuantityCategory detailItem;
	private Master masterObject;
	private DoubleBox doubleBox;
	private Label lblTitle;
	
	public QuantityComponent() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("226px", "280px");
		
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
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		flexTable.setWidget(2, 0, horizontalPanel);
		
		Button button_1 = new Button("Clear");
		button_1.setStyleName("gwt-Button-SurveyWidgetButton");
		horizontalPanel.add(button_1);
		
		Button button_2 = new Button("Edit");
		button_2.setStyleName("gwt-Button-SurveyWidgetButton");
		horizontalPanel.add(button_2);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
	}
	
	private void configureView(){
		doubleBox.setValue(detailItem.getQuantity());
		lblTitle.setText(detailItem.getCategoryName());
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
}
