package com.nju.se.team.mota.editor.uielem;

import javax.swing.JLabel;

import com.nju.se.team.mota.game.util.UnitStatus;
import com.nju.se.team.mota.util.ElemPanel;

public class StatusElem extends ElemPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel value;
	UnitStatus us;
	public StatusElem(UnitStatus value) {
		super(true);
		us = value;
		this.value = new JLabel(" "+value+" ");
		this.value.setOpaque(false);
		add(this.value);
		setSize(this.value.getPreferredSize());
		this.value.setLocation(0, 0);
		this.value.setSize(this.value.getPreferredSize());
	}
	
	public UnitStatus getValue(){
		return us;
	}
	
}
