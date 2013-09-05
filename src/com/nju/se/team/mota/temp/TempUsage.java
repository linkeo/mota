package com.nju.se.team.mota.temp;

import java.io.FileNotFoundException;
import org.json.JSONObject;

import com.nju.se.team.mota.game.unit.Unit;


public class TempUsage {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(new JSONObject(new Unit()));
	}
}

