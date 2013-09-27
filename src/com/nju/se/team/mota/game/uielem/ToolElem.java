package com.nju.se.team.mota.game.uielem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;

import com.nju.se.team.mota.game.unit.Tool;
import com.nju.se.team.mota.util.Selectable;
import com.nju.se.team.mota.util.SelectableListener;
import com.nju.se.team.mota.util.TransparentLabel;

public class ToolElem extends TransparentLabel implements Selectable<Tool>, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	boolean selected;
	Set<SelectableListener<Tool>> selectableListeners = new HashSet<SelectableListener<Tool>>();
	private void setSelected(boolean selected) {
		this.selected = selected;
	}
	boolean selectable;
	boolean initialized = false;
	private Tool tool;
	public ToolElem(Tool tool) {
		super(new ImageIcon(tool.getImage()));
		setTool(tool);
		setSize(32, 32);
		addMouseListener(this);
		setStyleToNormal();
	}

	public void setStyleToNormal(){
		this.setBackground(Color.LIGHT_GRAY);
		if(initialized)
			this.getTopLevelAncestor().repaint();
	}
	public void setStyleToSelected(){
		this.setBackground(Color.GRAY);
		if(initialized)
			this.getTopLevelAncestor().repaint();
	}
	public void setStyleToPressed(){
		this.setBackground(Color.DARK_GRAY);
		if(initialized)
			this.getTopLevelAncestor().repaint();
	}
	public void setStyleToHoverred(){
		setBackground(Color.WHITE);
		if(initialized)
			this.getTopLevelAncestor().repaint();
	}
	@Override
	public void paint(Graphics g) {
		if(!initialized) initialized = true;
		super.paint(g);
	}
	@Override
	public void select(boolean multiple) {
		setSelected(true);
		setStyleToSelected();
		for(SelectableListener<Tool> i : selectableListeners)
			i.itemSelected(this, multiple);
	}
	@Override
	public void unselect(boolean multiple) {
		setSelected(false);
		setStyleToNormal();
		for(SelectableListener<Tool> i : selectableListeners)
			i.itemUnselected(this, multiple);
	}
	@Override
	public Tool content() {
		return getTool();
	}
	@Override
	public boolean isSelected() {
		return selected;
	}
	@Override
	public boolean isSelectable() {
		return (tool!=null)&&selectable;
	}
	@Override
	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}
	@Override
	public void addSelectableListener(SelectableListener<Tool> l) {
		selectableListeners.add(l);
	}
	@Override
	public void removeSelectableListener(SelectableListener<Tool> l) {
		selectableListeners.remove(l);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(selected){
			unselect(e.isControlDown());
		}else{
			select(e.isControlDown());
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		setStyleToPressed();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(selected)
			setStyleToSelected();
		else
			setStyleToNormal();
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		setStyleToHoverred();
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(selected)
			setStyleToSelected();
		else
			setStyleToNormal();
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

}
