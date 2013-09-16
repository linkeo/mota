package com.nju.se.team.mota.util;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicLabelUI;

public class TransparentLabelUI extends BasicLabelUI {
	@Override
	public void paint(Graphics g, JComponent c) {
		if(c.isOpaque()){
			g.setColor(c.getBackground());
			g.fillRect(0, 0, c.getSize().width, c.getSize().height);
		}
		g.setColor(c.getForeground());
		((TransparentLabel)c).getIcon().paintIcon(c, g, 0, 0);
	}
}
