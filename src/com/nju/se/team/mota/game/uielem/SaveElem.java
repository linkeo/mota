package com.nju.se.team.mota.game.uielem;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.nju.se.team.mota.game.Save;
import com.nju.se.team.mota.util.Fonts;
import com.nju.se.team.mota.util.Selectable;
import com.nju.se.team.mota.util.SelectableListener;

public class SaveElem extends JPanel implements Selectable<Save>{

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
	}
	public Save getSave(){
		return save;
	}
	public void setStyleToNormal(){
		this.setBackground(UIManager.getColor("panel.background"));
	}
	public void setStyleToSelected(){
		this.setBackground(Color.BLUE);
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
}
