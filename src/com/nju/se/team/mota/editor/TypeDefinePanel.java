package com.nju.se.team.mota.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.JSONObject;

import com.nju.se.team.mota.data.DataLoader;
import com.nju.se.team.mota.data.ImageLoader;
import com.nju.se.team.mota.editor.uielem.ActionElem;
import com.nju.se.team.mota.editor.uielem.FrameElem;
import com.nju.se.team.mota.editor.uielem.ResElem;
import com.nju.se.team.mota.editor.uielem.SettingCheckElem;
import com.nju.se.team.mota.editor.uielem.SettingComboElem;
import com.nju.se.team.mota.editor.uielem.SettingIntegerElem;
import com.nju.se.team.mota.editor.uielem.SettingPointItem;
import com.nju.se.team.mota.editor.uielem.SettingTextElem;
import com.nju.se.team.mota.editor.uielem.StatusElem;
import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.util.Animation;
import com.nju.se.team.mota.game.util.Condition;
import com.nju.se.team.mota.game.util.TypeEnum;
import com.nju.se.team.mota.game.util.UnitStatus;
import com.nju.se.team.mota.util.ElemPanel;
import com.nju.se.team.mota.util.ListPanel;
import com.nju.se.team.mota.util.Warning;
/**
 * Unit���������
 * @author linkeo
 * @author lzw
 *
 */
public class TypeDefinePanel extends JPanel implements FrameEditListener{

	private static final long serialVersionUID = 1L;
	JPanel leftPanel;
		JPanel left_topPanel;
			JLabel typeLabel;
			JComboBox<TypeEnum> typeComboBox;
			JLabel subTypeLabel;
			JComboBox<String> subTypeComboBox;
		ListPanel typeEditPanel;
			JButton typeAddButton;
			JButton typeReloadButton;
			JButton typeDeleteButton;
			JButton typeSaveButton;
			SettingTextElem settype;
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
		
	Abiotic currAbiotic;
	Creature currCreature;
		
	/**
	 * ���췽��<br>
	 * ��ʼ�����
	 */
	public TypeDefinePanel() {
		super(null);
		leftPanel = new JPanel(null);
			left_topPanel = new JPanel(null);
				typeLabel = new JLabel("������:");
				typeComboBox = new JComboBox<TypeEnum>();
				subTypeLabel = new JLabel("����:");
				subTypeComboBox = new JComboBox<String>();
			typeEditPanel = new ListPanel("��������");
					JPanel lcph = new JPanel();
					lcph.setLayout(new BoxLayout(lcph, BoxLayout.X_AXIS));
					lcph.add(typeAddButton = new JButton("�½�"));
					lcph.add(typeReloadButton = new JButton("��ԭ"));
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
					lbph.add(Box.createGlue());
					lbph.add(new JLabel("�ű��б�"));
					lbph.add(Box.createGlue());
					lbph.add(actionEditButton = new JButton("�༭"));
					lbph.add(actionDeleteButton = new JButton("ɾ��"));
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
		
		
		ArrayList<String> keys = new ArrayList<String>(ImageLoader.getKeySet());
		Collections.sort(keys);
		for(String s : keys)
			resListPanel.add(new ResElem(s));
		loadTypes();
		addListeners();
		frameHolder.addFrameEditListener(this);
		typeSelected((TypeEnum) typeComboBox.getSelectedItem());
	}
	/**
	 * ����Unit������
	 */
	public void loadTypes(){
		typeComboBox.setModel(new DefaultComboBoxModel<TypeEnum>(TypeEnum.values()));
	}
	/**
	 * ���ط�����
	 * @param a(Abiotic)
	 */
	public void loadAbiotic(Abiotic a){
		settype.setValue(a.getType());
		setsize.setValue(a.getSize()[0],a.getSize()[1]);
		setbt.setValue(a.getBuddyType());
		setcangt.setValue(a.isCanGoThrough());
		
		actionListPanel.removeAll();
		for(Condition c : a.getAction().keySet())
			actionListPanel.add(new ActionElem(c, a.getAction().get(c)));
		
		statusListPanel.removeAll();
		for(UnitStatus s : a.getSprites().keySet())
			statusListPanel.add(new StatusElem(s));

		frameListPanel.removeAll();
		frameHolder.clear();
		
		currAbiotic = a;
	}
	/**
	 * ������������
	 * @param c(Creature)
	 */
	public void loadCreature(Creature c){
		settype.setValue(c.getType());
		setsize.setValue(c.getSize()[0],c.getSize()[1]);
		setbt.setValue(c.getBuddyType());
		setHP.setValue(c.getHP());
		setATK.setValue(c.getATK());
		setDEF.setValue(c.getDEF());
		setMoney.setValue(c.getMoney());
		setEXP.setValue(c.getEXP());
		
		actionListPanel.removeAll();
		for(Condition co : c.getAction().keySet())
			actionListPanel.add(new ActionElem(co, c.getAction().get(co)));
		
		statusListPanel.removeAll();
		for(UnitStatus s : c.getSprites().keySet())
			statusListPanel.add(new StatusElem(s));
		
		frameListPanel.removeAll();
		frameHolder.clear();
		currCreature = c;
	}
	/**
	 * ״̬ѡ��<br>
	 * ���ظ�״̬��֡�б�
	 * @param s(UnitStatus)
	 */
	private void statusSelected(UnitStatus s) {
		HashMap<UnitStatus, Animation> map = null;
			TypeEnum type = (TypeEnum) typeComboBox.getSelectedItem();
			if(type == TypeEnum.ABIOTIC) map = currAbiotic.getSprites();
			if(type == TypeEnum.CREATURE)	 map = currCreature.getSprites();
		Animation a = map.get(s);
		frameListPanel.removeAll();
		for(int i=0;i<a.frameCount();i++)
			frameListPanel.add(new FrameElem(i, a.getImageKeyAt(i)));
	}
	/**
	 * ֡ѡ��<br>
	 * ���ظ�״̬��Ӧ��֡ͼƬ
	 * @param e(FrameElem)
	 */
	private void frameSelected(FrameElem e){
		frameHolder.load(e.getImageKey());
	}
	
