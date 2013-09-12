package com.nju.se.team.mota.editor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nju.se.team.mota.editor.uielem.ActionElem;
import com.nju.se.team.mota.game.util.Condition;
/**
 * 脚本行为编辑(弹出)框
 * @author linkeo
 * @author lzw
 *
 */
public class ActionEditDialog extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	JLabel condition;
	JLabel action;
	JComboBox<Condition> inputCondition;
	JTextField inputAction;
	JButton submit;
	/**
	 * 构造方法<br>
	 * size:320x110
	 */
	public ActionEditDialog() {
		setTitle("编辑行为脚本");
		JPanel cp = new JPanel();
		cp.setPreferredSize(new Dimension(320, 110));
		cp.setLayout(null);
		setContentPane(cp);
		condition = new JLabel("触发条件:");
		action = new JLabel("行为脚本:");
		inputCondition = new JComboBox<Condition>();
		inputAction = new JTextField();
		submit = new JButton("提交");
		cp.add(condition);
		cp.add(action);
		cp.add(inputCondition);
		cp.add(inputAction);
		cp.add(submit);
		condition.setBounds(10, 10, 80, 20);
		action.setBounds(10, 40, 80, 20);
		inputCondition.setBounds(110, 10, 200, 20);
		inputAction.setBounds(110, 40, 200, 20);
		submit.setBounds(100, 70, 120, 30);
		pack();
	}
	/**
	 * 构造函数<br>
	 * 初始值为actionElem的对应值
	 * @param actionElem 对象行为列表单元
	 */
	public ActionEditDialog(ActionElem actionElem) {
		this();
		if(actionElem==null) return;
		inputAction.setText(actionElem.getAction());
		inputCondition.setSelectedItem(actionElem.getCondition());
	}
	/**
	 * 添加行为<br>
	 * static
	 * @param comp 位置相关组件(panel)
	 * @param candidates 可选触发条件
	 * @return
	 */
	public static ActionElem editAction(Component comp, Vector<Condition> candidates){
		return editAction(comp, null, candidates);
	}
	/**
	 * 编辑行为<br>
	 * static
	 * @param comp 位置相关组件(panel)
	 * @param elem 当前选择的ActionElem
	 * @param candidates 剩余的可选触发条件
	 * @return
	 */
	public static ActionElem editAction(Component comp, ActionElem elem, Vector<Condition> candidates){
		ActionEditDialog ss = new ActionEditDialog(elem);
		ss.inputCondition.setModel(new DefaultComboBoxModel<Condition>(candidates));
		ss.setModal(true);
		ss.addSubmitActionListener(ss);
		ss.setLocationRelativeTo(comp);
		ss.show();
		ActionElem result = ss.getResult();
		ss.dispose();
		return result;
	}
	/**
	 * 获取输入结果
	 * @return ActionElem,若不符规则返回null
	 */
	private ActionElem getResult() {
		if(inputCondition.getSelectedItem()==null||inputAction.getText()==null||inputAction.getText().matches("\\W*"))
			return null;
		return new ActionElem((Condition)inputCondition.getSelectedItem(), inputAction.getText());
	}
	/**
	 * 添加提交按钮监听
	 * @param actionListener
	 */
	public void addSubmitActionListener(ActionListener actionListener){
		submit.addActionListener(actionListener);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.setVisible(false);
	}
}
