/*
 * Sources
 * http://stackoverflow.com/questions/3125095/converting-from-jsarrayjavascriptobject-to-listjsonobject-in-gwt
 * http://www.krazer.com/?p=67
 */

package com.paintracking.SurveyWidget.client.JsList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.gwt.core.client.JavaScriptObject;

public class JsList<T extends JavaScriptObject> implements List<T>
{
	private final JsArrayExtend<T> jsArray;
	
	public JsList(final JsArrayExtend<T> jsArray)
	{	this.jsArray=jsArray;}
	
	@Override
	public int size() {
		return jsArray.length();
	}

	@Override
	public boolean isEmpty() {
		return size()==0;
	}

	@Override
	public boolean contains(Object o) {
		if(o instanceof JavaScriptObject)
		{
			return jsArray.indexOf((JavaScriptObject)o) != -1;
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return new JsListIterator<T>(this);
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean add(T e) {
		jsArray.push(e);
		return true;
	}

	@Override
	public boolean remove(Object o) {
		if(o instanceof JavaScriptObject)
		{
			int index=jsArray.indexOf((JavaScriptObject)o);
			if(index != -1)
			{
				jsArray.splice(index, 1);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		Iterator<?> iter=c.iterator();
		
		while(iter.hasNext())
		{
			jsArray.push((T)iter.next());
		}
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		jsArray.setLength(0);		
	}

	@Override
	public T get(int index) {
		return jsArray.get(index);
	}

	@Override
	public T set(int index, T element) {
		T oldElement=jsArray.get(index);
		jsArray.set(index,  oldElement);
		return element;
	}

	@Override
	public void add(int index, T element) {
		jsArray.add(index, element);
	}

	@Override
	public T remove(int index) {
		JsArrayExtend<T> array=jsArray.splice(index, 1);
		if(array.length() > 0)
			return array.get(0);
		return null;
	}

	@Override
	public int indexOf(Object o) {
		if(o instanceof JavaScriptObject)
		{	return jsArray.indexOf((JavaScriptObject)o);}
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		if(o instanceof JavaScriptObject)
		{	return jsArray.lastIndexOf((JavaScriptObject)o);}
		return 0;
	}

	@Override
	public ListIterator<T> listIterator() {
		return new JsListIterator<T>(this);
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return new JsListIterator<T>(this, index);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return new JsList<T>(jsArray.splice(fromIndex, toIndex));
	}

}