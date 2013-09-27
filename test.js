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
			currentLevel.removeUnit(enemy);
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
		player.addTool(source);
	disappear();
	
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
