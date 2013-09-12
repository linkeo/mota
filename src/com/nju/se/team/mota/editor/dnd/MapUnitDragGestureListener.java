package com.nju.se.team.mota.editor.dnd;

import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import com.nju.se.team.mota.editor.uielem.MapElem;
import com.nju.se.team.mota.game.unit.Unit;

public class MapUnitDragGestureListener implements DragGestureListener{

	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
		MapElem e = (MapElem) dge.getComponent();
		Unit u = e.getUnit();
		UnitTransferable unit = new UnitTransferable(u);
		dge.startDrag( DragSource.DefaultCopyDrop, unit, new CopyDragSourceListener() );//开始拖拽过程
	}

}
