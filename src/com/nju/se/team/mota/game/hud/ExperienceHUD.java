package com.nju.se.team.mota.game.hud;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import com.nju.se.team.mota.game.unit.Player;
import com.nju.se.team.mota.util.Fonts;
import com.nju.se.team.mota.util.ImageHandler;

public class ExperienceHUD extends HUD{

	private Rectangle rect = new Rectangle();
	private Player unit;
	public ExperienceHUD(Player unit) {
		setHUDSize(500, 20);
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

	@SuppressWarnings("unused")
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
		int bw = 500;
		int bh = 20;
		gg.setColor(bg);
		gg.fillRect(bx, by, bw, bh);
		gg.setColor(fg);
		gg.drawRect(bx, by, bw, bh);

		//block 2
		bx = rect.x; by = rect.y;
		int currEXP = unit.getEXP()-Player.getEXP(unit.getLV()-1);
		int lvEXP = Player.getEXP(unit.getLV())-Player.getEXP(unit.getLV()-1);
		bw = Math.min(500, (int) (500.0*currEXP/
				lvEXP));
		bh = 20;
		gg.setColor(fg);
		gg.fillRect(bx, by, bw, bh);
		BufferedImage bi1 = null,bi2 = null;
		if(bw>0)
			bi1 = new BufferedImage(bw, bh, BufferedImage.TYPE_INT_ARGB);
		if(bw<500)
			bi2 = new BufferedImage(500-bw, bh, BufferedImage.TYPE_INT_ARGB);
		
		//block 3
		int b1x = 0, b1y = 0, b1w = bw, b1h = bh;
		int b2x = -b1w, b2y = 0, b2w = 500-bw, b2h = bh;
		bx = rect.x; by = rect.y+2;
		bw = 500; bh = 16;
		
		if(bi1!=null){
			Graphics2D g1 = ImageHandler.getHQGraphics(bi1);
			g1.setFont(gg.getFont());
			String msg = String.valueOf(currEXP);
				g1.setColor(bg);
				g1.drawString(msg, b1x+2, b1y+y_con);
			msg = "LV."+unit.getLV();
				g1.drawString(msg,
					b1x+(bw-SwingUtilities.computeStringWidth(fm, String.valueOf(msg)))/2,
					b1y+y_con);
			msg = String.valueOf(lvEXP);
				g1.drawString(msg,
						b1x-2+bw-SwingUtilities.computeStringWidth(fm, String.valueOf(msg)),
						b1y+y_con);
			g1.dispose();
			gg.drawImage(bi1, bx, by, canvas);
		}
		if(bi2!=null){
			Graphics2D g2 = ImageHandler.getHQGraphics(bi2);
			g2.setFont(gg.getFont());	
			String msg = String.valueOf(currEXP);
				g2.setColor(fg);
				g2.drawString(msg, b2x+2, b2y+y_con);
			msg = "LV."+unit.getLV();
				g2.drawString(msg,
					b2x+(bw-SwingUtilities.computeStringWidth(fm, String.valueOf(msg)))/2,
					b2y+y_con);
			msg = String.valueOf(lvEXP);
				g2.drawString(msg,
						b2x-2+bw-SwingUtilities.computeStringWidth(fm, String.valueOf(msg)),
						b2y+y_con);
			g2.dispose();
			gg.drawImage(bi2, bx+b1w, by, canvas);
		}
	}

}
