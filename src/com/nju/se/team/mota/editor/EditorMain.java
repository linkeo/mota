package com.nju.se.team.mota.editor;

import com.nju.se.team.mota.util.MotaUtils;
/**
 * 主程序入口
 * @author linkeo
 * @author lzw
 *
 */
public class EditorMain {
	public static void main(String[] args) {
		MotaUtils.setLookandFeel();
		EditorFrame f = new EditorFrame();
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}
}
