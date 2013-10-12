package com.nju.se.team.mota.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nju.se.team.mota.data.LevelLoader;
import com.nju.se.team.mota.editor.uielem.MapElem;
import com.nju.se.team.mota.game.Level;
import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Unit;
/**
 * ��ͼ����
 * @author linkeo
 * @author lzw
 *
 */
public class MapPanel extends JPanel implements MapDropListener, MouseMotionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int row,col;
//	int floor;
	MapElem[][] blocks;
//	Set<Unit> units = new HashSet<Unit>();
	MapItemListener mapItemListener;
	private Level currLevel;
	/**
	 * ���췽��
	 * @param row ��ͼ��С_����
	 * @param col ��ͼ��С_����
	 * @param floor ¥��
	 * @param mil ��ͼ��Ԫ��Ϣ������
	 */
	public MapPanel(Level l, MapItemListener mil) {
		super(null);
		mapItemListener = mil;
		this.row = l.getSize()[1];
		this.col = l.getSize()[0];
//		this.floor = floor;
		blocks = new MapElem[col][row];
		this.setMySize(col, row);
		for(int i=0;i<col;++i)
			for(int j=0;j<row;++j){
				blocks[i][j] = new MapElem(i, j);
				blocks[i][j].setBounds(i*32, j*32, 32, 32);
				add(blocks[i][j]);
				blocks[i][j].addMapDropListener(this);
				blocks[i][j].setMapItemListener(mapItemListener);
			}
		loadLevel(l);
		init();
		addMouseMotionListener(this);
	}
