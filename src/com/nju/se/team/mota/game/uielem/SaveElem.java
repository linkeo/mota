package com.nju.se.team.mota.game.uielem;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.nju.se.team.mota.game.Save;
import com.nju.se.team.mota.util.Fonts;

public class SaveElem extends JPanel{

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
}
