package com.nju.se.team.mota.editor;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
/**
 * �༭��������Frame
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
	 * ���췽��<br>
	 * ���JTabbedPane<br>
	 * ������п�ѡ�༭���
	 */
	public EditorFrame() {
		setTitle("ħ����Ϸ���ݱ༭��");
		add(tab);
		tab.add(tdp, "���Ͷ���");
		tab.add(lep,"��ͼ�༭");
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}
}
