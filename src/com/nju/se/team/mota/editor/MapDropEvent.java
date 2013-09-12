package com.nju.se.team.mota.editor;

import java.util.EventObject;

import com.nju.se.team.mota.game.unit.Unit;
/**
 * 地图拖拽事件
 * @author linkeo
 * @author lzw
 */
public class MapDropEvent extends EventObject {
	int x, y;
	/**
	 * 构造方法
	 * @param sourse 拖拽的Unit单元
	 * @param x 拖拽的到达位置横坐标
	 * @param y	拖拽的到达位置纵坐标
	 */
	public MapDropEvent(Unit sourse, int x, int y) {
		super(sourse);
		this.x = x;
		this.y = y;
	}
	/**
	 * 获取拖拽的Unit单元
	 * @return Unit
	 */
	public Unit getSource(){
		return (Unit) source;
	}
	/**
	 * 获取拖拽的到达位置横坐标
	 * @return 横坐标(int)
	 */
	public int getX() {
		return x;
	}
	/**
	 * 获取拖拽的到达位置纵坐标
	 * @return 纵坐标(int)
	 */
	public int getY() {
		return y;
	}
	private static final long serialVersionUID = 1L;


}
