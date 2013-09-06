package com.nju.se.team.mota.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.nju.se.team.mota.data.ImageLoader;
import com.nju.se.team.mota.editor.uielem.SettingCheckElem;
import com.nju.se.team.mota.editor.uielem.SettingComboElem;
import com.nju.se.team.mota.editor.uielem.SettingElem;
import com.nju.se.team.mota.editor.uielem.SettingPointItem;
import com.nju.se.team.mota.editor.uielem.SettingTextElem;
import com.nju.se.team.mota.util.ListPanel;

public class TypeDefinePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel leftPanel;
		JPanel left_topPanel;
			JLabel typeLabel;
			JComboBox<String> typeComboBox;
			JLabel subTypeLabel;
			JComboBox<String> subTypeComboBox;
		ListPanel typeEditPanel;
			JButton typeAddButton;
			JButton typeDeleteButton;
			JButton typeSaveButton;
			SettingTextElem setname;
			SettingCheckElem setcangt;
			SettingPointItem setsize;
			SettingComboElem<String> setbt;
		ListPanel actionListPanel;
			JButton actionAddButton;
			JButton actionEditButton;
			JButton actionDeleteButton;
			JButton actionSaveButton;
			
			
	JPanel rightPanel;
		JPanel right_topPanel;
			ListPanel statusListPanel;
				JButton statusAddButton;
				JButton statusDeleteButton;
			ListPanel frameListPanel;
				JButton frameAddButton;
				JButton frameDeleteButton;
		FrameHolder frameHolder;
		ListPanel resListPanel;
		
		
	ArrayList<SettingElem> settingElems = new ArrayList<SettingElem>();
		
	public TypeDefinePanel() {
		super(null);
		leftPanel = new JPanel(null);
			left_topPanel = new JPanel(null);
				typeLabel = new JLabel("类型组:");
				typeComboBox = new JComboBox<String>(new String[]{"生物","静物"});
				subTypeLabel = new JLabel("类型:");
				subTypeComboBox = new JComboBox<String>(new String[]{"Wall","StairUp"});
			typeEditPanel = new ListPanel("属性设置");
					JPanel lcph = new JPanel();
					lcph.setLayout(new BoxLayout(lcph, BoxLayout.X_AXIS));
					lcph.add(typeAddButton = new JButton("新建"));
					lcph.add(Box.createGlue());
					lcph.add(new JLabel("属性设置"));
					lcph.add(Box.createGlue());
					lcph.add(typeDeleteButton = new JButton("删除"));
					lcph.add(typeSaveButton = new JButton("保存"));
			typeEditPanel.setColumnHeaderView(lcph);

			actionListPanel = new ListPanel("脚本列表");
					JPanel lbph = new JPanel();
					lbph.setLayout(new BoxLayout(lbph, BoxLayout.X_AXIS));
					lbph.add(actionAddButton = new JButton("新建"));
					lbph.add(actionEditButton = new JButton("编辑"));
					lbph.add(Box.createGlue());
					lbph.add(new JLabel("脚本列表"));
					lbph.add(Box.createGlue());
					lbph.add(actionDeleteButton = new JButton("删除"));
					lbph.add(actionSaveButton = new JButton("保存"));
			actionListPanel.setColumnHeaderView(lbph);
					
			
		
		rightPanel = new JPanel(null);
			right_topPanel = new JPanel(null);
				statusListPanel = new ListPanel("状态列表");
						JPanel slph = new JPanel();
						slph.setLayout(new BoxLayout(slph, BoxLayout.Y_AXIS));
						slph.add(statusAddButton = new JButton("添加"));
						slph.add(statusDeleteButton = new JButton("移除"));
						statusListPanel.setRowHeaderView(slph);
				frameListPanel = new ListPanel("帧列表");
					JPanel flph = new JPanel();
					flph.setLayout(new BoxLayout(flph, BoxLayout.Y_AXIS));
					flph.add(frameAddButton = new JButton("增加"));
					flph.add(frameDeleteButton = new JButton("减少"));
					frameListPanel.setRowHeaderView(flph);
			frameHolder = new FrameHolder();
			resListPanel = new ListPanel("图片素材");
		
		add(leftPanel);
			leftPanel.add(left_topPanel);
				left_topPanel.add(typeLabel);
				left_topPanel.add(typeComboBox);
				left_topPanel.add(subTypeLabel);
				left_topPanel.add(subTypeComboBox);
			leftPanel.add(typeEditPanel);
			leftPanel.add(actionListPanel);
		add(rightPanel);
			rightPanel.add(right_topPanel);
				right_topPanel.add(statusListPanel);
				right_topPanel.add(frameListPanel);
			rightPanel.add(frameHolder);
			rightPanel.add(resListPanel);
		setSize(960, 640);
		setPreferredSize(getSize());
		calcLayout();
		

		typeEditPanel.add(setname = new SettingTextElem("类型名:", "Wall"));
		typeEditPanel.add(setcangt =  new SettingCheckElem("可穿透:", false));
		typeEditPanel.add(setsize = new SettingPointItem("尺寸:", 1, 1, 1, 5, 1, 5));
		typeEditPanel.add(setbt = new SettingComboElem<String>("配对类型:", "Wall", new String[]{"Wall","StairUp"}));
		
		
		actionListPanel.add(new ActionElem("crash", "open"));
		actionListPanel.add(new ActionElem("leave", "close"));
		
		ArrayList<String> keys = new ArrayList<String>(ImageLoader.getKeySet());
		Collections.sort(keys);
		for(String s : keys)
			resListPanel.add(new ResElem(ImageLoader.get(s)));
		
		addListeners();
	}
	
	public void addListeners(){
		
		statusAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				statusListPanel.add(new StatusElem("normal"));
			}
		});
		
		setsize.addChangeListenerToSpinners(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				frameHolder.setGridSize(setsize.getXValue(), setsize.getYValue());
			}
		});
	}
	
	public void calcLayout(){
		leftPanel.setBounds(0, 0, 320, 640);
		rightPanel.setBounds(320, 0, 640 ,640);
		
		left_topPanel.setBounds(0, 0, 320, 60);
		typeEditPanel.setBounds(0, 60, 320, 290);
		actionListPanel.setBounds(0, 350, 320, 290);
		
		typeLabel.setBounds(10, 0, 100, 20);
		typeComboBox.setBounds(110, 0, 200, 20);
		subTypeLabel.setBounds(10, 30, 100, 20);
		subTypeComboBox.setBounds(110, 30, 200, 20);
		
		right_topPanel.setBounds(0, 0, 640, 160);
		frameHolder.setBounds(0, 160, 640, 190);
		resListPanel.setBounds(0, 350, 640, 290);
		
		statusListPanel.setBounds(0, 0, 320, 160);
		frameListPanel.setBounds(320, 0, 320, 160);
	}
}
