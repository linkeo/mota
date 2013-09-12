package com.nju.se.team.mota.editor.dnd;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;

import com.nju.se.team.mota.editor.uielem.MapElem;
import com.nju.se.team.mota.game.unit.Unit;

/**
 * 游戏单元(Unit)Drop监听器, 包含数据处理过程
 * @author Linkeo
 *
 */
public class UnitDropTargetListener implements DropTargetListener {

	public void drop( DropTargetDropEvent e ) {
		Transferable t = e.getTransferable();
		try {
			if( t.isDataFlavorSupported( MyDataFlavors.getUnitFlavor() ) ) {
				Unit u = (Unit) t.getTransferData(MyDataFlavors.getUnitFlavor());
				DropTarget dt = (DropTarget)e.getSource();
				MapElem d = ( MapElem )dt.getComponent();//获取拖拽目的组件
				d.dropCopy(u);
//				if((e.getDropAction()&DnDConstants.ACTION_MOVE)!=0){
//					System.out.println("T_MOVE");
//				}else if((e.getDropAction()&DnDConstants.ACTION_COPY)!=0){
//					System.out.println("T_COPY");
//				}
				e.dropComplete(true);
				return;
			}
		} catch( IOException ioe ) {
			ioe.printStackTrace();
		} catch( UnsupportedFlavorException ufe ) {
			ufe.printStackTrace();
		}
		e.dropComplete(false);
	}
	
	public void dragEnter( DropTargetDragEvent e ) {
		DropTarget dt = (DropTarget)e.getSource();
		MapElem d = ( MapElem )dt.getComponent();//获取拖拽目的组件
		d.highlight();
	}
	public void dragOver( DropTargetDragEvent e ) {}
	public void dropActionChanged( DropTargetDragEvent e ) {}
	public void dragExit( DropTargetEvent e ) {}

}
