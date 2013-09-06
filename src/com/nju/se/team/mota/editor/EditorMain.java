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
	 * ����LookAndFeelΪϵͳ��ʽ, 
	 * ������Label, Button, TextField, TextArea������Ϊ΢���ź�(Plain,12) <br/><b>ע:</b> ������ʹ��Swing֮ǰ����.
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
