package com.nju.se.team.mota.temp;

import java.io.FileNotFoundException;

import javax.script.ScriptException;


public class TempUsage {
	public static void main(String[] argss) throws FileNotFoundException, ScriptException, NoSuchMethodException {
		for(int i=0;i<5;++i){
			Dialogue d = new Dialogue();
			d.addAll(new Sentence[]{
				new Sentence("Jack", "Hey, Bill!"+randomPostfix()),
				new Sentence("Bill", "Hi, Jack!"+randomPostfix()),
				new Sentence("Jack", "Nothing, just call for fun."+randomPostfix())
			});
			d.setStart((int) (Math.random()*d.countSentence()+Dialogue.MINIMUM_ID));
			System.out.println(d);
			System.out.println(Dialogue.make(d.toJSONObject()));
			
			System.out.println();
		}
	}
	public static String randomPostfix(){
		return '\t'+String.format("%06X", (int)(Math.random()*0x1000000));
	}
}