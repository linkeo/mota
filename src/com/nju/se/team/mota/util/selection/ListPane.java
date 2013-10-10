package com.nju.se.team.mota.util.selection;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ListPane<T> extends JScrollPane implements SelectableList<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected HashSet<ListComponent<T>> comps = 
			new HashSet<ListComponent<T>>();
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
	private boolean toAdjust = true;

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
	public void add(ListComponent<T> comp) {
		addSelectableItem(comp);
		view.add(comp);
		comps.add(comp);
		requireAdjustment();
	}
	public void remove(ListComponent<T> comp) {
		removeSelectableItem(comp);
		view.remove(comp);
		comps.remove(comp);
		requireAdjustment();
	}
	public void clear() {
		for(ListComponent<T> c : comps)
			remove(c);
		for(Component c : view.getComponents())
			remove(c);
	}
	@Override
	public void itemSelected(Selectable<T> item, boolean multiple) {
		selectedItem = item;
		if(! (multiSelectable&&multiple) )
			for(Selectable<T> i : items)
				if(i!=item&&i.isSelected())
					i.unselect(true);
		if(! selectedItems.contains(item))
			selectedItems.remove(item);
		selectedItems.add(item);
	}

	@Override
	public void itemUnselected(Selectable<T> item, boolean multiple) {
		selectedItems.remove(item);
		if(! (multiSelectable&&multiple) ){
			for(Selectable<T> i : items)
				if(i!=item&&i.isSelected()&&multiSelectable)
					i.unselect(true);
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
	@Override
	public void paint(Graphics g){
		doLayout();
		super.paint(g);
	}
	private void requireAdjustment() {
		toAdjust = true;
	}
	private void requireAccepted() {
		toAdjust = false;
	}
	private boolean isAdjustmentRequired() {
		return toAdjust;
	}
	public int getHorizontalGap() {
		return horizontalGap;
	}
	public void setHorizontalGap(int horizontalGap) {
		if(this.horizontalGap != horizontalGap)
			requireAdjustment();
		this.horizontalGap = horizontalGap;
	}
	public int getVerticalGap() {
		return verticalGap;
	}
	public void setVerticalGap(int verticalGap) {
		if(this.verticalGap != verticalGap)
			requireAdjustment();
		this.verticalGap = verticalGap;
	}
	@Override
	public void validate() {
		requireAdjustment();
		super.validate();
	}
	@Override
	public void doLayout() {
		super.doLayout();
		if(!isAdjustmentRequired())
			return;
		requireAccepted();
		adjustLayout();
	}
	private void adjustLayout() {
		int LIST_WIDTH = getViewport().getSize().width;
		if(!(LIST_WIDTH>0))
			requireAdjustment();
		Component[] comps = view.getComponents();
		int n = comps.length;
		int currentX = horizontalGap;
		int currentY = verticalGap;
		int currentBottom = verticalGap;
		for(int i=0;i<n;i++){
			Component temp = comps[i];
			if(currentX+temp.getSize().width>LIST_WIDTH){
				currentX = horizontalGap;
				currentY = currentBottom + verticalGap;
			}
			temp.setLocation(currentX, currentY);
			currentX += temp.getSize().width + horizontalGap;
			currentBottom = Math.max(currentBottom, currentY + temp.getSize().height);
		}
		view.setSize(LIST_WIDTH, currentBottom+verticalGap);
		view.setPreferredSize(view.getSize());
	}
	@Override
	public void setSize(Dimension size) {
		requireAdjustment();
		super.setSize(size);
	}
	@Override
	public void setSize(int width, int height) {
		view.setSize(width-16, height-1);
		requireAdjustment();
		super.setSize(width, height);
	}
	
}
