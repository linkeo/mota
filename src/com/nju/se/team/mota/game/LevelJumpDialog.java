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
	 * 构造方法<br>
	 * size:320x110
	 */
	public LevelJumpDialog() {
		setTitle("选择要去的楼层");
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
//	 * 构造函数<br>
//	 * 初始值为actionElem的对应值
//	 * @param actionElem 对象行为列表单元
//	 */
//	public LevelSelectDialog(ActionElem actionElem) {
//		this();
//		if(actionElem==null) return;
//		inputAction.setText(actionElem.getAction());
//		inputCondition.setSelectedItem(actionElem.getCondition());
//	}
//	/**
//	 * 添加行为<br>
//	 * static
//	 * @param comp 位置相关组件(panel)
//	 * @param candidates 可选触发条件
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
	 * 获取输入结果
	 * @return ActionElem,若不符规则返回null
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
