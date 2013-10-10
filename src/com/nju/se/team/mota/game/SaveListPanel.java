package com.nju.se.team.mota.game;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.nju.se.team.mota.game.uielem.SaveElem;
import com.nju.se.team.mota.util.TransparentListPanel;
import com.nju.se.team.mota.util.selection.Selectable;
import com.nju.se.team.mota.util.selection.SelectableListener;

public class SaveListPanel extends TransparentListPanel implements SelectableListener<Save>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArrayList<Selectable<Save>> selectedItems = new ArrayList<Selectable<Save>>();
	HashSet<Selectable<Save>> allItems = new HashSet<Selectable<Save>>();
	Selectable<Save> selectedItem = null;
	boolean multipleSelectable = false;
	boolean fromself = false;
	
	public Component add(SaveElem comp) {
		allItems.add(comp);
		comp.setTransparency(getTransparency());
		comp.addSelectableListener(this);
		return super.add(comp);
	}
	
	public void remove(SaveElem comp) {
		allItems.remove(comp);
		comp.removeSelectableListener(this);
		super.remove(comp);
	}
	
	@Override
	public void itemSelected(Selectable<Save> item, boolean multiple) {
		if(fromself) return;//avoid cycle from unselect
		fromself = true;
		selectedItem = item;
		if( !(isMultipleSelectable()&&multiple) ){
			for(Selectable<Save> i : selectedItems)
				i.unselect(multiple);
			selectedItems.clear();
		}
		selectedItems.add(item);
		fromself = false;
	}

	@Override
	public synchronized void itemUnselected(Selectable<Save> item, boolean multiple) {
		if(fromself) return;//avoid cycle from unselect
		fromself = true;
		selectedItems.remove(item);
		if( !(isMultipleSelectable()&&multiple) ){
			for(Selectable<Save> i : selectedItems)
				i.unselect(multiple);
			selectedItems.clear();
			selectedItem = null;
		}
		int listSize = selectedItems.size();
		if(listSize > 0)
			selectedItem = selectedItems.get(listSize-1);
		else
			selectedItem = null;
		fromself = false;
	}

	@Override
	public Selectable<Save> getSelectedItem() {
		return selectedItem;
	}

	@Override
	public Save getSelectedContent() {
		Save content = null;
		if(selectedItem != null)
			content = selectedItem.content();
		return content;
	}

	@Override
	public Collection<Selectable<Save>> getSelectedItems() {
		return new HashSet<Selectable<Save>>(selectedItems);
	}

	@Override
	public Collection<Save> getSelectedContents() {
		HashSet<Save> contents = new HashSet<Save>();
		for(Selectable<Save> i : selectedItems)
			contents.add(i.content());
		return contents;
	}

	@Override
	public void selectAll() {
		for(Selectable<Save> i : allItems)
			i.select(true);
	}

	@Override
	public void unselectAll() {
		for(Selectable<Save> i : allItems)
			i.unselect(true);
	}

	@Override
	public boolean isMultipleSelectable() {
		return multipleSelectable;
	}

	@Override
	public void setMultipleSelectable(boolean multiple) {
		multipleSelectable = multiple;
	}
	
	
	
}
