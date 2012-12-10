/**
 * @author James Allen 
 * December 2012
 * Comp 523: Paintracker
 *  
 *  NewCategoryComponent is a composite that lets the user define
 *  the specifications for a new category. When the user hits done,
 *  the detail object adds a Category object to the Survey Cell List.
 * 
 */

package com.paintracking.SurveyWidget.client.detail;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Composite;
import com.paintracking.SurveyWidget.client.Detail;
import com.paintracking.SurveyWidget.client.Master;
import com.paintracking.SurveyWidget.client.CategoryCellList;
import com.paintracking.SurveyWidget.client.categories.Category;
import com.paintracking.SurveyWidget.client.categories.CategoryOption;
import com.paintracking.SurveyWidget.client.categories.DateCategory;
import com.paintracking.SurveyWidget.client.categories.OptionsCategory;
import com.paintracking.SurveyWidget.client.categories.QuantityCategory;
import com.paintracking.SurveyWidget.client.categories.TextCategory;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;

public class NewCategoryComponent extends Composite implements Detail {

	private Category detailItem;
	private Master masterObject;
	private ListBox listBox;
	private TextBox textBox;
	
	public NewCategoryComponent() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setWidth("300px");
		
		Label lblCategoryTitle = new Label("Category Title");
		flexTable.setWidget(0, 0, lblCategoryTitle);
		
		textBox = new TextBox();
		flexTable.setWidget(0, 1, textBox);
		textBox.setWidth("252px");
		
		Label lblCategoryType = new Label("Category Type");
		flexTable.setWidget(1, 0, lblCategoryType);
		lblCategoryType.setWidth("96px");
		
		listBox = new ListBox();
		listBox.addItem("Scale");
		listBox.addItem("Text");
		listBox.addItem("Date");
		listBox.addItem("Quantity");
		flexTable.setWidget(1, 1, listBox);
		listBox.setSize("100%", "102px");
		listBox.setVisibleItemCount(5);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		flexTable.setWidget(2, 1, horizontalPanel);
		horizontalPanel.setWidth("100%");
		
		Button btnCreate = new Button("Create");
		btnCreate.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				String type = listBox.getValue(listBox.getSelectedIndex());
				Category category;
				if(type.equals("Scale")){
					category = new OptionsCategory();
					ArrayList<CategoryOption> options = new ArrayList<CategoryOption>();
					options.add(new CategoryOption(1, ""));
					options.add(new CategoryOption(2, ""));
					options.add(new CategoryOption(3, ""));
					((OptionsCategory)category).setOptions(options);
				}else if(type.equals("Text")){
					category = new TextCategory();
				}else if(type.equals("Date")){
					category = new DateCategory();
				}else if(type.equals("Quantity")){
					category = new QuantityCategory();
				}else{
					category = new TextCategory();
				}
				
				category.setCategoryName(textBox.getText());
				
				((CategoryCellList)masterObject).getDataList().add(category);
				masterObject.refresh();
			}
		});
		horizontalPanel.add(btnCreate);
		
		Button btnCancel = new Button("Cancel");
		btnCancel.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				removeFromParent();
			}
		});
		horizontalPanel.add(btnCancel);
	}

	@Override
	public void setDetailItem(Category newDetailItem) {
		detailItem = newDetailItem;
	}

	@Override
	public void setMasterObject(Master newMasterObject) {
		masterObject = newMasterObject;
	}

}
