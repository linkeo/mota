package com.nju.se.team.mota.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Unit;

public class MapPanel extends JPanel implements MapDropListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int row,col;
	int floor;
	MapElem[][] blocks;
	Set<Unit> units = new HashSet<Unit>();
	public MapPanel(int row, int col, int floor) {
		super(null);
		this.row = row;
		this.col = col;
		this.floor = floor;
		blocks = new MapElem[col][row];
		this.setMySize(col, row);
		for(int i=0;i<col;++i)
			for(int j=0;j<row;++j){
				blocks[i][j] = new MapElem(i, j, floor);
				blocks[i][j].setBounds(i*32, j*32, 32, 32);
				add(blocks[i][j]);
				blocks[i][j].addMapDropListener(this);
			}
		clear(0,0,col,row);
	}
	private void setMySize(int gridw, int gridh) {
		this.setSize(gridw*32, gridh*32);
		this.setPreferredSize(getSize());
		columnHeader.removeAll();
		for(int i=0;i<gridw;++i){
			JLabel l = new JLabel(Integer.toString(i),JLabel.CENTER);
			l.setBounds(i*32, 0, 32, 32);
			l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			columnHeader.add(l);
		}
		columnHeader.setSize(getSize().width, 32);
		columnHeader.setPreferredSize(columnHeader.getSize());
		rowHeader.removeAll();
		for(int i=0;i<gridh;++i){
			JLabel l = new JLabel(Integer.toString(i),JLabel.CENTER);
			l.setBounds(0, i*32, 32, 32);
			l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			rowHeader.add(l);
		}
		rowHeader.setSize(32, getSize().height);
		rowHeader.setPreferredSize(rowHeader.getSize());
	}
	public void setGridSize(int row, int col){
		this.setMySize(col, row);
		removeAll();
		MapElem[][] blocks = new MapElem[col][row];
		for(int i=0;i<col;++i)
			for(int j=0;j<row;++j){
				if(i>=this.col||j>=this.row)
					blocks[i][j] = new MapElem(i, j, floor);
				else
					blocks[i][j] = this.blocks[i][j];
				blocks[i][j].setBounds(i*32, j*32, 32, 32);
				add(blocks[i][j]);
				blocks[i][j].addMapDropListener(this);
			}
		this.blocks = blocks;
		this.row = row;
		this.col = col;
		Rectangle rect = new Rectangle(0,0,col,row);
		for(Unit u : new HashSet<Unit>(units))
			if(!rect.contains(u.getRect()))
				units.remove(u);
	}
	public void init(int x, int y){
		setUnitBackground(Abiotic.make("floor", x, y, floor), x, y);
	}
	public void clear(Rectangle r){
		this.clear(r.x, r.y, r.width, r.height);
	}
	public void clear(int x, int y, int w, int h){
		for(int i=x;i<x+w;++i)
			for(int j=y;j<y+h;++j)
				init(i,j);
	}
	public void addUnit(Unit u, int x, int y){
		if(checkUnit(u, x, y)){
			setUnit(u, x, y);
			units.add(u);
		}
	}
	public void removeUnit(Unit u){
		units.remove(u);
		clear(u.getRect());
	}
	
	public boolean checkUnit(Unit u, int x, int y){
		int w = u.getSize()[0];
		int h = u.getSize()[1];
		if(x<0||y<0) return false;
		if(x+w>=col||y+h>=row) return false;
		for(Unit unit : units)
			if(unit.getRect().intersects(u.getRect()))
				return false;
		return true;
	}
	public void setUnit(Unit u, int x, int y){
		u.setPosition(x, y);
		int w = u.getSize()[0];
		int h = u.getSize()[1];
		for(int i=x;i<x+w;++i)
			for(int j=y;j<y+h;++j)
				blocks[i][j].setUnit(u, i-x, j-y);
	}
	private void setUnitBackground(Unit u, int x, int y) {
		u.setPosition(x, y);
		int w = u.getSize()[0];
		int h = u.getSize()[1];
		for(int i=x;i<x+w;++i)
			for(int j=y;j<y+h;++j)
				blocks[i][j].setUnitBackground(u, i-x, j-y);
	}
	@Override
	public void drop(MapDropEvent e) {
		Unit unit = e.getSource();
		addUnit(unit, e.getX(), e.getY());
	}
	JPanel columnHeader = new JPanel(null);
	public Component getColumnHeader() {
		return columnHeader;
	}
	JPanel rowHeader = new JPanel(null);
	public Component getRowHeader() {
		return rowHeader;
	}
}
