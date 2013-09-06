package com.nju.se.team.mota.editor.uielem;

import javax.swing.JLabel;

import com.nju.se.team.mota.util.ElemPanel;

public class FrameElem extends ElemPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel value;
	int intvalue;
	
	public FrameElem(int value) {
		super(true);
		intvalue = value;
		this.value = new JLabel(Integer.toString(value));
		this.value.setOpaque(false);
		add(this.value);
		setSize(this.value.getPreferredSize());
		this.value.setLocation(0, 0);
		this.value.setSize(this.value.getPreferredSize());
	}
	
	public int getValue(){
		return intvalue;
	}
	
}
