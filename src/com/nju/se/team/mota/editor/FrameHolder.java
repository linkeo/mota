package com.nju.se.team.mota.editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nju.se.team.mota.data.ImageLoader;
import com.nju.se.team.mota.editor.dnd.DndHandler;
import com.nju.se.team.mota.editor.uielem.FrameGridElem;
/**
 * ֡ͼƬ����
 * @author soft
 *
 */
public class FrameHolder extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FrameGridElem content[][];
	int col, row;
	private static final int BLOCK_SIZE = 34;
	/**
	 * ���췽��<br>
	 * ���Ϊ5x5,Ĭ��Ϊ1x1
	 */
	public FrameHolder() {
		setLayout(null);
		content = new FrameGridElem[5][5];
		col = row = 1;
		for(int i = 0; i < 5; ++i)
			for(int j = 0; j < 5; ++j){
				content[i][j] = new FrameGridElem();
				JLabel temp = content[i][j];
				temp.setSize(BLOCK_SIZE, BLOCK_SIZE);
				temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				temp.setBackground(Color.WHITE);
				temp.setOpaque(true);
				DndHandler.addResDropTarget(temp);
				add(temp);
			}
		setGridSize(row, col);
	}
	/**
	 * ����ͼƬ
	 * @param imagekey ͼƬ��ʶ(String[][])
	 */
	public void load(String[][] imagekey){
		clear();
		int x = imagekey.length;
		int y = 0;
		if(x>0)y=imagekey[0].length;
		for(int i=0;i<x;++i)
			for(int j=0;j<y;++j){
				String str = imagekey[i][j];
				if(str==null){
					content[i][j].setIcon(null);
					content[i][j].setKey(null);					
				}else{
					content[i][j].setIcon(new ImageIcon(ImageLoader.get(str)));
					content[i][j].setKey(str);				
				}
			}
	}
	/**
	 * ��ȡ��ǰͼƬ
	 * @return imagekey ͼƬ��ʶ(String[][])
	 */
	public String[][] getImageKey(){
		String[][] imagekey = new String[row][col];
		for(int i=0;i<row;++i)
			for(int j=0;j<col;++j)
				imagekey[i][j] = content[i][j].getKey();
		return imagekey;
	}
	/**
	 * ������ʾ��С
	 * @param row ����
	 * @param col ����
	 */
	public void setGridSize(int row, int col){
		this.row = row;
		this.col = col;
		for(int i = 0; i < 5; ++i)
			for(int j = 0; j < 5; ++j)
				content[i][j].setVisible(false);
		for(int i = 0; i < row; ++i)
			for(int j = 0; j < col; ++j)
				content[i][j].setVisible(true);
		adjustLayout();
	}
	/**
	 * ������ʾλ��
	 */
	// (size.width-row*BLOCK_SIZE)/2
	public void adjustLayout(){
		Dimension size = getSize();
		for(int i = 0; i < row; ++i)
			for(int j = 0; j < col; ++j)
				content[i][j].setLocation((size.width-row*BLOCK_SIZE)/2+BLOCK_SIZE*i, (size.height-col*BLOCK_SIZE)/2+BLOCK_SIZE*j);
	}
	@Override
	public void paint(Graphics g) {
		adjustLayout();
		super.paint(g);
	}
	
	
	private ArrayList<FrameEditListener> frameEditListerners = new ArrayList<FrameEditListener>();
	/**
	 * ���֡ͼƬ�仯������
	 * @param frameEditListener
	 * @return
	 */
	public boolean addFrameEditListener(FrameEditListener frameEditListener){
		return frameEditListerners.add(frameEditListener);
	}
	/**
	 * �Ƴ�֡ͼƬ�仯������
	 * @param frameEditListener
	 * @return
	 */
	public boolean removeFrameEditListener(FrameEditListener frameEditListener){
		return frameEditListerners.remove(frameEditListener);
	}
	/**
	 * ˢ��ͼƬ
	 */
	public void refresh() {
		for(FrameEditListener l : frameEditListerners) l.frameChanged();
	}
	/**
	 * ���ͼƬ
	 */
	public void clear() {
		for(int i=0;i<row;++i)
			for(int j=0;j<col;++j)
				content[i][j].setIcon(null);
	}
}
