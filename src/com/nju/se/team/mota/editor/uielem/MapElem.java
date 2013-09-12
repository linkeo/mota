package com.nju.se.team.mota.editor.uielem;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nju.se.team.mota.editor.MapDropEvent;
import com.nju.se.team.mota.editor.MapDropListener;
import com.nju.se.team.mota.editor.MapItemListener;
import com.nju.se.team.mota.editor.dnd.DndHandler;
import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Unit;
import com.nju.se.team.mota.game.util.TypeEnum;
import com.nju.se.team.mota.game.util.UnitStatus;

/**
 * ��ͼ��UI��Ԫ
 * @author linkeo
 * @author lzw
 * 
 *
 */
public class MapElem extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	private ArrayList<MapDropListener> mapDropListeners = new ArrayList<MapDropListener>();
	Unit unit;
	TypeEnum type;
	int x,  y;
	int floor;
	private JLabel back, front;
	/**
	 * ���췽��
	 * @param x ��Ԫ������
	 * @param y ��Ԫ������
	 * @param floor ��Ԫ����¥��
	 */
	public MapElem(int x, int y, int floor){
		super(null);
		this.x = x;
		this.y = y;
		this.setSize(32, 32);
		this.setPreferredSize(getSize());
		back = new JLabel();
		front = new JLabel();
		add(front);
		add(back);
		back.setSize(32, 32);
		back.setPreferredSize(getSize());
		front.setSize(32, 32);
		front.setPreferredSize(getSize());
		DndHandler.addUnitDropTarget(this);
		this.addMouseListener(this);
	}
	/**
	 * ���õ�Ԫ��Ӧ�Ķ���
	 * @param u �µ�Unit����
	 * @param x	��ͼ��Ԫ��Ӧ��Unit�����1x1�Ӷ���ĺ�����
	 * @param y	��ͼ��Ԫ��Ӧ��Unit�����1x1�Ӷ����������
	 */
	public void setUnit(Unit u, int x, int y){
		unit = u;
		if(u==null){
			front.setIcon(null);
		}else{
			if(u instanceof Abiotic)
				type = TypeEnum.ABIOTIC;
			if(u instanceof Creature)
				type = TypeEnum.CREATURE;
			front.setIcon(new ImageIcon(u.getSprites().get(UnitStatus.NORMAL).currImage()[x][y]));
		}
	}
	/**
	 * ���õ�Ԫ��Ӧ�ı�������
	 * @param u �µı���Unit����
	 * @param x ��ͼ��Ԫ��Ӧ��Unit���������1x1�Ӷ���ĺ�����
	 * @param y ��ͼ��Ԫ��Ӧ��Unit���������1x1�Ӷ����������
	 */
	public void setUnitBackground(Unit u, int x, int y) {
		unit = u;
		if(u instanceof Abiotic)
			type = TypeEnum.ABIOTIC;
		if(u instanceof Creature)
			type = TypeEnum.CREATURE;
		back.setIcon(new ImageIcon(u.getSprites().get(UnitStatus.NORMAL).currImage()[x][y]));
	}
	/**
	 * ��ȡ��ͼ��Ԫ��Ӧ��Unit����
	 * @return Unit����
	 */
	public Unit getUnit(){
		return unit;
	}
	/**
	 * ��ȡ��Unit����ľ�������
	 * @return Unit������
	 */
	public TypeEnum getType(){
		return type;
	}
	/**
	 * �����קListener
	 * @param mapDroplistener
	 */
	public void addMapDropListener(MapDropListener mapDroplistener){
		mapDropListeners.add(mapDroplistener);
	}
	/**
	 * ɾ����קListener
	 * @param mapDroplistener
	 */
	public void removeMapDropListener(MapDropListener mapDroplistener){
		mapDropListeners.remove(mapDroplistener);
	}
	/**
	 * ��ק����
	 * @param u Unit����
	 */
	public void dropped(Unit u){
		MapDropEvent e = new MapDropEvent(u, x, y);
		for(MapDropListener l : mapDropListeners)
			l.drop(e);
	}
	MapItemListener mil;
	/**
	 * ���õ����ȡ��Unit�����Listener
	 * @param mapItemListener
	 */
	public void setMapItemListener(MapItemListener mapItemListener) {
		mil = mapItemListener;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(mil!=null)
			mil.mapItemSelected(getUnit());
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
