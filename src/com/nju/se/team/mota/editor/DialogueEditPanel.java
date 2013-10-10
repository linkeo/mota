package com.nju.se.team.mota.editor;

import javax.swing.JPanel;

import com.nju.se.team.mota.data.DataLoader;
import com.nju.se.team.mota.editor.uielem.DialogueElem;
import com.nju.se.team.mota.temp.Dialogue;
import com.nju.se.team.mota.util.ListPanel;
import com.nju.se.team.mota.util.selection.ListPane;

public class DialogueEditPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ListPane<Dialogue> dialogueListPanel;
	ListPanel sentenceListPanel;
//	SentenceEditPanel
	
	public DialogueEditPanel() {
		super(null);
		
		dialogueListPanel = new ListPane<Dialogue>("对话列表");
		dialogueListPanel.setMultipleSelectable(false);
		sentenceListPanel = new ListPanel("语句列表");
		
		add(dialogueListPanel);
		add(sentenceListPanel);
		
		calcLayout();
		loadDialogues();
	}

	private void loadDialogues() {
		dialogueListPanel.clear();
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
