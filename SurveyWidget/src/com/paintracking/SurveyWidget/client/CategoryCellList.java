/**
 * @author James Allen 
 * December 2012
 * Comp 523: Paintracker
 *  
 *  CategoryCell List is a composite that displays the user Survey.
 *  It contains a cell table, and buttons controlling that cell table.
 *  This implements the master interface, meaning that it has a relationship
 *  with all objects that implement the cooresponding detail interface, such
 *  as the Category composites.
 * 
 */

package com.paintracking.SurveyWidget.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.paintracking.SurveyWidget.client.categories.Category;
import com.paintracking.SurveyWidget.client.categories.JSONCategory;
import com.paintracking.SurveyWidget.client.categories.CategoryOption;
import com.paintracking.SurveyWidget.client.categories.DateCategory;
import com.paintracking.SurveyWidget.client.categories.OptionsCategory;
import com.paintracking.SurveyWidget.client.categories.QuantityCategory;
import com.paintracking.SurveyWidget.client.categories.TextCategory;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;

public class CategoryCellList extends Composite implements Master {
	private List<Category> dataList = new ArrayList<Category>();
	private CellTable<Category> cellTable;
	private ListDataProvider<Category> dataProvider;
	private Detail detailObject;
	private ScrollPanel scrollPanel;
	private Button btnSubmit;
	private Button btnClear;
	private Button btnAdd;

