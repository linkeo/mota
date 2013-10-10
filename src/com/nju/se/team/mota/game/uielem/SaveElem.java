package com.nju.se.team.mota.game.uielem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;

import com.nju.se.team.mota.game.Save;
import com.nju.se.team.mota.util.Fonts;
import com.nju.se.team.mota.util.TransparentPanel;
import com.nju.se.team.mota.util.selection.Selectable;
import com.nju.se.team.mota.util.selection.SelectableListener;

public class SaveElem extends TransparentPanel implements Selectable<Save>, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel lv, name, floor, saveTime;
	boolean selected;
	Set<SelectableListener<Save>> selectableListeners = new HashSet<SelectableListener<Save>>();
	private void setSelected(boolean selected) {
		this.selected = selected;
	}
	boolean selectable;
	Save save;
	boolean initialized = false;
	public SaveElem(Save save) {
		super(null);
		this.save = save;
		this.setSize(300, 60);
		this.add(name = new JLabel("玩家: "+save.getName()));
		this.add(lv = new JLabel("等级: "+save.getLv(), JLabel.RIGHT));
		this.add(floor = new JLabel("楼层: "+save.getFloor(), JLabel.RIGHT));
		this.add(saveTime = new JLabel("存档时间: "+save.getSavetime(), JLabel.RIGHT));
		name.setBounds(10, 0, 200, 40);
		name.setFont(Fonts.getYahei(24));
		lv.setBounds(200, 0, 100, 20);
		floor.setBounds(200, 20, 100, 20);
		saveTime.setBounds(100, 40, 200, 20);
		addMouseListener(this);
		setStyleToNormal();
	}
	public Save getSave(){
		return save;
	}
	public void setStyleToNormal(){
		this.setBackground(Color.LIGHT_GRAY);
		floor.setForeground(Color.BLACK);
		name.setForeground(Color.BLACK);
		lv.setForeground(Color.BLACK);
		saveTime.setForeground(Color.BLACK);
		if(initialized)
			this.getTopLevelAncestor().repaint();
	}
	public void setStyleToSelected(){
		this.setBackground(Color.GRAY);
		floor.setForeground(Color.WHITE);
		name.setForeground(Color.WHITE);
		lv.setForeground(Color.WHITE);
		saveTime.setForeground(Color.WHITE);
		if(initialized)
			this.getTopLevelAncestor().repaint();
	}
	public void setStyleToPressed(){
		this.setBackground(Color.DARK_GRAY);
		floor.setForeground(Color.BLACK);
		name.setForeground(Color.BLACK);
		lv.setForeground(Color.BLACK);
		saveTime.setForeground(Color.BLACK);
		if(initialized)
			this.getTopLevelAncestor().repaint();
	}
	public void setStyleToHoverred(){
		setBackground(Color.WHITE);
		floor.setForeground(Color.BLACK);
		name.setForeground(Color.BLACK);
		lv.setForeground(Color.BLACK);
		saveTime.setForeground(Color.BLACK);
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
		for(SelectableListener<Save> i : selectableListeners)
			i.itemSelected(this, multiple);
	}
	@Override
	public void unselect(boolean multiple) {
		setSelected(false);
		setStyleToNormal();
		for(SelectableListener<Save> i : selectableListeners)
			i.itemUnselected(this, multiple);
	}
	@Override
	public Save content() {
		return getSave();
	}
	@Override
	public boolean isSelected() {
		return selected;
	}
	@Override
	public boolean isSelectable() {
		return (save!=null)&&selectable;
	}
	@Override
	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}
	@Override
	public void addSelectableListener(SelectableListener<Save> l) {
		selectableListeners.add(l);
	}
	@Override
	public void removeSelectableListener(SelectableListener<Save> l) {
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
}
