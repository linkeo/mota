package com.nju.se.team.mota.temp;

import java.io.FileNotFoundException;

import javax.swing.JFrame;

import com.nju.se.team.mota.editor.uielem.SettingPointItem;


public class TempUsage {
	public static void main(String[] args) throws FileNotFoundException {
		JFrame frame = new JFrame("test");
		final SettingPointItem testspi = new SettingPointItem("test", 1, 1, 1, 5, 1, 5);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(testspi);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
	}
}

