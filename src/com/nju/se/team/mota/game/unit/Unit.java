package com.nju.se.team.mota.game.unit;

import java.awt.Point;
import java.util.HashMap;

import com.nju.se.team.mota.game.util.Animation;
import com.nju.se.team.mota.game.util.Condition;
import com.nju.se.team.mota.game.util.UnitStatus;

public class Unit {
	String name;
	String type;
	HashMap<UnitStatus, Animation> sprites;
	HashMap<Condition, String> action;
	
	Point position;
	Point size;
	int floor;
	String buddy;
	String buddyType;
}
