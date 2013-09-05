package com.nju.se.team.mota.data;

import java.io.File;
import java.util.HashSet;

import com.nju.se.team.mota.game.Save;

public class SaveLoader {
	private HashSet<File> saveFiles = new HashSet<File>();
	private HashSet<Save> saves = new HashSet<Save>();
	private static SaveLoader instance;
	public static void load(){
		if(instance==null)instance = new SaveLoader();
		File dir = new File("save/");
		for(File f : dir.listFiles())
			if(f.isFile()&&f.getName().matches(".*\\.save"))
				instance.saveFiles.add(f);
		for(File f : instance.saveFiles){
			Save temp = new Save();
			
		}
	}
}
