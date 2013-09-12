package com.nju.se.team.mota.editor.uielem;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import com.nju.se.team.mota.util.ElemPanel;

/**
 * 是否选择属性的UI单元
 * @author linkeo
 * @author lzw
 *
 */

public class SettingCheckElem extends ElemPanel{

	private static final long serialVersionUID = 1L;
	JLabel label;
	JCheckBox value;
	/**
	 * 构造函数(Default:false)
	 * @param label 属性名称
	 */
	public SettingCheckElem(String label){this(label,false);}
	/**
	 * 构造函数
	 * @param label 属性名称
	 * @param defaultValue 默认是否选择该属性
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
	 * 获取是否已选择该属性
	 * @return boolean
	 */
	public boolean getValue() {
		return value.isSelected();
	}
	/**
	 * 设置是否选择该属性
	 * @param value
	 */
	public void setValue(boolean value) {
		this.value.setSelected(value);
	}

}
