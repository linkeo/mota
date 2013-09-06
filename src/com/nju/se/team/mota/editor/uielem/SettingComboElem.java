package com.nju.se.team.mota.editor.uielem;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.nju.se.team.mota.util.ElemPanel;

public class SettingComboElem<T> extends ElemPanel implements SettingElem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel label;
	JComboBox<T> combo;
	public SettingComboElem(String label, T selecteditem, T[] items) {
		super(false);
		setSize(280, 20);
		this.label = new JLabel(label);
		combo = new JComboBox<T>(items);
		combo.setEditable(false);
		combo.setSelectedItem(selecteditem);
		this.add(this.label);
		this.add(this.combo);
		this.label.setBounds(0, 0, 90, 20);
		this.combo.setBounds(90, 0, 190, 20);
	}
	@Override
	public String getResult() {
		return "\""+label.getText()+"\""+":"+combo.getSelectedItem().toString();
	}

}
