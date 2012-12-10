package com.paintracking.SurveyWidget.client;


import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.paintracking.SurveyWidget.client.categories.Category;
import com.paintracking.SurveyWidget.client.categories.CategoryOption;
import com.paintracking.SurveyWidget.client.detail.BasicPainCategoryComposite;
import com.paintracking.SurveyWidget.client.detail.DateCategoryComposite;
import com.paintracking.SurveyWidget.client.detail.Detail;
import com.paintracking.SurveyWidget.client.detail.OptionsCategoryComponent;
import com.paintracking.SurveyWidget.client.detail.QuantityComponent;
import com.paintracking.SurveyWidget.client.detail.TextCategoryComposite;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SurveyWidget implements EntryPoint {
	private SimplePanel detailPanel;
	private Detail detailComposite;
	private CategoryCellList painCategoryComposite;

	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		rootPanel.add(horizontalPanel, 10, 10);
		horizontalPanel.setSize("415px", "279px");
		
		painCategoryComposite = new CategoryCellList();
		horizontalPanel.add(painCategoryComposite);
//		painCategoryComposite.setSize("226px", "279px");
		
		detailPanel = new SimplePanel();
		horizontalPanel.add(detailPanel);
		detailPanel.setSize("226px\n", "280px");
		
	    // Add a selection model to handle user selection.
	    final SingleSelectionModel<Category> selectionModel = new SingleSelectionModel<Category>();
	    painCategoryComposite.getCellTable().setSelectionModel(selectionModel);
	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	      public void onSelectionChange(SelectionChangeEvent event) {
	    	Category selectedCategory = selectionModel.getSelectedObject();
	        if (selectedCategory != null) {
				//Select type of composite based on data type
				if(selectedCategory.getCategoryType().equals("options")){
					detailComposite = new OptionsCategoryComponent(); //TEST
				}else if(selectedCategory.getCategoryType().equals("text")){
					detailComposite = new TextCategoryComposite();
				}else if(selectedCategory.getCategoryType().equals("basic")){
					detailComposite = new BasicPainCategoryComposite(); //TEST
				}else if(selectedCategory.getCategoryType().equals("date")){
					detailComposite = new DateCategoryComposite();
				}else if(selectedCategory.getCategoryType().equals("quantity")){
					detailComposite = new QuantityComponent();
				}
				
				//Hook up master and detail
				detailPanel.setWidget((Composite)detailComposite);
				painCategoryComposite.setDetailObject(detailComposite);
				detailComposite.setMasterObject(painCategoryComposite);
				detailComposite.setDetailItem(selectedCategory);
	        }
	      }
	    });
	    
//	    doGet("http://wwwx.cs.unc.edu/Courses/comp523-f12/paintracker/testloadcategories.php");
	}
	
	public void doGet(String myurl) {
        // make sure spaces and special characters have the correct encoding
        myurl = URL.encode(myurl);

        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, myurl);

        try {
          Request request = builder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable exception) {
                processResponse("ERROR. Could not connect to server.");
            }

			@Override
            public void onResponseReceived(Request request, Response response) {
              if (200 == response.getStatusCode()) {
                  // call the method in main class to process the response
                  processResponse(response.getText());
              } else {
                  processResponse("ERROR. Errorcode: " + response.getStatusCode());
              }
            }


          });

        } catch (RequestException e) {
                processResponse("ERROR. No connection to server");
        }
	}
	
	public void processResponse(String responseString) {
        System.out.println(responseString);
	} // processResponse

}
