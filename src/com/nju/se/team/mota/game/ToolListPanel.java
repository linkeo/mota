package com.nju.se.team.mota.game;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.nju.se.team.mota.game.uielem.ToolElem;
import com.nju.se.team.mota.game.unit.Tool;
import com.nju.se.team.mota.util.TransparentListPanel;
import com.nju.se.team.mota.util.selection.Selectable;
import com.nju.se.team.mota.util.selection.SelectableListener;

public class ToolListPanel extends TransparentListPanel implements SelectableListener<Tool>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Selectable<Tool>> selectedItems = new ArrayList<Selectable<Tool>>();
	HashSet<Selectable<Tool>> allItems = new HashSet<Selectable<Tool>>();
	Selectable<Tool> selectedItem = null;
	boolean multipleSelectable = false;
	boolean fromself = false;
	
	public Component add(ToolElem comp) {
		allItems.add(comp);
		comp.setTransparency(1f);
		comp.addSelectableListener(this);
		return super.add(comp);
	}
	
	public void remove(ToolElem comp) {
		allItems.remove(comp);
		comp.removeSelectableListener(this);
		super.remove(comp);
	}
	
	public ToolListPanel() {
		setHGAP(0);
		setWGAP(0);
	}
	
	@Override
	public void itemSelected(Selectable<Tool> item, boolean multiple) {
		if(fromself) return;//avoid cycle from unselect
		fromself = true;
		selectedItem = item;
		if( !(isMultipleSelectable()&&multiple) ){
			for(Selectable<Tool> i : selectedItems)
				i.unselect(multiple);
			selectedItems.clear();
		}
		selectedItems.add(item);
		fromself = false;
	}

	@Override
	public synchronized void itemUnselected(Selectable<Tool> item, boolean multiple) {
		if(fromself) return;//avoid cycle from unselect
		fromself = true;
		selectedItems.remove(item);
		if( !(isMultipleSelectable()&&multiple) ){
			for(Selectable<Tool> i : selectedItems)
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
	public Selectable<Tool> getSelectedItem() {
		return selectedItem;
	}

	@Override
	public Tool getSelectedContent() {
		Tool content = null;
		if(selectedItem != null)
			content = selectedItem.content();
		return content;
	}

	@Override
	public Collection<Selectable<Tool>> getSelectedItems() {
		return new HashSet<Selectable<Tool>>(selectedItems);
	}

	@Override
	public Collection<Tool> getSelectedContents() {
		HashSet<Tool> contents = new HashSet<Tool>();
		for(Selectable<Tool> i : selectedItems)
			contents.add(i.content());
		return contents;
	}

	@Override
	public void selectAll() {
		for(Selectable<Tool> i : allItems)
			i.select(true);
	}

	@Override
	public void unselectAll() {
		for(Selectable<Tool> i : allItems)
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
