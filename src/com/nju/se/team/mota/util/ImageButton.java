package com.nju.se.team.mota.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;

public class ImageButton extends TransparentLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon normal;
	private ImageIcon hover;
	private ImageIcon active;
	private boolean actived = true;
	public boolean isActived() {
		return actived;
	}
	public void setActived(boolean Actived) {
		this.actived = Actived;
	}
	public ImageButton(ImageIcon normal,ImageIcon hover,ImageIcon active){
		super(normal);
		this.normal = normal;
		this.hover = hover;
		this.active = active;
		this.setSize(normal.getIconWidth(), normal.getIconHeight());
		this.setMinimumSize(getSize());
		this.setMaximumSize(getSize());
		this.setPreferredSize(getSize());
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
				if(getIcon()==ImageButton.this.active && ImageButton.this.hover!=null)
					setIcon(ImageButton.this.hover);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(actived)
				if(ImageButton.this.active!=null)
					setIcon(ImageButton.this.active);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(actived)
				if(ImageButton.this.normal!=null)
					setIcon(ImageButton.this.normal);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(actived)
				if(ImageButton.this.hover!=null)
					setIcon(ImageButton.this.hover);
			}
		});
	}
	
	private Set<ActionListener> actionListeners = new HashSet<ActionListener>();
	public void addActionListener(ActionListener l) {
		actionListeners.add(l);
	}public void removeActionListener(ActionListener l) {
		actionListeners.remove(l);
	}
	public void setNormal() {
		if(ImageButton.this.normal!=null)
			setIcon(ImageButton.this.normal);
	}
}
