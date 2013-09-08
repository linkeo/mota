package com.nju.se.team.mota.editor.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import com.nju.se.team.mota.data.ImageLoader;
import com.nju.se.team.mota.editor.FrameHolder;
import com.nju.se.team.mota.editor.uielem.FrameGridElem;

public class ResDropTargetListener implements DropTargetListener {

	public void drop( DropTargetDropEvent e ) {
		Transferable t = e.getTransferable();
		String s;
		try {
			if( t.isDataFlavorSupported( DataFlavor.stringFlavor ) ) {
				s = (String) t.getTransferData( DataFlavor.stringFlavor );
				DropTarget dt = (DropTarget)e.getSource();
				FrameGridElem d = ( FrameGridElem )dt.getComponent();
				d.setIcon(new ImageIcon(ImageLoader.get(s)));
				d.setKey(s);
				FrameHolder fh = (FrameHolder) d.getParent();
				fh.refresh();
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
