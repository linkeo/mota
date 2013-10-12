package com.nju.se.team.mota.game;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JDialog;

import com.nju.se.team.mota.util.TransparentListPanel;

public class LevelJumpDialog extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	TransparentListPanel panel;
	Set<Integer> floors;
	int value = 0;
	/**
	 * ���췽��<br>
	 * size:320x110
	 */
	public LevelJumpDialog() {
		setTitle("ѡ��Ҫȥ��¥��");
		panel = new TransparentListPanel();
		panel.setPreferredSize(new Dimension(320, 110));
		panel.setLayout(null);
		setContentPane(panel);
		floors = GamingLevels.getVisitedLevels();
		for(int i : floors){
			LevelSelectElem lse = new LevelSelectElem(i);
			lse.addActionListener(this);
			panel.add(lse);
		}
		pack();
	}
//	/**
//	 * ���캯��<br>
//	 * ��ʼֵΪactionElem�Ķ�Ӧֵ
//	 * @param actionElem ������Ϊ�б�Ԫ
//	 */
//	public LevelSelectDialog(ActionElem actionElem) {
//		this();
//		if(actionElem==null) return;
//		inputAction.setText(actionElem.getAction());
//		inputCondition.setSelectedItem(actionElem.getCondition());
//	}
//	/**
//	 * �����Ϊ<br>
//	 * static
//	 * @param comp λ��������(panel)
//	 * @param candidates ��ѡ��������
//	 * @return
//	 */
//	public static ActionElem editAction(Component comp, Vector<Condition> candidates){
//		return editAction(comp, null, candidates);
//	}
	@SuppressWarnings("deprecation")
	public static int selectLevel(Component comp){
		LevelJumpDialog ss = new LevelJumpDialog();
		ss.setModal(true);
		ss.setLocationRelativeTo(comp);
		ss.show();
		int result = ss.getResult();
		ss.dispose();
		return result;
	}
	/**
	 * ��ȡ������
	 * @return ActionElem,���������򷵻�null
	 */
	private int getResult() {
		return value;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		value = ((LevelSelectElem)e.getSource()).getFloor();
		this.setVisible(false);
	}
}
