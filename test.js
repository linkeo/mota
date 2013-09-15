function fight(){
	/* player, enemy is global input from jvm */
	var attack = function(atker, atked){
		var dmg = atker.atk - atked.def;
		if(dmg<0) dmg = 0;
		atked.hp -= dmg;
	}
	
}
function sleepTest(){
	
}