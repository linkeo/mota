package com.nju.se.team.mota.script;

public class MotaScriptUtil {
	public void sleep(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
