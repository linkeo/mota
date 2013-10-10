importClass(com.nju.se.team.mota.game.GamingLevels);

var surrender = false;
function fight(){
	/* player, enemy is global input from jvm */
	var atker = player;
	var atked = enemy;
	do{
		if(surrender==true){
			surrender = false;
			return;
		}
		attack(atker, atked);
		var result = check();
		if(result){
			sleep();
			var temp = atker;
			atker = atked;
			atked = temp;
		}
	}while(result);
	
}

function check(){
	if(player.getHP()>0&&enemy.getHP()>0){
		util.println(player.getName()+':'+player.getHP()+"\t  "+enemy.getName()+':'+enemy.getHP());
		return true;
	}else{
		if(player.getHP()>0){
			util.println(player.getName()+"战胜了"+enemy.getName()+'.');
			enemy.setDead(true);
			gift();
		}
		else{
			util.println(player.getName()+"被"+enemy.getName()+"杀死了.");
		}
		return false;
	}
}
function playerSurrender(){
	surrender = true;
}
function sleep(){
	util.sleep();
}
function get(){
	if(source.getType().equals("黄钥匙"))
		player.setYellowKey(player.getYellowKey()+1);
	else if(source.getType().equals("蓝钥匙"))
		player.setBlueKey(player.getBlueKey()+1);
	else if(source.getType().equals("红钥匙"))
		player.setRedKey(player.getRedKey()+1);
	else if(source.getType().equals("铁镐"))
		player.addTool(source, "功能:破坏面前的一堵墙\n一次性使用");
	else if(source.getType().equals("疾风鞋"))
		player.addTool(source, "功能:跳转至已经过的楼层\n永久性使用");
	disappear();
	
}
function use(){
	if(source.getType().equals("炸弹")){
		for each(var a in level.getAbiotics().toArray()){
			if(a.getType().equals("土墙"))
				a.setDead(true);
		}
		source.setDead(true);
	}
	else if(source.getType().equals("铁镐")){
		var nextX;
		var nextY;
		var used = false;
		if(player.getCurrStatus() == UnitStatus.load("walking_left")){
			nextX = player.getPosition()[0] - 1;
			nextY = player.getPosition()[1];
		}
		else if(player.getCurrStatus() == UnitStatus.load("walking_up")){
 			nextX = player.getPosition()[0];
			nextY = player.getPosition()[1] - 1;
		}
		else if(player.getCurrStatus() == UnitStatus.load("walking_right")){
			nextX = player.getPosition()[0] + 1;
			nextY = player.getPosition()[1];
		}
		else if(player.getCurrStatus() == UnitStatus.load("walking_down")){
			nextX = player.getPosition()[0];
			nextY = player.getPosition()[1] + 1;
		}
		for each(var a in level.getAbiotics().toArray()){
			if(!used && a.getType().equals("土墙") &&　a.getPosition()[0] == nextX && a.getPosition()[1] == nextY){
				a.setDead(true);
				used = true;
			}
		}
		source.setDead(used);
	}
	else if(source.getType().equals("疾风鞋")){
		var i = util.levelSelect();
		var l = GamingLevels.getLevel(i);
		tpLevel(l);
	}
}
function upstair(){
	var buddy = source.getBuddy();
	tp(buddy);
}
function downstair(){
	var buddy = source.getBuddy();
	tp(buddy);
}
function tp(target){
	var nextLevel;
	for each(var a in GamingLevels.getAllAbiotics().toArray()){
		if(a.getName().equals(target)){
			nextLevel = a.getFloor();
			player.setFloor(nextLevel);
			player.setPosition(a.getPosition());
		}
	}
	if(nextLevel!=null)
	GamingLevels.toLevel(nextLevel);
}
function tpLevel(newlvl){
	for each(var a in newlvl.getAbiotics().toArray()){
		if(level.getLevel() < newlvl.getLevel()){
			if(a.getType().equals("楼梯(下)")){
				tp(a.getName());
				break;
			}
				
		}
		if(level.getLevel() > newlvl.getLevel()){
			if(a.getType().equals("楼梯(上)")){
				tp(a.getName());
				break;
			}
		}	
	}
}
function attack(atker, atked){
	var dmg = atker.getATK() - atked.getDEF();
	if(dmg<0) dmg = 0;
	util.println(atker.getName()+"对"+atked.getName()+"造成"+dmg+"点伤害.");
	atked.setHP( Math.max(0,atked.getHP() - dmg) );
}

function gift(){
	player.setMoney(player.getMoney()+enemy.getMoney());
	player.setEXP(player.getEXP()+enemy.getEXP());
	util.println(player.getName()+"获得了"+enemy.getMoney()+"金币和"+enemy.getEXP()+"点经验值.");
}

function open(color){
	println(color);
	var key;
	switch(color){
	case "red": 
		key = player.getRedKey();
		if(key>0){
			source.setCurrStatus(UnitStatus.load("dying"));
			player.setRedKey(key-1);
		}
		break;
	case "blue":
		key = player.getBlueKey();
		if(key>0){
			source.setCurrStatus(UnitStatus.load("dying"));
			player.setBlueKey(key-1);
		}
		break;
	case "yellow":
		key = player.getYellowKey();
		if(key>0){
			source.setCurrStatus(UnitStatus.load("dying"));
			player.setYellowKey(key-1);
		}
		break;
	}
}

function hpup(hp){
	player.setHP(player.getHP()+hp);
}
function atkup(atk){
	player.setATK(player.getATK()+atk);
}
function defup(def){
	player.setDEF(player.getDEF()+def);
} 
function expup(exp){
	player.setEXP(player.getEXP()+exp);
} 
function moneyup(money){
	player.setMoney(player.getMoney()+money);
} 
function disappear(){
	source.setDead(true);
}
