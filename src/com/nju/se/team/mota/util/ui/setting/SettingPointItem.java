package com.nju.se.team.mota.util.ui.setting;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

import com.nju.se.team.mota.util.ElemPanel;
/**
 * ��ά����ѡ���UI��Ԫ
 * @author linkeo
 * @author lzw
 *
 */
public class SettingPointItem extends ElemPanel{

	private static final long serialVersionUID = 1L;
	JLabel label;
	JSpinner spinx, spiny;
	/**
	 * ���췽��
	 * @param label ����
	 * @param currx ��ǰ������
	 * @param curry	��ǰ������
	 * @param minx ��������Сֵ
	 * @param maxx ���������ֵ
	 * @param miny ��������Сֵ
	 * @param maxy ���������ֵ
	 */
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
	/**
	 * ��ȡ������
	 * @return ������
	 */
	public int getXValue(){
		return (int)spinx.getValue();
	}
	/**
	 * ��ȡ������
	 * @return ������
	 */
	public int getYValue(){
		return (int)spiny.getValue();
	}
	/**
	 * �����Ӧ���ݸı��listener
	 * @param cl ChangeListener
	 */
	public void addChangeListenerToSpinners(ChangeListener cl){
		spinx.addChangeListener(cl);
		spiny.addChangeListener(cl);
	}
	/**
	 * ��������
	 * @param x ������
	 * @param y	������
	 */
	public void setValue(int x, int y) {
		spinx.setValue(x);
		spiny.setValue(y);
	}
}
