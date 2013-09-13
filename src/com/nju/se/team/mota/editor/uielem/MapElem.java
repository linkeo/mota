package com.nju.se.team.mota.editor.uielem;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nju.se.team.mota.editor.MapDropEvent;
import com.nju.se.team.mota.editor.MapDropListener;
import com.nju.se.team.mota.editor.MapItemListener;
import com.nju.se.team.mota.editor.dnd.DndHandler;
import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Unit;
import com.nju.se.team.mota.game.util.TypeEnum;
import com.nju.se.team.mota.game.util.UnitStatus;

/**
 * 地图的UI单元
 * @author linkeo
 * @author lzw
 * 
 *
 */
public class MapElem extends JPanel implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	private Set<MapDropListener> mapDropListeners = new HashSet<MapDropListener>();
	Unit unit;
	TypeEnum type;
	int x,  y;
//	int floor;
	private JLabel back, front;
	/**
	 * 构造方法
	 * @param x 单元横坐标
	 * @param y 单元纵坐标
	 */
	public MapElem(int x, int y){
		super(null);
		this.x = x;
		this.y = y;
		this.setSize(32, 32);
		this.setPreferredSize(getSize());
		back = new JLabel();
		front = new JLabel();
		add(front);
		add(back);
		back.setSize(32, 32);
		back.setPreferredSize(getSize());
		front.setSize(32, 32);
		front.setPreferredSize(getSize());
//		DndHandler.addUnitDropTarget(this);
		DndHandler.addMapUnitDropTarget(this);
		DndHandler.addMapUnitDragSource(this);
		this.addMouseListener(this);
	}
	public int getRow(){return y;}
	public int getCol(){return x;}
	/**
	 * 
	 * @param u
	 * @param x 当前区域在单位贴图中的x轴位置
	 * @param y 当前区域在单位贴图中的y轴位置
	 */
	public void setUnit(Unit u, int x, int y){
		unit = u;
		if(u==null){
			front.setIcon(null);
		}else{
			if(u instanceof Abiotic)
				type = TypeEnum.ABIOTIC;
			if(u instanceof Creature)
				type = TypeEnum.CREATURE;
			front.setIcon(new ImageIcon(u.getSprites().get(UnitStatus.NORMAL).currImage()[x][y]));
		}
	}
	/**
	 * 设置单元对应的背景对象
	 * @param u 新的背景Unit对象
	 * @param x 地图单元对应的Unit背景对象的1x1子对象的横坐标
	 * @param y 地图单元对应的Unit背景对象的1x1子对象的纵坐标
	 */
	public void setUnitBackground(Unit u, int x, int y) {
		if(u instanceof Abiotic)
			type = TypeEnum.ABIOTIC;
		if(u instanceof Creature)
			type = TypeEnum.CREATURE;
		back.setIcon(new ImageIcon(u.getSprites().get(UnitStatus.NORMAL).currImage()[x][y]));
//		back.setBackground(Color.GRAY);
//		back.setOpaque(true);
	}
	/**
	 * 获取地图单元对应的Unit对象
	 * @return Unit对象
	 */
	public Unit getUnit(){
		return unit;
	}
	/**
	 * 获取该Unit对象的具体类型
	 * @return Unit子类型
	 */
	public TypeEnum getType(){
		return type;
	}
	/**
	 * 添加拖拽Listener
	 * @param mapDroplistener
	 */
	public void addMapDropListener(MapDropListener mapDroplistener){
		mapDropListeners.add(mapDroplistener);
	}
	/**
	 * 删除拖拽Listener
	 * @param mapDroplistener
	 */
	public void removeMapDropListener(MapDropListener mapDroplistener){
		mapDropListeners.remove(mapDroplistener);
	}
	/**
	 * 拖拽处理
	 * @param u Unit对象
	 */
	public void dropCopy(Unit u){
		MapDropEvent e = new MapDropEvent(u, x, y);
		for(MapDropListener l : mapDropListeners)
			l.dropCopy(e);
	}
	public void dropMove(Unit u) {
		MapDropEvent e = new MapDropEvent(u, x, y);
		for(MapDropListener l : mapDropListeners)
			l.dropMove(e);
	}
	MapItemListener mil;
	/**
	 * 设置点击读取该Unit对象的Listener
	 * @param mapItemListener
	 */
	public void setMapItemListener(MapItemListener mapItemListener) {
		mil = mapItemListener;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(mil!=null)
			mil.mapItemSelected(getUnit());
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		highlight();
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		highlight();
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void highlight(){
		if(mil!=null)
			mil.mapItemHighlighted(this);
	}
}
