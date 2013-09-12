package com.nju.se.team.mota.editor;

import java.util.EventObject;

import com.nju.se.team.mota.game.unit.Unit;
/**
 * ��ͼ��ק�¼�
 * @author linkeo
 * @author lzw
 */
public class MapDropEvent extends EventObject {
	int x, y;
	/**
	 * ���췽��
	 * @param sourse ��ק��Unit��Ԫ
	 * @param x ��ק�ĵ���λ�ú�����
	 * @param y	��ק�ĵ���λ��������
	 */
	public MapDropEvent(Unit sourse, int x, int y) {
		super(sourse);
		this.x = x;
		this.y = y;
	}
	/**
	 * ��ȡ��ק��Unit��Ԫ
	 * @return Unit
	 */
	public Unit getSource(){
		return (Unit) source;
	}
	/**
	 * ��ȡ��ק�ĵ���λ�ú�����
	 * @return ������(int)
	 */
	public int getX() {
		return x;
	}
	/**
	 * ��ȡ��ק�ĵ���λ��������
	 * @return ������(int)
	 */
	public int getY() {
		return y;
	}
	private static final long serialVersionUID = 1L;


}