//	/**
//	 * ��ȡ¥��
//	 * @return floor(int)
//	 */
//	public int getFloor() {
//		return floor;
//	}
//	/**
//	 * ����¥��
//	 * @param floor(int)
//	 */
//	public void setFloor(int floor) {
//		this.floor = floor;
//	}
	private void setMySize(int gridw, int gridh) {
		this.setSize(gridw*32, gridh*32);
		this.setPreferredSize(getSize());
		columnHeader.removeAll();
		for(int i=0;i<gridw;++i){
			JLabel l = new JLabel(Integer.toString(i),JLabel.CENTER);
			l.setBounds(i*32, 0, 32, 32);
			l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			l.setBackground(Color.YELLOW);
			columnHeader.add(l,i);
		}
		columnHeader.setSize(getSize().width, 32);
		columnHeader.setPreferredSize(columnHeader.getSize());
		rowHeader.removeAll();
		for(int i=0;i<gridh;++i){
			JLabel l = new JLabel(Integer.toString(i),JLabel.CENTER);
			l.setBounds(0, i*32, 32, 32);
			l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			l.setBackground(Color.YELLOW);
			rowHeader.add(l,i);
		}
		rowHeader.setSize(32, getSize().height);
		rowHeader.setPreferredSize(rowHeader.getSize());
	}
	public void setGridSize(int x, int y){
		this.setMySize(x, y);
		removeAll();
		MapElem[][] blocks = new MapElem[x][y];
		for(int i=0;i<x;++i)
			for(int j=0;j<y;++j){
				if(i>=this.col||j>=this.row){
					blocks[i][j] = new MapElem(i, j);
				}else
					blocks[i][j] = this.blocks[i][j];
				blocks[i][j].setBounds(i*32, j*32, 32, 32);
				add(blocks[i][j]);
				blocks[i][j].addMapDropListener(this);
				blocks[i][j].setMapItemListener(mapItemListener);
			}
		this.blocks = blocks;
		this.row = y;
		this.col = x;
		init();
		for(Unit u : new HashSet<Unit>(getUnits()))
			if(!rectangle().contains(u.rectangle()))
				removeUnit(u);
		currLevel.setSize(new int[]{x,y});
	}
	
	Rectangle rect = new Rectangle(0,0,col,row);
	public Rectangle rectangle(){
		if(col!=rect.width||row!=rect.height)
			rect = new Rectangle(0,0,col,row);
		return rect;
	}
	/**
	 * ��ʼ�����е�ͼ��Ԫ
	 */
	public void init(){
		for(int i=0;i<col;++i)
			for(int j=0;j<row;++j)
				init(i,j);
		
	}
	/**
	 * ��ʼ����ͼ��Ԫ
	 * @param x ��Ԫ������
	 * @param y ��Ԫ������
	 */
	public void init(int x, int y){
		setUnitBackground(Abiotic.make("����", x, y, 0), x, y);
	}
	/**
	 * �Ƴ�����Unit����
	 */
	public void removeAllUnit(){
		for(Unit u : new HashSet<Unit>(getUnits()))
			removeUnit(u);
	}
	/**
	 * ������������ж���
	 * @param rectangle
	 */
	private void clear_area(Rectangle rectangle){
		Rectangle r = rectangle.intersection(rectangle());
		this.clear(r.x, r.y, r.width, r.height);
	}
	/**
	 * ������������ж���
	 * @param x ��ʼ������
	 * @param y ��ʼ������
	 * @param w ������
	 * @param h	����߶�
	 */
	private void clear(int x, int y, int w, int h){
		for(int i=x;i<x+w;++i)
			for(int j=y;j<y+h;++j)
				clear(i,j);
	}
	/**
	 * ���һ����Ԫ�Ķ���
	 * @param x ��Ԫ������
	 * @param y ��Ԫ������
	 */
	public void clear(int x, int y){
		setUnit(null, x, y);
	}
	/**
	 * ���Unit����<br>
	 * Unit��ʼλ��Ϊ(x,y)
	 * @param unit Unit����
	 * @param x ��ʼ�����
	 * @param y ��ʼ������
	 */
	public void addUnit(Unit unit, int x, int y){
		if(unit instanceof Abiotic)
			addUnit((Abiotic)unit, x, y);
		if(unit instanceof Creature)
			addUnit((Creature)unit, x, y);
	}
	public void addUnit(Abiotic u, int x, int y){
		if(checkUnit(u, x, y)){
			setUnit(u, x, y);
			currLevel.getAbiotics().add(u);
		}
	}
	public void addUnit(Creature u, int x, int y){
		if(checkUnit(u, x, y)){
			setUnit(u, x, y);
			currLevel.getCreatures().add(u);
		}
	}
	private void moveUnit(Unit u, int x, int y) {
		if(checkUnit(u, x, y)){
			removeUnit(u);
			addUnit(u, x, y);
		}
	}
	/**
	 * �Ƴ�Unit����
	 * @param 
	 */
	public void removeUnit(Unit unit){
		if(unit instanceof Abiotic)
			removeUnit((Abiotic)unit);
		if(unit instanceof Creature)
			removeUnit((Creature)unit);
	}
	public void removeUnit(Abiotic unit){
		currLevel.getAbiotics().remove(unit);
		clear_area(unit.rectangle());
	}
	public void removeUnit(Creature unit){
		currLevel.getCreatures().remove(unit);
		clear_area(unit.rectangle());
	}
	
	public boolean checkUnit(Unit u, int x, int y){
		int w = u.getSize()[0];
		int h = u.getSize()[1];
		if(x<0||y<0){
			return false;
		}
		if(x+w>col||y+h>row){
			return false;
		}
		Rectangle r = new Rectangle(x, y, w, h);
		for(Unit unit : getUnits())
			if(unit!=u&&unit.rectangle().intersects(r)){
				return false;
			}
		return true;
	}
	private Set<Unit> getUnits() {
		Set<Unit> res = new HashSet<Unit>();
		res.addAll(currLevel.getAbiotics());
		res.addAll(currLevel.getCreatures());
		return res;
	}
	/**
	 * ����Unit����<br>
	 * ����ʼx��y��ʼ��Unit�ߴ�����ͼ��Ԫ
	 * @param u Unit����
	 * @param x ��ʼ�����
	 * @param y	��ʼ������
	 */
	public void setUnit(Unit u, int x, int y){
		if(u!=null){
			u.setPosition(x, y);
			int w = u.getSize()[0];
			int h = u.getSize()[1];
			for(int i=x;i<x+w;++i)
				for(int j=y;j<y+h;++j)
					blocks[i][j].setUnit(u, i-x, j-y);
		}else
			blocks[x][y].setUnit(u, 0, 0);
		
	}
	/**
	 * ����Unit��������ı���
	 * @param u Unit����
	 * @param x ��ʼ������
	 * @param y ��ʼ������
	 */
	private void setUnitBackground(Unit u, int x, int y) {
		u.setPosition(x, y);
		int w = u.getSize()[0];
		int h = u.getSize()[1];
		for(int i=x;i<x+w;++i)
			for(int j=y;j<y+h;++j)
				blocks[i][j].setUnitBackground(u, i-x, j-y);
	}
	/**
	 * ������ק
	 */
	@Override
	public void dropCopy(MapDropEvent e) {
		Unit unit = e.getSource();
		unit = unit.clone();
		unit.setName(LevelLoader.randomName(unit.getType(), currLevel));
		addUnit(unit, e.getX(), e.getY());
	}
	@Override
	public void dropMove(MapDropEvent e) {
		Unit unit = e.getSource();
		moveUnit(unit, e.getX(), e.getY());
	}
	JPanel columnHeader = new JPanel(null);
	/**
	 * ��ȡ������ʶ
	 * @return columnHeader
	 */
	public Component getColumnHeader() {
		return columnHeader;
	}
	JPanel rowHeader = new JPanel(null);
	/**
	 * ��ȡ������ʶ
	 * @return rowHeader
	 */
	public Component getRowHeader() {
		return rowHeader;
	}
	/**
	 * ����¥��
	 * @param level
	 */
	public void loadLevel(Level level) {
		currLevel = level;
		clear(0,0,col,row);
		setGridSize(level.getSize()[0], level.getSize()[1]);
		for(Abiotic a : level.getAbiotics())
			addUnit(a, a.getPosition()[0], a.getPosition()[1]);
		for(Creature c : level.getCreatures())
			addUnit(c, c.getPosition()[0], c.getPosition()[1]);
		init();
	}
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		Point pos = e.getPoint();
		if(!(pos.x/32<col&&pos.y/32<row))
				unlighlight();
	}
	private void unlighlight() {
		for(Component c : columnHeader.getComponents())
			((JLabel)c).setOpaque(false);
		for(Component c : rowHeader.getComponents())
			((JLabel)c).setOpaque(false);
		rowHeader.repaint();
		columnHeader.repaint();
	}
	public void highlight(MapElem mapElem) {
		for(Component c : columnHeader.getComponents())
			((JLabel)c).setOpaque(false);
		if(mapElem.getCol()<col){
			((JLabel)columnHeader.getComponent(mapElem.getCol())).setOpaque(true);
		}
		for(Component c : rowHeader.getComponents())
			((JLabel)c).setOpaque(false);
		if(mapElem.getRow()<row){
			((JLabel)rowHeader.getComponent(mapElem.getRow())).setOpaque(true);
		}
		rowHeader.repaint();
		columnHeader.repaint();
	}
	public Level getCurrLevel() {
		return currLevel;
	}
	public void setCurrLevel(Level currLevel) {
		this.currLevel = currLevel;
	}
	
}
