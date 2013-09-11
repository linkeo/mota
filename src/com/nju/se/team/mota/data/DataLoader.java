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
 * ��ȡ��ά��������Ϸ���ݶ���<br>
 * �����������Ͷ���, ������(����)���Ͷ���, ¥���ͼ�Ͷ�����<br>
 * P.S.ʹ�� <b>����ģʽ</b><br>
 * @author Linkeo
 * @author lzw
 *
 */
public class DataLoader {
	/**
	 * ����¥���<¥����-JSON����>��ӳ���
	 */
	private HashMap<Integer,JSONObject> levelMap = new HashMap<Integer,JSONObject>();

	/**
	 * ���������<������-JSON����>��ӳ���
	 */
	private HashMap<String,JSONObject> creatureMap = new HashMap<String,JSONObject>();

	/**
	 * ���о���(������)��<������-JSON����>��ӳ���
	 */
	private HashMap<String,JSONObject> abioticMap = new HashMap<String,JSONObject>();
	private DataLoader() {
	}
	private static DataLoader instance;
	/**
	 * ����Դ�ļ�ӳ�伯��ȡ¥��, ����, ����Ķ����ļ�, ��ȡΪJSON���� �����䵽��Ӧ��ӳ�����
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
	 * ��ȡ��������������
	 * @return �������������������ļ���, ��������Ϊ�ռ�(����null)
	 */
	public static Set<String> getCreatureTypes(){
		if(instance==null)load();
		return instance.creatureMap.keySet();
	}
	/**
	 * ��ȡ���з�����������
	 * @return �������з������������ļ���, ��������Ϊ�ռ�(����null)
	 */
	public static Set<String> getAbioticTypes(){
		if(instance==null)load();
		return instance.abioticMap.keySet();
	}
	/**
	 * ��ȡ����¥��Ĳ���, ��������һ������
	 * @return �������в����ļ���, ��������Ϊ�ռ�(����null)
	 */
	public static Set<Integer> getLevelFloors(){
		if(instance==null)load();
		return instance.levelMap.keySet();
	}
	/**
	 * ������������������͵�JSON����
	 * @param type ����������
	 * @return �������Ͷ����JSON����, �������򷵻�null
	 */
	public static JSONObject getCreatureDefine(String type){
		if(instance==null)load();
		return instance.creatureMap.get(type);
	}
	/**
	 * ����������÷��������͵�JSON����
	 * @param type ������������
	 * @return ���������Ͷ����JSON����, �������򷵻�null
	 */
	public static JSONObject getAbioticDefine(String type){
		if(instance==null)load();
		return instance.abioticMap.get(type);
	}
	/**
	 * ��¥��������¥���JSON����
	 * @param floor ¥�����
	 * @return ¥�㶨���JSON����, �������򷵻�null
	 */
	public static JSONObject getLevelDefine(int floor){
		if(instance==null)load();
		return instance.levelMap.get(floor);
	}
	
	/**
	 * �������з����ﶨ�嵽�ļ�
	 */
	public static void saveAbiotics(){
		JSONArray j = new JSONArray(instance.abioticMap.values());
		String str = j.toString(4);
		saveFileContent(ResLoader.getDataFile("abiotics"), str);
	}
	/**
	 * �����������ﶨ�嵽�ļ�
	 */
	public static void saveCreatures(){
		JSONArray j = new JSONArray(instance.creatureMap.values());
		String str = j.toString(4);
		saveFileContent(ResLoader.getDataFile("creatures"), str);
	}


	/**
	 * �����ﶨ��ӳ����и���(���/�޸�)��ֵ��
	 * @param c Ҫ���µ��������
	 */
	public static void putCreatureType(Creature c){
		String type = c.getType();
		JSONObject def = new JSONObject(c.parseTypeJSON().toString());
		instance.creatureMap.put(type, def);
	}
	/**
	 * �����ﶨ��ӳ������Ƴ���ֵ��
	 * @param c Ҫ�Ƴ����������
	 */
	public static void removeCreatureType(Creature c){
		String type = c.getType();
		instance.creatureMap.remove(type);
	}
	/**
	 * �ڷ����ﶨ��ӳ����и���(���/�޸�)��ֵ��
	 * @param a Ҫ���µķ��������
	 */
	public static void putAbioticType(Abiotic a){
		String type = a.getType();
		JSONObject def = new JSONObject(a.parseTypeJSON().toString());
		instance.abioticMap.put(type, def);
	}
	/**
	 * �ڷ����ﶨ��ӳ������Ƴ���ֵ��
	 * @param a Ҫ�Ƴ��ķ��������
	 */
	public static void removeAbioticType(Abiotic a){
		String type = a.getType();
		instance.abioticMap.remove(type);
	}
}

