package com.nju.se.team.mota.util;

public interface Selectable<T> {
	/**
	 * <b>Step 1 of procedure of item selection:<br>
	 * Tell item that it is selected, it should be:<br></b>
	 * First mark selected state.<br>
	 * Then change visual style to selected/active style.<br>
	 * Last tell all listener that it's selected.
	 * 
	 * @param multiple
	 */
	public void select(boolean multiple);
	/**
	 * <b>Step 1 of procedure of item unselection:<br>
	 * Tell item that it is unselected, it should be:<br></b>
	 * First mark unselected state.<br>
	 * Then change visual style to unselected/normal style.<br>
	 * Last tell all listener that it's unselected.
	 * 
	 * @param multiple
	 */
	public void unselect(boolean multiple);
	
	/**
	 * <b>Get the content of selected item.</b>
	 * @return
	 */
	public T content();
	
	/**
	 * <b>Tell whether the item is selected.</b>
	 * @return
	 */
	public boolean isSelected();
	

	/**
	 * <b>Tell whether the item is selectable.</b><br>
	 * It depends on that content is not empty and item is set to selectable.<br>
	 * <i>( content != null && selectable )</i>
	 * @return
	 */
	public boolean isSelectable();
	/**
	 * <b>Set item to selectable or unselectable.</b><br>
	 * PS: it not means that what isSelectable() returns is determined.
	 * @param selectable
	 */
	public void setSelectable(boolean selectable);
	
	public void addSelectableListener(SelectableListener<T> l);
	public void removeSelectableListener(SelectableListener<T> l);
}
