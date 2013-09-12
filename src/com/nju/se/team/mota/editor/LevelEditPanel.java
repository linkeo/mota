package com.nju.se.team.mota.editor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.json.JSONObject;

import com.nju.se.team.mota.data.DataLoader;
import com.nju.se.team.mota.data.LevelLoader;
import com.nju.se.team.mota.editor.uielem.MapElem;
import com.nju.se.team.mota.editor.uielem.SettingComboElem;
import com.nju.se.team.mota.editor.uielem.SettingPointItem;
import com.nju.se.team.mota.editor.uielem.SettingTextElem;
import com.nju.se.team.mota.editor.uielem.UnitElem;
import com.nju.se.team.mota.game.Level;
import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Unit;
import com.nju.se.team.mota.game.util.TypeEnum;
import com.nju.se.team.mota.util.ListPanel;
/**
 * 地图编辑面板
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
		SettingTextElem setLevel;
		SettingPointItem setsize;
		JButton levelAddButton;
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
	 * 构造方法<br>
	 * 初始化组件
	 */
	public LevelEditPanel(){
		super(null);
		mapview = new JScrollPane();
		levelInfoPanel = new ListPanel();
			JPanel liph = new JPanel();
			liph.setLayout(new BoxLayout(liph, BoxLayout.X_AXIS));
			liph.add(levelAddButton = new JButton("选择"));
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
		unitInfoPanel = new ListPanel();
			JPanel uiph = new JPanel();
			uiph.setLayout(new BoxLayout(uiph, BoxLayout.X_AXIS));
			
			uiph.add(unitDeleteButton = new JButton("删除"));
			uiph.add(Box.createGlue());
			uiph.add(new JLabel("单元属性"));
			uiph.add(Box.createGlue());
			uiph.add(unitSaveButton = new JButton("保存"));
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
	 * 加载所有楼层数
	 */
	public void loadLevels(){
		ArrayList<Integer> floors = LevelLoader.floors();
		this.floors.retainAll(floors);
	}
	/**
	 * 加载一个楼层的数据
	 * @param i 楼层数
	 */
	public void loadLevel(int i){
		Level l = LevelLoader.getLevel(i);
		if(map==null){
			mapview.setViewportView(map = new MapPanel(l, this));
			mapview.setColumnHeaderView(map.getColumnHeader());
			mapview.setRowHeaderView(map.getRowHeader());
		}
		levelInfoPanel.removeAll();
		setLevel = new SettingTextElem("楼层", Integer.toString(l.getLevel()));
		setsize = new SettingPointItem("楼层尺寸", l.getSize()[0], l.getSize()[1], 1, 64, 1, 64);
		levelInfoPanel.add(setLevel);
		levelInfoPanel.add(setsize);
	}
	public void loadNewLevel(){
		
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
	/**
	 * 获取选择Unit子类型的所有对象
	 * @param selectedItem 选择的Unit子类型
	 */
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
	 * 硬布局
	 */
	private void calcLayout(){
		mapview.setBounds(0, 0, 640, 640);
		levelInfoPanel.setBounds(640, 0, 320, 100);
		unitListPanel.setBounds(640, 100, 320, 320);
		unitInfoPanel.setBounds(640, 420, 320, 220);
	}
	/**
	 * 显示地图上Unit信息
	 * @param u
	 */
	private void loadItem(Unit u){
		unitInfoPanel.removeAll();
		if(u!=null){
			unitInfoPanel.add(unitName = new SettingTextElem("单元名:", u.getName()));
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
			unitInfoPanel.add(unitBuddy = new SettingComboElem<String>("配对对象:", u.getBuddy(), options));
		}
	}
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
