package com.nju.se.team.mota.temp;

import java.io.FileNotFoundException;
import javax.script.ScriptException;

import com.nju.se.team.mota.script.MotaScript;


public class TempUsage {
	public static void main(String[] argss) throws FileNotFoundException, ScriptException, NoSuchMethodException {
//		String calls[] = {
//				"function0()",
//				"function1(arg)",
//				"function2(arg1,arg2)",
//				"function3( arg )",
//				"function4( arg1, arg2 )",
//				"function5(arg1, arg2, arg3, arg4)"
//		};
//		ArrayList<String> strs = new ArrayList<String>();
//		Pattern pattern = Pattern.compile("\\w+");
//		for(String call : calls){
//			strs.clear();
//			Matcher m = pattern.matcher(call);
//			while(m.find()){
//				strs.add(m.group());
//			}
//			System.out.println(strs);
//			Object[] args = new Object[strs.size()-1];
//			int size = strs.size();
//			for(int i=1;i<size;++i){
//				String arg=strs.get(i);
//				try{
//					int intvalue = Integer.parseInt(arg);
//					args[i-1] = intvalue;
//				}catch(NumberFormatException e){
//					try{
//						double doublevalue = Double.parseDouble(arg);
//						args[i-1] = doublevalue;
//					}catch(NumberFormatException e2){
//						if(arg.equalsIgnoreCase("true"))
//							args[i-1] = true;
//						else if(arg.equalsIgnoreCase("false"))
//							args[i-1] = false;
//						else
//							args[i-1] = arg;
//					}
//				}
//			}
//			System.out.println(strs.get(0)+args);
//		}
//		System.out.println(MotaScript.call("add",new Object[]{}));
		System.out.println();
		System.out.println(MotaScript.eval("100+100"));
		System.out.println(MotaScript.eval("sleep()"));
		System.out.println(MotaScript.eval("println(util)"));
		System.out.println(MotaScript.eval("add(100,true)"));
		System.out.println(MotaScript.eval("add(100,100)"));
		
	}
}