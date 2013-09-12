package com.nju.se.team.mota.editor;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
/**
 * 编辑器总容器Frame
 * @author linkeo
 * @author lzw
 *
 */
public class EditorFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	JTabbedPane tab = new JTabbedPane();
	TypeDefinePanel tdp = new TypeDefinePanel();
	LevelEditPanel lep = new LevelEditPanel();
	/**
	 * 构造方法<br>
	 * 添加JTabbedPane<br>
	 * 添加所有可选编辑面板
	 */
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
