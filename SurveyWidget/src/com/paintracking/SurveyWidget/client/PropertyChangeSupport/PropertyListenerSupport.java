package com.paintracking.SurveyWidget.client.PropertyChangeSupport;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
//Taken from Prasun Dewan's Comp 401 class.
//@WebDocuments({"Lectures/MvcProperties.pptx", "Lectures/MvcProperties.pdf", "Videos/MvcProperties.avi"})
public interface PropertyListenerSupport {
    public void add(PropertyChangeListener l);
    public PropertyChangeListener get(int index); 
    public int size();
    public void notifyAllListeners(PropertyChangeEvent event);
    public void remove(PropertyChangeListener element) ;
    public void remove(int startIndex) ;
    public int indexOf(PropertyChangeListener element);
    public boolean member(PropertyChangeListener element);
}