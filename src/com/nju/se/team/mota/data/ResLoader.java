package com.nju.se.team.mota.data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ResLoader {
	private HashMap<String,File> datamap = new HashMap<String,File>();
	private HashMap<String,File> imagemap = new HashMap<String,File>();
	private static ResLoader instance;
	
	public static void load(){
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
	
	public static File getDataFile(String key){
		if(instance==null)
			load();
		return instance.datamap.get(key);
	}
	public static File getImageFile(String key){
		if(instance==null)
			load();
		return instance.imagemap.get(key);
	}
	public static Set<String> getDataKeySet(){
		if(instance==null)
			load();
		return instance.datamap.keySet();
	}
	public static Set<String> getImageKeySet(){
		if(instance==null)
			load();
		return instance.imagemap.keySet();
	}
	
}
