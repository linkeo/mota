package com.nju.se.team.mota.editor;

import javax.swing.JPanel;

import com.nju.se.team.mota.data.DataLoader;
import com.nju.se.team.mota.editor.uielem.DialogueElem;
import com.nju.se.team.mota.temp.Dialogue;
import com.nju.se.team.mota.util.ListPanel;

public class DialogueEditPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ListPanel dialogueListPanel;
	ListPanel sentenceListPanel;
//	SentenceEditPanel
	
	public DialogueEditPanel() {
		super(null);
		
		dialogueListPanel = new ListPanel("对话列表");
		sentenceListPanel = new ListPanel("语句列表");
		
		add(dialogueListPanel);
		add(sentenceListPanel);
		
		loadDialogues();
		calcLayout();
	}

	private void loadDialogues() {
		dialogueListPanel.removeAll();
		for(int i : DataLoader.getDialogueIndexs()){
			Dialogue dialogue = Dialogue.make(DataLoader.getDialogueDefine(i));
			dialogueListPanel.add(new DialogueElem(dialogue));
		}
	}

	private void calcLayout() {
		dialogueListPanel.setBounds(0, 0, 320, 640);
		sentenceListPanel.setBounds(320, 0, 320, 640);
	}
}
