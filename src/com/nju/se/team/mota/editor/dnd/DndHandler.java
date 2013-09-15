package com.nju.se.team.mota.editor.dnd;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;

import javax.swing.JLabel;

import com.nju.se.team.mota.editor.uielem.MapElem;
import com.nju.se.team.mota.editor.uielem.ResElem;
import com.nju.se.team.mota.editor.uielem.UnitElem;

/**
 * DragAndDrop功能模块的对外显示部分, 包括向组件绑定Drag/Drop监听器的方法
 * @author Linkeo
 *
 */
public class DndHandler {
	public static void addResDragSource(ResElem elem){
		DragSource dragSource = DragSource.getDefaultDragSource();
		dragSource.createDefaultDragGestureRecognizer(elem, DnDConstants.ACTION_COPY, new ResDragGestureListener());
	}
	public static void addUnitDragSource(UnitElem elem){
		DragSource dragSource = DragSource.getDefaultDragSource();
		dragSource.createDefaultDragGestureRecognizer(elem, DnDConstants.ACTION_COPY, new UnitDragGestureListener());
	}
	public static void addMapUnitDragSource(MapElem elem){
		DragSource dragSource = DragSource.getDefaultDragSource();
		dragSource.createDefaultDragGestureRecognizer(elem, DnDConstants.ACTION_COPY_OR_MOVE, new MapUnitDragGestureListener());
	}
	public static void addResDropTarget(JLabel label){
		new DropTarget( label, new ResDropTargetListener() );
	}
	public static void addUnitDropTarget(MapElem elem){
		new DropTarget( elem, new MapUnitDropTargetListener() );
	}
	public static void addMapUnitDropTarget(MapElem elem){
		new DropTarget( elem, new MapUnitDropTargetListener() );
	}
	
}
