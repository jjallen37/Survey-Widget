package com.paintracking.SurveyWidget.client;

import com.paintracking.SurveyWidget.client.detail.Detail;

public interface Master {
	public Detail getDetailObject();
	public void setDetailObject(Detail detailObject);
	public void refresh();

}
