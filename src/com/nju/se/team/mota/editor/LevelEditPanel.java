package com.nju.se.team.mota.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.JSONObject;

import com.nju.se.team.mota.data.DataLoader;
import com.nju.se.team.mota.data.LevelLoader;
import com.nju.se.team.mota.editor.uielem.MapElem;
import com.nju.se.team.mota.editor.uielem.SettingComboElem;
import com.nju.se.team.mota.editor.uielem.SettingIntegerElem;
import com.nju.se.team.mota.editor.uielem.SettingPointItem;
import com.nju.se.team.mota.editor.uielem.SettingTextElem;
import com.nju.se.team.mota.editor.uielem.UnitElem;
import com.nju.se.team.mota.game.Level;
import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Unit;
import com.nju.se.team.mota.game.util.TypeEnum;
import com.nju.se.team.mota.util.ListPanel;
import com.nju.se.team.mota.util.Warning;
/**
 * ��ͼ�༭���
 * @author linkeo
 * @author lzw
 *
 */
public class LevelEditPanel extends JPanel implements MapItemListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MapPanel map;
	JScrollPane mapview;
	ListPanel levelInfoPanel;
		SettingIntegerElem setLevel;
		SettingPointItem setsize;
		JButton levelSelectButton;
		JButton levelDeleteButton;
		JButton levelReloadButton;
		JButton levelSaveButton;
	ListPanel unitListPanel;
		JComboBox<TypeEnum> unitTypeSelect;
	ListPanel unitInfoPanel;
		SettingTextElem unitName;
		SettingComboElem<String> unitBuddy;
		JButton unitDeleteButton;
		JButton unitSaveButton;	
	ArrayList<Integer> floors = new ArrayList<Integer>();
		
	/**
	 * ���췽��<br>
	 * ��ʼ�����
	 */
	public LevelEditPanel(){
		super(null);
		mapview = new JScrollPane();
		levelInfoPanel = new ListPanel();
			JPanel liph = new JPanel();
			liph.setLayout(new BoxLayout(liph, BoxLayout.X_AXIS));
			liph.add(levelSelectButton = new JButton("ѡ��"));
			liph.add(levelReloadButton = new JButton("��ԭ"));
			liph.add(Box.createGlue());
			liph.add(new JLabel("¥������"));
			liph.add(Box.createGlue());
			liph.add(levelDeleteButton = new JButton("ɾ��"));
			liph.add(levelSaveButton = new JButton("����"));
		levelInfoPanel.setColumnHeaderView(liph);
		unitListPanel = new ListPanel();
			JPanel ulph = new JPanel();
			ulph.setLayout(new BoxLayout(ulph, BoxLayout.X_AXIS));
			ulph.add(Box.createGlue());
			ulph.add(new JLabel("��Ԫ�б�"));
			ulph.add(Box.createGlue());
			ulph.add(unitTypeSelect = new JComboBox<TypeEnum>(TypeEnum.values()));
		unitListPanel.setColumnHeaderView(ulph);
		unitInfoPanel = new ListPanel();
			JPanel uiph = new JPanel();
			uiph.setLayout(new BoxLayout(uiph, BoxLayout.X_AXIS));
			
			uiph.add(unitDeleteButton = new JButton("ɾ��"));
			uiph.add(Box.createGlue());
			uiph.add(new JLabel("��Ԫ����"));
			uiph.add(Box.createGlue());
			uiph.add(unitSaveButton = new JButton("����"));
		unitInfoPanel.setColumnHeaderView(uiph);
		
		add(mapview);
		add(levelInfoPanel);
		add(unitListPanel);
		add(unitInfoPanel); 
			
		loadLevels();
		loadLevel(1);
		calcLayout();
		addListener();
	}
	/**
	 * ��������¥����
	 */
	public void loadLevels(){
		ArrayList<Integer> floors = LevelLoader.floors();
		this.floors.clear();
		this.floors.addAll(floors);
	}
	/**
	 * ����һ��¥�������
	 * @param i ¥����
	 */
	public void loadLevel(int i){
		Level l = LevelLoader.getLevel(i);
		if(map==null){
			mapview.setViewportView(map = new MapPanel(l, this));
			mapview.setColumnHeaderView(map.getColumnHeader());
			mapview.setRowHeaderView(map.getRowHeader());
		}else
			map.loadLevel(l);
		levelInfoPanel.removeAll();
		setLevel = new SettingIntegerElem("¥��", l.getLevel(), 1, 200);
		setsize = new SettingPointItem("¥��ߴ�", l.getSize()[0], l.getSize()[1], 1, 64, 1, 64);
		levelInfoPanel.add(setLevel);
		levelInfoPanel.add(setsize);
		setsize.addChangeListenerToSpinners(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int x = setsize.getXValue();
				int y = setsize.getYValue();
				map.setGridSize(x, y);
			}
		});
	}
	public void loadNewLevel(int i){
		Level l = Level.newLevel(i);
		if(map==null){
			mapview.setViewportView(map = new MapPanel(l, this));
			mapview.setColumnHeaderView(map.getColumnHeader());
			mapview.setRowHeaderView(map.getRowHeader());
		}else
			map.loadLevel(l);
		levelInfoPanel.removeAll();
		setLevel = new SettingIntegerElem("¥��", l.getLevel() ,1, 200);
		setsize = new SettingPointItem("¥��ߴ�", l.getSize()[0], l.getSize()[1], 1, 64, 1, 64);
		levelInfoPanel.add(setLevel);
		levelInfoPanel.add(setsize);
		setsize.addChangeListenerToSpinners(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int x = setsize.getXValue();
				int y = setsize.getYValue();
				map.setGridSize(x, y);
			}
		});
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
		unitDeleteButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				map.removeUnit(currSelectedUnit);
				loadItem(currSelectedUnit);
			}
			
		});
		unitSaveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(unitName.getValue().equals(""))
					Warning.alarm("error", "����������!", null);
				else if(UnitNameExited(currSelectedUnit,unitName.getValue())){
					Warning.alarm("error", "�����Ѵ���!", null);
					unitName.setValue("");
				}
				else
					currSelectedUnit.setName(unitName.getValue());
				currSelectedUnit.setBuddy(unitBuddy.getValue());	
			}
		});
		levelSelectButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Vector<Integer> floorsVector = new Vector<Integer>();
				for(Integer f : floors) floorsVector.add(f);
				JSONObject level = LevelSelectDialog.selectedLevel(LevelEditPanel.this, floorsVector);
				if(level == null)
					return;
				if(level.getString("type").equals("select"))
					loadLevel(level.getInt("floor"));
				if(level.getString("type").equals("new"))
					if(floors.contains(level.getInt("floor")))
						Warning.alarm("", "¥���Ѵ���", null);
					else
						loadNewLevel(level.getInt("floor"));
				loadItem(null);
			}
		});
		levelReloadButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadLevel(map.getCurrLevel().getLevel());
			}
		});
		levelDeleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int option = JOptionPane.showConfirmDialog(null, "�Ƿ�Ҫɾ�����ڱ༭��¥��?", "ȷ��ɾ��?", JOptionPane.OK_CANCEL_OPTION);
				if(option==JOptionPane.OK_OPTION){
					if(map.getCurrLevel().getLevel() == 1)
						Warning.alarm("", "��һ�㲻��ɾ��!", LevelEditPanel.this);
					else{
						LevelLoader.removeLevel(map.getCurrLevel().getLevel());
						loadLevels();
						loadLevel(1);
					}
				}
			}
		});
		levelSaveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int floor = setLevel.getValue();
				if(floor!=map.getCurrLevel().getLevel()
						&&floors.contains(floor)){
					Warning.alarm("", "¥���Ѵ���", LevelEditPanel.this);
					return;
				}
				if((e.getModifiers()&ActionEvent.CTRL_MASK)!=0){
					Warning.alarmWithoutButton("JSON", map.getCurrLevel().toJSONObject().toString(), LevelEditPanel.this);
					return;
				}
				if(floors.contains(map.getCurrLevel().getLevel()))
					LevelLoader.removeLevel(map.getCurrLevel().getLevel());
				map.getCurrLevel().setLevel(floor);
				LevelLoader.putLevel(map.getCurrLevel());
				
				loadLevels();
			
				if((e.getModifiers()&ActionEvent.SHIFT_MASK)!=0){
					int option = JOptionPane.showConfirmDialog(null, "�Ƿ�ĿǰΪֹ�����ñ��浽�ļ�?", "ȷ�ϱ���?", JOptionPane.OK_CANCEL_OPTION);
					if(option==JOptionPane.OK_OPTION){
						LevelLoader.putLevelsIntoFile();
					}
				}
			}
		});
	}
	/**
	 * ��ȡѡ��Unit�����͵����ж���
	 * @param selectedItem ѡ���Unit������
	 */
	private boolean UnitNameExited(Unit unit, String name){
		for(Unit u : LevelLoader.getAllUnits()){
			if(u.getFloor()!=unit.getFloor()&&u.getName().equals(name)) return true;
		}
		for(Unit u : map.getCurrLevel().units()){
			if(u!=unit&&u.getName().equals(name)) return true;
		}
		return false;
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
	/**
	 * ��������
	 */
	private void calcLayout(){
		mapview.setBounds(0, 0, 640, 640);
		levelInfoPanel.setBounds(640, 0, 320, 100);
		unitListPanel.setBounds(640, 100, 320, 320);
		unitInfoPanel.setBounds(640, 420, 320, 220);
	}
	/**
	 * ��ʾ��ͼ��Unit��Ϣ
	 * @param u
	 */
	private void loadItem(Unit u){
		unitInfoPanel.removeAll();
		if(u!=null){
			unitInfoPanel.add(unitName = new SettingTextElem("��Ԫ��:", u.getName()));
			Set<String> optionset = new HashSet<String>();
			for(Unit existingU: LevelLoader.getUnits(u.getBuddyType()))
				optionset.add(existingU.getName());
			for(Unit tu: map.getCurrLevel().getAbiotics())
				if(tu.getType().equals(u.getBuddyType()))
					optionset.add(tu.getName());
			for(Unit tu: map.getCurrLevel().getCreatures())
				if(tu.getType().equals(u.getBuddyType()))
					optionset.add(tu.getName());
			ArrayList<String> options = new ArrayList<>(optionset);
			Collections.sort(options);
			unitInfoPanel.add(unitBuddy = new SettingComboElem<String>("��Զ���:", u.getBuddy(), options));
		}
		currSelectedUnit = u;
	}
	Unit currSelectedUnit = null;
	@Override
	public void mapItemSelected(Unit u) {
		loadItem(u);
	}
	@Override
	public void mapItemHighlighted(MapElem mapElem) {
		map.highlight(mapElem);
	}
	@Override
	public void mapItemsUpdated(Collection<Unit> units) {
		// TODO Auto-generated method stub
		
	}
}
