package com.nju.se.team.mota.editor.dnd;

import java.awt.datatransfer.DataFlavor;

/**
 * 此类包含Java的dnd包内没有预置的拖拽过程数据类型标示
 * @author Linkeo
 *
 */
public class MyDataFlavors {
	private static DataFlavor unitFlavor;
	/**
	 * Unit对象的类型标志, <b>单件模式</b>
	 * @return
	 */
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
