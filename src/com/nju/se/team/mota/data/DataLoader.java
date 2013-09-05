package com.nju.se.team.mota.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import org.json.JSONArray;

public class DataLoader {

	private HashMap<Integer,String> levelMap = new HashMap<Integer,String>();
	private HashMap<String,String> creatureMap = new HashMap<String,String>();
	private HashMap<String,String> abioticMap = new HashMap<String,String>();
	public DataLoader() {
	}
	private static DataLoader instance;
	public static void load(){
		if(instance == null)
			instance = new DataLoader();
		JSONArray cs = new JSONArray(getFileContent(ResLoader.getDataFile("creatures")));
		for(int i=0;i<cs.length();++i){
			instance.creatureMap.put(cs.getJSONObject(i).getString("type"), cs.getJSONObject(i).toString());
		}
		JSONArray as = new JSONArray(getFileContent(ResLoader.getDataFile("abiotics")));
		for(int i=0;i<as.length();++i){
			instance.abioticMap.put(as.getJSONObject(i).getString("type"), as.getJSONObject(i).toString());
		}
		JSONArray ls = new JSONArray(getFileContent(ResLoader.getDataFile("levels")));
		for(int i=0;i<ls.length();++i){
			instance.levelMap.put(ls.getJSONObject(i).getInt("level"), ls.getJSONObject(i).toString());
		}
	}
	public static String getFileContent(File textfile){
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
	public static Set<String> getCreatureTypes(){
		if(instance==null)load();
		return instance.creatureMap.keySet();
	}
	public static Set<String> getAbioticTypes(){
		if(instance==null)load();
		return instance.abioticMap.keySet();
	}
	public static Set<Integer> getLevelFloors(){
		if(instance==null)load();
		return instance.levelMap.keySet();
	}
	public static String getCreatureDefine(String type){
		if(instance==null)load();
		return instance.creatureMap.get(type);
	}
	public static String getAbioticDefine(String type){
		if(instance==null)load();
		return instance.abioticMap.get(type);
	}
	public static String getLevelDefine(int floor){
		if(instance==null)load();
		return instance.levelMap.get(floor);
	}
}

