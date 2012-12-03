package com.paintracking.SurveyWidget.client.detail;

import com.paintracking.SurveyWidget.client.Master;
import com.paintracking.SurveyWidget.client.PainCategory;

public interface Detail {
	public void setDetailItem(PainCategory newDetailItem);
	public void setMasterObject(Master newMasterObject);
}
