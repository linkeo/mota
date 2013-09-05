package com.nju.se.team.mota.editor.uielem;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.nju.se.team.mota.temp.ElemPanel;

public class SettingTextElem extends ElemPanel implements SettingElem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel label;
	JTextField value;
	public SettingTextElem(String label){this(label,"");}
	public SettingTextElem(String label, String defaultValue) {
		super(false);
		setSize(300, 30);
		this.label = new JLabel(label);
		this.value = new JTextField(defaultValue);
		this.label.setBounds(0, 0, 100, 30);
		this.value.setBounds(100, 0, 200, 30);
	}
	@Override
	public String getResult() {
		return "\""+label.getText()+"\""+":"+"\""+value.getText()+"\"";
	}

}
