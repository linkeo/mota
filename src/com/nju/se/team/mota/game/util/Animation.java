package com.nju.se.team.mota.game.util;

import java.awt.image.BufferedImage;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nju.se.team.mota.data.ImageLoader;
/**
 * 动画
 * @author linkeo
 * @author lzw
 *
 */
public class Animation {
	private String[][][] images;
	private int loopCount = 0;
	private HashSet<AnimationListener> animationListeners = new HashSet<AnimationListener>();
	/**
	 * 获取动画图片
	 * @return images(String[][][])
	 */
	public String[][][] getImages() {
		return images;
	}
	/**
	 * 获取动画图片
	 * @return images(String[][][])
	 */
	public void setImages(String[][][] images) {
		this.images = images;
	}
	int currentFrame;
	/**
	 * 构造函数(空)
	 */
	public Animation() {
		images = new String[0][0][0];
	}
	/**
	 * 构造函数
	 * @param frameCnt 帧数
	 * @param x 宽
	 * @param y 高
	 */
	public Animation(int frameCnt, int x, int y) {
		images = new String[frameCnt][x][y];
	}
	/**
	 * 更新帧数
	 */
	public void update(){
		if(currentFrame+1>=frameCount()){
			setLoopCount(getLoopCount() + 1);
			for(AnimationListener l : animationListeners)
				l.animationLooped();
		}
		currentFrame = (currentFrame+1)%frameCount();
	}
	/**
	 * 获取当前帧的图片
	 * @return BufferedImage[][]
	 */
	public BufferedImage[][] currImage(){
		return getImageAt(currentFrame);
	}
	/**
	 * 获取帧对应的图片
	 * @param frame
	 * @return BufferedImage[][]
	 */
	public BufferedImage[][] getImageAt(int frame){
		int y = 0, z=0;
		y = images[frame].length;
		if(y>0)z = images[frame][0].length;
		BufferedImage[][] bis = new BufferedImage[y][z];
		for(int i=0;i<y;++i)
			for(int j=0;j<z;++j)
				bis[i][j] = ImageLoader.get(images[frame][i][j]);
		return bis;
	}
	/**
	 * 获取帧对应图片的标识key
	 * @param frame
	 * @return String[][]
	 */
	public String[][] getImageKeyAt(int frame){
		int y = 0, z=0;
		y = images[frame].length;
		if(y>0)z = images[frame][0].length;
		String[][] bis = new String[y][z];
		for(int i=0;i<y;++i)
			for(int j=0;j<z;++j)
				bis[i][j] = images[frame][i][j];
		return bis;
	}
	/**
	 * 初始化帧
	 */
	public void init(){
		currentFrame = 0;
	}
	/**
	 * 从json加载帧数据
	 * @param json
	 */
	public void load(JSONObject json){
		JSONArray js = json.getJSONArray("images");
		int x = js.length();
		int y = 0, z=0;
		if(x>0)y = js.getJSONArray(0).length();
		if(y>0)z = js.getJSONArray(0).getJSONArray(0).length();
		images = new String[x][y][z];
		for(int i=0;i<x;++i){
			JSONArray subj = js.getJSONArray(i);
			for(int j=0;j<y;++j){
				JSONArray subsubj = subj.getJSONArray(j);
				for(int k=0;k<z;++k){
					images[i][j][k] = subsubj.getString(k);
				}
			}
		}
	}
	/**
	 * 创造一个具体的帧对象
	 * @param json
	 * @return Animation
	 */
	public static Animation make(JSONObject json){
		Animation a = new Animation();
		a.load(json);
		return a;
	}
	/**
	 * 帧克隆
	 * @return Animation
	 */
	public Animation copy(){
		return Animation.make(new JSONObject(this));
	}
	/**
	 * 获取帧的长度
	 * @return
	 */
	public int frameCount(){
		return images.length;
	}
	
	
	public void addListener(AnimationListener l){
		animationListeners.add(l);
	}
	public void removeListener(AnimationListener l){
		animationListeners.remove(l);
	}
	public int getLoopCount() {
		return loopCount;
	}
	public void setLoopCount(int loopCount) {
		this.loopCount = loopCount;
	}
}
