package com.nju.se.team.mota.temp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.json.JSONObject;

import com.nju.se.team.mota.game.unit.Abiotic;


public class TempUsage {
	public static void main(String[] args) throws FileNotFoundException, ScriptException, NoSuchMethodException {
//		JFrame frame = new JFrame("test");
//		final SettingPointItem testspi = new SettingPointItem("test", 1, 1, 1, 5, 1, 5);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.add(testspi);
//		frame.pack();
//		frame.setVisible(true);
//		frame.setLocationRelativeTo(null);
		Abiotic a = Abiotic.make("µÿ√Ê", 0, 0, 0);
//
//		System.out.println(new JSONObject(a));
//		System.out.println(new JSONObject(a.clone()));
//		Unit u =a;
//		System.out.println(new JSONObject(u.clone()));
//		
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine se = sem.getEngineByName("javascript");
		String str = getFileContent(new File("test.js"));
		System.out.println("EVAL:"+str);
		se.eval(str);
		Invocable inv = (Invocable) se;     
		inv.invokeFunction("test",a);  
		System.out.println(a.getType());
	}
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

