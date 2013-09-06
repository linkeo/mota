package com.nju.se.team.mota.editor.uielem;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import com.nju.se.team.mota.util.ElemPanel;


public class SettingCheckElem extends ElemPanel implements SettingElem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel label;
	JCheckBox value;
	public SettingCheckElem(String label){this(label,false);}
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
	@Override
	public String getResult() {
		return "\""+label.getText()+"\""+":"+(value.isSelected()?"true":"false");
	}
	public void setValue(boolean value) {
		this.value.setSelected(value);
	}

}