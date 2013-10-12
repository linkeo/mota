package com.nju.se.team.mota.util.ui.setting;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

import com.nju.se.team.mota.util.ElemPanel;
/**
 * 二维坐标选择的UI单元
 * @author linkeo
 * @author lzw
 *
 */
public class SettingPointItem extends ElemPanel{

	private static final long serialVersionUID = 1L;
	JLabel label;
	JSpinner spinx, spiny;
	/**
	 * 构造方法
	 * @param label 名称
	 * @param currx 当前横坐标
	 * @param curry	当前纵坐标
	 * @param minx 横坐标最小值
	 * @param maxx 横坐标最大值
	 * @param miny 纵坐标最小值
	 * @param maxy 纵坐标最大值
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
	 * 获取横坐标
	 * @return 横坐标
	 */
	public int getXValue(){
		return (int)spinx.getValue();
	}
	/**
	 * 获取纵坐标
	 * @return 纵坐标
	 */
	public int getYValue(){
		return (int)spiny.getValue();
	}
	/**
	 * 添加响应数据改变的listener
	 * @param cl ChangeListener
	 */
	public void addChangeListenerToSpinners(ChangeListener cl){
		spinx.addChangeListener(cl);
		spiny.addChangeListener(cl);
	}
	/**
	 * 设置坐标
	 * @param x 横坐标
	 * @param y	纵坐标
	 */
	public void setValue(int x, int y) {
		spinx.setValue(x);
		spiny.setValue(y);
	}
}
