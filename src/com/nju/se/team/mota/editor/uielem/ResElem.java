package com.nju.se.team.mota.editor.uielem;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.nju.se.team.mota.data.ImageLoader;
import com.nju.se.team.mota.editor.dnd.DndHandler;
import com.nju.se.team.mota.util.ElemPanel;
/**
 * 图片素材库UI单元
 * @author linkeo
 * @author lzw
 *
 */
public class ResElem  extends ElemPanel {

	private static final long serialVersionUID = 1L;	
	JLabel value;
	BufferedImage res;
	String reskey;
	/**
	 * 构造函数
	 * @param reskey 图片对应的标识key
	 */
	public ResElem(String reskey) {
		super(false);
		this.res = ImageLoader.get(reskey);
		this.reskey = reskey;
		this.value = new JLabel(new ImageIcon(res));
		this.value.setText(reskey.split("_")[1]);
		this.value.setVerticalTextPosition(JLabel.BOTTOM);
		this.value.setHorizontalTextPosition(JLabel.CENTER);
		this.value.setOpaque(false);
		add(this.value);
		setSize(this.value.getPreferredSize());
		this.value.setLocation(0, 0);
		this.value.setSize(this.value.getPreferredSize());
		DndHandler.addResDragSource(this);
	}
	/**
	 * 获取图片标识key
	 * @return reskey 
	 */
	public String getReskey(){
		return reskey;
	}
	
}
