package com.nju.se.team.mota.util;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLayeredPane;

import com.nju.se.team.mota.editor.Colors;


public class ElemPanel extends JLayeredPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Color
			color_normal = Colors.THEME_COLOR,
			color_hover = Colors.THEME_COLOR_LIGHT,
			color_active = Colors.THEME_COLOR_2;
	private boolean isSelected = false,selectable;
	public ElemPanel(boolean selectable){
		if(selectable){
			this.setBackground(color_normal);
		}
		this.setLayout(null);
		this.setOpaque(true);
		this.setSelected(false);
		this.selectable = selectable;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(isSelectable()){
					if(isSelected)
						ElemPanel.this.setBackground(color_active);//Ðü¸¡ÑÕÉ«
					else
						ElemPanel.this.setBackground(color_hover);//Ðü¸¡ÑÕÉ«
				}
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(isSelectable())
					ElemPanel.this.setBackground(color_active);//µã»÷ÑÕÉ«
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(isSelectable()){
					if(isSelected)
						ElemPanel.this.setBackground(color_active);//Ðü¸¡ÑÕÉ«
					else
						ElemPanel.this.setBackground(color_normal);//Ðü¸¡ÑÕÉ«
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(isSelectable()){
					if(isSelected)
						ElemPanel.this.setBackground(color_active);//Ðü¸¡ÑÕÉ«
					else
						ElemPanel.this.setBackground(color_hover);//Ðü¸¡ÑÕÉ«
				}
			}
		});
	}
	public void cancelSelected(){
		if(selectable){
			isSelected = false;
			setBackground(color_normal);
		}
	}
	public void select(){
		if(selectable){
			isSelected = true;
			setBackground(color_active);
		}
	}
	public void setSelected(boolean b){
		if(selectable){
			if(b) select();
			else cancelSelected();
		}
	}
	public boolean isSelected(){
		return isSelected;
	}
	public void setColor_normal(Color color_normal) {
		this.color_normal = color_normal;
	}
	public void setColor_hover(Color color_hover) {
		this.color_hover = color_hover;
	}
	public void setColor_active(Color color_active) {
		this.color_active = color_active;
	}
	public Color getColor_normal() {
		return color_normal;
	}
	public Color getColor_hover() {
		return color_hover;
	}
	public Color getColor_active() {
		return color_active;
	}
	public boolean isSelectable() {
		return selectable;
	}
}
