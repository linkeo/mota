package com.nju.se.team.mota.script;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.nju.se.team.mota.data.ResLoader;

/**
 * The script module of Mota.<br>
 * Using <b>Singleton</b>.<br>
 * Initialize when software load this class.
 * @author Linkeo
 *
 */
public class MotaScript {
	
	private final static MotaScript inst = new MotaScript();
	
	private ScriptEngine engine;
	
	private MotaScript(){
		File scriptFile = ResLoader.getDataFile("script");
		ScriptEngineManager sem = new ScriptEngineManager();
		engine = sem.getEngineByName("javascript");
		try {
			engine.eval(new FileReader(scriptFile));
		} catch (FileNotFoundException | ScriptException e) {
			System.out.println("Load script failed!");
			e.printStackTrace();
		}
		System.out.println("Load script successfully.");
	}
	
	/**
	 * Put a object in global key of JavaScript
	 * @param key
	 * @param value
	 */
	public static void put(String key, Object value){
		inst.engine.put(key, value);
	}
	
	/**
	 * Call a JavaScript function.<br>
	 * If no such function, cause a exception(solved inside).
	 * @param function function name
	 * @param args arguments of the function
	 * @return
	 */
	public static Object call(String function, Object... args){
		Invocable inv = (Invocable) inst.engine;
		try {
			return inv.invokeFunction(function, args);
		} catch (NoSuchMethodException | ScriptException e) {
			e.printStackTrace();
		}
		return null;
	}
}
