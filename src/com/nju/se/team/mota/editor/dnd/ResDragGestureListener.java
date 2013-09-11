package com.nju.se.team.mota.editor.dnd;

import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import com.nju.se.team.mota.editor.uielem.ResElem;

/**
 * 资源列表元素Drag监听器, 包含数据准备过程
 * @author Linkeo
 *
 */
public class ResDragGestureListener implements DragGestureListener {

	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
		ResElem e = (ResElem) dge.getComponent();
		String s = e.getReskey();
			StringSelection dragAndDropTransferable = new StringSelection(s);//准备数据
			dge.startDrag( DragSource.DefaultCopyDrop, dragAndDropTransferable, new CopyDragSourceListener() );//开始拖拽过程
	}

}