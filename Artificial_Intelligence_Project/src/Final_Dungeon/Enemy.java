package Final_Dungeon;

public class Enemy {
	
	public Enemy(Enemy e) {
		name=  e.name;
		enemy_die_dialog = e.enemy_die_dialog;
		player_die_dialog = e.player_die_dialog;
		description = e.description;
		damage = e.damage;
		hp = e.hp;
		isFirst = e.isFirst;
	}

	public String name;
	public String enemy_die_dialog;
	public String player_die_dialog;
	public String description;
	public int damage;
	public int hp;
	public boolean isFirst = false;
	
	public Enemy(String name, int damage, int hp) {
		this.name = name;
		this.damage = damage;
		this.hp = hp;
		enemy_die_dialog = "Player defeated "+name+".";
		player_die_dialog = name+" killed the player.";
	}
	
	/*
	 * Utility
	 */
	public void printEnemy() {
		System.out.println("name: "+name+"||hp: "+hp);
	}
	
	public String gethp() {
		if(hp>500)
			return "inf";
		if(hp<=0)
			return "0";
		return hp+"";
	}
	
	
	
	/*
	 * Static functions	
	 */
	
	public static Enemy generateEnemy(String e) {
		Enemy n = null;
		if(e.equals("slime")) {
			n = new Enemy(e, 1, 1);
			n.description = "An enemy that deals "+n.damage+" damage and have "+n.hp+" hp.";
			
		}
		else if(e.equals("skeleton")) {
			n = new Enemy(e, 1, 2);
			n.description = "An enemy that deals "+n.damage+" damage and have "+n.hp+" hp.";
		}
		else if(e.equals("pit")) {
			n = new Enemy(e, 999, 999);
			n.description = "A deadly pit.";
			n.enemy_die_dialog = "player succefully avoided the pit.";
			n.player_die_dialog = "player has fallen to his death.";
		} else if(e.equals("spear skeleton")) {
			n = new Enemy(e, 2, 2);
			n.description = "An enemy that deals "+n.damage+" damage and have "+n.hp+" hp.";
		} else if(e.equals("evil eye")) {
			n = new Enemy(e, 10, 1);
			n.isFirst = true;
			n.description = "An enemy that deals "+n.damage+" damage and have "+n.hp+" hp.";
		} else if(e.equals("golem")) {
			n = new Enemy(e, 3, 20);
			n.description = "An enemy that deals "+n.damage+" damage and have "+n.hp+" hp.";
		} 
		
		return n;
	}
	
	
	
	
	
}
