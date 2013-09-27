package com.nju.se.team.mota.game.hud;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

public abstract class HUD {
	private HUD dependingHUD;
	private HUDDirection dependingDirection;
	private HUDAligment horizontalHUDAligment, verticalHUDAligment;
	private int horizontalHUDGap, verticalHUDGap;
	private boolean adjusted;

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

	public HUDAligment getHorizontalHUDAligment() {
		return horizontalHUDAligment;
	}

	public void setHorizontalHUDAligment(HUDAligment horizontalHUDAligment) {
		this.horizontalHUDAligment = horizontalHUDAligment;
	}

	public HUDAligment getVerticalHUDAligment() {
		return verticalHUDAligment;
	}

	public void setVerticalHUDAligment(HUDAligment verticalHUDAligment) {
		this.verticalHUDAligment = verticalHUDAligment;
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

	public abstract void adjust();
	public abstract void setHUDSize(int width, int height);
	public abstract void setHUDLocation(int x, int y);
	public abstract Rectangle getHUDBounds();
	public Dimension getHUDSize(){
		Rectangle r = getHUDBounds();
		return new Dimension(r.width, r.height);
	}
}
