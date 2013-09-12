package com.nju.se.team.mota.editor.uielem;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import com.nju.se.team.mota.util.ElemPanel;

/**
 * �Ƿ�ѡ�����Ե�UI��Ԫ
 * @author linkeo
 * @author lzw
 *
 */

public class SettingCheckElem extends ElemPanel{

	private static final long serialVersionUID = 1L;
	JLabel label;
	JCheckBox value;
	/**
	 * ���캯��(Default:false)
	 * @param label ��������
	 */
	public SettingCheckElem(String label){this(label,false);}
	/**
	 * ���캯��
	 * @param label ��������
	 * @param defaultValue Ĭ���Ƿ�ѡ�������
	 */
	public SettingCheckElem(String label, boolean defaultValue) {
		super(false);
		setSize(280, 20);
		this.label = new JLabel(label);
		this.value = new JCheckBox();
		this.value.setHorizontalAlignment(JCheckBox.RIGHT);
		this.value.setSelected(defaultValue);
		this.add(this.label);
		this.add(this.value);
		this.label.setBounds(0, 0, 90, 20);
		this.value.setBounds(90, 0, 190, 20);
	}
	/**
	 * ��ȡ�Ƿ���ѡ�������
	 * @return boolean
	 */
	public boolean getValue() {
		return value.isSelected();
	}
	/**
	 * �����Ƿ�ѡ�������
	 * @param value
	 */
	public void setValue(boolean value) {
		this.value.setSelected(value);
	}

}
