package com.nju.se.team.mota.temp;

import java.io.FileNotFoundException;

import javax.script.ScriptException;

import com.nju.se.team.mota.script.MotaScript;


public class TempUsage {
	public static void main(String[] args) throws FileNotFoundException, ScriptException, NoSuchMethodException {
		Ett player = new Ett(200, 15, 10);
		Ett enemy = new Ett(50, 10, 5);
		MotaScript.put("player", player);
		MotaScript.put("enemy", enemy);
		MotaScript.call("fight");
	}
}

