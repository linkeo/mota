function fight(){
	/* player, enemy is global input from jvm */
	var atker = player;
	var atked = enemy;
	do{
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
			currentLevel.removeUnit(enemy);
			gift();
		}
		else{
			util.println(player.getName()+"被"+enemy.getName()+"杀死了.");
		}
		return false;
	}
}

function sleep(){
	util.sleep();
}

function attack(atker, atked){
	var dmg = atker.getATK() - atked.getDEF();
	if(dmg<0) dmg = 0;
	util.println(atker.getName()+"对"+atked.getName()+"照成"+dmg+"点伤害.");
	atked.setHP( Math.max(0,atked.getHP() - dmg) );
}

function gift(){
	player.setMoney(player.getMoney()+enemy.getMoney());
	player.setEXP(player.getEXP()+enemy.getEXP());
	util.println(player.getName()+"获得了"+enemy.getMoney()+"金币和"+enemy.getEXP()+"点经验值.");
}

function open(color){
	println(color);
}