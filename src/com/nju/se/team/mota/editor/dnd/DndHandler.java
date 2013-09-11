package com.nju.se.team.mota.editor.dnd;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;

import javax.swing.JLabel;

import com.nju.se.team.mota.editor.MapElem;
import com.nju.se.team.mota.editor.uielem.ResElem;
import com.nju.se.team.mota.editor.uielem.UnitElem;

public class DndHandler {
	public static void addResDragSource(ResElem elem){
		DragSource dragSource = DragSource.getDefaultDragSource();
		dragSource.createDefaultDragGestureRecognizer(elem, DnDConstants.ACTION_COPY_OR_MOVE, new ResDragGestureListener());
	}
	public static void addUnitDragSource(UnitElem elem){
		DragSource dragSource = DragSource.getDefaultDragSource();
		dragSource.createDefaultDragGestureRecognizer(elem, DnDConstants.ACTION_COPY_OR_MOVE, new UnitDragGestureListener());
	}
	public static void addResDropTarget(JLabel label){
		new DropTarget( label, new ResDropTargetListener() );
	}
	public static void addUnitDropTarget(MapElem elem){
		new DropTarget( elem, new UnitDropTargetListener() );
	}
	
}
