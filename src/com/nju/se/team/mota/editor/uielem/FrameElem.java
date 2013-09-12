package com.nju.se.team.mota.editor.uielem;

import javax.swing.JLabel;

import com.nju.se.team.mota.util.ElemPanel;
/**
 * 帧列表的UI单元
 * @author linkeo
 * @author lzw
 *
 */
public class FrameElem extends ElemPanel {

	private static final long serialVersionUID = 1L;
	JLabel value;
	int intvalue;
	String[][] imagekey;
	/**
	 * 构造方法
	 * @param value 帧数
	 * @param image	帧图片
	 */
	public FrameElem(int value, String[][] image) {
		super(true);
		intvalue = value;
		imagekey = image;
		this.value = new JLabel(" "+value+" ");
		this.value.setOpaque(false);
		add(this.value);
		setSize(this.value.getPreferredSize());
		this.value.setLocation(0, 0);
		this.value.setSize(this.value.getPreferredSize());
	}
	/**
	 * 获取帧数
	 * @return 帧数
	 */
	public int getValue(){
		return intvalue;
	}
	/**
	 * 获取帧图片
	 * @return 帧对应图片
	 */
	public String[][] getImageKey(){
		return imagekey;
	}
	/**
	 * 设置帧图片
	 * @param imagekey 帧图片
	 */
	public void setImageKey(String[][] imagekey) {
		this.imagekey = imagekey;
	}
	
}
