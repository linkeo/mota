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
	public EditorFrame() {
		setTitle("ħ����Ϸ���ݱ༭��");
		add(tab);
		tab.add(tdp, "���Ͷ���");
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}
}
