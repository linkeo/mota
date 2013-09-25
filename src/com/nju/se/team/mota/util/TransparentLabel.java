package com.nju.se.team.mota.util;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;
import javax.swing.JLabel;

public class TransparentLabel extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	float transparency = 1f;
	public TransparentLabel() {
		super();
	}
	public TransparentLabel(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
	}
	public TransparentLabel(Icon image) {
		super(image);
	}
	public TransparentLabel(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
	}
	public TransparentLabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
	}
	public TransparentLabel(String text) {
		super(text);
	}
	
	public float getTransparency() {
		return transparency;
	}
	public void setTransparency(float transparency) {
		this.transparency = transparency;
		this.repaint();
	}
	@Override
	public void paint(Graphics g) {
		if(transparency>=0f&&transparency<=1f)
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
		super.paint(g);
	}
}
