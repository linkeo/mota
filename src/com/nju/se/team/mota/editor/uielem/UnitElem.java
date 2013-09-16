package com.nju.se.team.mota.editor.uielem;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.nju.se.team.mota.editor.dnd.DndHandler;
import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Unit;
import com.nju.se.team.mota.game.util.TypeEnum;
import com.nju.se.team.mota.util.ElemPanel;
/**
 * Unit对象列表的UI单元
 * @author soft
 *
 */
public class UnitElem extends ElemPanel {

	private static final long serialVersionUID = 1L;
	private Unit unit;
	private JLabel imageLabel;
	private JLabel typeNameLabel;
	private JLabel sizeLabel;
	/**
	 * 构造方法
	 * @param u Unit对象
	 */
	public UnitElem(Unit u) {
		super(false);
		this.unit = u;
		int[] size = u.getSize();
		
		imageLabel = new JLabel(new ImageIcon(u.thumbnail(32, 32)));
		typeNameLabel = new JLabel(u.getType());
		sizeLabel = new JLabel(size[0]+" x "+size[1]);
		add(imageLabel);
		add(typeNameLabel);
		add(sizeLabel);
		setSize(135, 32);
		imageLabel.setBounds(0, 0, 32, 32);
		typeNameLabel.setBounds(32, 0, 103, 16);
		sizeLabel.setBounds(32, 16, 103, 16);
		DndHandler.addUnitDragSource(this);
	}
	/**
	 * 获取Unit对象
	 * @return Unit
	 */
	public Unit getUnit() {
		return unit;
	}
	/**
	 * 获取Unit对象的具体类型
	 * @return (String)Type
	 */
	public String getUnitType(){
		return unit.getType();
	}
	/**
	 * 获取Unit对象的子类型
	 * @return 
	 */
	public TypeEnum getTypeEnum(){
		if(unit instanceof Abiotic) return TypeEnum.ABIOTIC;
		if(unit instanceof Creature) return TypeEnum.CREATURE;
		return null;
	}
}
