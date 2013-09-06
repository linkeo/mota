package com.nju.se.team.mota.editor;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.nju.se.team.mota.util.ElemPanel;

public class ResElem  extends ElemPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel value;
	BufferedImage res;
	
	public ResElem(BufferedImage res) {
		super(false);
		this.res = res;
		this.value = new JLabel(new ImageIcon(res));
		this.value.setOpaque(false);
		add(this.value);
		setSize(this.value.getPreferredSize());
		this.value.setLocation(0, 0);
		this.value.setSize(this.value.getPreferredSize());
	}
	
	public BufferedImage getRes(){
		return res;
	}
	
}
