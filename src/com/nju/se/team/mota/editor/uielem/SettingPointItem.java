package com.nju.se.team.mota.editor.uielem;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

import com.nju.se.team.mota.util.ElemPanel;

public class SettingPointItem extends ElemPanel implements SettingElem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel label;
	JSpinner spinx, spiny;
	public SettingPointItem(String label, int currx, int curry, int minx, int maxx, int miny, int maxy) {
		super(false);
		setSize(280, 20);
		this.label = new JLabel(label);
		spinx = new JSpinner(new SpinnerNumberModel(currx,minx,maxx,1));
		spiny = new JSpinner(new SpinnerNumberModel(curry,miny,maxy,1));
		JSpinner.DefaultEditor spinxe = new JSpinner.DefaultEditor(spinx);
		JSpinner.DefaultEditor spinye = new JSpinner.DefaultEditor(spiny);
		spinx.setEditor(spinxe);
		spiny.setEditor(spinye);
		this.add(this.label);
		this.add(this.spinx);
		this.add(this.spiny);
		this.label.setBounds(0, 0, 90, 20);
		this.spinx.setBounds(90, 0, 90, 20);
		this.spiny.setBounds(190, 0, 90, 20);
	}
	@Override
	public String getResult() {
		return "\""+label.getText()+"\""+":["+(int)spinx.getValue()+","+(int)spiny.getValue()+"]";
	}
	public int getXValue(){
		return (int)spinx.getValue();
	}
	public int getYValue(){
		return (int)spiny.getValue();
	}
	public void addChangeListenerToSpinners(ChangeListener cl){
		spinx.addChangeListener(cl);
		spiny.addChangeListener(cl);
	}
	public void setValue(int x, int y) {
		spinx.setValue(x);
		spiny.setValue(y);
	}
}
