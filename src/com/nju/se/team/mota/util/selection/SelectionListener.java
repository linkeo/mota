package com.nju.se.team.mota.util.selection;

public interface SelectionListener<T> {

	/**
	 * 
	 * <b>Step 3 of procedure of item selection:<br>
	 * Tell listener that a item is selected<br></b>
	 *
	 * @param item Item selected.
	 * @param multiple Is this action a multiple selection
	 * @param list list that contains item
	 */
	public void itemSelected(Selectable<T> item, boolean multiple, SelectableListener<T> list);
	/**
	 * 
	 * <b>Step 3 of procedure of item unselection:<br>
	 * Tell listener that a item is unselected<br></b>
	 * @param item Item unselected.
	 * @param multiple Is this action a multiple selection
	 * @param list list that contains item
	 */
	public void itemUnselected(Selectable<T> item, boolean multiple, SelectableListener<T> list);
}
