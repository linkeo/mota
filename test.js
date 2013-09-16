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
	if(player.hp>0&&enemy.hp>0){
		println("player:"+player.hp+"\t  enemy:"+enemy.hp);
		return true;
	}else{
		if(player.hp>0)
			println("player wins");
		else
			println("player loses.");
		return false;
	}
}

function sleep(){
	util.sleep();
}

function attack(atker, atked){
	var dmg = atker.atk - atked.def;
	if(dmg<0) dmg = 0;
	atked.hp -= dmg;
}