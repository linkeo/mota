package com.nju.se.team.mota.game.hud;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.SwingUtilities;

import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.util.Fonts;
import com.nju.se.team.mota.util.ImageHandler;

public class RightUnitHUD extends HUD {
	private Rectangle rect = new Rectangle();
	private Creature unit;
	public RightUnitHUD(Creature unit) {
		setHUDSize(160, 64);
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
		int bx = rect.x+112;
		int by = rect.y;
		int bw = 48;
		int bh = 48;
		gg.setColor(bg);
		gg.fillRect(bx, by, bw, bh);
		gg.setColor(fg);
//		gg.drawRect(bx, by, bw, bh);
		if(unit.getSize()[0]>1||unit.getSize()[1]>1)
			gg.drawImage(unit.thumbnail(48, 48), bx, by, canvas);
		else
			gg.drawImage(unit.thumbnail(32, 32), bx+8, by+8, canvas);

		//block 2
		bx = rect.x; by = rect.y+48;
		bw = 160; bh = 16;
		gg.setColor(bg);
		gg.fillRect(bx, by, bw, bh);
		gg.setColor(fg);
//		gg.drawRect(bx, by, bw, bh);
		String msg = unit.getType();
			gg.drawString(msg,
				bx-2+bw-SwingUtilities.computeStringWidth(fm, String.valueOf(msg)),
				by+y_con);

		//block 3
		bx = rect.x; by = rect.y;
		bw = 112; bh = 16;
		gg.setColor(bg);
		gg.fillRect(bx, by, bw, bh);
		gg.setColor(fg);
//			gg.drawRect(bx, by, bw, bh);
		msg = "HP";
			gg.drawString(msg, bx+2, by+y_con);
		msg = String.valueOf(unit.getHP());
			gg.drawString(msg,
				bx-2+bw-SwingUtilities.computeStringWidth(fm, String.valueOf(msg)),
				by+y_con);
		
		//block 4
		bx = rect.x; by = rect.y+16;
		bw = 112; bh = 16;
		gg.setColor(bg);
		gg.fillRect(bx, by, bw, bh);
		gg.setColor(fg);
//			gg.drawRect(bx, by, bw, bh);
		msg = "ATK";
		gg.drawString(msg, bx+2, by+y_con);
		msg = String.valueOf(unit.getATK());
			gg.drawString(msg,
				bx-2+bw-SwingUtilities.computeStringWidth(fm, String.valueOf(msg)),
				by+y_con);
			
		//block 5
		bx = rect.x; by = rect.y+32;
		bw = 112; bh = 16;
		gg.setColor(bg);
		gg.fillRect(bx, by, bw, bh);
		gg.setColor(fg);
//			gg.drawRect(bx, by, bw, bh);
		msg = "DEF";
		gg.drawString(msg, bx+2, by+y_con);
		msg = String.valueOf(unit.getDEF());
			gg.drawString(msg,
				bx-2+bw-SwingUtilities.computeStringWidth(fm, String.valueOf(msg)),
				by+y_con);
	}

}
