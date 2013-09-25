package com.nju.se.team.mota.temp;

import java.io.FileNotFoundException;

import javax.script.ScriptException;


public class TempUsage {
	public static void main(String[] args) throws FileNotFoundException, ScriptException, NoSuchMethodException {
		double i = 10;
		long z = 0;
		for(int j = 0; j < 50; ++j){
			z+=(long)i;
			System.out.println(z);
			i*=Math.pow(10, 0.1);
		}
	}
}