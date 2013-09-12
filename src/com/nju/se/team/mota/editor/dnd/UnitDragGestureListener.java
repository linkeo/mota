package com.nju.se.team.mota.editor.dnd;

import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import com.nju.se.team.mota.editor.uielem.UnitElem;
import com.nju.se.team.mota.game.unit.Unit;
/**
 * 游戏单元(Unit)Drag监听器, 包含数据准备过程
 * @author Linkeo
 *
 */
public class UnitDragGestureListener implements DragGestureListener{

	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
		UnitElem e = (UnitElem) dge.getComponent();
		Unit u = e.getUnit().clone();
		UnitTransferable unit = new UnitTransferable(u);
		dge.startDrag( DragSource.DefaultCopyDrop, unit, new CopyDragSourceListener() );//开始拖拽过程
	}

}
