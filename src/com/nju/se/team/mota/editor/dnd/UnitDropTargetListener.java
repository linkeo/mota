package com.nju.se.team.mota.editor.dnd;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;

import com.nju.se.team.mota.editor.MapElem;
import com.nju.se.team.mota.game.unit.Unit;

public class UnitDropTargetListener implements DropTargetListener {

	public void drop( DropTargetDropEvent e ) {
		Transferable t = e.getTransferable();
		try {
			if( t.isDataFlavorSupported( MyDataFlavors.getUnitFlavor() ) ) {
				Unit u = (Unit) t.getTransferData(MyDataFlavors.getUnitFlavor());
				DropTarget dt = (DropTarget)e.getSource();
				MapElem d = ( MapElem )dt.getComponent();//获取拖拽目的组件
				d.dropped(u);
			}
		} catch( IOException ioe ) {
			ioe.printStackTrace();
		} catch( UnsupportedFlavorException ufe ) {
			ufe.printStackTrace();
		}
	}
	
	public void dragEnter( DropTargetDragEvent e ) {}
	public void dragOver( DropTargetDragEvent e ) {}
	public void dropActionChanged( DropTargetDragEvent e ) {}
	public void dragExit( DropTargetEvent e ) {}

}
