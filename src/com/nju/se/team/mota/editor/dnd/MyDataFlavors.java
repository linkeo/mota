package com.nju.se.team.mota.editor.dnd;

import java.awt.datatransfer.DataFlavor;


public class MyDataFlavors {
	private static DataFlavor unitFlavor;
	public static DataFlavor getUnitFlavor(){
		if(unitFlavor==null) {
			try {
				unitFlavor = new DataFlavor(DataFlavor.javaSerializedObjectMimeType+";class=com.nju.se.team.mota.game.unit.Unit");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return unitFlavor;
	}
	
}
