package com.nju.se.team.mota.editor.uielem;

import javax.swing.JLabel;

import com.nju.se.team.mota.game.util.UnitStatus;
import com.nju.se.team.mota.util.ElemPanel;
/**
 * 状态列表的UI单元
 * @author linkeo
 * @author lzw
 *
 */
public class StatusElem extends ElemPanel {

	private static final long serialVersionUID = 1L;
	JLabel value;
	UnitStatus us;
	/**
	 * 构造函数
	 * @param value 状态名称
	 */
	public StatusElem(UnitStatus value) {
		super(true);
		us = value;
		this.value = new JLabel(" "+value+" ");
		this.value.setOpaque(false);
		add(this.value);
		setSize(this.value.getPreferredSize());
		this.value.setLocation(0, 0);
		this.value.setSize(this.value.getPreferredSize());
	}
	/**
	 * 获取当前状态
	 * @return
	 */
	public UnitStatus getValue(){
		return us;
	}
	
}
