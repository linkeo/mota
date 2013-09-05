package com.nju.se.team.mota.temp;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import com.nju.se.team.mota.editor.Colors;


public class ElemPanel extends JLayeredPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static Color
			color_normal = Colors.THEME_COLOR,
			color_hover = Colors.THEME_COLOR_LIGHT,
			color_active = Colors.THEME_COLOR_2;
	private boolean isSelected = false,selectable;
	private static ImageIcon iiSelected = new ImageIcon("image/selected.png");
	private JLabel lSelected = new JLabel(iiSelected);
	public ElemPanel(boolean selectable){
		if(selectable){
			this.setBackground(color_normal);
		}
		this.setLayout(null);
		this.setOpaque(true);
		this.add(lSelected, Integer.MIN_VALUE);
		this.setSelected(false);
		this.selectable = selectable;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(isSelectable())
					ElemPanel.this.setBackground(color_hover);//悬浮颜色
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(isSelectable())
					ElemPanel.this.setBackground(color_active);//点击颜色
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(isSelectable())
					ElemPanel.this.setBackground(color_normal);//普通颜色
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(isSelectable())
					ElemPanel.this.setBackground(color_hover);//悬浮颜色
			}
		});
	}
	@Override
	public void repaint() {
		lSelected.setBounds(getWidth()-34, 2, 32, 29);
		if(isSelected)
			lSelected.setVisible(true);
		else
			lSelected.setVisible(false);
			
		super.repaint();
	}
	public void cancelSelected(){
		if(selectable){
			isSelected = false;
			lSelected.setVisible(false);//
		}
	}
	public void select(){
		if(selectable){
			isSelected = true;
			lSelected.setVisible(true);//选中
		}
	}
	public void setSelected(boolean b){
		if(selectable){
			isSelected = b;
			lSelected.setVisible(b);//选中
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
