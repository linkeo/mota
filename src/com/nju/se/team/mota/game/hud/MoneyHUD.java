package com.nju.se.team.mota.game.hud;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import com.nju.se.team.mota.data.ResLoader;
import com.nju.se.team.mota.game.unit.Player;
import com.nju.se.team.mota.util.Fonts;
import com.nju.se.team.mota.util.ImageHandler;

public class MoneyHUD extends HUD {
	private Rectangle rect = new Rectangle();
	private Player unit;
	public MoneyHUD(Player unit) {
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
		//	gg.drawRect(bx, by, bw, bh);
		try {
			BufferedImage bi = ImageIO.read(ResLoader.getImageFile("ge_money"));
			gg.drawImage(ImageHandler.zoomFully(bi, bw, bh), bx, by, canvas);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//block 2
		bx = rect.x+16; by = rect.y;
		bw = 160-16; bh = 16;
		gg.setColor(bg);
		gg.fillRect(bx, by, bw, bh);
		gg.setColor(fg);
		//	gg.drawRect(bx, by, bw, bh);
		String msg = String.valueOf(unit.getMoney());
			gg.drawString(msg,
				bx-2+bw-SwingUtilities.computeStringWidth(fm, String.valueOf(msg)),
				by+y_con);
	}

}
