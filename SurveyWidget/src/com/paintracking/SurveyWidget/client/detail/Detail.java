package com.paintracking.SurveyWidget.client.detail;

import com.paintracking.SurveyWidget.client.JSONCategory;
import com.paintracking.SurveyWidget.client.Master;

public interface Detail {
	public void setDetailItem(JSONCategory newDetailItem);
	public void setMasterObject(Master newMasterObject);
}
