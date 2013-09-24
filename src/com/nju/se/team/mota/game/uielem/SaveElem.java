package com.nju.se.team.mota.game.uielem;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.nju.se.team.mota.game.Save;
import com.nju.se.team.mota.util.Fonts;
import com.nju.se.team.mota.util.Selectable;

public class SaveElem extends JPanel implements Selectable<Save>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel lv, name, floor, saveTime;
	
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void unselect(boolean multiple) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Save content() {
		return getSave();
	}
	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isSelectable() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setSelectable(boolean selectable) {
		// TODO Auto-generated method stub
		
	}
}
