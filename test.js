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
			util.println(player.getName()+"սʤ��"+enemy.getName()+'.');
			enemy.setDead(true);
			gift();
		}
		else{
			util.println(player.getName()+"��"+enemy.getName()+"ɱ����.");
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
	if(source.getType().equals("��Կ��"))
		player.setYellowKey(player.getYellowKey()+1);
	else if(source.getType().equals("��Կ��"))
		player.setBlueKey(player.getBlueKey()+1);
	else if(source.getType().equals("��Կ��"))
		player.setRedKey(player.getRedKey()+1);
	else if(source.getType().equals("����"))
		player.addTool(source, "����:�ƻ���ǰ��һ��ǽ\nһ����ʹ��");
	else if(source.getType().equals("����Ь"))
		player.addTool(source, "����:��ת���Ѿ�����¥��\n������ʹ��");
	disappear();
	
}
function use(){
	if(source.getType().equals("ը��")){
		for each(var a in level.getAbiotics().toArray()){
			if(a.getType().equals("��ǽ"))
				a.setDead(true);
		}
		source.setDead(true);
	}
	else if(source.getType().equals("����")){
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
			if(!used && a.getType().equals("��ǽ") &&��a.getPosition()[0] == nextX && a.getPosition()[1] == nextY){
				a.setDead(true);
				used = true;
			}
		}
		source.setDead(used);
	}
	else if(source.getType().equals("����Ь")){
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
			if(a.getType().equals("¥��(��)")){
				tp(a.getName());
				break;
			}
				
		}
		if(level.getLevel() > newlvl.getLevel()){
			if(a.getType().equals("¥��(��)")){
				tp(a.getName());
				break;
			}
		}	
	}
}
function attack(atker, atked){
	var dmg = atker.getATK() - atked.getDEF();
	if(dmg<0) dmg = 0;
	util.println(atker.getName()+"��"+atked.getName()+"���"+dmg+"���˺�.");
	atked.setHP( Math.max(0,atked.getHP() - dmg) );
}

function gift(){
	player.setMoney(player.getMoney()+enemy.getMoney());
	player.setEXP(player.getEXP()+enemy.getEXP());
	util.println(player.getName()+"�����"+enemy.getMoney()+"��Һ�"+enemy.getEXP()+"�㾭��ֵ.");
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
