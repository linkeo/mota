package com.nju.se.team.mota.temp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;

public class Dialogue {
	public static final int FREE_ID = 0;
	public static final int MINIMUM_ID = 1;
	private static int MAX_ID = MINIMUM_ID-1;
	private int id;
	private ArrayList<Sentence> sentences = new ArrayList<Sentence>();
	private int start;
	public Dialogue() {
		setId(++MAX_ID);
	}
	public Sentence getSentence(int index){
		for(Sentence s : sentences)
			if(s.getId()==index)
				return s;
		return null;
	}
	public Sentence startSentence(){
		return getSentence(start);
	}
	
	public boolean add(Sentence sentence){
		for(Sentence s : sentences)
			if(s.equals(sentence))
				return false;
		if(sentence.getId()<MINIMUM_ID)
			sentence.adjustID(this);
		if(sentences.isEmpty())
			setStart(sentence.getId());
		boolean result = sentences.add(sentence);
		sort();
		return result;
	}
	public void addAll(Sentence[] sentences){
		for(Sentence s : sentences)
			add(s);
	}
	public void addAll(Collection<Sentence> sentences){
		for(Sentence s : sentences)
			add(s);
	}
	public void removeAll(Sentence[] sentences){
		for(Sentence s : sentences)
			remove(s);
	}
	public void removeAll(Collection<Sentence> sentences){
		for(Sentence s : sentences)
			remove(s);
	}
	public void sort(){
		Collections.sort(this.sentences);
	}
	
	public boolean remove(Sentence sentence){
		boolean result = sentences.remove(sentence);
		sort();
		return result;
	}

	public static Dialogue make(JSONObject json) {
		Dialogue dialogue = new Dialogue();
		dialogue.load(json);
		return dialogue;
	}
	
	public void load(JSONObject json){
		setId(json.getInt("id"));
		JSONArray sentencesInJSON = json.getJSONArray("sentences");
		HashSet<Sentence> sentences = new HashSet<Sentence>();
		for(int i=0;i<sentencesInJSON.length();++i)
			sentences.add(
				Sentence.make(
					sentencesInJSON.getJSONObject(i)
				)
			);
		setSentences(sentences);
		setStart(json.getInt("start"));
	}
	public JSONObject toJSONObject(){
		JSONObject json = new JSONObject(this);
		JSONArray sentences = new JSONArray();
		for(Sentence s : this.sentences)
			sentences.put(s.toJSONObject());
		json.put("sentences", sentences);
		return json;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		MAX_ID = Math.max(MAX_ID, id);
	}
	public ArrayList<Sentence> getSentences() {
		sort();
		return sentences;
	}
	public void setSentences(Collection<Sentence> sentences) {
		this.sentences.clear();
		this.sentences.addAll(sentences);
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	@Override
	public String toString() {
		String str = "Dialogue "+id+"\t(start with sentence "+start+")";
		for(Sentence s : sentences){
			str += '\n';
			str += s;
		}
		return str;
	}
	public int countSentence() {
		return sentences.size();
	}
}
