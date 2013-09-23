package com.nju.se.team.mota.game;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class TransparentListPanel extends TrasparentPanel implements MouseWheelListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int ROTATION_INCREMENT = 20;
	protected int HGAP = 10;
	protected int WGAP = 10;
	
	int arch = 0;
	
	public TransparentListPanel() {
		super(null);
		this.addMouseWheelListener(this);
	}
	
	@Override
	public Component add(Component comp) {
		comp.addMouseWheelListener(this);
		Component result = super.add(comp);
		doLayout();
		return result;
	}
	
	@Override
	public void remove(int index) {
		Component comp = getComponent(index);
		comp.removeMouseWheelListener(this);
		super.remove(index);
		doLayout();
	}
	
	@Override
	public void doLayout() {
		int LIST_WIDTH = getSize().width;
		int n = getComponentCount();
		int currentX = WGAP;
		int currentY = HGAP;
		int currentBottom = HGAP;
		for(int i=0;i<n;i++){
			Component temp = getComponent(i);
			if(currentX+temp.getSize().width>LIST_WIDTH){
				currentX = WGAP;
				currentY = currentBottom + HGAP;
			}
			temp.setLocation(currentX, currentY - arch);
			currentX += temp.getSize().width + WGAP;
			currentBottom = Math.max(currentBottom, currentY + temp.getSize().height);
		}
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		arch += e.getWheelRotation()*ROTATION_INCREMENT;
		checkArch();
		doLayout();
		getParent().repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	@Override
	public void remove(Component comp) {
		comp.removeMouseWheelListener(this);
		super.remove(comp);
	}
	
	private void checkArch(){
		int max = getCurrentBottom() - getSize().height + HGAP;
		if(arch>max) arch = max;
		if(arch<0) arch = 0;
	}

	private int getCurrentBottom(){
		int LIST_WIDTH = getSize().width;
		int n = getComponentCount();
		int currentX = WGAP;
		int currentY = HGAP;
		int currentBottom = HGAP;
		for(int i=0;i<n;i++){
			Component temp = getComponent(i);
			if(currentX+temp.getSize().width>LIST_WIDTH){
				currentX = WGAP;
				currentY = currentBottom + HGAP;
			}
			currentX += temp.getSize().width + WGAP;
			currentBottom = Math.max(currentBottom, currentY + temp.getSize().height);
		}
		return currentBottom;
	}
}
