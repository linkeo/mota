package com.nju.se.team.mota.editor.uielem;

import javax.swing.JLabel;

import com.nju.se.team.mota.game.util.Condition;
import com.nju.se.team.mota.util.ElemPanel;
/**
 * 对象行为的列表UI单元
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
	 * 构造方法
	 * @param condition 触发条件
	 * @param value 具体行为
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
	 * 获取触发条件
	 * @return 触发条件
	 */
	public Condition getCondition(){
		return this.condition;
	}
	/**
	 * 获取具体行为
	 * @return 具体行为
	 */
	public String getAction(){
		return value.getText();
	}
}
