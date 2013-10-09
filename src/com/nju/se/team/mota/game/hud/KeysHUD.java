package com.nju.se.team.mota.game.hud;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import com.nju.se.team.mota.data.ImageLoader;
import com.nju.se.team.mota.game.unit.Player;
import com.nju.se.team.mota.util.Fonts;
import com.nju.se.team.mota.util.ImageHandler;

public class KeysHUD extends HUD{
	private Rectangle rect = new Rectangle();
	private Player unit;
	public KeysHUD(Player unit) {
		setHUDSize(160, 16);
		this.unit = unit;
	}
	@Override
	public void setHUDSize(int width, int height) {
		rect.setSize(width, height);
	}

	@Override
	public void setHUDLocation(int x, int y) {
		rect.setLocation(x, y);
	}

	@Override
	public Rectangle getHUDBounds() {
		return new Rectangle(rect);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D gg = (Graphics2D) g;
		gg.setRenderingHints(ImageHandler.getHQRenderingHints());
		Rectangle rect = getHUDBounds();
		gg.setFont(Fonts.getYahei(16));
		FontMetrics fm = gg.getFontMetrics();
		int y_con = (int) (fm.getAscent()-fm.getDescent()/2.0);
		Color bg = new Color(255,255,255,127);
		Color fg = new Color(63,63,63,255);

		//block 1
		int bx = rect.x;
		int by = rect.y;
		int bw = 16;
		int bh = 16;
		gg.setColor(bg);
		gg.fillRect(bx, by, bw, bh);
		gg.setColor(fg);
		BufferedImage bi = ImageLoader.get("item1_0x0");
		gg.drawImage(ImageHandler.zoomFully(bi, bw, bh), bx, by, canvas);
		//block 2
		bx = rect.x+16; by = rect.y;
		bw = 34; bh = 16;
		gg.setColor(bg);
		gg.fillRect(bx, by, bw, bh);
		gg.setColor(fg);
		//	gg.drawRect(bx, by, bw, bh);
		String msg = String.valueOf(unit.getYellowkey());
			gg.drawString(msg,
				bx-2+bw-SwingUtilities.computeStringWidth(fm, String.valueOf(msg)),
				by+y_con);
		//block 3
		bx = rect.x+55; by = rect.y;
		bw = 16; bh = 16;
		gg.setColor(bg);
		gg.fillRect(bx, by, bw, bh);
		gg.setColor(fg);
		bi = ImageLoader.get("item1_0x1");
		gg.drawImage(ImageHandler.zoomFully(bi, bw, bh), bx, by, canvas);
		//block 4
		bx = rect.x+55+16; by = rect.y;
		bw = 34; bh = 16;
		gg.setColor(bg);
		gg.fillRect(bx, by, bw, bh);
		gg.setColor(fg);
		//	gg.drawRect(bx, by, bw, bh);
		msg = String.valueOf(unit.getBluekey());
			gg.drawString(msg,
				bx-2+bw-SwingUtilities.computeStringWidth(fm, String.valueOf(msg)),
				by+y_con);
		//block 5
		bx = rect.x+110; by = rect.y;
		bw = 16; bh = 16;
		gg.setColor(bg);
		gg.fillRect(bx, by, bw, bh);
		gg.setColor(fg);
		bi = ImageLoader.get("item1_0x2");
		gg.drawImage(ImageHandler.zoomFully(bi, bw, bh), bx, by, canvas);
		//block 6
		bx = rect.x+110+16; by = rect.y;
		bw = 34; bh = 16;
		gg.setColor(bg);
		gg.fillRect(bx, by, bw, bh);
		gg.setColor(fg);
		//	gg.drawRect(bx, by, bw, bh);
		msg = String.valueOf(unit.getBluekey());
			gg.drawString(msg,
				bx-2+bw-SwingUtilities.computeStringWidth(fm, String.valueOf(msg)),
				by+y_con);
	}

}
