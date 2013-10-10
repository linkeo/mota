package com.nju.se.team.mota.util.selection;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ListPane<T extends Component> extends JScrollPane implements SelectableList<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected HashSet<Selectable<T>> items = 
			new HashSet<Selectable<T>>();
	protected HashSet<SelectionListener<T>> selectionListeners = 
			new HashSet<SelectionListener<T>>();
	protected Selectable<T> selectedItem;
	protected ArrayList<Selectable<T>> selectedItems = 
			new ArrayList<Selectable<T>>();
	protected JPanel view = new JPanel();
	protected JLabel head;
	protected int horizontalGap = 10, verticalGap = 10;
	private boolean multiSelectable = true;

	public ListPane(){
		this(null);
	}
	public ListPane(String title) {
		this.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.getVerticalScrollBar().setBlockIncrement(10*verticalGap);
		this.getVerticalScrollBar().setUnitIncrement(verticalGap);
		this.setHead(title);
		this.setViewportView(view);
		view.setLayout(null);
	}
	private void setHead(String title) {
		if(title!=null){
			head = new JLabel(title,JLabel.CENTER);
			head.setOpaque(true);
			this.setColumnHeaderView(head);
		}
	}
	@Override
	public void itemSelected(Selectable<T> item, boolean multiple) {
		selectedItem = item;
		if(! (multiSelectable&&multiple) )
			selectedItems.clear();
		if(! selectedItems.contains(item))
			selectedItems.remove(item);
		selectedItems.add(item);
	}

	@Override
	public void itemUnselected(Selectable<T> item, boolean multiple) {
		selectedItems.remove(item);
		if(! (multiSelectable&&multiple) ){
			selectedItems.clear();
			selectedItem = null;
		}else{
			if(selectedItems.isEmpty())
				selectedItem = null;
			else
				selectedItem = selectedItems.get(selectedItems.size()-1);
		}
	}

	@Override
	public Selectable<T> getSelectedItem() {
		return selectedItem;
	}

	@Override
	public T getSelectedContent() {
		return selectedItem.content();
	}

	ArrayList<Selectable<T>> selectedItemsCopy = new ArrayList<Selectable<T>>();
	@Override
	public Collection<Selectable<T>> getSelectedItems() {
		if(! selectedItemsCopy.equals(selectedItems)){
			selectedItemsCopy.clear();
			selectedItemsCopy.addAll(selectedItems);
		}
		return selectedItemsCopy;
	}

	HashSet<T> selectedContents = new HashSet<T>();
	@Override
	public Collection<T> getSelectedContents() {
		if(! selectedItemsCopy.equals(selectedItems)){
			selectedItemsCopy.clear();
			selectedItemsCopy.addAll(selectedItems);
			selectedContents.clear();
			for(Selectable<T> item : selectedItems)
				selectedContents.add(item.content());
		}
		return selectedContents;
	}

	@Override
	public void selectAll() {
		for(Selectable<T> item : items)
			item.select(true);
	}

	@Override
	public void unselectAll() {
		for(Selectable<T> item : items)
			item.unselect(true);
	}

	@Override
	public boolean isMultipleSelectable() {
		return multiSelectable;
	}

	@Override
	public void setMultipleSelectable(boolean multiple) {
		multiSelectable = multiple;
	}
	@Override
	public void addSelectableItem(Selectable<T> item) {
		items.add(item);
		item.addSelectableListener(this);
	}
	@Override
	public void removeSelectableItem(Selectable<T> item) {
		items.remove(item);
		item.removeSelectableListener(this);
	}
	HashSet<Selectable<T>> itemsCopy = new HashSet<Selectable<T>>();
	@Override
	public Collection<Selectable<T>> getSelectableItems() {
		if(! itemsCopy.equals(items)){
			itemsCopy.clear();
			itemsCopy.addAll(items);
		}
		return itemsCopy;
	}
	
}
