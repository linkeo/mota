package com.nju.se.team.mota.editor;

import java.util.Collection;

import com.nju.se.team.mota.game.unit.Unit;
/**
 * ��ͼ��Ԫ��Ϣ������
 * @author soft
 *
 */
public interface MapItemListener {
	public void mapItemSelected(Unit u);
	public void mapItemsUpdated(Collection<Unit> units);
}
