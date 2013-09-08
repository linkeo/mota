package com.nju.se.team.mota.temp;

import java.io.FileNotFoundException;

import javax.swing.JFrame;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nju.se.team.mota.editor.uielem.SettingPointItem;
import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.util.Animation;
import com.nju.se.team.mota.game.util.Condition;
import com.nju.se.team.mota.game.util.UnitStatus;


public class TempUsage {
	public static void main(String[] args) throws FileNotFoundException {
//		JFrame frame = new JFrame("test");
//		final SettingPointItem testspi = new SettingPointItem("test", 1, 1, 1, 5, 1, 5);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.add(testspi);
//		frame.pack();
//		frame.setVisible(true);
//		frame.setLocationRelativeTo(null);
		Abiotic a = Abiotic.make(new JSONObject("{\"position\":[1,1],\"canGoThrough\":true,\"floor\":0,\"name\":\"Hello\",\"buddyType\":\"none\",\"action\":{\"closeto\":\"open\",\"crash\":\"open\"},\"sprites\":{\"normal\":{\"images\":[[\"STR\"]]}},\"type\":\"floor\",\"size\":[1,1]}"));

		System.out.println(new JSONObject(a));
		System.out.println(new JSONObject(a.copy()));
	}
}

