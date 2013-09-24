package com.nju.se.team.mota.game.unit;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.nju.se.team.mota.game.util.Animation;
import com.nju.se.team.mota.game.util.Condition;
import com.nju.se.team.mota.game.util.UnitStatus;
import com.nju.se.team.mota.util.ImageHandler;
/**
 * 游戏中的对象
 * @author linkeo
 * @author lzw
 */
public class Unit implements Comparable<Unit>{
	private String name;
	private String type;
	private HashMap<UnitStatus, Animation> sprites;
	private HashMap<Condition, String> action;
	private UnitStatus currStatus;
	private int position[];
	private int size[];
	private int floor;
	private String buddy;
	private String buddyType;
	/**
	 * 构造方法<br>
	 * 设置默认值
	 */
	public Unit() {
		name = "new";
		sprites = new HashMap<UnitStatus, Animation>();
		action = new HashMap<Condition, String>();
		position = new int[]{1,1};
		size = new int[]{1,1};
		setCurrStatus(UnitStatus.NORMAL);
	}
	public Animation currAnimation(){
		return sprites.get(getCurrStatus());
	}
	/**
	 * 获取名称
	 * @return name(String)
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置名称
	 * @param name(String)
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取类型
	 * @return type(String)
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置类型
	 * @param type(String)
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取动画
	 * @return sprites(Map)
	 */
	public HashMap<UnitStatus, Animation> getSprites() {
		return sprites;
	}
	/**
	 * 设置动画
	 * @param sprites(Map)
	 */
	public void setSprites(HashMap<UnitStatus, Animation> sprites) {
		this.sprites = sprites;
	}
	/**
	 * 获取对象行为
	 * @return action(Map)
	 */
	public HashMap<Condition, String> getAction() {
		return action;
	}
	/**
	 * 设置对象行为
	 * @param action(Map)
	 */
	public void setAction(HashMap<Condition, String> action) {
		this.action = action;
	}
	/**
	 * 获取坐标
	 * @return position(int[])
	 */
	public int[] getPosition() {
		return position;
	}
	/**
	 * 设置坐标
	 * @param position(int[])
	 */
	public void setPosition(int[] position) {
		this.position = position;
	}
	/**
	 * 获取尺寸
	 * @return size(int[])
	 */
	public int[] getSize() {
		return size;
	}
	/**
	 * 设置尺寸
	 * @param size(int[])
	 */
	public void setSize(int[] size) {
		this.size = size;
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
	/**
	 * 获取伙伴名
	 * @return buddy(String)
	 */
	public String getBuddy() {
		return buddy;
	}
	/**
	 * 设置伙伴名
	 * @param buddy(String)
	 */
	public void setBuddy(String buddy) {
		this.buddy = buddy;
	}
	/**
	 * 获取伙伴类型
	 * @return type(String)
	 */
	public String getBuddyType() {
		return buddyType;
	}
	/**
	 * 设置伙伴类型
	 * @param buddyType(String)
	 */
	public void setBuddyType(String buddyType) {
		this.buddyType = buddyType;
	}
	/**
	 * 获取实体矩形<br>
	 * 起始坐标,尺寸
	 * @return Rectangle
	 */
	public Rectangle rectangle(){
		return new Rectangle(position[0], position[1], size[0], size[1]);
	}
	/**
	 * 设置坐标
	 * @param x 横坐标
	 * @param y 纵坐标
	 */
	public void setPosition(int x, int y) {
		position[0] = x;
		position[1] = y;
	}
	/**
	 * 设置尺寸
	 * @param w 宽
	 * @param h 高
	 */
	public void setSize(int w, int h) {
		size[0] = w;
		size[1] = h;
	}
	/**
	 * 获取对象克隆
	 * @return Unit
	 */
	public Unit clone(){
		Unit u = new Unit();
		u.setName(getName());
		u.setType(getType());
		u.setSprites(new HashMap<UnitStatus, Animation>(getSprites()));
		u.setAction(new HashMap<Condition, String>(getAction()));
		u.setPosition(getPosition().clone());
		u.setSize(getSize().clone());
		u.setFloor(getFloor());
		u.setBuddy(getBuddy());
		u.setBuddyType(getBuddyType());
		return u;
	}
	@Override
	public int compareTo(Unit o) {
		return getName().compareToIgnoreCase(o.getName());
	}
	
	public BufferedImage thumbnail(int w, int h){
		BufferedImage bi = new BufferedImage(size[0]*32, size[1]*32, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = ImageHandler.getHQGraphics(bi);
		BufferedImage[][] bis = this.getSprites().get(UnitStatus.NORMAL).currImage();
		for(int i=0;i<size[1];++i)
			for(int j=0;j<size[0];++j){
				g.drawImage(bis[j][i], 32*j, 32*i, null);
			}
		g.dispose();
		return ImageHandler.zoomFully(bi, w, h);
	}
	public UnitStatus getCurrStatus() {
		return currStatus;
	}
	public void setCurrStatus(UnitStatus currStatus) {
		this.currStatus = currStatus;
	}
}
