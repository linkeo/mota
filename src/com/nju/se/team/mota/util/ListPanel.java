package com.nju.se.team.mota.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class ListPanel extends JScrollPane implements MouseListener, ComponentListener{
	private static final long serialVersionUID = 1L;
	protected ArrayList<ElemPanel> elems = new ArrayList<ElemPanel>();
	protected ArrayList<MouseListener> mouseListeners = new ArrayList<MouseListener>();
	protected ElemPanel selectedElem = null;
	protected ArrayList<ElemPanel> selectedElems = new ArrayList<ElemPanel>();
	protected JPanel list = new JPanel();
	protected JLabel head;
	protected int HGAP = 10;
	protected int WGAP = 10;
	protected boolean multiSelectable = true;
	public boolean isMultiSelectable() {
		return multiSelectable;
	}
	public void setMultiSelectable(boolean multiSelectable) {
		this.multiSelectable = multiSelectable;
	}
	public ListPanel(){
		this(null);
	}
	public ListPanel(String title) {
		this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.getVerticalScrollBar().setBlockIncrement(10*HGAP);
		this.getVerticalScrollBar().setUnitIncrement(HGAP);
		this.setHead(title);
		this.setViewportView(list);
		list.setLayout(null);
	}
	private void setHead(String title) {
		if(title!=null){
			head = new JLabel(title,JLabel.CENTER);
			head.setOpaque(true);
			this.setColumnHeaderView(head);
		}
	}
	@Override
	public void setBackground(Color bg) {
		super.setBackground(bg);
		if(list!=null)
			list.setBackground(bg);
		if(head!=null)
			head.setBackground(bg);
	}
	public ElemPanel add(ElemPanel comp) {
		comp.addMouseListener(this);
		comp.addComponentListener(this);
		elems.add(comp);
		list.add(comp);
		adjustSize();
//		if(comp.isSelectable()&&selectedElem==null){
//			selectedElem = comp;
//			selectedElems.add(comp);
//			comp.select();
//		}
		comp.repaint();
		return comp;
	}
	public void remove(ElemPanel comp) {
		comp.removeMouseListener(this);
		comp.removeComponentListener(this);
		elems.remove(comp);
		list.remove(comp);
		adjustSize();
		if(comp.isSelectable()&&selectedElem==comp){
			comp.cancelSelected();
			selectedElem = null;
		}
	}
	@Override
	public void removeAll() {
		@SuppressWarnings("unchecked")
		ArrayList<ElemPanel> temp = (ArrayList<ElemPanel>) elems.clone();
		for(ElemPanel comp : temp)
			remove(comp);
	}
	@Override
	public void setSize(Dimension size) {
		super.setSize(size);
		list.setSize(size.width-16, size.height-1);
		adjustSize();
	}
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		list.setSize(width-16, height-1);
		adjustSize();
	}
	public void adjustSize(){
//		int LIST_WIDTH = getSize().width-16;
		int LIST_WIDTH = getViewport().getSize().width;
		int n = elems.size();
		int currentX = WGAP;
		int currentY = HGAP;
		int currentBottom = HGAP;
		for(int i=0;i<n;i++){
			ElemPanel temp = elems.get(i);
			if(currentX+temp.getSize().width>LIST_WIDTH){
				currentX = WGAP;
				currentY = currentBottom + HGAP;
			}
			temp.setLocation(currentX, currentY);
			currentX += temp.getSize().width + WGAP;
			currentBottom = Math.max(currentBottom, currentY + temp.getSize().height);
		}
		list.setSize(LIST_WIDTH, currentBottom+HGAP);
		list.setPreferredSize(list.getSize());
	}
	public void setTitle(String string) {
		if(head!=null)
			head.setText(string);
	}
	public void selectAll(){
		selectedElems.clear();
		for(ElemPanel elem : elems){
			selectedElems.add(elem);
			elem.select();
		}
	}
	public void setSelectedElem(ElemPanel comp){
		this.selectedElem = comp;
	}
	public ArrayList<ElemPanel> getSelectedElems(){
		return this.selectedElems;
	}
	public ElemPanel getSelectedElem(){
		return this.selectedElem;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		for(MouseListener ml : mouseListeners)
			ml.mouseClicked(e);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		for(MouseListener ml : mouseListeners)
			ml.mousePressed(e);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		ElemPanel elem = (ElemPanel)e.getComponent();
		if(elem.isSelectable()){
			if(isMultiSelectable()&&e.isControlDown()){
				if(elem.isSelected()){
					elem.setSelected(false);
					selectedElems.remove(elem);
					selectedElem = null;
				}
				else{
					elem.setSelected(true);
					selectedElems.add(elem);
					selectedElem = elem;
				}
			}else{
				if(elem.isSelected()){
					elem.setSelected(false);
					selectedElems.remove(elem);
					selectedElem = null;
					for(ElemPanel se : selectedElems)
						if(se.isSelected())
							se.cancelSelected();
					selectedElems.clear();
				}else{
					for(ElemPanel se : selectedElems)
						if(se.isSelected())
							se.cancelSelected();
					selectedElems.clear();
					elem.setSelected(true);
					selectedElems.add(elem);
					selectedElem = elem;
				}
			}
			if(elem.isSelected())
				elem.setSelected(true);//бЁжа
			else
				elem.setSelected(false);//
		}
		for(MouseListener ml : mouseListeners)
			ml.mouseReleased(e);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		for(MouseListener ml : mouseListeners)
			ml.mouseEntered(e);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		for(MouseListener ml : mouseListeners)
			ml.mouseExited(e);
	}
	public ArrayList<ElemPanel> getElems() {
		return elems;
	}
	public void addElemMouseListener(MouseListener ml){
		mouseListeners.add(ml);
	}
	@Override
	public void componentResized(ComponentEvent e) {
		adjustSize();
	}
	@Override
	public void paint(Graphics g){
		adjustSize();
		super.paint(g);
	}
	@Override
	public void componentMoved(ComponentEvent e) {}
	@Override
	public void componentShown(ComponentEvent e) {}
	@Override
	public void componentHidden(ComponentEvent e) {}
}
