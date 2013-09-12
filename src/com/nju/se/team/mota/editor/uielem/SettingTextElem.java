package com.nju.se.team.mota.editor.uielem;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.nju.se.team.mota.util.ElemPanel;
/**
 * 文本输入的UI单元
 * @author soft
 *
 */
public class SettingTextElem extends ElemPanel{


	private static final long serialVersionUID = 1L;
	JLabel label;
	JTextField value;
	/**
	 * 构造函数(defaultText:空)
	 * @param label 名称
	 */
	public SettingTextElem(String label){this(label,"");}
	/**
	 * 构造函数
	 * @param label 名称
	 * @param defaultValue 默认文本
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
	 * 获取当前文本
	 * @return String
	 */
	public String getValue() {
		return value.getText();
	}
	/**
	 * 设置当前文本
	 * @param str String
	 */
	public void setValue(String str){
		value.setText(str);
	}

}
