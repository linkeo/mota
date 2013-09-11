package com.nju.se.team.mota.editor.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import com.nju.se.team.mota.game.unit.Unit;

public class UnitTransferable implements Transferable{

	private Unit unit;
	public UnitTransferable(Unit unit ) {
		this.unit = unit;
	}
	DataFlavor[] flavors = { MyDataFlavors.getUnitFlavor() };
	public DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}
	public boolean isDataFlavorSupported( DataFlavor flavor ) {
		for( DataFlavor df : flavors ) {
			if( df.equals( flavor ) ) {
				return true;
			}
		}
		return false;
	}
	public Object getTransferData( DataFlavor df ) throws UnsupportedFlavorException, IOException {
		if(df == MyDataFlavors.getUnitFlavor())
			return unit;
		return null;
	}
}
