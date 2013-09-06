package com.nju.se.team.mota.editor;

import javax.swing.JLabel;

import com.nju.se.team.mota.editor.uielem.SettingElem;
import com.nju.se.team.mota.util.ElemPanel;

public class ActionElem extends ElemPanel implements SettingElem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel label;
	JLabel value;
	public ActionElem(String label, String value) {
		super(true);
		setSize(280, 20);
		this.label = new JLabel(label);
		this.value = new JLabel(value);
		this.add(this.label);
		this.add(this.value);
		this.label.setBounds(0, 0, 90, 20);
		this.value.setBounds(90, 0, 190, 20);
	}
	@Override
	public String getResult() {
		return "\""+label.getText()+"\""+":"+value.getText();
	}

}
