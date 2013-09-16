package com.nju.se.team.mota.data;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import javax.imageio.ImageIO;

/**
 * 读取并维护所有图片素材<br>
 * 已将图片切割处理(见load())<br>
 * P.S.使用 <b>单件模式</b><br>
 * @author Linkeo
 * @author lzw
 *
 */
public class ImageLoader {
	/**
	 * 游戏图片素材的<索引键-图片对象>映射表<br>
	 * 相关方法: getKeySet(), get(String key)
	 */
	private HashMap<String,BufferedImage> imageMap = new HashMap<String,BufferedImage>();
	private ImageLoader() {
	}
	private static ImageLoader instance;
	/**
	 * 方法功能: 从资源文件映射集中读取图片资源文件并做切割处理, 
	 * 并分配到的图片映射表中<br>
	 * <b>注: 此方法会在此类第一次使用时被调用一次.</b>
	 */
	private static void load(){
		if(instance == null)
			instance = new ImageLoader();
		for(String s : ResLoader.getSpriteKeySet()){
			BufferedImage bi;
			try {
				bi = ImageIO.read(ResLoader.getSpriteFile(s));
				if(bi.getWidth()%32!=0||bi.getHeight()%32!=0){
					continue;
				}
				int w = bi.getWidth()/32;
				int h = bi.getHeight()/32;
				for(int i = 0; i < w ; ++i)
					for(int j = 0; j < h ; ++j){
						instance.imageMap.put(s+'_'+j+"x"+i,bi.getSubimage(i*32, j*32, 32, 32));
					}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 用特定的索引键获取图片对象<br>
	 * @param key 图片的索引键
	 * @return 如果存在此键,则返回键对应的图片对象(不保证非空)<br>
	 * 如果不存在此键,则返回null
	 */
	public static BufferedImage get(String key){
		if(instance==null)
			load();
		return instance.imageMap.get(key);
	}
	/**
	 * 获取所有图片素材的索引键
	 * @return 含有所有索引键的集合(Set), 可能为空集
	 */
	public static Set<String> getKeySet(){
		if(instance==null)
			load();
		return instance.imageMap.keySet();
	}
	
}