package com.nju.se.team.mota.util;

public interface Selectable<T> {
	
	public void select(boolean multiple);
	public void unselect(boolean multiple);
	
	public T content();
	
	public boolean isSelected();
	
	public boolean isSelectable();
	public void setSelectable(boolean selectable);
	
	public void addSelectableListener(SelectableListener<T> l);
	public void removeSelectableListener(SelectableListener<T> l);
}
