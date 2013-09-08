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
	String[][] imagekey;
	
	public FrameElem(int value, String[][] image) {
		super(true);
		intvalue = value;
		imagekey = image;
		this.value = new JLabel(" "+value+" ");
		this.value.setOpaque(false);
		add(this.value);
		setSize(this.value.getPreferredSize());
		this.value.setLocation(0, 0);
		this.value.setSize(this.value.getPreferredSize());
	}
	
	public int getValue(){
		return intvalue;
	}
	
	public String[][] getImageKey(){
		return imagekey;
	}

	public void setImageKey(String[][] imagekey) {
		this.imagekey = imagekey;
	}
	
}
