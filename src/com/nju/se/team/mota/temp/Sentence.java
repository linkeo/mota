package com.nju.se.team.mota.temp;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class Sentence implements Comparable<Sentence>{
	/**
	 * 语句的索引
	 */
	private int id = Dialogue.FREE_ID;
	/**
	 * 语句的内容, 即显示部分
	 */
	private String content;
	/**
	 * 标记是否有选项
	 */
	private boolean optional;
	/**
	 * 语句含有的选项: 为选项文本->下一对话索引的映射
	 */
	private Map<String, Integer> optionMap = new HashMap<String, Integer>();
	/**
	 * 无选项情况下下一句话;
	 */
	private int next;
	protected Dialogue dialogue;
	
	public Sentence() {
		this.dialogue = null;
	}
	public Sentence(Dialogue dialogue){
		this.dialogue = dialogue;
		if(dialogue!=null)
			dialogue.add(this);
	}
	public Sentence(String content){
		setContent(content);
	}
	
	public static Sentence make(JSONObject json){
		Sentence s = new Sentence();
		s.load(json);
		return s;
	}
	public int adjustID(Dialogue dialogue) {
		this.dialogue = dialogue;
		int maxID = Dialogue.MINIMUM_ID-1;
		for(Sentence s : dialogue.getSentences())
			maxID=Math.max(maxID, s.getId());
		setId(++maxID);
		return maxID;
	}
	
	public int getNext(String option){
		return optionMap.get(option);
	}
	
	public void load(JSONObject json){
		setId(json.getInt("id"));
		setContent(json.getString("content"));
		setOptional(json.getBoolean("optional"));
		if(optional){
			HashMap<String, Integer> options = new HashMap<String, Integer>();
			JSONObject oOption = json.getJSONObject("optionMap");
			for(Object o : oOption.keySet()){
				options.put((String)o, oOption.getInt((String)o));
			}
			setOptionMap(options);
		}else{
			setNext(json.getInt("next"));
		}
	}
	public JSONObject toJSONObject(){
		JSONObject json = new JSONObject(this);
		if(optional)
			json.remove("next");
		else
			json.remove("optionMap");
		return json;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Map<String, Integer> getOptionMap() {
		return optionMap;
	}
	public void setOptionMap(Map<String, Integer> optionMap) {
		this.optionMap.clear();
		this.optionMap.putAll(optionMap);
	}

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}
	
	public boolean equals(Sentence sentence) {
		return (sentence.id == id);
	}
	@Override
	public int compareTo(Sentence o) {
		return id - o.id;
	}
	@Override
	public String toString() {
		return "["+id+']'+'>'+content.replace('\n', '>');
	}
}
