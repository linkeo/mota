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
 * �ű���Ϊ�༭(����)��
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
	 * ���췽��<br>
	 * size:320x110
	 */
	public ActionEditDialog() {
		setTitle("�༭��Ϊ�ű�");
		JPanel cp = new JPanel();
		cp.setPreferredSize(new Dimension(320, 110));
		cp.setLayout(null);
		setContentPane(cp);
		condition = new JLabel("��������:");
		action = new JLabel("��Ϊ�ű�:");
		inputCondition = new JComboBox<Condition>();
		inputAction = new JTextField();
		submit = new JButton("�ύ");
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
	 * ���캯��<br>
	 * ��ʼֵΪactionElem�Ķ�Ӧֵ
	 * @param actionElem ������Ϊ�б�Ԫ
	 */
	public ActionEditDialog(ActionElem actionElem) {
		this();
		if(actionElem==null) return;
		inputAction.setText(actionElem.getAction());
		inputCondition.setSelectedItem(actionElem.getCondition());
	}
	/**
	 * �����Ϊ<br>
	 * static
	 * @param comp λ��������(panel)
	 * @param candidates ��ѡ��������
	 * @return
	 */
	public static ActionElem editAction(Component comp, Vector<Condition> candidates){
		return editAction(comp, null, candidates);
	}
	/**
	 * �༭��Ϊ<br>
	 * static
	 * @param comp λ��������(panel)
	 * @param elem ��ǰѡ���ActionElem
	 * @param candidates ʣ��Ŀ�ѡ��������
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
	 * ��ȡ������
	 * @return ActionElem,���������򷵻�null
	 */
	private ActionElem getResult() {
		if(inputCondition.getSelectedItem()==null||inputAction.getText()==null||inputAction.getText().matches("\\W*"))
			return null;
		return new ActionElem((Condition)inputCondition.getSelectedItem(), inputAction.getText());
	}
	/**
	 * ����ύ��ť����
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
