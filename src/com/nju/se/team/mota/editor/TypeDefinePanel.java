package com.nju.se.team.mota.editor;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.nju.se.team.mota.temp.ListPanel;

public class TypeDefinePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel leftPanel;
		JPanel left_topPanel;
			JLabel typeLabel;
			JComboBox<String> typeComboBox;
		ListPanel left_centerPanel;

		ListPanel left_buttomPanel;
			
	JPanel rightPanel;
		JPanel right_topPanel;
			ListPanel right_top_leftPanel;
			ListPanel right_top_rightPanel;
		JPanel right_centerPanel;
		ListPanel right_buttomPanel;
	public TypeDefinePanel() {
		super(null);
		leftPanel = new JPanel(null);
			left_topPanel = new JPanel(null);
				typeLabel = new JLabel("������:");
				typeComboBox = new JComboBox<String>(new String[]{"����","����"});
			left_centerPanel = new ListPanel("��������");

			left_buttomPanel = new ListPanel("�ű��б�");
			
		
		rightPanel = new JPanel(null);
			right_topPanel = new JPanel(null);
				right_top_leftPanel = new ListPanel("״̬�б�");
				right_top_rightPanel = new ListPanel("֡�б�");
			right_centerPanel = new JPanel(null);
			right_buttomPanel = new ListPanel("ͼƬ�ز�");
		
		add(leftPanel);
			leftPanel.add(left_topPanel);
				left_topPanel.add(typeLabel);
				left_topPanel.add(typeComboBox);
			leftPanel.add(left_centerPanel);
			leftPanel.add(left_buttomPanel);
		add(rightPanel);
			rightPanel.add(right_topPanel);
				right_topPanel.add(right_top_leftPanel);
				right_topPanel.add(right_top_rightPanel);
			rightPanel.add(right_centerPanel);
			rightPanel.add(right_buttomPanel);
		setSize(960, 640);
		setPreferredSize(getSize());
		calcLayout();
	}
	public void calcLayout(){
		leftPanel.setBounds(0, 0, 320, 640);
		rightPanel.setBounds(320, 0, 640 ,640);
		
		left_topPanel.setBounds(0, 0, 320, 30);
		left_centerPanel.setBounds(0, 30, 320, 305);
		left_buttomPanel.setBounds(0, 335, 320, 305);
		
		typeLabel.setBounds(0, 0, 100, 30);
		typeComboBox.setBounds(100, 0, 220, 30);
		
		right_topPanel.setBounds(0, 0, 640, 160);
		right_centerPanel.setBounds(0, 160, 640, 320);
		right_buttomPanel.setBounds(0, 480, 640, 160);
		
		right_top_leftPanel.setBounds(0, 0, 320, 160);
		right_top_rightPanel.setBounds(320, 0, 320, 160);
	}
}
