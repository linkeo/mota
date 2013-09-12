package com.nju.se.team.mota.editor.uielem;

import java.util.Collection;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.nju.se.team.mota.util.ElemPanel;
/**
 * 下拉选择框的UI单元
 * @author linkeo
 * @author lzw
 *
 * @param <T> 对应类型
 */
public class SettingComboElem<T> extends ElemPanel{

	private static final long serialVersionUID = 1L;
	JLabel label;
	JComboBox<T> combo;
	/**
	 * 构造方法
	 * @param label 选择框名称
	 * @param selecteditem 默认的选择对象
	 * @param items 所有可选对象T[]
	 */
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
	/**
	 * 构造方法
	 * @param label 选择框名称
	 * @param selecteditem 默认的选择对象
	 * @param items 所有可选对象Collection
	 */
	public SettingComboElem(String label, T selecteditem, Collection<T> items) {
		super(false);
		setSize(280, 20);
		this.label = new JLabel(label);
		combo = new JComboBox<T>(new Vector<T>(items));
		combo.setEditable(false);
		combo.setSelectedItem(selecteditem);
		this.add(this.label);
		this.add(this.combo);
		this.label.setBounds(0, 0, 90, 20);
		this.combo.setBounds(90, 0, 190, 20);
	}
	/**
	 * 获取当前选择的对象
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public T getValue() {
		return (T) combo.getSelectedItem();
	}
	/**
	 * 设置要选择的对象
	 * @param value T对象
	 */
	public void setValue(T value) {
		combo.setSelectedItem(value);
	}

}
