package com.nju.se.team.mota.editor.uielem;

import javax.swing.JLabel;

import com.nju.se.team.mota.temp.Dialogue;
import com.nju.se.team.mota.util.selection.ListComponent;

public class DialogueElem extends ListComponent<Dialogue>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel index;
	JLabel sentence1;
	JLabel sentence2;
	JLabel sentence3;
	Dialogue dialogue;
	public DialogueElem(Dialogue dialogue) {
		this.dialogue = dialogue;
		index = new JLabel();
		index.setHorizontalAlignment(JLabel.CENTER);
		int size = Math.min(dialogue.countSentence(), 3);
		setSize(280, 25*Math.min(size+1, 3));
		dialogue.sort();
		index.setText(String.valueOf(dialogue.getId()));
		add(index);
		index.setBounds(0, 0, 50, 25);
		switch(size){
		case 3:
			sentence3 = new JLabel(dialogue.getSentences().get(2).toString());
			add(sentence3);
			sentence3.setBounds(50, 50, 230, 25);
		case 2:
			sentence2 = new JLabel(dialogue.getSentences().get(1).toString());
			add(sentence2);
			sentence2.setBounds(50, 25, 230, 25);
		case 1:
			sentence1 = new JLabel(dialogue.getSentences().get(0).toString());
			add(sentence1);
			sentence1.setBounds(50, 0, 230, 25);
		}
	}
	@Override
	@Deprecated
	public void setContent(Dialogue content) {
		this.dialogue = content;
	}
	@Override
	@Deprecated
	public Dialogue getContent() {
		return dialogue;
	}

}
