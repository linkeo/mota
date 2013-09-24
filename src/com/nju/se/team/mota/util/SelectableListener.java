package com.nju.se.team.mota.util;

import java.util.Collection;

public interface SelectableListener<T>{
	
	public void itemSelected(Selectable<T> item, boolean multiple);
	public void itemUnselected(Selectable<T> item, boolean multiple);
	
	public Selectable<T> getSelectedItem();
	public T getSelectedContent();
	
	public Collection<Selectable<T>> getSelectedItems();
	public Collection<T> getSelectedContents();
	
	public void selectAll();
	public void unselectAll();
	
	public boolean isMultipleSelectable();
	public void setMultipleSelectable(boolean multiple);
}
