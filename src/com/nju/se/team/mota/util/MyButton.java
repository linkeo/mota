package com.nju.se.team.mota.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;

public class MyButton extends TransparentLabel{
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
	public MyButton(ImageIcon normal,ImageIcon hover,ImageIcon active){
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
				if(getIcon()==MyButton.this.active && MyButton.this.hover!=null)
					setIcon(MyButton.this.hover);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(actived)
				if(MyButton.this.active!=null)
					setIcon(MyButton.this.active);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(actived)
				if(MyButton.this.normal!=null)
					setIcon(MyButton.this.normal);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(actived)
				if(MyButton.this.hover!=null)
					setIcon(MyButton.this.hover);
			}
		});
	}
	
	private Set<ActionListener> actionListeners = new HashSet<ActionListener>();
	public void addActionListener(ActionListener l) {
		actionListeners.add(l);
	}public void removeActionListener(ActionListener l) {
		actionListeners.remove(l);
	}
}
