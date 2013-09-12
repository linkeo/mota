package com.nju.se.team.mota.editor.uielem;

import java.util.Collection;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.nju.se.team.mota.util.ElemPanel;
/**
 * ����ѡ����UI��Ԫ
 * @author linkeo
 * @author lzw
 *
 * @param <T> ��Ӧ����
 */
public class SettingComboElem<T> extends ElemPanel{

	private static final long serialVersionUID = 1L;
	JLabel label;
	JComboBox<T> combo;
	/**
	 * ���췽��
	 * @param label ѡ�������
	 * @param selecteditem Ĭ�ϵ�ѡ�����
	 * @param items ���п�ѡ����T[]
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
	 * ���췽��
	 * @param label ѡ�������
	 * @param selecteditem Ĭ�ϵ�ѡ�����
	 * @param items ���п�ѡ����Collection
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
	 * ��ȡ��ǰѡ��Ķ���
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public T getValue() {
		return (T) combo.getSelectedItem();
	}
	/**
	 * ����Ҫѡ��Ķ���
	 * @param value T����
	 */
	public void setValue(T value) {
		combo.setSelectedItem(value);
	}

}
