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

function sleep(){
	util.sleep();
}

function attack(atker, atked){
	var dmg = atker.getATK() - atked.getDEF();
	if(dmg<0) dmg = 0;
	util.println(atker.getName()+"��"+atked.getName()+"�ճ�"+dmg+"���˺�.");
	atked.setHP( Math.max(0,atked.getHP() - dmg) );
}

function gift(){
	player.setMoney(player.getMoney()+enemy.getMoney());
	player.setEXP(player.getEXP()+enemy.getEXP());
	util.println(player.getName()+"�����"+enemy.getMoney()+"��Һ�"+enemy.getEXP()+"�㾭��ֵ.");
}

function open(color){
	println(color);
}