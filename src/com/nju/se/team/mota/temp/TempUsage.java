package com.nju.se.team.mota.temp;

import java.io.File;
import java.util.ArrayList;

public class TempUsage {
	public static void main(String[] args) {
		String path = "image/sprite/";
		File dir = new File(path);
		for(File f : dir.listFiles()){
			if(f.isFile())
				System.out.println(f.getName().split("\\.")[0]+"="+f.getPath());
		}
	}
}
