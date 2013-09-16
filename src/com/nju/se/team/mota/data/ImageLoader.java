package com.nju.se.team.mota.data;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import javax.imageio.ImageIO;

/**
 * ��ȡ��ά������ͼƬ�ز�<br>
 * �ѽ�ͼƬ�и��(��load())<br>
 * P.S.ʹ�� <b>����ģʽ</b><br>
 * @author Linkeo
 * @author lzw
 *
 */
public class ImageLoader {
	/**
	 * ��ϷͼƬ�زĵ�<������-ͼƬ����>ӳ���<br>
	 * ��ط���: getKeySet(), get(String key)
	 */
	private HashMap<String,BufferedImage> imageMap = new HashMap<String,BufferedImage>();
	private ImageLoader() {
	}
	private static ImageLoader instance;
	/**
	 * ��������: ����Դ�ļ�ӳ�伯�ж�ȡͼƬ��Դ�ļ������и��, 
	 * �����䵽��ͼƬӳ�����<br>
	 * <b>ע: �˷������ڴ����һ��ʹ��ʱ������һ��.</b>
	 */
	private static void load(){
		if(instance == null)
			instance = new ImageLoader();
		for(String s : ResLoader.getSpriteKeySet()){
			BufferedImage bi;
			try {
				bi = ImageIO.read(ResLoader.getSpriteFile(s));
				if(bi.getWidth()%32!=0||bi.getHeight()%32!=0){
					continue;
				}
				int w = bi.getWidth()/32;
				int h = bi.getHeight()/32;
				for(int i = 0; i < w ; ++i)
					for(int j = 0; j < h ; ++j){
						instance.imageMap.put(s+'_'+j+"x"+i,bi.getSubimage(i*32, j*32, 32, 32));
					}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * ���ض�����������ȡͼƬ����<br>
	 * @param key ͼƬ��������
	 * @return ������ڴ˼�,�򷵻ؼ���Ӧ��ͼƬ����(����֤�ǿ�)<br>
	 * ��������ڴ˼�,�򷵻�null
	 */
	public static BufferedImage get(String key){
		if(instance==null)
			load();
		return instance.imageMap.get(key);
	}
	/**
	 * ��ȡ����ͼƬ�زĵ�������
	 * @return ���������������ļ���(Set), ����Ϊ�ռ�
	 */
	public static Set<String> getKeySet(){
		if(instance==null)
			load();
		return instance.imageMap.keySet();
	}
	
}