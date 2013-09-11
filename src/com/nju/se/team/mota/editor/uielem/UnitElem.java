package com.nju.se.team.mota.editor.uielem;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.nju.se.team.mota.editor.dnd.DndHandler;
import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Unit;
import com.nju.se.team.mota.game.util.TypeEnum;
import com.nju.se.team.mota.game.util.UnitStatus;
import com.nju.se.team.mota.util.ElemPanel;
import com.nju.se.team.mota.util.ImageHandler;

public class UnitElem extends ElemPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Unit unit;
	private JLabel imageLabel;
	private JLabel typeNameLabel;
	private JLabel sizeLabel;
	public UnitElem(Unit u) {
		super(false);
		this.unit = u;
		int[] size = u.getSize();
		
		BufferedImage bi = new BufferedImage(size[0]*32, size[1]*32, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = ImageHandler.getHQGraphics(bi);
		BufferedImage[][] bis = u.getSprites().get(UnitStatus.NORMAL).currImage();
		for(int i=0;i<size[0];++i)
			for(int j=0;j<size[1];++j){
				g.drawImage(bis[j][i], 32*j, 32*i, null);
			}
		g.dispose();
		
		imageLabel = new JLabel(new ImageIcon(ImageHandler.zoomFully(bi, 32, 32)));
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
	public Unit getUnit() {
		return unit;
	}
	public String getUnitType(){
		return unit.getType();
	}
	public TypeEnum getTypeEnum(){
		if(unit instanceof Abiotic) return TypeEnum.ABIOTIC;
		if(unit instanceof Creature) return TypeEnum.CREATURE;
		return null;
	}
}
