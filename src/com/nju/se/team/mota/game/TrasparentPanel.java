package com.nju.se.team.mota.game;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class TrasparentPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	float transparency = 1f;

	public TrasparentPanel() {
		super();
	}

	public TrasparentPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	public TrasparentPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	public TrasparentPanel(LayoutManager layout) {
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
