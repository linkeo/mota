package com.nju.se.team.mota.util;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MotaUtils {

	public static void setLookandFeel() {
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
