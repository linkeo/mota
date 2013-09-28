package com.nju.se.team.mota.script;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.nju.se.team.mota.data.ResLoader;
import com.nju.se.team.mota.game.GamingLevels;
import com.nju.se.team.mota.game.util.UnitStatus;

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
		MotaScriptUtil util = new MotaScriptUtil();
		engine.put("util",util);
		engine.put("UnitStatus", UnitStatus.values()[0]);
		System.out.println("Load script utilities successfully.");
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
	@Deprecated
	public static Object call(String function, Object... args){
		Invocable inv = (Invocable) inst.engine;
		try {
			return inv.invokeFunction(function, args);
		} catch (NoSuchMethodException | ScriptException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Executes the specified script. The default ScriptContext for the ScriptEngine is used.
	 * @param script The script language source to be executed.
	 * @return The value returned from the execution of the script.
	 */
	public static Object eval(String script){
		try {
			return inst.engine.eval(script);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return null;
	}
}
