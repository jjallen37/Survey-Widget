package com.paintracking.SurveyWidget.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.paintracking.SurveyWidget.client.detail.BasicPainCategoryComposite;
import com.paintracking.SurveyWidget.client.detail.Detail;
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
		final SingleSelectionModel<JSONCategory> masterSelectionModel = new SingleSelectionModel<JSONCategory>();
		painCategoryComposite.getCategoryCellList().setSelectionModel(masterSelectionModel);
		masterSelectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						//Create the detail Composite
						//TODO select type of composite based on type
						detailComposite = new TextCategoryComposite();
//						detailComposite = new BasicPainCategoryComposite(); //TEST
						
						//Hook up master and detail
						detailPanel.setWidget((Composite)detailComposite);
						painCategoryComposite.setDetailObject(detailComposite);
						detailComposite.setDetailItem(masterSelectionModel.getSelectedObject());						
					}
				});
		
		
		
		

	}
}