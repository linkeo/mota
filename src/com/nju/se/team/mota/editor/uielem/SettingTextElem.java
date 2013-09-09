package com.nju.se.team.mota.editor.uielem;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.nju.se.team.mota.util.ElemPanel;

public class SettingTextElem extends ElemPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel label;
	JTextField value;
	public SettingTextElem(String label){this(label,"");}
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
	public String getValue() {
		return value.getText();
	}
	public void setValue(String str){
		value.setText(str);
	}

}
