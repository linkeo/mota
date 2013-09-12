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
/**
 * ״̬���(����)��
 * @author linkeo
 * @author lzw
 *
 */
public class StatusComboDialog extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	JLabel status;
	JComboBox<UnitStatus> inputStatus;
	JButton submit;
	/**
	 * ���췽��<br>
	 * size:320x80
	 */
	public StatusComboDialog() {
		setTitle("ѡ��״̬");
		JPanel cp = new JPanel();
		cp.setPreferredSize(new Dimension(320, 80));
		cp.setLayout(null);
		setContentPane(cp);
		status = new JLabel("��ѡ״̬:");
		inputStatus = new JComboBox<UnitStatus>();
		submit = new JButton("�ύ");
		cp.add(status);
		cp.add(inputStatus);
		cp.add(submit);
		status.setBounds(10, 10, 80, 20);
		inputStatus.setBounds(110, 10, 200, 20);
		submit.setBounds(100, 40, 120, 30);
		pack();
	}
	/**
	 * ���췽��
	 * @param statusElem
	 */
	public StatusComboDialog(StatusElem statusElem) {
		this();
		if(statusElem==null) return;
		inputStatus.setSelectedItem(statusElem.getValue());
	}
	/**
	 * ��ȡѡ����<br>
	 * static
	 * @param comp λ���������(panel)
	 * @param candidates ��ѡ״̬
	 * @return
	 */
	public static StatusElem chooseStatus(Component comp, Vector<UnitStatus> candidates){
		return chooseStatus(comp, null, candidates);
	}
	/**
	 * ��ȡѡ����<br>
	 * static
	 * @param comp λ���������(panel)
	 * @param elem ��ǰѡ��״̬UI��Ԫ
	 * @param candidates ��ѡ״̬
	 * @return
	 */
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
	/**
	 * ��ȡѡ����
	 * @return StatusElem
	 */
	private StatusElem getResult() {
		if(inputStatus.getSelectedItem()==null)
			return null;
		return new StatusElem((UnitStatus)inputStatus.getSelectedItem());
	}
	/**
	 * ����ύ������
	 * @param al
	 */
	public void addSubmitActionListener(ActionListener al){
		submit.addActionListener(al);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.setVisible(false);
	}
}
