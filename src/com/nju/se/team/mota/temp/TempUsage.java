package com.nju.se.team.mota.temp;

import java.io.FileNotFoundException;

import javax.script.ScriptException;

import com.nju.se.team.mota.data.DataLoader;


public class TempUsage {
	public static void main(String[] argss) throws FileNotFoundException, ScriptException, NoSuchMethodException {
		for(int i : DataLoader.getDialogueIndexs()){
			Dialogue d = Dialogue.make(DataLoader.getDialogueDefine(i));
			System.out.println(d);
		}
	}
	public static String randomPostfix(){
		return '\t'+String.format("%06X", (int)(Math.random()*0x1000000));
	}
}