	/**
	 * Unit������ѡ��<br>
	 * ����������������������
	 * @param type
	 */
	public void typeSelected(TypeEnum type){
		Vector<String> typeVector = new Vector<String>();
		typeEditPanel.removeAll();
		typeEditPanel.add(settype = new SettingTextElem("������:", "new"));
		typeEditPanel.add(setsize = new SettingPointItem("�ߴ�:", 1, 1, 1, 5, 1, 5));
		if(type == TypeEnum.ABIOTIC){
			ArrayList<String> btSet = new ArrayList<String>(DataLoader.getAbioticTypes());
			btSet.add("none");
			Collections.sort(btSet);
			typeEditPanel.add(setbt = new SettingComboElem<String>("�������:", "none", btSet));
			typeEditPanel.add(setcangt =  new SettingCheckElem("�ɴ�͸:", false));
			for(String s : DataLoader.getAbioticTypes())
				typeVector.add(s);
		}
		if(type == TypeEnum.CREATURE){
			ArrayList<String> btSet = new ArrayList<String>(DataLoader.getCreatureTypes());
			btSet.add("none");
			Collections.sort(btSet);
			typeEditPanel.add(setbt = new SettingComboElem<String>("�������:", "none", btSet));
			typeEditPanel.add(setHP = new SettingIntegerElem("Ѫ��:", 10, 1, 99999999));
			typeEditPanel.add(setATK = new SettingIntegerElem("������:", 10, 1, 99999999));
			typeEditPanel.add(setDEF = new SettingIntegerElem("������:", 10, 1, 99999999));
			typeEditPanel.add(setMoney = new SettingIntegerElem("��Ǯ:", 10, 1, 99999999));
			typeEditPanel.add(setEXP = new SettingIntegerElem("����:", 10, 1, 99999999));
			for(String s : DataLoader.getCreatureTypes())
				typeVector.add(s);
		}

		setsize.addChangeListenerToSpinners(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				TypeEnum type = (TypeEnum) typeComboBox.getSelectedItem();
				if(type == TypeEnum.ABIOTIC){
					currAbiotic.setSize(setsize.getXValue(), setsize.getYValue());
				}
				if(type == TypeEnum.CREATURE){
					currCreature.setSize(setsize.getXValue(), setsize.getYValue());
				}
				frameHolder.setGridSize(setsize.getXValue(), setsize.getYValue());
			}
		});
		Collections.sort(typeVector);
		subTypeComboBox.setModel(new DefaultComboBoxModel<String>(typeVector));
		subTypeSelected((String) subTypeComboBox.getSelectedItem());
	}
	/**
	 * Unit��������ѡ��<br>
	 * ���ظ����Ͷ�Ӧ������
	 * @param string
	 */
	public void subTypeSelected(String string){
		if(string == null) return;
		TypeEnum type = (TypeEnum) typeComboBox.getSelectedItem();
		if(type == TypeEnum.ABIOTIC){
			JSONObject json = DataLoader.getAbioticDefine(string);
			Abiotic abi = new Abiotic();
			abi.loadType(json);
			loadAbiotic(abi);
		}
		if(type == TypeEnum.CREATURE){
			JSONObject json = DataLoader.getCreatureDefine(string);
			Creature cre = new Creature();
			cre.loadType(json);
			loadCreature(cre);
		}
	}
	/**
	 * ��Ӽ�����
	 */
	public void addListeners(){
		typeSaveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TypeEnum type = (TypeEnum) typeComboBox.getSelectedItem();
				if((e.getModifiers()&ActionEvent.SHIFT_MASK)!=0){
					int option = JOptionPane.showConfirmDialog(null, "�Ƿ�ĿǰΪֹ�����ñ��浽�ļ�?", "ȷ�ϱ���?", JOptionPane.OK_CANCEL_OPTION);
					if(option==JOptionPane.OK_OPTION){
						DataLoader.saveAbiotics();
						DataLoader.saveCreatures();
					}
				}
				if(type == TypeEnum.ABIOTIC){
					String typename = settype.getValue();
					String buddytype = setbt.getValue();
					boolean canGT = setcangt.getValue();
					if(typename==null||typename.isEmpty()||buddytype==null||buddytype.isEmpty()){
						Warning.alarm("�༭������", "������д��Ŀ�Ƿ����.", null );
						return;
					}
					boolean fullfilled = true;
					for(Animation a : currAbiotic.getSprites().values())
						for(String[][] sss : a.getImages())
							for(String[] ss : sss)
								for(String s : ss)
									if(s==null)
										fullfilled=false;
					if(!fullfilled){
						Warning.alarm("ͼƬ��䲻����", "��������״̬������֡��ͼƬ", null );
						return;
					}
					if((e.getModifiers()& ActionEvent.CTRL_MASK)==0)
						DataLoader.removeAbioticType(currAbiotic);
					currAbiotic.setType(typename);
					currAbiotic.setBuddyType(buddytype);
					currAbiotic.setCanGoThrough(canGT);
					if((e.getModifiers()& ActionEvent.CTRL_MASK)!=0)
						Warning.alarmWithoutButton("JSON", currAbiotic.parseTypeJSON().toString(), null );
					else{
						DataLoader.putAbioticType(currAbiotic);
						typeSelected((TypeEnum) typeComboBox.getSelectedItem());
						subTypeSelected(typename);
					}
				}
				if(type == TypeEnum.CREATURE){
					String typename = settype.getValue();
					String buddytype = setbt.getValue();
					int
						hp = setHP.getValue(),
						atk = setATK.getValue(),
						def = setDEF.getValue(),
						money = setMoney.getValue(),
						exp = setEXP.getValue();
					if(typename==null||typename.isEmpty()||buddytype==null||buddytype.isEmpty()
							||hp<0||atk<0||def<0||money<0||exp<0){
						Warning.alarm("�༭������", "������д��Ŀ�Ƿ����.", null );
						return;
					}
					boolean fullfilled = true;
					for(Animation a : currCreature.getSprites().values())
						for(String[][] sss : a.getImages())
							for(String[] ss : sss)
								for(String s : ss)
									if(s==null)
										fullfilled=false;
					if(!fullfilled){
						Warning.alarm("ͼƬ��䲻����", "��������״̬������֡��ͼƬ", null );
						return;
					}
					if((e.getModifiers()& ActionEvent.CTRL_MASK)==0)
						DataLoader.removeCreatureType(currCreature);
					currCreature.setType(typename);
					currCreature.setBuddyType(buddytype);
					currCreature.setHP(hp);
					currCreature.setATK(atk);
					currCreature.setDEF(def);
					currCreature.setMoney(money);
					currCreature.setEXP(exp);					
					if((e.getModifiers()& ActionEvent.CTRL_MASK)!=0) 
						Warning.alarmWithoutButton("JSON", currCreature.parseTypeJSON().toString(), null );
					else{
						DataLoader.putCreatureType(currCreature);
						typeSelected((TypeEnum) typeComboBox.getSelectedItem());
						subTypeSelected(typename);
					}
				}
			}
		});
		typeDeleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TypeEnum type = (TypeEnum) typeComboBox.getSelectedItem();
				if(type == TypeEnum.ABIOTIC){
					DataLoader.removeAbioticType(currAbiotic);
					typeSelected((TypeEnum) typeComboBox.getSelectedItem());
				}
				if(type == TypeEnum.CREATURE){
					DataLoader.removeCreatureType(currCreature);
					typeSelected((TypeEnum) typeComboBox.getSelectedItem());
				}
			}
		});
		statusListPanel.setMultiSelectable(false);
		statusListPanel.addElemMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				ElemPanel ep = statusListPanel.getSelectedElem();
				if(ep == null){
					frameListPanel.removeAll();
					frameHolder.clear();
				}else
					statusSelected(((StatusElem)ep).getValue());
			}
		});
		frameListPanel.setMultiSelectable(false);
		frameListPanel.addElemMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				ElemPanel ep = frameListPanel.getSelectedElem();
				if(ep == null) return;
				frameSelected(((FrameElem)ep));
			}
		});
		frameAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StatusElem se = ((StatusElem)statusListPanel.getSelectedElem());
				if(se==null)return;
				TypeEnum type = (TypeEnum) typeComboBox.getSelectedItem();
				if(type == TypeEnum.ABIOTIC)
					frameListPanel.add(new FrameElem(frameListPanel.getElems().size(), new String[currAbiotic.getSize()[0]][currAbiotic.getSize()[1]]));
				if(type == TypeEnum.CREATURE)	
					frameListPanel.add(new FrameElem(frameListPanel.getElems().size(), new String[currCreature.getSize()[0]][currCreature.getSize()[1]]));
				frameListChanged();
			}
		});
		frameDeleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StatusElem se = ((StatusElem)statusListPanel.getSelectedElem());
				if(se==null) return;
				if(frameListPanel.getElems().size()<=1) return;
				frameListPanel.remove(frameListPanel.getElems().get(frameListPanel.getElems().size()-1));
				frameListChanged();
			}
		});
		statusAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Vector<UnitStatus> candidates = new Vector<UnitStatus>();
				for(UnitStatus s:UnitStatus.values()) candidates.add(s);
				for(ElemPanel ep:statusListPanel.getElems()) candidates.remove(((StatusElem)ep).getValue());
				StatusElem elem = StatusComboDialog.chooseStatus(TypeDefinePanel.this, candidates);
				if(elem==null)
					return;
				statusListPanel.add(elem);
				HashMap<UnitStatus, Animation> sprites = null;
				int[] size = null;
				TypeEnum type = (TypeEnum) typeComboBox.getSelectedItem();
				if(type == TypeEnum.ABIOTIC){
					sprites = currAbiotic.getSprites();
					size = currAbiotic.getSize();
				}
				if(type == TypeEnum.CREATURE){
					sprites = currCreature.getSprites();
					size = currCreature.getSize();
				}
				sprites.put(elem.getValue(), new Animation(1,size[0],size[1]));
			}
		});
		
		statusDeleteButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(ElemPanel elem : statusListPanel.getSelectedElems()){
					statusListPanel.remove(elem);
					HashMap<UnitStatus, Animation> sprites = null;
					TypeEnum type = (TypeEnum) typeComboBox.getSelectedItem();
					if(type == TypeEnum.ABIOTIC) sprites = currAbiotic.getSprites();
					if(type == TypeEnum.CREATURE) sprites = currCreature.getSprites();
					sprites.remove(((StatusElem) elem).getValue());
				}
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
		subTypeComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()!=ItemEvent.SELECTED){
					subTypeSelected((String) subTypeComboBox.getSelectedItem());
				}
			}
		});
		typeReloadButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				subTypeSelected((String) subTypeComboBox.getSelectedItem());
			}
		});
		
		typeAddButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TypeEnum type = (TypeEnum) typeComboBox.getSelectedItem();
				if(type == TypeEnum.ABIOTIC) loadAbiotic(Abiotic.defaultAbiotic());
				if(type == TypeEnum.CREATURE)	 loadCreature(Creature.defaultCreature());
			}
		});
		
		
		actionAddButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Vector<Condition> candidates = new Vector<Condition>();
				for(Condition c:Condition.values()) candidates.add(c);
				for(ElemPanel ep:actionListPanel.getElems()) candidates.remove(((ActionElem)ep).getCondition());
				ActionElem elem = ActionEditDialog.editAction(TypeDefinePanel.this, candidates);
				if(elem==null)
					return;
				actionListPanel.add(elem);
				saveActions();
			}
		});
		
		actionDeleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(ElemPanel elem : actionListPanel.getSelectedElems())
					actionListPanel.remove(elem);
				saveActions();
			}
		});
		
		actionEditButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionElem selectedElem = (ActionElem) actionListPanel.getSelectedElem();
				if(selectedElem == null) return;
				Vector<Condition> candidates = new Vector<Condition>();
				for(Condition c:Condition.values()) candidates.add(c);
				for(ElemPanel ep:actionListPanel.getElems()) candidates.remove(((ActionElem)ep).getCondition());
				candidates.add(selectedElem.getCondition());
				ActionElem elem = ActionEditDialog.editAction(TypeDefinePanel.this, selectedElem, candidates);
				if(elem.getAction()==null||elem.getAction().equals(""))
					return;
				actionListPanel.remove(selectedElem);
				actionListPanel.add(elem);
				saveActions();
			}
		});
		
	}
	/**
	 * ������Ϊ�б�
	 */
	public void saveActions(){
		HashMap<Condition,String> actions = new HashMap<Condition,String>();
		for(ElemPanel e : actionListPanel.getElems()){
			ActionElem a = (ActionElem) e;
			actions.put(a.getCondition(), a.getAction());
			TypeEnum type = (TypeEnum) typeComboBox.getSelectedItem();
			if(type == TypeEnum.ABIOTIC) currAbiotic.setAction(actions);
			if(type == TypeEnum.CREATURE)	 currCreature.setAction(actions);
		}
		
	}
	/**
	 * Ӳ����
	 */
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
	public void statusListChanged(){
		
	}
	/**
	 * ֡�б�仯
	 */
	public void frameListChanged() {
		StatusElem se = ((StatusElem)statusListPanel.getSelectedElem());
		if(se==null)return;
		UnitStatus s = se.getValue();

		
		int frameCnt = frameListPanel.getElems().size();
		int[] size = null;
		TypeEnum type = (TypeEnum) typeComboBox.getSelectedItem();
		if(type == TypeEnum.ABIOTIC) size = currAbiotic.getSize();
		if(type == TypeEnum.CREATURE)	 size = currCreature.getSize();
		
		String[][][] images = new String[frameCnt][size[0]][size[1]];
		for(int i=0;i<frameCnt;++i){
			String[][] imagekey = null;
			for(ElemPanel e : frameListPanel.getElems())
				if(((FrameElem)e).getValue()==i)
					imagekey = ((FrameElem)e).getImageKey();
			for(int x=0;x<size[0];++x)
				for(int y=0;y<size[1];++y){
					if(x<imagekey.length&&y<imagekey[x].length)
						images[i][x][y] = imagekey[x][y];
					else
						images[i][x][y] = null;
				}
		}
		
		Animation a = new Animation();
		a.setImages(images);
		
		if(type == TypeEnum.ABIOTIC) currAbiotic.getSprites().put(s, a);
		if(type == TypeEnum.CREATURE)	 currCreature.getSprites().put(s, a);
	}
	
	@Override
	public void frameChanged() {
		FrameElem ep = (FrameElem) frameListPanel.getSelectedElem();
		if(ep==null)return;
		ep.setImageKey(frameHolder.getImageKey());
		frameListChanged();
	}
}
