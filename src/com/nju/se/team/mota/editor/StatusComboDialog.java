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

import com.nju.se.team.mota.editor.uielem.StatusElem;
import com.nju.se.team.mota.game.util.UnitStatus;

public class StatusComboDialog extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel status;
	JComboBox<UnitStatus> inputStatus;
	JButton submit;
	
	public StatusComboDialog() {
		setTitle("选择状态");
		JPanel cp = new JPanel();
		cp.setPreferredSize(new Dimension(320, 80));
		cp.setLayout(null);
		setContentPane(cp);
		status = new JLabel("可选状态:");
		inputStatus = new JComboBox<UnitStatus>();
		submit = new JButton("提交");
		cp.add(status);
		cp.add(inputStatus);
		cp.add(submit);
		status.setBounds(10, 10, 80, 20);
		inputStatus.setBounds(110, 10, 200, 20);
		submit.setBounds(100, 40, 120, 30);
		pack();
	}
	public StatusComboDialog(StatusElem elem) {
		this();
		if(elem==null) return;
		inputStatus.setSelectedItem(elem.getValue());
	}
	public static StatusElem chooseStatus(Component comp, Vector<UnitStatus> candidates){
		return chooseStatus(comp, null, candidates);
	}
	public static StatusElem chooseStatus(Component comp, StatusElem elem, Vector<UnitStatus> candidates){
		StatusComboDialog ss = new StatusComboDialog(elem);
		ss.inputStatus.setModel(new DefaultComboBoxModel<UnitStatus>(candidates));
		ss.setModal(true);
		ss.addSubmitActionListener(ss);
		ss.setLocationRelativeTo(comp);
		ss.show();
		StatusElem result = ss.getResult();
		ss.dispose();
		return result;
	}
	private StatusElem getResult() {
		if(inputStatus.getSelectedItem()==null)
			return null;
		return new StatusElem((UnitStatus)inputStatus.getSelectedItem());
	}
	public void addSubmitActionListener(ActionListener al){
		submit.addActionListener(al);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.setVisible(false);
	}
}
