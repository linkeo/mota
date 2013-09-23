package com.nju.se.team.mota.util;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class TransparentPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	float transparency = 1f;

	public TransparentPanel() {
		super();
	}

	public TransparentPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	public TransparentPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	public TransparentPanel(LayoutManager layout) {
		super(layout);
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
