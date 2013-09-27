package com.nju.se.team.mota.game.hud;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.font.LineMetrics;

import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.util.Fonts;

public class LeftUnitHUD extends HUD {
	private Rectangle rect = new Rectangle();
	private Creature unit;
	public LeftUnitHUD(Creature unit) {
		setHUDSize(196, 48);
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
		Rectangle rect = getHUDBounds();
		g.drawRect(rect.x, rect.y, 34, 34);
		g.drawImage(unit.thumbnail(32, 32), rect.x+1, rect.y+1, canvas);
		
		g.setFont(Fonts.getYahei(34));
		Font font = g.getFont();
		String message = unit.getName();
		LineMetrics lm = font.getLineMetrics(message, g.getFontMetrics().getFontRenderContext());
		System.out.println("String ascent: "+lm.getAscent());
		System.out.println("String descent: "+lm.getDescent());
		int y_con = (int) (lm.getAscent()+lm.getBaselineOffsets()[lm.getBaselineIndex()]);
		g.drawString(message, rect.x+40, rect.y+y_con);
	}

}
