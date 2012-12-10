/**
 * @author James Allen 
 * December 2012
 * Comp 523: Paintracker
 *  
 *  The interface for the Master part of a 
 *  master detail controller. This interface requires
 *  that a master object is connected to a detail object.
 * 
 */
package com.paintracking.SurveyWidget.client;


public interface Master {
	public Detail getDetailObject();
	public void setDetailObject(Detail detailObject);
	public void refresh();

}
