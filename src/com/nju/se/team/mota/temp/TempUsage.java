package com.nju.se.team.mota.temp;

import java.io.FileNotFoundException;
import com.nju.se.team.mota.data.DataLoader;


public class TempUsage {
	public static void main(String[] args) throws FileNotFoundException {
		for(int i : DataLoader.getLevelFloors())
		System.out.println(i+"==>"+DataLoader.getLevelDefine(i));
	}
}

