package com.nju.se.team.mota.data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 这个类负责读取资源文件.<br>
 * 主要供其他Loader使用.<br>
 * P.S.使用 <b>单件模式</b><br>
 * @author Linkeo
 * @author lzw
 *
 */
public class ResLoader {
	/**
	 * 游戏内容定义文件的<索引键-文件对象>映射表<br>
	 * 相关方法: getDataKeySet(), getDataFile(String key)
	 */
	private HashMap<String,File> datamap = new HashMap<String,File>();
	/**
	 * 游戏图片素材文件的<索引键-文件对象>映射表<br>
	 * 相关方法: getImageKeySet(), getImageFile(String key)
	 */
	private HashMap<String,File> imagemap = new HashMap<String,File>();
	private static ResLoader instance;
	private ResLoader() {
	}
	/**
	 * 方法功能: 从资源列表文件(默认为res.list)中读取资源文件映射集, 
	 * 并分配到不同类型的映射表中<br>
	 * <b>注: 此方法会在此类第一次使用时被调用一次.</b>
	 */
	private static void load(){
		if(instance==null)
			instance = new ResLoader();
		IniEditor ie = new IniEditor();
		try {
			ie.load("res.list");
		} catch (IOException e) {
			System.err.println("资源文件缺失");
		}

		List<String> datakeys = ie.optionNames("data");
		for(String dk : datakeys)
			instance.datamap.put(dk,new File(ie.get("data", dk)));
		
		List<String> imagekeys = ie.optionNames("image");
		for(String ik : imagekeys)
			instance.imagemap.put(ik,new File(ie.get("image", ik)));
	}
	/**
	 * 用特定的索引键获取游戏内容定义的文件对象<br>
	 * @param key 文件的索引键
	 * @return 如果存在此键,则返回键对应的文件对象(不保证非空)<br>
	 * 如果不存在此键,则返回null
	 */
	public static File getDataFile(String key){
		if(instance==null)
			load();
		return instance.datamap.get(key);
	}
	/**
	 * 用特定的索引键获取图片素材的文件对象<br>
	 * @param key 文件的索引键
	 * @return 如果存在此键,则返回键对应的文件对象(不保证非空)<br>
	 * 如果不存在此键,则返回null
	 */
	public static File getImageFile(String key){
		if(instance==null)
			load();
		return instance.imagemap.get(key);
	}
	/**
	 * 获取所有游戏定义文件的索引键
	 * @return 含有所有索引键的集合(Set), 可能为空集
	 */
	public static Set<String> getDataKeySet(){
		if(instance==null)
			load();
		return instance.datamap.keySet();
	}
	/**
	 * 获取所有图片素材文件的索引键
	 * @return 含有所有索引键的集合(Set), 可能为空集
	 */
	public static Set<String> getImageKeySet(){
		if(instance==null)
			load();
		return instance.imagemap.keySet();
	}
	
}
