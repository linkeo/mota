package com.nju.se.team.mota.editor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.json.JSONObject;

import com.nju.se.team.mota.util.MyDocuments;
import com.nju.se.team.mota.util.Warning;
/**
 * 选择楼层(弹出)框
 * @author linkeo
 * @author lzw
 *
 */
public class LevelSelectDialog extends JDialog implements ActionListener{


	private static final long serialVersionUID = 1L;
	JTabbedPane tab;
	JPanel sp;
	JPanel np;
	JLabel selectLevelLabel;
	JLabel newLevelLabel;
	JComboBox<Integer> existedLevels;
	JTextField newLevelField;
	JButton selectSubmit;
	JButton newSubmit;
	String action;
	private LevelSelectDialog() {
		setTitle("选择楼层");
		tab = new JTabbedPane();
		tab.setPreferredSize(new Dimension(340, 150));
		setContentPane(tab);	
		sp = new JPanel();
		np = new JPanel();
		sp.setLayout(null);
		np.setLayout(null);
		selectLevelLabel = new JLabel("已有楼层:");
		newLevelLabel = new JLabel("新建楼层:");
		existedLevels = new JComboBox<Integer>();
		newLevelField = new JTextField();
		selectSubmit = new JButton("提交");
		newSubmit = new JButton("提交");
		sp.add(selectLevelLabel);
		sp.add(existedLevels);
		sp.add(selectSubmit);
		np.add(newLevelLabel);
		np.add(newLevelField);
		np.add(newSubmit);
		selectLevelLabel.setBounds(20, 20, 80, 20);
		existedLevels.setBounds(100, 20, 200, 20);
		selectSubmit.setBounds(130, 60, 80, 30);
		newLevelLabel.setBounds(20, 20, 80, 20);
		newLevelField.setBounds(100, 20, 200, 20);
		newSubmit.setBounds(130, 60, 80, 30);
		tab.add(sp, "已有楼层");
		tab.add(np, "新建楼层");
		pack();	
		
		newLevelField.setDocument(MyDocuments.getIntDocument(1, 200));
		
	}
	@SuppressWarnings("deprecation")
	public static JSONObject selectedLevel(JComponent component,Vector<Integer> floors){
		LevelSelectDialog lsd = new LevelSelectDialog();
		lsd.existedLevels.setModel(new DefaultComboBoxModel<Integer>(floors));
		lsd.setLocationRelativeTo(component);
		lsd.setModal(true);
		lsd.addSubmitActionListener(lsd);
		lsd.show();
		if(lsd.getAction()==null)
			return null;
		int level = 0;
		if(lsd.getAction().equals("select"))
			level = lsd.getSelectedLevel();
		if(lsd.getAction().equals("new"))
			level = lsd.getNewLevel();
		JSONObject json = new JSONObject();
		json.put("floor", level);
		json.put("type", lsd.getAction());
		lsd.dispose();
		return json;
	}
	private int getNewLevel() {
		if(newLevelField.getText()==null||newLevelField.getText().equals("")){
			Warning.alarm("错误", "新建楼层失败:楼层名错误", null);
			return 0;
		}
		return Integer.parseInt(newLevelField.getText());
	}
	public int getSelectedLevel(){
		if(existedLevels.getSelectedItem()==null)
			return 0;
		return (int)existedLevels.getSelectedItem();
	}
	public void addSubmitActionListener(ActionListener actionListener){
		selectSubmit.addActionListener(actionListener);
		newSubmit.addActionListener(actionListener);
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == selectSubmit) action = "select";
		if(e.getSource() == newSubmit) action = "new";
		this.setVisible(false);
	}

}
