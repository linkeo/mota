package com.nju.se.team.mota.editor.uielem;

import javax.swing.JLabel;

/**
 * ����ͼƬ��UI��Ԫ
 * @author linkeo
 * @author lzw
 *
 */
public class FrameGridElem extends JLabel {
	
	private static final long serialVersionUID = 1L;
	private String key;
	/**
	 * ���췽��
	 */
	public FrameGridElem() {
	}
	/**
	 * ��ȡͼƬ�ı�ʶkey
	 * @return key ͼƬ��ʶ
	 */
	public String getKey() {
		return key;
	}
	/**
	 * ���øõ�Ԫ��Ӧ��ͼƬ�ı�ʶkey
	 * @param key ͼƬ��ʶ
	 */
	public void setKey(String key) {
		this.key = key;
	}
}
