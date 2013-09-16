package com.nju.se.team.mota.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
/**
 * 图片处理
 * 
 * @version 1.0
 * @author Linkeo
 *
 */
public class ImageHandler {
	/**
	 * 蒙版型叠加图片
	 * @param base
	 * @param img
	 * @return
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public static BufferedImage overlapAtop(BufferedImage base, BufferedImage img)
			throws ArrayIndexOutOfBoundsException{
		BufferedImage res = new BufferedImage
				(base.getWidth(), base.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g=getGraphics(res);
		g.drawImage(base, 0, 0, null);
		g.setComposite(AlphaComposite.SrcAtop);
		g.drawImage(img, 0, 0, null);
		g.dispose();
		return res;
	}
	/**
	 * 叠加图片
	 * @param base
	 * @param img
	 * @return
	 */
	public static BufferedImage overlap(BufferedImage base, BufferedImage img){
		BufferedImage res = new BufferedImage
				(base.getWidth(), base.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g=getGraphics(res);
		g.drawImage(base, 0, 0, null);
		g.drawImage(img, 0, 0, null);
		g.dispose();
		return res;
	}
	/**
	 * 裁剪图片
	 * @param img
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @return
	 */
	public static BufferedImage clip(BufferedImage img, int x, int y, int w, int h){
		return img.getSubimage(x, y, w, h);
	}
	/**
	 * 拉伸型缩放图片
	 * @param img
	 * @param w
	 * @param h
	 * @return
	 */
	public static BufferedImage zoomStretch(BufferedImage img, int w, int h){
		BufferedImage res = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		int oriw = img.getWidth(), orih = img.getHeight();
		double rate = Math.max((double)w/oriw, (double)h/orih);
		while(rate < 0.5){
			img = zoom(img, 0.5);
			oriw = img.getWidth(); orih = img.getHeight();
			rate = Math.max((double)w/oriw, (double)h/orih);
		}
		Graphics2D g=getGraphics(res);
		g.drawImage(img, 0, 0, w, h, null);
		g.dispose();
		return res;
	}
	/**
	 * 指定比例缩放
	 * @param img
	 * @param w
	 * @param h
	 * @return
	 */
	private static BufferedImage zoom(BufferedImage img, double rate){
		int w = (int) (img.getWidth()*rate), h = (int) (img.getHeight()*rate);
		BufferedImage res = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g=getGraphics(res);
		g.drawImage(img, 0, 0, w, h, null);
		g.dispose();
		return res;
	}
	/**
	 * 比例不变铺满显示
	 * @param img
	 * @param w
	 * @param h
	 * @return
	 */
	public static BufferedImage zoomFill(BufferedImage img, int w, int h){
		int oriw = img.getWidth(), orih = img.getHeight();
		double rate = Math.max((double)w/oriw, (double)h/orih);
		while(rate < 0.5){
			img = zoom(img, 0.5);
			oriw = img.getWidth(); orih = img.getHeight();
			rate = Math.max((double)w/oriw, (double)h/orih);
		}
		int nx = (int) ((w-oriw*rate)/2);
		int ny = (int) ((h-orih*rate)/2);
		int nw = w-2*nx, nh = h-2*ny;
		BufferedImage res = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g=getGraphics(res);
		g.drawImage(img, nx, ny, nw, nh, null);
		g.dispose();
		return res;
	}
	/**
	 * 比例不变完全显示
	 * @param img
	 * @param w
	 * @param h
	 * @return
	 */
	public static BufferedImage zoomFully(BufferedImage img, int w, int h){
		int oriw = img.getWidth(), orih = img.getHeight();
		double rate = Math.min((double)w/oriw, (double)h/orih);
		while(rate < 0.5){
			img = zoom(img, 0.5);
			oriw = img.getWidth(); orih = img.getHeight();
			rate = Math.min((double)w/oriw, (double)h/orih);
		}
		int nx = (int) ((w-oriw*rate)/2);
		int ny = (int) ((h-orih*rate)/2);
		int nw = w-2*nx, nh = h-2*ny;
		BufferedImage res = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g=getGraphics(res);
		g.drawImage(img, nx, ny, nw, nh, null);
		g.dispose();
		return res;
	}
	public static BufferedImage transparent(BufferedImage img, float opacity){
		int w = img.getWidth(), h = img.getHeight();
		BufferedImage res = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g=getGraphics(res);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		g.drawImage(img, 0, 0, null);
		g.dispose();
		return res;
	}
	private static Graphics2D getGraphics(BufferedImage bi) {
		// TODO Auto-generated method stub
		return getHQGraphics(bi);
	}
	public static Graphics2D getHQGraphics(BufferedImage bi){
		Graphics2D g=bi.createGraphics();
		g.setRenderingHints(getHQRenderingHints());
		return g;
	}
	static RenderingHints rh;
	public static RenderingHints getHQRenderingHints(){
		if(rh==null){
			rh = new RenderingHints(RenderingHints. KEY_ANTIALIASING,
					RenderingHints. VALUE_ANTIALIAS_ON);
			rh.put(RenderingHints.KEY_TEXT_ANTIALIASING
					, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			rh.put(RenderingHints.KEY_RENDERING
					, RenderingHints.VALUE_RENDER_QUALITY);
			rh.put(RenderingHints.KEY_COLOR_RENDERING
					, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
			rh.put(RenderingHints.KEY_STROKE_CONTROL
					, RenderingHints.VALUE_STROKE_PURE);
			rh.put(RenderingHints.KEY_ALPHA_INTERPOLATION
					, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
			rh.put(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			rh.put(RenderingHints.KEY_DITHERING,
					RenderingHints.VALUE_DITHER_ENABLE);
			rh.put(RenderingHints.KEY_FRACTIONALMETRICS,
					RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		}
		return rh;
	}
}
