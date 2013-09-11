package com.nju.se.team.mota.editor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.json.JSONObject;

import com.nju.se.team.mota.data.DataLoader;
import com.nju.se.team.mota.editor.uielem.SettingComboElem;
import com.nju.se.team.mota.editor.uielem.SettingPointItem;
import com.nju.se.team.mota.editor.uielem.UnitElem;
import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.util.TypeEnum;
import com.nju.se.team.mota.util.ListPanel;

public class LevelEditPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MapPanel map;
	JScrollPane mapview;
	ListPanel levelInfoPanel;
		SettingComboElem<Integer> setLevel;
		SettingPointItem setsize;
		JButton levelAddButton;
		JButton levelDeleteButton;
		JButton levelReloadButton;
		JButton levelSaveButton;
	ListPanel unitListPanel;
		JComboBox<TypeEnum> unitTypeSelect;
	ListPanel unitInfoPanel;
	public LevelEditPanel(){
		super(null);
		mapview = new JScrollPane();
		levelInfoPanel = new ListPanel();
			setLevel = new SettingComboElem<Integer>("楼层", 1, DataLoader.getLevelFloors());
			setsize = new SettingPointItem("楼层尺寸", 18, 18, 1, 64, 1, 64);
			JPanel liph = new JPanel();
			liph.setLayout(new BoxLayout(liph, BoxLayout.X_AXIS));
			liph.add(levelAddButton = new JButton("添加"));
			liph.add(levelReloadButton = new JButton("还原"));
			liph.add(Box.createGlue());
			liph.add(new JLabel("楼层属性"));
			liph.add(Box.createGlue());
			liph.add(levelDeleteButton = new JButton("删除"));
			liph.add(levelSaveButton = new JButton("保存"));
		levelInfoPanel.setColumnHeaderView(liph);
		unitListPanel = new ListPanel();
			JPanel ulph = new JPanel();
			ulph.setLayout(new BoxLayout(ulph, BoxLayout.X_AXIS));
			ulph.add(Box.createGlue());
			ulph.add(new JLabel("单元列表"));
			ulph.add(Box.createGlue());
			ulph.add(unitTypeSelect = new JComboBox<TypeEnum>(TypeEnum.values()));
		unitListPanel.setColumnHeaderView(ulph);
		unitInfoPanel = new ListPanel("单元属性");
		
		add(mapview);
		add(levelInfoPanel);
			levelInfoPanel.add(setLevel);
			levelInfoPanel.add(setsize);
		add(unitListPanel);
		add(unitInfoPanel); 
		
		
		mapview.setViewportView(map = new MapPanel(18, 18, 100));
		mapview.setColumnHeaderView(map.getColumnHeader());
		mapview.setRowHeaderView(map.getRowHeader());
		
		calcLayout();
		addListener();
	}
	public void addListener(){
		unitTypeSelect(TypeEnum.ABIOTIC);
		unitTypeSelect.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()!=ItemEvent.SELECTED){
					unitTypeSelect((TypeEnum) unitTypeSelect.getSelectedItem());
				}
			}
		});
	}
	private void unitTypeSelect(TypeEnum selectedItem) {
		unitListPanel.removeAll();
		if(selectedItem == TypeEnum.ABIOTIC){
			for(String a : DataLoader.getAbioticTypes()){
				JSONObject json = DataLoader.getAbioticDefine(a);
				Abiotic abiotic = new Abiotic();
				abiotic.loadType(json);
				unitListPanel.add(new UnitElem(abiotic));
			}
		}
		if(selectedItem == TypeEnum.CREATURE){
			for(String a : DataLoader.getCreatureTypes()){
				JSONObject json = DataLoader.getCreatureDefine(a);
				Creature creature = new Creature();
				creature.loadType(json);
				unitListPanel.add(new UnitElem(creature));
			}
		}
	}
	private void calcLayout(){
		mapview.setBounds(0, 0, 640, 640);
		levelInfoPanel.setBounds(640, 0, 320, 100);
		unitListPanel.setBounds(640, 100, 320, 320);
		unitInfoPanel.setBounds(640, 420, 320, 220);
		
	}
}
