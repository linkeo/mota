package com.nju.se.team.mota.editor;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class EditorMain {
	public static void main(String[] args) {
		setLookandFeel();
		EditorFrame f = new EditorFrame();
		f.setVisible(true);
		f.setLocationRelativeTo(null);

	}/**
	 * 设置LookAndFeel为系统样式, 
	 * 并设置Label, Button, TextField, TextArea的字体为微软雅黑(Plain,12) <br/><b>注:</b> 必须在使用Swing之前调用.
	 */
	public static void setLookandFeel(){
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				UIManager.put("Label.font", Fonts.getYahei(12));
				UIManager.put("Button.font", Fonts.getYahei(12));
				UIManager.put("TextField.font", Fonts.getYahei(12));
				UIManager.put("TextArea.font", Fonts.getYahei(12));
				UIManager.put("Panel.background", Colors.THEME_BACKGROUND);
				UIManager.put("Label.background", Colors.THEME_BACKGROUND);
				UIManager.put("Button.background", Colors.THEME_BACKGROUND);
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
