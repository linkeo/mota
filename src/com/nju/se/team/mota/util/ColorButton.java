package com.nju.se.team.mota.util;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class ColorButton extends TransparentLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color normal;
	private Color hover;
	private Color active;
	private boolean actived = true;
	public boolean isActived() {
		return actived;
	}
	public void setActived(boolean Actived) {
		this.actived = Actived;
	}
	public ColorButton(String text, Color normal,Color hover,Color active){
		super(text);
		this.setOpaque(true);
		this.setBackground(normal);
		this.normal = normal;
		this.hover = hover;
		this.active = active;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(actived){
					for(ActionListener l : actionListeners)
						l.actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, ""));
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(actived)
				if(getBackground().equals(ColorButton.this.active) && ColorButton.this.hover!=null)
					setBackground(ColorButton.this.hover);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(actived)
				if(ColorButton.this.active!=null)
					setBackground(ColorButton.this.active);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(actived)
				if(ColorButton.this.normal!=null)
					setBackground(ColorButton.this.normal);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(actived)
				if(ColorButton.this.hover!=null)
					setBackground(ColorButton.this.hover);
			}
		});
	}
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		this.setMinimumSize(getSize());
		this.setMaximumSize(getSize());
		this.setPreferredSize(getSize());
	}
	private Set<ActionListener> actionListeners = new HashSet<ActionListener>();
	public void addActionListener(ActionListener l) {
		actionListeners.add(l);
	}public void removeActionListener(ActionListener l) {
		actionListeners.remove(l);
	}
}