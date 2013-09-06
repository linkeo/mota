package com.nju.se.team.mota.editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameHolder extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel content[][];
	int col, row;
	public FrameHolder() {
		setLayout(null);
		content = new JLabel[5][5];
		col = row = 1;
		for(int i = 0; i < 5; ++i)
			for(int j = 0; j < 5; ++j){
				content[i][j] = new JLabel();
				JLabel temp = content[i][j];
				temp.setSize(32, 32);
				temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				temp.setBackground(Color.WHITE);
				temp.setOpaque(true);
				add(temp);
			}
		setGridSize(row, col);
	}
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
	
	// (size.width-row*32)/2
	public void adjustLayout(){
		Dimension size = getSize();
		for(int i = 0; i < row; ++i)
			for(int j = 0; j < col; ++j)
				content[i][j].setLocation((size.width-row*32)/2+32*i, (size.height-col*32)/2+32*j);
	}
	@Override
	public void paint(Graphics g) {
		adjustLayout();
		super.paint(g);
	}
}
