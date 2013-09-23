package com.nju.se.team.mota.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nju.se.team.mota.data.ImageLoader;
import com.nju.se.team.mota.game.unit.Tool;

public class ToolsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Tool> tools;
	public ToolsPanel(ArrayList<Tool> tools) {
		super(null);
		this.tools = tools;
//		Tool t1 = new Tool();
//		t1.setImageKey("item1_2x2");
//		tools.add(t1);
//		tools.add(t1);
//		tools.add(t1);
//		tools.add(t1);
//		tools.add(t1);
//		tools.add(t1);
//		tools.add(t1);
//		tools.add(t1);
//		tools.add(t1);
//		tools.add(t1);
//		tools.add(t1);
//		tools.add(t1);
//		tools.add(t1);
//		tools.add(t1);
//		tools.add(t1);
//		tools.add(t1);

		setSize(new Dimension(320, 64));
		setBackground(Color.DARK_GRAY);
		addAllTools();
	}
	public void addAllTools(){
		for(int i=0;i<tools.size();i++){
			Tool t = tools.get(i);
			final JPanel tbb = new JPanel(null);
			tbb.setBackground(Color.DARK_GRAY);
			JLabel tbf = new JLabel(new ImageIcon(ImageLoader.get(t.getImageKey())));
			int y = i/10;
			if(y==0)
				tbb.setBounds(32*i, 0, 32, 32);
			else
				tbb.setBounds(32*(i%10), 32, 32, 32);
			tbf.setBounds(0, 0, 32, 32);
			tbf.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					tbb.setBackground(Color.DARK_GRAY);
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					tbb.setBackground(Color.LIGHT_GRAY);
				}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
				}
			});
			add(tbb);
			tbb.add(tbf);
		}
	}
}
