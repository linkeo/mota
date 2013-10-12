package com.nju.se.team.mota.editor.uielem;

import javax.swing.JLabel;

import com.nju.se.team.mota.temp.Sentence;
import com.nju.se.team.mota.util.selection.ListComponent;

public class SentenceElem extends ListComponent<Sentence> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel index;
	JLabel content;
	public SentenceElem(Sentence sentence) {
		setContent(sentence);
		index = new JLabel(String.valueOf(sentence.getId()));
		content = new JLabel(sentence.getContent());
		setSize(280, 25);
		add(index);
		add(content);
		index.setBounds(0, 0, 50, 25);
		content.setBounds(50, 0, 230, 25);
	}
}
