package com.nju.se.team.mota.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import org.json.JSONObject;

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
			JSONObject o = new JSONObject(getFileContent(f));
			Save save = Save.make(o);
			instance.saves.add(save);
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
}
