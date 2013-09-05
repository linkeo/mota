package com.nju.se.team.mota.data;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {
	private HashMap<String,BufferedImage> imageMap = new HashMap<String,BufferedImage>();
	public ImageLoader() {
	}
	private static ImageLoader instance;
	public static void load(){
		if(instance == null)
			instance = new ImageLoader();
		for(String s : ResLoader.getImageKeySet()){
			BufferedImage bi;
			try {
				bi = ImageIO.read(ResLoader.getImageFile(s));
				if(bi.getWidth()%32!=0||bi.getHeight()%32!=0){
					System.err.println("Image size dismatch."+s);
					continue;
				}
				int n = 0;
				int w = bi.getWidth()/32;
				int h = bi.getHeight()/32;
				for(int i = 0; i < w ; ++i)
					for(int j = 0; j < h ; ++j){
						instance.imageMap.put(s+'_'+(++n),bi.getSubimage(i*32, j*32, 32, 32));
					}
				System.out.println("Image read successfully( "+w+" x "+h+" ).");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static BufferedImage get(String key){
		if(instance==null)
			load();
		return instance.imageMap.get(key);
	}
	public static BufferedImage[] get(String[] keys){
		if(instance==null)
			load();
		BufferedImage[] images = new BufferedImage[keys.length];
		for(int i=0;i<keys.length;++i)
			images[i]=get(keys[i]);
		return images;
	}
	
}