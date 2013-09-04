package com.nju.se.team.mota.game.unit;

import java.awt.Point;
import java.util.HashMap;

import com.nju.se.team.mota.game.util.Animation;
import com.nju.se.team.mota.game.util.UnitStatus;

public class Unit {
	String name;
	HashMap<UnitStatus, Animation> sprites;
	
	boolean canGoThrough;
	Point location, size;
	
}
