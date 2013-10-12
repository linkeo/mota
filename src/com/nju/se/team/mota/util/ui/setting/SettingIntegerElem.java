package com.nju.se.team.mota.util.ui.setting;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.nju.se.team.mota.util.ElemPanel;
import com.nju.se.team.mota.util.MyDocuments;
/**
 * ����������UI��Ԫ
 * @author linkeo
 * @author lzw
 *
 */
public class SettingIntegerElem extends ElemPanel{

	private static final long serialVersionUID = 1L;
	JLabel label;
	JTextField value;
	/**
	 * ���췽��
	 * @param label ��������
	 * @param defaultValue Ĭ��ֵ
	 * @param min ��������Сֵ
	 * @param max ���������ֵ
	 */
	public SettingIntegerElem(String label, int defaultValue, int min, int max) {
		super(false);
		setSize(280, 20);
		this.label = new JLabel(label);
		this.value = new JTextField();
		this.value.setDocument(MyDocuments.getIntDocument(min, max));
		this.value.setText(Integer.toString(defaultValue));
		this.add(this.label);
		this.add(this.value);
		this.label.setBounds(0, 0, 90, 20);
		this.value.setBounds(90, 0, 190, 20);
	}
	/**
	 * ��ȡ��ǰ����ֵ
	 * @return ��ǰ����,��û�����ݷ���0
	 */
	public int getValue() {
		if(value.getText()==null||value.getText().isEmpty())return 0;
		return Integer.parseInt(value.getText());
	}
	/**
	 * ���õ�ǰ����ֵ
	 * @param value int
	 */
	public void setValue(int value) {
		this.value.setText(Integer.toString(value));
	}

}
