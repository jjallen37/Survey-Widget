package com.paintracking.SurveyWidget.client.JsList;

import java.util.ListIterator;
import com.google.gwt.core.client.JavaScriptObject;

public class JsListIterator<T extends JavaScriptObject> implements ListIterator<T>
{
	private final JsList<T> list;
	private int index=0;
	
	public JsListIterator(JsList<T> list)
	{	this.list=list;}
	
	public JsListIterator(JsList<T> list, int index)
	{
		this.list=list;
		this.index=index;
	}
	
	@Override
	public boolean hasNext() 
	{
		if(index < list.size())
			return true;
		return false;
	}

	@Override
	public T next()
	{
		if(index < list.size()-1)
		{
			++index;
			return list.get(index);
		}
		return null;
	};

	@Override
	public void remove()
	{
		if(index < list.size())
			list.remove(index);
	};

	@Override
	public void add(T arg0)
	{	list.add(index, arg0);}

	@Override
	public boolean hasPrevious()
	{
		if(index>0)
			return true;
		return false;
	}

	@Override
	public int nextIndex()
	{	return index+1;}

	@Override
	public T previous()
	{
		if(index>1)
		{
			--index;
			return list.get(index);
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int previousIndex()
	{
		if(index>0)
			return index-1;
		return 0;
	}

	@Override
	public void set(T arg0)
	{
		list.set(index, arg0);		
	}
}