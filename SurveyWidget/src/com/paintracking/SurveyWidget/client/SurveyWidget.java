/**
 * @author James Allen 
 * December 2012
 * Comp 523: Paintracker
 *  
 *  The main class in the SurveyWidget GWT Product because it implments EntryPoint.
 *  This class is the controller between the Master CategoryCellList and the 
 *  Detail Components: OptionsCategoryComponent, DateCategoryComposite, and TextCategoryComposite to name a few.
 * 
 */

package com.paintracking.SurveyWidget.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.paintracking.SurveyWidget.client.categories.Category;
import com.paintracking.SurveyWidget.client.detail.BasicPainCategoryComposite;
import com.paintracking.SurveyWidget.client.detail.DateCategoryComposite;
import com.paintracking.SurveyWidget.client.detail.NewCategoryComponent;
import com.paintracking.SurveyWidget.client.detail.OptionsCategoryComponent;
import com.paintracking.SurveyWidget.client.detail.QuantityComponent;
import com.paintracking.SurveyWidget.client.detail.TextCategoryComposite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SurveyWidget implements EntryPoint {
	private SimplePanel detailPanel;
	private Detail detailComposite;
	private CategoryCellList painCategoryComposite;

	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();

		// Add a selection model to handle user selection.
		final SingleSelectionModel<Category> selectionModel = new SingleSelectionModel<Category>();

		FlexTable flexTable = new FlexTable();
		rootPanel.add(flexTable, 10, 10);
		flexTable.setSize("489px", "329px");

		painCategoryComposite = new CategoryCellList();
		painCategoryComposite.getBtnAdd().addMouseUpHandler(
				new MouseUpHandler() {
					public void onMouseUp(MouseUpEvent event) {
						selectionModel.setSelected(
								selectionModel.getSelectedObject(), false);
						NewCategoryComponent newCategoryComponent = new NewCategoryComponent();
						// Hook up master and detail
						detailPanel.setWidget((Composite) newCategoryComponent);
						painCategoryComposite.setDetailObject(detailComposite);
						newCategoryComponent
								.setMasterObject(painCategoryComposite);

					}
				});
		painCategoryComposite.getCellTable().setHeight("230px");
		flexTable.setWidget(0, 0, painCategoryComposite);
		painCategoryComposite.setSize("253px", "300px");
		painCategoryComposite.getCellTable().setSelectionModel(selectionModel);
		// painCategoryComposite.setSize("226px", "279px");

		detailPanel = new SimplePanel();
		flexTable.setWidget(0, 1, detailPanel);
		detailPanel.setSize("226px\n", "280px");
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						Category selectedCategory = selectionModel
								.getSelectedObject();
						if (selectedCategory != null) {
							// Select type of composite based on data type
							if (selectedCategory.getCategoryType().equals(
									"options")) {
								detailComposite = new OptionsCategoryComponent(); // TEST
							} else if (selectedCategory.getCategoryType()
									.equals("text")) {
								detailComposite = new TextCategoryComposite();
							} else if (selectedCategory.getCategoryType()
									.equals("basic")) {
								detailComposite = new BasicPainCategoryComposite(); // TEST
							} else if (selectedCategory.getCategoryType()
									.equals("date")) {
								detailComposite = new DateCategoryComposite();
							} else if (selectedCategory.getCategoryType()
									.equals("quantity")) {
								detailComposite = new QuantityComponent();
							}

							// Hook up master and detail
							detailPanel.setWidget((Composite) detailComposite);
							painCategoryComposite
									.setDetailObject(detailComposite);
							detailComposite
									.setMasterObject(painCategoryComposite);
							detailComposite.setDetailItem(selectedCategory);
						}
					}
				});

		// Make the
		// doGet(GWT.getModuleBaseURL()+"testloadcategories.php?key=value");
		// doGet("http://wwwx.cs.unc.edu/Courses/comp523-f12/paintracker/SurveyWidget/testloadcategories.php?key=value");
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
				public void onResponseReceived(Request request,
						Response response) {
					if (200 == response.getStatusCode()) {
						// call the method in main class to process the response
						processResponse(response.getText());
					} else {
						processResponse("ERROR. Errorcode: "
								+ response.getStatusCode());
					}
				}

			});

		} catch (RequestException e) {
			processResponse("ERROR. No connection to server");
		}
	}

	public void processResponse(String responseString) {
		Window.alert(responseString);
		// System.out.println(responseString);
	} // processResponse
}
