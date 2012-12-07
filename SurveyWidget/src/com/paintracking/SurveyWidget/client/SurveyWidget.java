package com.paintracking.SurveyWidget.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.paintracking.SurveyWidget.client.categories.Category;
import com.paintracking.SurveyWidget.client.detail.BasicPainCategoryComposite;
import com.paintracking.SurveyWidget.client.detail.DateCategoryComposite;
import com.paintracking.SurveyWidget.client.detail.Detail;
import com.paintracking.SurveyWidget.client.detail.OptionsCategoryComponent;
import com.paintracking.SurveyWidget.client.detail.TextCategoryComposite;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SurveyWidget implements EntryPoint {
	private SimplePanel detailPanel;
	private Detail detailComposite;
	private CategoryList painCategoryComposite;

	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		rootPanel.add(horizontalPanel, 10, 10);
		horizontalPanel.setSize("415px", "279px");
		
		painCategoryComposite = new CategoryList();
		horizontalPanel.add(painCategoryComposite);
		painCategoryComposite.setSize("226px", "279px");
		
		detailPanel = new SimplePanel();
		horizontalPanel.add(detailPanel);
		detailPanel.setSize("226px\n", "280px");
		
		
		// Add a selection model to master that pulls up the pain category options in the detail view
		final SingleSelectionModel<Category> masterSelectionModel = new SingleSelectionModel<Category>();
		painCategoryComposite.getCategoryCellList().setSelectionModel(masterSelectionModel);
		masterSelectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						//Create the detail Composite
						Category selectedCategory = masterSelectionModel.getSelectedObject();
						
						//Select type of composite based on data type
						if(selectedCategory.getCategoryType().equals("options")){
							detailComposite = new OptionsCategoryComponent(); //TEST
						}else if(selectedCategory.getCategoryType().equals("text")){
							detailComposite = new TextCategoryComposite();
						}else if(selectedCategory.getCategoryType().equals("basic")){
							detailComposite = new BasicPainCategoryComposite(); //TEST
						}else if(selectedCategory.getCategoryType().equals("date")){
							detailComposite = new DateCategoryComposite();
						}
						
						//Hook up master and detail
						detailPanel.setWidget((Composite)detailComposite);
						painCategoryComposite.setDetailObject(detailComposite);
						detailComposite.setMasterObject(painCategoryComposite);
						detailComposite.setDetailItem(selectedCategory);
						
					}
				});
		
		
		
		

	}
}
