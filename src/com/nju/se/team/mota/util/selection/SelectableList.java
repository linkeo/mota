package com.nju.se.team.mota.util.selection;

import java.util.Collection;

public interface SelectableList<T> extends SelectableListener<T> {
	public void addSelectableItem(Selectable<T> item);
	public void removeSelectableItem(Selectable<T> item);
	public Collection<Selectable<T>> getSelectableItems();
	public void addSelectionListener(SelectionListener<T> listener);
	public void removeSelectionListener(SelectionListener<T> listener);
}
