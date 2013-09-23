package com.nju.se.team.mota.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;

public class TransparentButton extends TransparentLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float normal;
	private float hover;
	private float active;
	private boolean actived = true;
	public boolean isActived() {
		return actived;
	}
	public void setActived(boolean Actived) {
		this.actived = Actived;
	}
	public TransparentButton(String text, float normal,float hover,float active){
		super(text, JLabel.CENTER);
		this.setOpaque(true);
		this.setTransparency(normal);
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
				if(getTransparency()==TransparentButton.this.active
						&& TransparentButton.this.hover>=0
						&& TransparentButton.this.hover<=1)
					setTransparency(TransparentButton.this.hover);
				getParent().repaint();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(actived)
				if(TransparentButton.this.active>=0
						&& TransparentButton.this.active<=1)
					setTransparency(TransparentButton.this.active);
				getParent().repaint();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(actived)
				if(TransparentButton.this.normal>=0
						&& TransparentButton.this.normal<=1)
					setTransparency(TransparentButton.this.normal);
				getParent().repaint();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(actived)
				if(TransparentButton.this.hover>=0
						&& TransparentButton.this.hover<=1)
					setTransparency(TransparentButton.this.hover);
				getParent().repaint();
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