package com.nju.se.team.mota.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.nju.se.team.mota.util.Selectable;
import com.nju.se.team.mota.util.SelectableListener;

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
	
	@Override
	public synchronized void itemSelected(Selectable<Save> item, boolean multiple) {
		if(fromself) return;
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
		if(fromself) return;
		fromself = true;
		selectedItems.remove(item);
		
		fromself = false;
	}

	@Override
	public Selectable<Save> getSelectedItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Save getSelectedContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Selectable<Save>> getSelectedItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Save> getSelectedContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void selectAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unselectAll() {
		// TODO Auto-generated method stub
		
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
