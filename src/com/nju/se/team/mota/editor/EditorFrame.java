package com.nju.se.team.mota.editor;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class EditorFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTabbedPane tab = new JTabbedPane();
	public EditorFrame() {
		setTitle("ħ����Ϸ���ݱ༭��");
		add(tab);
		tab.add(new TypeDefinePanel(), "���Ͷ���");
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}
}
