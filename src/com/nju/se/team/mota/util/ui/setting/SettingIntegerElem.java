package com.nju.se.team.mota.util.ui.setting;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.nju.se.team.mota.util.ElemPanel;
import com.nju.se.team.mota.util.MyDocuments;
/**
 * 数字输入框的UI单元
 * @author linkeo
 * @author lzw
 *
 */
public class SettingIntegerElem extends ElemPanel{

	private static final long serialVersionUID = 1L;
	JLabel label;
	JTextField value;
	/**
	 * 构造方法
	 * @param label 属性名称
	 * @param defaultValue 默认值
	 * @param min 可输入最小值
	 * @param max 可输入最大值
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
	 * 获取当前属性值
	 * @return 当前数据,若没有数据返回0
	 */
	public int getValue() {
		if(value.getText()==null||value.getText().isEmpty())return 0;
		return Integer.parseInt(value.getText());
	}
	/**
	 * 设置当前属性值
	 * @param value int
	 */
	public void setValue(int value) {
		this.value.setText(Integer.toString(value));
	}

}
