package com.nju.se.team.mota.editor;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class EditorFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTabbedPane tab = new JTabbedPane();
	TypeDefinePanel tdp = new TypeDefinePanel();
	LevelEditPanel lep = new LevelEditPanel();
	public EditorFrame() {
		setTitle("魔塔游戏数据编辑器");
		add(tab);
		tab.add(tdp, "类型定义");
		tab.add(lep,"地图编辑");
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}
}
