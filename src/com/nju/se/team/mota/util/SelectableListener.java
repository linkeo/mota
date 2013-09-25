package com.nju.se.team.mota.util;

import java.util.Collection;

public interface SelectableListener<T>{

	/**
	 * 
	 * <b>Step 2 of procedure of item selection:<br>
	 * Tell list that a item is selected, it should be:<br></b>
	 * First mark selected item as parameter 1.<br>
	 * Then check whether this is a multiple selection <b>and</b> 
	 * list allows multiple selection.<br>
	 * &nbsp&nbsp&nbsp&nbsp
	 * If so, add this item to list of selected items,<br>
	 * &nbsp&nbsp&nbsp&nbsp
	 * else, clear the list and add this item.
	 * 
	 *
	 * @param item Item selected.
	 * @param multiple Is this action a multiple selection
	 */
	public void itemSelected(Selectable<T> item, boolean multiple);
	/**
	 * 
	 * <b>Step 2 of procedure of item unselection:<br>
	 * Tell list that a item is unselected, it should be:<br></b>
	 * First remove this item from list of selected item.<br>
	 * Then check whether this is a multiple selection <b>and</b> 
	 * list allows multiple selection.<br>
	 * &nbsp&nbsp&nbsp&nbsp
	 * If <b>not</b> so, clear the list and mark selected item as <b>null</b>,<br>
	 * &nbsp&nbsp&nbsp&nbsp
	 * else, mark selected item as the last item of list, if list is empty it marks <b>null</b>.
	 *
	 * @param item Item unselected.
	 * @param multiple Is this action a multiple selection
	 */
	public void itemUnselected(Selectable<T> item, boolean multiple);
	
	
	/**
	 * <b>Get selected item</b> ( that marked in (un)selection procedure )<br>
	 * If multiple items were selected, it returns last selected item,<br>
	 * if no selected items, it returns <b>null</b>.
	 * @return
	 */
	public Selectable<T> getSelectedItem();
	/**
	 * <b>Get content fo selected item</b> ( that marked in (un)selection procedure )<br>
	 * If multiple items were selected, it returns content of last selected item,<br>
	 * if no selected items, it returns <b>null</b>.
	 * @return
	 */
	public T getSelectedContent();
	
	
	/**
	 * <b>Get list of selected items</b><br>
	 * @return
	 */
	public Collection<Selectable<T>> getSelectedItems();
	/**
	 * <b>Get contents of selected items</b><br>
	 * @return
	 */
	public Collection<T> getSelectedContents();
	
	
	/**
	 * <b>select all selectable items of this list</b><br>
	 * Call select(mutiple:true) of all items in implementation list.
	 */
	public void selectAll();
	/**
	 * <b>unselect all selectable items of this list</b><br>
	 * Call unselect(mutiple:true) of all items in implementation list.
	 */
	public void unselectAll();
	
	
	/**
	 * <b>Tell whether implementation list allows multiple selection.</b><br>
	 * @return
	 */
	public boolean isMultipleSelectable();
	/**
	 * <b>Let implementation list (not) allow multiple selection</b><br>
	 * Disallowed in default.
	 * @return
	 */
	public void setMultipleSelectable(boolean multiple);
}
