package com.nju.se.team.mota.script;

import com.nju.se.team.mota.game.GameRuntime;

public class MotaScriptUtil {
	public void sleep(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void println(String str){
		GameRuntime.println(str);
	}
}