	public CategoryCellList() {

		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("300px", "385px");

		Label label = new Label("Categories");
		flexTable.setWidget(0, 0, label);

		// Load the dummy data
		loadData();
		// Create a data provider for the cell table
		dataProvider = new ListDataProvider<Category>();
		dataProvider.setList(getDataList());

		scrollPanel = new ScrollPanel();
		flexTable.setWidget(1, 0, scrollPanel);
		scrollPanel.setSize("100%", "300px");

		setCellTable(new CellTable<Category>());
		cellTable.setSize("100%", "100%");
		// Connect the table to the data provider.
		dataProvider.addDataDisplay(getCellTable());

		TextColumn<Category> categoryTextColumn = new TextColumn<Category>() {
			@Override
			public String getValue(Category object) {
				return object.getCategoryName();
			}
		};

		getCellTable().addColumn(categoryTextColumn, "Category");
		cellTable.setColumnWidth(categoryTextColumn, "100px");

		TextColumn<Category> valueTextColumn = new TextColumn<Category>() {
			@Override
			public String getValue(Category object) {
				return object.getActualValue();
			}
		};
		getCellTable().addColumn(valueTextColumn, "Value");
		flexTable.getCellFormatter().setVerticalAlignment(0, 0,
				HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(2, 0, horizontalPanel);
		horizontalPanel.setWidth("100%");
		
		btnSubmit = new Button("Submit");
		horizontalPanel.add(btnSubmit);
		
		btnClear = new Button("Clear");
		btnClear.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				for(Category c : dataList){
					c.setActualValue("");
					if (c instanceof OptionsCategory){
						((OptionsCategory)c).setSelectedOption(null);
					}else if(c instanceof QuantityCategory){
						((QuantityCategory)c).setQuantity(0.0);
					}
				}
				refresh();
			}
		});
		horizontalPanel.add(btnClear);
		
		setBtnAdd(new Button("Add"));
		horizontalPanel.add(getBtnAdd());
	}

	@Override
	public Detail getDetailObject() {
		return detailObject;
	}

	@Override
	public void setDetailObject(Detail detailObject) {
		this.detailObject = detailObject;
	}

	@Override
	public void refresh() {
		dataProvider.setList(getDataList());
		cellTable.redraw();
	}

	public CellTable<Category> getCellTable() {
		return cellTable;
	}

	public void setCellTable(CellTable<Category> cellTable) {
		this.cellTable = cellTable;
		scrollPanel.setWidget(cellTable);
	}

	// /Dummy data
	public void loadData() {
		// Dummy data printed
		String dummyJSON = "[{\"surveyId\":\"64\",\"categoryId\":\"a\",\"userid\":\"aasdf\",\"fields\":0,\"categoryType\":\"data\",\"categoryName\":\"Well-Being\",\"options\":{\"0\":\"hopeless\",\"1\":\"drained\",\"2\":\"irritable\",\"3\":\"discontent\",\"4\":\"average\",\"5\":\"decent\",\"6\":\"satisfied\",\"7\":\"pleasant\",\"8\":\"content\",\"9\":\"thriving\",\"10\":\"amazing\"}},{\"surveyID\":\"63\",\"categoryID\":\"3\",\"userid\":\"11507\",\"fields\":0,\"categoryType\":\"data\",\"categoryName\":\"Mood\",\"options\":{\"0\":\"hopeless\",\"1\":\"drained\",\"2\":\"irritable\",\"3\":\"discontent\",\"4\":\"average\",\"5\":\"decent\",\"6\":\"satisfied\",\"7\":\"pleasant\",\"8\":\"content\",\"9\":\"thriving\",\"10\":\"amazing\",\"11\":\"bitter\",\"12\":\"awful\",\"13\":\"exasperated\",\"14\":\"agitated\",\"15\":\"anxious\",\"16\":\"adequate\",\"17\":\"agreeable\",\"18\":\"happy\",\"19\":\"very good\",\"20\":\"elated\",\"21\":\"extremely happy\"}},{\"surveyID\":\"62\",\"categoryID\":\"2\",\"userid\":\"11507\",\"fields\":0,\"categoryType\":\"data\",\"categoryName\":\"Fatigue\",\"options\":{\"0\":\"hopeless\",\"1\":\"drained\",\"2\":\"irritable\",\"3\":\"discontent\",\"4\":\"average\",\"5\":\"decent\",\"6\":\"satisfied\",\"7\":\"pleasant\",\"8\":\"content\",\"9\":\"thriving\",\"10\":\"amazing\",\"11\":\"bitter\",\"12\":\"awful\",\"13\":\"exasperated\",\"14\":\"agitated\",\"15\":\"anxious\",\"16\":\"adequate\",\"17\":\"agreeable\",\"18\":\"happy\",\"19\":\"very good\",\"20\":\"elated\",\"21\":\"extremely happy\",\"22\":\"refreshed\",\"23\":\"present\",\"24\":\"slow-minded\",\"25\":\"low energy\",\"26\":\"sleepy\",\"27\":\"foggy\",\"28\":\"tired\",\"29\":\"lethargic\",\"30\":\"exhuasted\",\"31\":\"depleted\",\"32\":\"all-consuming\"}},{\"surveyID\":\"60\",\"categoryID\":\"0\",\"userid\":\"11507\",\"fields\":\"0\",\"categoryType\":\"\",\"categoryName\":\"Note\",\"options\":{\"0\":\"hopeless\",\"1\":\"drained\",\"2\":\"irritable\",\"3\":\"discontent\",\"4\":\"average\",\"5\":\"decent\",\"6\":\"satisfied\",\"7\":\"pleasant\",\"8\":\"content\",\"9\":\"thriving\",\"10\":\"amazing\",\"11\":\"bitter\",\"12\":\"awful\",\"13\":\"exasperated\",\"14\":\"agitated\",\"15\":\"anxious\",\"16\":\"adequate\",\"17\":\"agreeable\",\"18\":\"happy\",\"19\":\"very good\",\"20\":\"elated\",\"21\":\"extremely happy\",\"22\":\"refreshed\",\"23\":\"present\",\"24\":\"slow-minded\",\"25\":\"low energy\",\"26\":\"sleepy\",\"27\":\"foggy\",\"28\":\"tired\",\"29\":\"lethargic\",\"30\":\"exhuasted\",\"31\":\"depleted\",\"32\":\"all-consuming\"}},{\"surveyID\":\"61\",\"categoryID\":\"1\",\"userid\":\"11507\",\"fields\":0,\"categoryType\":\"data\",\"categoryName\":\"Pain\",\"options\":{\"0\":\"hopeless\",\"1\":\"drained\",\"2\":\"irritable\",\"3\":\"discontent\",\"4\":\"average\",\"5\":\"decent\",\"6\":\"satisfied\",\"7\":\"pleasant\",\"8\":\"content\",\"9\":\"thriving\",\"10\":\"amazing\",\"11\":\"bitter\",\"12\":\"awful\",\"13\":\"exasperated\",\"14\":\"agitated\",\"15\":\"anxious\",\"16\":\"adequate\",\"17\":\"agreeable\",\"18\":\"happy\",\"19\":\"very good\",\"20\":\"elated\",\"21\":\"extremely happy\",\"22\":\"refreshed\",\"23\":\"present\",\"24\":\"slow-minded\",\"25\":\"low energy\",\"26\":\"sleepy\",\"27\":\"foggy\",\"28\":\"tired\",\"29\":\"lethargic\",\"30\":\"exhuasted\",\"31\":\"depleted\",\"32\":\"all-consuming\",\"33\":\"no pain\",\"34\":\"minor\",\"35\":\"mild\",\"36\":\"annoying\",\"37\":\"irritating\",\"38\":\"significant\",\"39\":\"challenging\",\"40\":\"high\",\"41\":\"severe\",\"42\":\"disabling\",\"43\":\"highest\"}}]";

//
//		try {
//			Object obj = JSONParser.parseStrict(dummyJSON);
//			JSONArray array = (JSONArray)obj;
//			for (int i = 0; i < array.size(); i++) {
//				System.out.println(array.get(i));
//			}
//		} catch (Exception e) {
//			System.out.println("position: " + e);
//		}

//		 Parse jsonData into a JsArray
		JsArray<JSONCategory> jsCategoryArray = asArrayOfCategories(dummyJSON);
//		 Convert JsArray into an ArrayList to create a mutable copy with a
//		 list interface for editing.
		for (int i = 0; i < jsCategoryArray.length(); i++) {
			System.out.println(jsCategoryArray.get(i).asString());
		}

		// Dummy data before I added JSON data
		setDataList(new ArrayList<Category>());

		OptionsCategory a = new OptionsCategory();
		a.setCategoryName("Pain");
		a.setCategoryType("options");
		a.setActualValue("");
		ArrayList<CategoryOption> optionsA = new ArrayList<CategoryOption>();
		optionsA.add(new CategoryOption(1, "None"));
		optionsA.add(new CategoryOption(2, "Some"));
		optionsA.add(new CategoryOption(3, "A lot"));
		a.setOptions(optionsA);

		TextCategory b = new TextCategory();
		b.setCategoryName("Mood");
		b.setCategoryType("text");
		b.setActualValue("");

		// Date
		DateCategory c = new DateCategory();
		c.setCategoryName("Date Submitted");
		c.setCategoryType("date");
		c.setActualValue("");

		QuantityCategory d = new QuantityCategory();
		d.setCategoryName("Pushups");
		d.setCategoryType("quantity");
		d.setActualValue("");

		getDataList().add(a);
		getDataList().add(b);
		getDataList().add(c);
		getDataList().add(d);

	}

	/**
	 * Convert the string of JSON into JavaScript object.
	 */
	private final native JsArray<JSONCategory> asArrayOfCategories(String json) /*-{
																				return eval('('+json+')');
																				}-*/;

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
	} // processResponse

	public Button getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(Button btnAdd) {
		this.btnAdd = btnAdd;
	}

	public List<Category> getDataList() {
		return dataList;
	}

	public void setDataList(List<Category> dataList) {
		this.dataList = dataList;
	}

}
