package com.nju.se.team.mota.editor.uielem;

import javax.swing.JLabel;

import com.nju.se.team.mota.game.util.Condition;
import com.nju.se.team.mota.util.ElemPanel;
/**
 * ������Ϊ���б�UI��Ԫ
 * @author linkeo
 * @author lzw
 *
 */
public class ActionElem extends ElemPanel{
	
	private static final long serialVersionUID = 1L;
	JLabel label;
	JLabel value;
	Condition condition;
	/**
	 * ���췽��
	 * @param condition ��������
	 * @param value ������Ϊ
	 */
	public ActionElem(Condition condition, String value) {
		super(true);
		setSize(280, 20);
		this.condition = condition;
		this.label = new JLabel(condition.toString());
		this.value = new JLabel(value);
		this.add(this.label);
		this.add(this.value);
		this.label.setBounds(0, 0, 90, 20);
		this.value.setBounds(90, 0, 190, 20);
	}
	/**
	 * ��ȡ��������
	 * @return ��������
	 */
	public Condition getCondition(){
		return this.condition;
	}
	/**
	 * ��ȡ������Ϊ
	 * @return ������Ϊ
	 */
	public String getAction(){
		return value.getText();
	}
}
