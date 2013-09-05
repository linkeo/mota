package com.nju.se.team.mota.editor.uielem;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import com.nju.se.team.mota.temp.ElemPanel;


public class SettingCheckItem extends ElemPanel implements SettingElem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel label;
	JCheckBox value;
	public SettingCheckItem(String label){this(label,false);}
	public SettingCheckItem(String label, boolean defaultValue) {
		super(false);
		setSize(300, 30);
		this.label = new JLabel(label);
		this.value = new JCheckBox();
		this.value.setSelected(defaultValue);
		this.label.setBounds(0, 0, 100, 30);
		this.value.setBounds(100, 0, 200, 30);
	}
	@Override
	public String getResult() {
		return "\""+label.getText()+"\""+":"+(value.isSelected()?"true":"false");
	}

}
