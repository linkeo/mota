package com.nju.se.team.mota.editor.uielem;

import javax.swing.JLabel;

/**
 * 放置图片的UI单元
 * @author linkeo
 * @author lzw
 *
 */
public class FrameGridElem extends JLabel {
	
	private static final long serialVersionUID = 1L;
	private String key;
	/**
	 * 构造方法
	 */
	public FrameGridElem() {
	}
	/**
	 * 获取图片的标识key
	 * @return key 图片标识
	 */
	public String getKey() {
		return key;
	}
	/**
	 * 设置该单元对应的图片的标识key
	 * @param key 图片标识
	 */
	public void setKey(String key) {
		this.key = key;
	}
}
