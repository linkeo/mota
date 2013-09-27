package com.nju.se.team.mota.game.hud;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class HUD {
	private HUD dependingHUD;
	private HUDDirection dependingDirection = HUDDirection.IN;
	private HUDAlignment horizontalHUDAlignment = HUDAlignment.CENTER;
	private HUDAlignment  verticalHUDAlignment = HUDAlignment.CENTER;
	private int horizontalHUDGap = 10, verticalHUDGap = 10;
	private boolean adjusted;
	protected Component canvas;

	public abstract void setHUDSize(int width, int height);
	public abstract void setHUDLocation(int x, int y);
	public abstract Rectangle getHUDBounds();
	public abstract void paint(Graphics g);
	public void setDepending(HUDDirection direction, HUD dependingHUD){
		setDependingHUD(dependingHUD);
		setDependingDirection(direction);
	}
	public void setAlignment(HUDAlignment horizontal, HUDAlignment vertical){
		setHorizontalHUDAlignment(horizontal);
		setVerticalHUDAlignment(vertical);
	}
	public void setGap(int horizontal, int vertical){
		setHorizontalHUDGap(horizontal);
		setVerticalHUDGap(vertical);
	}
	
	public HUD getDependingHUD() {
		return dependingHUD;
	}

	public void setDependingHUD(HUD dependingHUD) {
		this.dependingHUD = dependingHUD;
	}

	public HUDDirection getDependingDirection() {
		return dependingDirection;
	}

	public void setDependingDirection(HUDDirection dependingDirection) {
		this.dependingDirection = dependingDirection;
	}

	public HUDAlignment getHorizontalHUDAlignment() {
		return horizontalHUDAlignment;
	}

	public void setHorizontalHUDAlignment(HUDAlignment horizontalHUDAlignment) {
		this.horizontalHUDAlignment = horizontalHUDAlignment;
	}

	public HUDAlignment getVerticalHUDAlignment() {
		return verticalHUDAlignment;
	}

	public void setVerticalHUDAlignment(HUDAlignment verticalHUDAlignment) {
		this.verticalHUDAlignment = verticalHUDAlignment;
	}

	public int getHorizontalHUDGap() {
		return horizontalHUDGap;
	}

	public void setHorizontalHUDGap(int horizontalHUDGap) {
		this.horizontalHUDGap = horizontalHUDGap;
	}

	public int getVerticalHUDGap() {
		return verticalHUDGap;
	}

	public void setVerticalHUDGap(int verticalHUDGap) {
		this.verticalHUDGap = verticalHUDGap;
	}

	public boolean isAdjusted() {
		return adjusted;
	}

	public void setAdjusted(boolean adjusted) {
		this.adjusted = adjusted;
	}

	public Dimension getHUDSize(){
		Rectangle r = getHUDBounds();
		return new Dimension(r.width, r.height);
	}
	public Point getHUDLocation(){
		Rectangle r = getHUDBounds();
		return new Point(r.x, r.y);
	}
	public void adjust(){
		if( isAdjusted() )
			return;
		HUD depend = getDependingHUD();
		if(depend != null){
			if( !depend.isAdjusted() )
				depend.adjust();

			Rectangle dr = depend.getHUDBounds();
			Dimension size = getHUDSize();
			HUDDirection dire = getDependingDirection();
			HUDAlignment halign = getHorizontalHUDAlignment();
			HUDAlignment valign = getVerticalHUDAlignment();
			int vgap = getVerticalHUDGap();
			int hgap = getHorizontalHUDGap();
			int x = getHUDLocation().x;
			int y = getHUDLocation().y;
			//START SWITCH
			switch(dire){
			case IN:
				switch(halign){
				default: case CENTER:
					x = dr.x + (dr.width-size.width)/2;
					break;
				case LEFT:
					x = dr.x + hgap;
					break;
				case RIGHT:
					x = dr.x + (dr.width - size.width) - hgap;
					break;
				}
				switch(valign){
				default: case CENTER:
					y = dr.y + (dr.height - size.height)/2;
					break;
				case TOP:
					y = dr.y + vgap;
					break;
				case BOTTOM:
					y = dr.y + (dr.height - size.height) - vgap;
					break;
				}
				break;
			case SOUTH:
				switch(halign){
				case CENTER:
					x = dr.x + (dr.width-size.width)/2;
					break;
				default: case LEFT:
					x = dr.x;
					break;
				case RIGHT:
					x = dr.x + (dr.width - size.width);
					break;
				}
				y = dr.y + dr.height + vgap;
				break;
			case NORTH:
				switch(halign){
				case CENTER:
					x = dr.x + (dr.width-size.width)/2;
					break;
				default: case LEFT:
					x = dr.x;
					break;
				case RIGHT:
					x = dr.x + (dr.width - size.width);
					break;
				}
				y = dr.y - size.height - vgap;
				break;
			case EAST:
				x = dr.x + dr.width + hgap;
				switch(valign){
				case CENTER:
					y = dr.y + (dr.height - size.height)/2;
					break;
				default: case TOP:
					y = dr.y;
					break;
				case BOTTOM:
					y = dr.y + (dr.height - size.height);
					break;
				}
				break;
			case WEST:
				x = dr.x - size.width -  hgap;
				switch(valign){
				case CENTER:
					y = dr.y + (dr.height - size.height)/2;
					break;
				default: case TOP:
					y = dr.y;
					break;
				case BOTTOM:
					y = dr.y + (dr.height - size.height);
					break;
				}
				break;
			}
			//END SWITCH
			setHUDLocation(x, y);
		}
		setAdjusted(true);
	}
	public Component getCanvas() {
		return canvas;
	}
	public void setCanvas(Component canvas) {
		this.canvas = canvas;
	}
}
