package com.nju.se.team.mota.editor.dnd;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

/**
 * 基本的DragSourceListener, 只定制了光标显示规则, 与具体内容无关
 * <br>可无视
 * @author Linkeo
 *
 */
public class CopyDragSourceListener implements DragSourceListener {

	public void dragDropEnd( DragSourceDropEvent e ) {
//		if( e.getDropSuccess() ) {
//			int dropAction = e.getDropAction();
//			if( dropAction == DnDConstants.ACTION_MOVE ) {
//				System.out.println( "MOVE: remove node" );
//			}
//		}
	}
	/**
	 * 控制拖拽过程光标显示
	 */
	public void dragEnter( DragSourceDragEvent e ) {
		DragSourceContext context = e.getDragSourceContext();
		int dropAction = e.getDropAction();
		if( ( dropAction & DnDConstants.ACTION_COPY ) != 0 ) {
			context.setCursor( DragSource.DefaultCopyDrop );
		} else if( ( dropAction & DnDConstants.ACTION_MOVE ) != 0 ) {
			context.setCursor( DragSource.DefaultMoveDrop );
		} else {
			context.setCursor( DragSource.DefaultCopyNoDrop );
		}
	}
	public void dragExit( DragSourceEvent e ) {}
	public void dragOver( DragSourceDragEvent e ){}
	public void dropActionChanged( DragSourceDragEvent e ){}

}
