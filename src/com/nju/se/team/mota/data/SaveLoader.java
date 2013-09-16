package com.nju.se.team.mota.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.json.JSONObject;

import com.nju.se.team.mota.game.Save;

/**
 * ��ȡ��ά�����д浵�ļ�<br>
 * P.S.ʹ�� <b>����ģʽ</b><br>
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
	 * ������д浵
	 * @return �������д浵����ļ���, �������򷵻ؿռ�������null
	 */
	public static Set<Save> getAllSaves(){
		load();
		return instance.saves;
	}
	/**
	 * �˷������ļ��ж�ȡȫ������(���ַ�������ʽ)
	 * @param textfile �ļ�����, �����׳��쳣, ����<b>���뱣֤�ļ��ɶ�</b>.
	 * @return ���ذ����ļ�ȫ�����ݵ��ַ���
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
