package com.nju.se.team.mota.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nju.se.team.mota.editor.uielem.MapElem;
import com.nju.se.team.mota.game.Level;
import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Unit;
/**
 * 地图容器
 * @author linkeo
 * @author lzw
 *
 */
public class MapPanel extends JPanel implements MapDropListener{

	private static final long serialVersionUID = 1L;
	int row,col;
	int floor;
	MapElem[][] blocks;
	Set<Unit> units = new HashSet<Unit>();
	MapItemListener mapItemListener;
	/**
	 * 构造方法
	 * @param row 地图大小_行数
	 * @param col 地图大小_列数
	 * @param floor 楼层
	 * @param mil 地图单元信息监听器
	 */
	public MapPanel(int row, int col, int floor, MapItemListener mil) {
		super(null);
		mapItemListener = mil;
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
				blocks[i][j].setMapItemListener(mapItemListener);
			}
		init();
	}
	/**
	 * 获取楼层
	 * @return floor(int)
	 */
	public int getFloor() {
		return floor;
	}
	/**
	 * 设置楼层
	 * @param floor(int)
	 */
	public void setFloor(int floor) {
		this.floor = floor;
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
				blocks[i][j].setMapItemListener(mapItemListener);
			}
		this.blocks = blocks;
		this.row = row;
		this.col = col;
		Rectangle rect = new Rectangle(0,0,col,row);
		for(Unit u : new HashSet<Unit>(units))
			if(!rect.contains(u.getRect()))
				units.remove(u);
	}
	/**
	 * 初始化所有地图单元
	 */
	public void init(){
		for(int i=0;i<col;++i)
			for(int j=0;j<row;++j)
				init(i,j);
	}
	/**
	 * 初始化地图单元
	 * @param x 单元横坐标
	 * @param y 单元纵坐标
	 */
	public void init(int x, int y){
		setUnitBackground(Abiotic.make("floor", x, y, floor), x, y);
	}
	/**
	 * 移除所有Unit对象
	 */
	public void removeAllUnit(){
		for(Unit u : new HashSet<Unit>(units))
			removeUnit(u);
	}
	/**
	 * 清空区域内所有对象
	 * @param rectangle
	 */
	private void clear_area(Rectangle rectangle){
		this.clear(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}
	/**
	 * 清空区域内所有对象
	 * @param x 起始横坐标
	 * @param y 起始纵坐标
	 * @param w 区域宽度
	 * @param h	区域高度
	 */
	private void clear(int x, int y, int w, int h){
		for(int i=x;i<x+w;++i)
			for(int j=y;j<y+h;++j)
				clear(i,j);
	}
	/**
	 * 清空一个单元的对象
	 * @param x 单元横坐标
	 * @param y 单元纵坐标
	 */
	public void clear(int x, int y){
		setUnit(null, x, y);
	}
	/**
	 * 添加Unit对象<br>
	 * Unit起始位置为(x,y)
	 * @param u Unit对象
	 * @param x 起始横左边
	 * @param y 起始纵坐标
	 */
	public void addUnit(Unit u, int x, int y){
		if(checkUnit(u, x, y)){
			setUnit(u, x, y);
			units.add(u);
		}
	}
	/**
	 * 移除Unit对象
	 * @param 
	 */
	public void removeUnit(Unit unit){
		units.remove(unit);
		clear_area(unit.getRect());
	}
	/**
	 * 检测是否可在单元添加Unit对象
	 * @param u Unit
	 * @param x 单元横坐标
	 * @param y 单元纵坐标
	 * @return
	 */
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
	/**
	 * 设置Unit对象<br>
	 * 从起始x和y开始按Unit尺寸填充地图单元
	 * @param u Unit对象
	 * @param x 起始横左边
	 * @param y	起始纵坐标
	 */
	public void setUnit(Unit u, int x, int y){
		u.setPosition(x, y);
		int w = u.getSize()[0];
		int h = u.getSize()[1];
		for(int i=x;i<x+w;++i)
			for(int j=y;j<y+h;++j)
				blocks[i][j].setUnit(u, i-x, j-y);
	}
	/**
	 * 设置Unit对象区域的背景
	 * @param u Unit对象
	 * @param x 起始横坐标
	 * @param y 起始纵坐标
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
	 * 处理拖拽
	 */
	@Override
	public void drop(MapDropEvent e) {
		Unit unit = e.getSource();
		addUnit(unit, e.getX(), e.getY());
	}
	JPanel columnHeader = new JPanel(null);
	/**
	 * 获取列数标识
	 * @return columnHeader
	 */
	public Component getColumnHeader() {
		return columnHeader;
	}
	JPanel rowHeader = new JPanel(null);
	/**
	 * 获取行数标识
	 * @return rowHeader
	 */
	public Component getRowHeader() {
		return rowHeader;
	}
	/**
	 * 加载楼层
	 * @param level
	 */
	public void loadLevel(Level level) {
		setFloor(level.getLevel());
		setGridSize(level.getSize()[0], level.getSize()[1]);
		for(Abiotic a : level.getAbiotics())
			addUnit(a, a.getPosition()[0], a.getPosition()[1]);
		for(Creature c : level.getCreatures())
			addUnit(c, c.getPosition()[0], c.getPosition()[1]);
	}
}
