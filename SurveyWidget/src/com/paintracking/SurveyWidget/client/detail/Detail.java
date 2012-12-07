package com.paintracking.SurveyWidget.client.detail;

import com.paintracking.SurveyWidget.client.Master;
import com.paintracking.SurveyWidget.client.categories.Category;

public interface Detail {
	public void setDetailItem(Category newDetailItem);
	public void setMasterObject(Master newMasterObject);
}
