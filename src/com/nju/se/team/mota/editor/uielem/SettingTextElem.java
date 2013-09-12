package com.nju.se.team.mota.editor.uielem;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.nju.se.team.mota.util.ElemPanel;
/**
 * �ı������UI��Ԫ
 * @author soft
 *
 */
public class SettingTextElem extends ElemPanel{


	private static final long serialVersionUID = 1L;
	JLabel label;
	JTextField value;
	/**
	 * ���캯��(defaultText:��)
	 * @param label ����
	 */
	public SettingTextElem(String label){this(label,"");}
	/**
	 * ���캯��
	 * @param label ����
	 * @param defaultValue Ĭ���ı�
	 */
	public SettingTextElem(String label, String defaultValue) {
		super(false);
		setSize(280, 20);
		this.label = new JLabel(label);
		this.value = new JTextField(defaultValue);
		this.add(this.label);
		this.add(this.value);
		this.label.setBounds(0, 0, 90, 20);
		this.value.setBounds(90, 0, 190, 20);
	}
	/**
	 * ��ȡ��ǰ�ı�
	 * @return String
	 */
	public String getValue() {
		return value.getText();
	}
	/**
	 * ���õ�ǰ�ı�
	 * @param str String
	 */
	public void setValue(String str){
		value.setText(str);
	}

}
