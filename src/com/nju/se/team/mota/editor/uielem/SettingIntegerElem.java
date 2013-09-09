package com.nju.se.team.mota.editor.uielem;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.nju.se.team.mota.util.ElemPanel;
import com.nju.se.team.mota.util.MyDocuments;

public class SettingIntegerElem extends ElemPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel label;
	JTextField value;
	public SettingIntegerElem(String label, int defaultValue, int min, int max) {
		super(false);
		setSize(280, 20);
		this.label = new JLabel(label);
		this.value = new JTextField(Integer.toString(defaultValue));
		this.value.setDocument(MyDocuments.getIntDocument(min, max));
		this.add(this.label);
		this.add(this.value);
		this.label.setBounds(0, 0, 90, 20);
		this.value.setBounds(90, 0, 190, 20);
	}
	public int getValue() {
		if(value.getText()==null||value.getText().isEmpty())return 0;
		return Integer.parseInt(value.getText());
	}
	public void setValue(int value) {
		this.value.setText(Integer.toString(value));
	}

}
