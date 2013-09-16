package com.nju.se.team.mota.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.json.JSONObject;

import com.nju.se.team.mota.game.Save;

/**
 * 读取并维护所有存档文件<br>
 * P.S.使用 <b>单件模式</b><br>
 * @author Linkeo
 * @author lzw
 *
 */
public class SaveLoader {
	private HashSet<File> saveFiles = new HashSet<File>();
	private HashSet<Save> saves = new HashSet<Save>();
	private static SaveLoader instance;
	private SaveLoader() {
	}
	private static void load(){
		if(instance==null)instance = new SaveLoader();
		File dir = new File("save/");
		instance.saveFiles.clear();
		for(File f : dir.listFiles())
			if(f.isFile()&&f.getName().matches(".*\\.save"))
				instance.saveFiles.add(f);
		instance.saves.clear();
		for(File f : instance.saveFiles){
			JSONObject o = new JSONObject(getFileContent(f));
			Save save = Save.make(o);
			instance.saves.add(save);
		}
	}
	public static Save getDefaultSave(){
		File def = ResLoader.getDataFile("save");
		JSONObject defo = new JSONObject(getFileContent(def));
		Save save = Save.make(defo);
		return save;
	}
	/**
	 * 获得所有存档
	 * @return 含有所有存档对象的集合, 无内容则返回空集而不是null
	 */
	public static Set<Save> getAllSaves(){
		load();
		return instance.saves;
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
}
