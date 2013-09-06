package com.nju.se.team.mota.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.nju.se.team.mota.data.ImageLoader;
import com.nju.se.team.mota.editor.uielem.ActionElem;
import com.nju.se.team.mota.editor.uielem.ResElem;
import com.nju.se.team.mota.editor.uielem.SettingCheckElem;
import com.nju.se.team.mota.editor.uielem.SettingComboElem;
import com.nju.se.team.mota.editor.uielem.SettingElem;
import com.nju.se.team.mota.editor.uielem.SettingIntegerElem;
import com.nju.se.team.mota.editor.uielem.SettingPointItem;
import com.nju.se.team.mota.editor.uielem.SettingTextElem;
import com.nju.se.team.mota.editor.uielem.StatusElem;
import com.nju.se.team.mota.game.util.Condition;
import com.nju.se.team.mota.game.util.TypeEnum;
import com.nju.se.team.mota.util.ElemPanel;
import com.nju.se.team.mota.util.ListPanel;

public class TypeDefinePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel leftPanel;
		JPanel left_topPanel;
			JLabel typeLabel;
			JComboBox<TypeEnum> typeComboBox;
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
			SettingIntegerElem setHP;
			SettingIntegerElem setATK;
			SettingIntegerElem setDEF;
			SettingIntegerElem setMoney;
			SettingIntegerElem setEXP;
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
				typeLabel = new JLabel("������:");
				typeComboBox = new JComboBox<TypeEnum>(TypeEnum.values());
				subTypeLabel = new JLabel("����:");
				subTypeComboBox = new JComboBox<String>(new String[]{"Wall","StairUp"});
			typeEditPanel = new ListPanel("��������");
					JPanel lcph = new JPanel();
					lcph.setLayout(new BoxLayout(lcph, BoxLayout.X_AXIS));
					lcph.add(typeAddButton = new JButton("�½�"));
					lcph.add(Box.createGlue());
					lcph.add(new JLabel("��������"));
					lcph.add(Box.createGlue());
					lcph.add(typeDeleteButton = new JButton("ɾ��"));
					lcph.add(typeSaveButton = new JButton("����"));
			typeEditPanel.setColumnHeaderView(lcph);

			actionListPanel = new ListPanel("�ű��б�");
					JPanel lbph = new JPanel();
					lbph.setLayout(new BoxLayout(lbph, BoxLayout.X_AXIS));
					lbph.add(actionAddButton = new JButton("�½�"));
					lbph.add(actionEditButton = new JButton("�༭"));
					lbph.add(Box.createGlue());
					lbph.add(new JLabel("�ű��б�"));
					lbph.add(Box.createGlue());
					lbph.add(actionDeleteButton = new JButton("ɾ��"));
					lbph.add(actionSaveButton = new JButton("����"));
			actionListPanel.setColumnHeaderView(lbph);
					
			
		
		rightPanel = new JPanel(null);
			right_topPanel = new JPanel(null);
				statusListPanel = new ListPanel("״̬�б�");
						JPanel slph = new JPanel();
						slph.setLayout(new BoxLayout(slph, BoxLayout.Y_AXIS));
						slph.add(statusAddButton = new JButton("���"));
						slph.add(statusDeleteButton = new JButton("�Ƴ�"));
						statusListPanel.setRowHeaderView(slph);
				frameListPanel = new ListPanel("֡�б�");
					JPanel flph = new JPanel();
					flph.setLayout(new BoxLayout(flph, BoxLayout.Y_AXIS));
					flph.add(frameAddButton = new JButton("����"));
					flph.add(frameDeleteButton = new JButton("����"));
					frameListPanel.setRowHeaderView(flph);
			frameHolder = new FrameHolder();
			resListPanel = new ListPanel("ͼƬ�ز�");
		
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
		
		
		
		actionListPanel.add(new ActionElem(Condition.CRASH, "open"));
		actionListPanel.add(new ActionElem(Condition.CLOSETO, "close"));
		
		ArrayList<String> keys = new ArrayList<String>(ImageLoader.getKeySet());
		Collections.sort(keys);
		for(String s : keys)
			resListPanel.add(new ResElem(s));
		
		addListeners();
		typeSelected((TypeEnum) typeComboBox.getSelectedItem());
	}
	public void addType(){
		TypeEnum type = (TypeEnum) typeComboBox.getSelectedItem();
		setname.setValue("new");
		setsize.setValue(1,1);
		setbt.setValue("none");
		if(type == TypeEnum.ABIOTIC)
			setcangt.setValue(false);
		if(type == TypeEnum.CREATURE){
			setHP.setValue(10);
			setATK.setValue(10);
			setDEF.setValue(10);
			setMoney.setValue(10);
			setEXP.setValue(10);
		}
		
	}
	public void typeSelected(TypeEnum type){
		typeEditPanel.removeAll();
		
		typeEditPanel.add(setname = new SettingTextElem("������:", "new"));
		typeEditPanel.add(setsize = new SettingPointItem("�ߴ�:", 1, 1, 1, 5, 1, 5));
		typeEditPanel.add(setbt = new SettingComboElem<String>("�������:", "none", new String[]{"none","Wall","StairUp"}));
		if(type == TypeEnum.ABIOTIC)
			typeEditPanel.add(setcangt =  new SettingCheckElem("�ɴ�͸:", false));
		if(type == TypeEnum.CREATURE){
			typeEditPanel.add(setHP = new SettingIntegerElem("Ѫ��:", 10, 1, 99999999));
			typeEditPanel.add(setATK = new SettingIntegerElem("������:", 10, 1, 99999999));
			typeEditPanel.add(setDEF = new SettingIntegerElem("������:", 10, 1, 99999999));
			typeEditPanel.add(setMoney = new SettingIntegerElem("��Ǯ:", 10, 1, 99999999));
			typeEditPanel.add(setEXP = new SettingIntegerElem("����:", 10, 1, 99999999));
		}

		setsize.addChangeListenerToSpinners(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				frameHolder.setGridSize(setsize.getXValue(), setsize.getYValue());
			}
		});
		
	}
	
	public void addListeners(){
		
		statusAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				statusListPanel.add(new StatusElem("normal"));
			}
		});
		
		typeComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {

				if(e.getStateChange()!=ItemEvent.SELECTED){
					typeSelected((TypeEnum) typeComboBox.getSelectedItem());
				}
			}
		});
		
		typeAddButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addType();
			}
		});
		
		actionAddButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionElem elem = ActionEditDialog.editAction(TypeDefinePanel.this);
				if(elem.getAction()==null||elem.getAction().equals(""))
					return;
				actionListPanel.add(elem);
			}
		});
		
		actionDeleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(ElemPanel elem : actionListPanel.getSelectedElems())
					actionListPanel.remove(elem);
			}
		});
		
		actionEditButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionElem selectedElem = (ActionElem) actionListPanel.getSelectedElem();
				if(selectedElem == null) return;
				ActionElem elem = ActionEditDialog.editAction(TypeDefinePanel.this, selectedElem);
				if(elem.getAction()==null||elem.getAction().equals(""))
					return;
				actionListPanel.remove(selectedElem);
				actionListPanel.add(elem);
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
