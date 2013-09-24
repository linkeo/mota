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
		println(player.getName()+':'+player.getHP()+"\t  "+enemy.getName()+':'+enemy.getHP());
		util.println(player.getName()+':'+player.getHP()+"\t  "+enemy.getName()+':'+enemy.getHP());
		return true;
	}else{
		if(player.getHP()>0)
			util.println("player wins");
		else
			util.println("player loses.");
		return false;
	}
}

function sleep(){
	util.sleep();
}

function attack(atker, atked){
	var dmg = atker.getATK() - atked.getDEF();
	if(dmg<0) dmg = 0;
	println(player.getName()+"对"+enemy.getName()+"照成"+dmg+"点伤害.");
	util.println(player.getName()+"对"+enemy.getName()+"照成"+dmg+"点伤害.");
	atked.setHP( atked.getHP() - dmg );
}

function open(color){
	println(color);
}