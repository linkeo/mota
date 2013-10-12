package com.nju.se.team.mota.util.selection;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public abstract class ListComponent<T> extends JPanel implements Selectable<T>,MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean selected = false;
	private boolean selectable = true;
	private HashSet<SelectableListener<T>> selectedListeners =
			new HashSet<SelectableListener<T>>();
	private Color normalColor, selectedColor;
	private T content;
	public ListComponent() {
		super(null);
		normalColor = getBackground();
		if(normalColor==null)
			normalColor = Color.LIGHT_GRAY;
		selectedColor = normalColor.darker();
		this.addMouseListener(this);
	}

	@Override
	public void select(boolean multiple) {
		setSelected(true);
		for(SelectableListener<T> l : selectedListeners)
			l.itemSelected(this, multiple);
	}

	@Override
	public void unselect(boolean multiple) {
		setSelected(false);
		for(SelectableListener<T> l : selectedListeners)
			l.itemUnselected(this, multiple);
	}

	@Override
	public T getContent() {
		return content;
	}

	public void setContent(T content){
		this.content = content;
	}
	
	@Override
	public boolean isSelected() {
		return selected;
	}
	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
		if(selected)
			setStyleToSelected();
		else
			setStyleToNormal();
	}
	public void setStyleToNormal(){
		setBackground(normalColor);
	}
	public void setStyleToSelected(){
		setBackground(selectedColor);
	}

	@Override
	public boolean isSelectable() {
		return selectable&&(getContent()!=null);
	}

	@Override
	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	@Override
	public void addSelectableListener(SelectableListener<T> l) {
		selectedListeners.add(l);
	}

	@Override
	public void removeSelectableListener(SelectableListener<T> l) {
		selectedListeners.remove(l);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)){
			boolean multiple = e.isControlDown();
			if(selected)
				unselect(multiple);
			else
				select(multiple);
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
