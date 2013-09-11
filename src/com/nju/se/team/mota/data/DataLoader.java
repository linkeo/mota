package com.nju.se.team.mota.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;

/**
 * 读取并维护所有游戏内容定义<br>
 * 包括生物类型定义, 非生物(静物)类型定义, 楼层地图和对象定义<br>
 * P.S.使用 <b>单件模式</b><br>
 * @author Linkeo
 * @author lzw
 *
 */
public class DataLoader {
	/**
	 * 所有楼层的<楼层数-JSON对象>的映射表
	 */
	private HashMap<Integer,JSONObject> levelMap = new HashMap<Integer,JSONObject>();

	/**
	 * 所有生物的<种类名-JSON对象>的映射表
	 */
	private HashMap<String,JSONObject> creatureMap = new HashMap<String,JSONObject>();

	/**
	 * 所有静物(非生物)的<种类名-JSON对象>的映射表
	 */
	private HashMap<String,JSONObject> abioticMap = new HashMap<String,JSONObject>();
	private DataLoader() {
	}
	private static DataLoader instance;
	/**
	 * 从资源文件映射集读取楼层, 生物, 静物的定义文件, 读取为JSON对象 并分配到相应的映射表中
	 */
	private static void load(){
		if(instance == null)
			instance = new DataLoader();
		JSONArray cs = new JSONArray(getFileContent(ResLoader.getDataFile("creatures")));
		for(int i=0;i<cs.length();++i){
			instance.creatureMap.put(cs.getJSONObject(i).getString("type"), cs.getJSONObject(i));
		}
		JSONArray as = new JSONArray(getFileContent(ResLoader.getDataFile("abiotics")));
		for(int i=0;i<as.length();++i){
			instance.abioticMap.put(as.getJSONObject(i).getString("type"), as.getJSONObject(i));
		}
		JSONArray ls = new JSONArray(getFileContent(ResLoader.getDataFile("levels")));
		for(int i=0;i<ls.length();++i){
			instance.levelMap.put(ls.getJSONObject(i).getInt("level"), ls.getJSONObject(i));
		}
	}
	/**
	 * 此方法从文件中读取全部内容(以字符串的形式)
	 * @param textfile 文件对象, 不会抛出异常, 所以<b>必须保证文件可读</b>.
	 * @return 返回包含文件全部内容的字符串
	 */
	private static String getFileContent(File textfile){
		StringBuilder sb = new StringBuilder();
		Scanner scanner;
		try {
			scanner = new Scanner(textfile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		while(scanner.hasNextLine()){
			sb.append(scanner.nextLine());
			sb.append('\n');
		}
		scanner.close();
		return sb.toString();
	}
	public static void saveFileContent(File textfile, String content){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(textfile));
			bw.write(content);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 获取所有生物类型名
	 * @return 含有所有生物类型名的集合, 无内容则为空集(并非null)
	 */
	public static Set<String> getCreatureTypes(){
		if(instance==null)load();
		return instance.creatureMap.keySet();
	}
	/**
	 * 获取所有非生物类型名
	 * @return 含有所有非生物类型名的集合, 无内容则为空集(并非null)
	 */
	public static Set<String> getAbioticTypes(){
		if(instance==null)load();
		return instance.abioticMap.keySet();
	}
	/**
	 * 获取所有楼层的层数, 层数并非一定连续
	 * @return 含有所有层数的集合, 无内容则为空集(并非null)
	 */
	public static Set<Integer> getLevelFloors(){
		if(instance==null)load();
		return instance.levelMap.keySet();
	}
	/**
	 * 以类型名获得生物类型的JSON定义
	 * @param type 生物类型名
	 * @return 生物类型定义的JSON对象, 不存在则返回null
	 */
	public static JSONObject getCreatureDefine(String type){
		if(instance==null)load();
		return instance.creatureMap.get(type);
	}
	/**
	 * 以类型名获得非生物类型的JSON定义
	 * @param type 非生物类型名
	 * @return 非生物类型定义的JSON对象, 不存在则返回null
	 */
	public static JSONObject getAbioticDefine(String type){
		if(instance==null)load();
		return instance.abioticMap.get(type);
	}
	/**
	 * 以楼层层数获得楼层的JSON定义
	 * @param floor 楼层层数
	 * @return 楼层定义的JSON对象, 不存在则返回null
	 */
	public static JSONObject getLevelDefine(int floor){
		if(instance==null)load();
		return instance.levelMap.get(floor);
	}
	
	/**
	 * 保存所有非生物定义到文件
	 */
	public static void saveAbiotics(){
		JSONArray j = new JSONArray(instance.abioticMap.values());
		String str = j.toString(4);
		saveFileContent(ResLoader.getDataFile("abiotics"), str);
	}
	/**
	 * 保存所有生物定义到文件
	 */
	public static void saveCreatures(){
		JSONArray j = new JSONArray(instance.creatureMap.values());
		String str = j.toString(4);
		saveFileContent(ResLoader.getDataFile("creatures"), str);
	}


	/**
	 * 在生物定义映射表中更新(添加/修改)键值对
	 * @param c 要更新的生物对象
	 */
	public static void putCreatureType(Creature c){
		String type = c.getType();
		JSONObject def = new JSONObject(c.parseTypeJSON().toString());
		instance.creatureMap.put(type, def);
	}
	/**
	 * 在生物定义映射表中移除键值对
	 * @param c 要移除的生物对象
	 */
	public static void removeCreatureType(Creature c){
		String type = c.getType();
		instance.creatureMap.remove(type);
	}
	/**
	 * 在非生物定义映射表中更新(添加/修改)键值对
	 * @param a 要更新的非生物对象
	 */
	public static void putAbioticType(Abiotic a){
		String type = a.getType();
		JSONObject def = new JSONObject(a.parseTypeJSON().toString());
		instance.abioticMap.put(type, def);
	}
	/**
	 * 在非生物定义映射表中移除键值对
	 * @param a 要移除的非生物对象
	 */
	public static void removeAbioticType(Abiotic a){
		String type = a.getType();
		instance.abioticMap.remove(type);
	}
}

