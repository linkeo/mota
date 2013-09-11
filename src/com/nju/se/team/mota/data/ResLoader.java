package com.nju.se.team.mota.data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * ����ฺ���ȡ��Դ�ļ�.<br>
 * ��Ҫ������Loaderʹ��.<br>
 * P.S.ʹ�� <b>����ģʽ</b><br>
 * @author Linkeo
 * @author lzw
 *
 */
public class ResLoader {
	/**
	 * ��Ϸ���ݶ����ļ���<������-�ļ�����>ӳ���<br>
	 * ��ط���: getDataKeySet(), getDataFile(String key)
	 */
	private HashMap<String,File> datamap = new HashMap<String,File>();
	/**
	 * ��ϷͼƬ�ز��ļ���<������-�ļ�����>ӳ���<br>
	 * ��ط���: getImageKeySet(), getImageFile(String key)
	 */
	private HashMap<String,File> imagemap = new HashMap<String,File>();
	private static ResLoader instance;
	private ResLoader() {
	}
	/**
	 * ��������: ����Դ�б��ļ�(Ĭ��Ϊres.list)�ж�ȡ��Դ�ļ�ӳ�伯, 
	 * �����䵽��ͬ���͵�ӳ�����<br>
	 * <b>ע: �˷������ڴ����һ��ʹ��ʱ������һ��.</b>
	 */
	private static void load(){
		if(instance==null)
			instance = new ResLoader();
		IniEditor ie = new IniEditor();
		try {
			ie.load("res.list");
		} catch (IOException e) {
			System.err.println("��Դ�ļ�ȱʧ");
		}

		List<String> datakeys = ie.optionNames("data");
		for(String dk : datakeys)
			instance.datamap.put(dk,new File(ie.get("data", dk)));
		
		List<String> imagekeys = ie.optionNames("image");
		for(String ik : imagekeys)
			instance.imagemap.put(ik,new File(ie.get("image", ik)));
	}
	/**
	 * ���ض�����������ȡ��Ϸ���ݶ�����ļ�����<br>
	 * @param key �ļ���������
	 * @return ������ڴ˼�,�򷵻ؼ���Ӧ���ļ�����(����֤�ǿ�)<br>
	 * ��������ڴ˼�,�򷵻�null
	 */
	public static File getDataFile(String key){
		if(instance==null)
			load();
		return instance.datamap.get(key);
	}
	/**
	 * ���ض�����������ȡͼƬ�زĵ��ļ�����<br>
	 * @param key �ļ���������
	 * @return ������ڴ˼�,�򷵻ؼ���Ӧ���ļ�����(����֤�ǿ�)<br>
	 * ��������ڴ˼�,�򷵻�null
	 */
	public static File getImageFile(String key){
		if(instance==null)
			load();
		return instance.imagemap.get(key);
	}
	/**
	 * ��ȡ������Ϸ�����ļ���������
	 * @return ���������������ļ���(Set), ����Ϊ�ռ�
	 */
	public static Set<String> getDataKeySet(){
		if(instance==null)
			load();
		return instance.datamap.keySet();
	}
	/**
	 * ��ȡ����ͼƬ�ز��ļ���������
	 * @return ���������������ļ���(Set), ����Ϊ�ռ�
	 */
	public static Set<String> getImageKeySet(){
		if(instance==null)
			load();
		return instance.imagemap.keySet();
	}
	
}
