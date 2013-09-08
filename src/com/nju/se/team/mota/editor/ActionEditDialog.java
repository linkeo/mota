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

public class ActionEditDialog extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel condition;
	JLabel action;
	JComboBox<Condition> inputCondition;
	JTextField inputAction;
	JButton submit;
	
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
	public ActionEditDialog(ActionElem elem) {
		this();
		if(elem==null) return;
		inputAction.setText(elem.getAction());
		inputCondition.setSelectedItem(elem.getCondition());
	}
	public static ActionElem editAction(Component comp, Vector<Condition> candidates){
		return editAction(comp, null, candidates);
	}
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
	private ActionElem getResult() {
		if(inputCondition.getSelectedItem()==null||inputAction.getText()==null||inputAction.getText().matches("\\W*"))
			return null;
		return new ActionElem((Condition)inputCondition.getSelectedItem(), inputAction.getText());
	}
	public void addSubmitActionListener(ActionListener al){
		submit.addActionListener(al);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.setVisible(false);
	}
}
