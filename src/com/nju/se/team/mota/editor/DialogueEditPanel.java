package com.nju.se.team.mota.editor;

import javax.swing.JPanel;

import com.nju.se.team.mota.data.DataLoader;
import com.nju.se.team.mota.editor.uielem.DialogueElem;
import com.nju.se.team.mota.editor.uielem.SentenceElem;
import com.nju.se.team.mota.temp.Dialogue;
import com.nju.se.team.mota.temp.Sentence;
import com.nju.se.team.mota.util.selection.ListPane;
import com.nju.se.team.mota.util.selection.Selectable;
import com.nju.se.team.mota.util.selection.SelectableListener;
import com.nju.se.team.mota.util.selection.SelectionListener;

public class DialogueEditPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ListPane<Dialogue> dialogueListPanel;
	ListPane<Sentence> sentenceListPanel;
	SentenceEditPanel sentenceEditPanel;
	SelectionListener<Dialogue> dialogueSelectionListener = new SelectionListener<Dialogue>() {

		@Override
		public void itemSelected(Selectable<Dialogue> item, boolean multiple,
				SelectableListener<Dialogue> list) {
			loadDialogue(item.getContent());
		}
		@Override
		public void itemUnselected(Selectable<Dialogue> item, boolean multiple,
				SelectableListener<Dialogue> list) {
			Selectable<Dialogue> selectedItem = list.getSelectedItem();
			if(selectedItem!=null)
				loadDialogue(selectedItem.getContent());
			else
				loadDialogue(null);
		}
	};
	
	public DialogueEditPanel() {
		super(null);
		
		dialogueListPanel = new ListPane<Dialogue>("对话列表");
		dialogueListPanel.setMultipleSelectable(false);
		sentenceListPanel = new ListPane<Sentence>("语句列表");
		
		dialogueListPanel.addSelectionListener(dialogueSelectionListener);
		
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

	private void loadDialogue(Dialogue dialogue) {
		sentenceListPanel.clear();
		if(dialogue!=null){
			for(Sentence s : dialogue.getSentences())
				sentenceListPanel.add(new SentenceElem(s));
		}
	}


	private void calcLayout() {
		dialogueListPanel.setBounds(0, 0, 320, 640);
		sentenceListPanel.setBounds(320, 0, 320, 640);
	}
}
