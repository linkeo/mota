package com.nju.se.team.mota.editor;

import java.util.Collection;

import com.nju.se.team.mota.editor.uielem.MapElem;
import com.nju.se.team.mota.game.unit.Unit;

public interface MapItemListener {
	public void mapItemSelected(Unit u);
	public void mapItemsUpdated(Collection<Unit> units);
	public void mapItemHighlighted(MapElem mapElem);
}
