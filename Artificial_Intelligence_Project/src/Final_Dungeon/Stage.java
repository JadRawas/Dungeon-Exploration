package Final_Dungeon;

public class Stage {

	public Enemy[] enemies;
	public Player player;
	public boolean won = false;
	public boolean loss = false;

	public Stage(Enemy[] e, Player p) {
		
		enemies = new Enemy[e.length];
		player = Player.copyPlayer(p);
		for (int i = 0; i < e.length; i++) {
			enemies[i] = new Enemy(e[i]);
		}
	}

	/*
	 * Player ...
	 */

	// functions that allows the player to chose a skill
	// returns 0 if nothing happened, 1 if the player won, -1 if the player lost.
	public int playerAction(int i) {
		i--;
		if (i < 0 || i >= player.skills.length) {
			System.out.println("Invalid skill");
		} else if (player.skills[i].skill_count <= 0)
			System.out.println("you can't use this spell anymore");
		else {
			System.out.println("you used " + player.skills[i].name);
			return applyAction(i);
		}
		return 0;
	}

	// function that computes the game state after the player action
	public int applyAction(int i) {
		Skill s = player.skills[i];
		int p = getCurrPos();

		// Implementing enemy damage
		System.out.println(s.enemy_count);
		for (int j = p; j < enemies.length && j < p + s.enemy_count; j++) {
			enemies[j].hp = enemies[j].hp - s.damage;
		}
		s.skill_count--;

		// Implementing win system
		if (getCurrPos() == -1) {
			return 1;
		}

		// Implementing player damage system
		if (enemies[p].hp > 0) {
			player.hp = player.hp - enemies[p].damage;
		} else if (enemies[getCurrPos()].isFirst) {
			player.hp = player.hp - enemies[getCurrPos()].damage;
		}

		// Implementing loss system
		if (player.hp <= 0) {
			return -1;
		}

		return 0;

	}

	/*
	 * Utility
	 */
	public int getCurrPos() {
		for (int i = 0; i < enemies.length; i++) {
			if (enemies[i].hp > 0)
				return i;
		}
		return -1;
	}

	public void printStage() {
		System.out.println("Current position: " + getCurrPos());
		System.out.println("player\n------");
		player.printPlayer();
		System.out.println("\nEnemies\n------");
		for (int i = 0; i < enemies.length; i++) {
			enemies[i].printEnemy();
		}
	}
	
	
	
	

	/*
	 * Static
	 */

	public static Stage generateStage(int lvl) {
		Stage st = null;

		if (lvl == 1) {
			Enemy[] x = { Enemy.generateEnemy("slime"), Enemy.generateEnemy("skeleton"),
					Enemy.generateEnemy("skeleton"), Enemy.generateEnemy("pit"), Enemy.generateEnemy("skeleton") };
			Skill[] y = { Skill.generateSkill("swing", 99), Skill.generateSkill("dash", 1),
					Skill.generateSkill("slash", 1) };
			Player z = new Player(3, y);
			st = new Stage(x, z);
		} else if (lvl == 2) {
			Enemy[] x = { Enemy.generateEnemy("pit"), Enemy.generateEnemy("skeleton"),
					Enemy.generateEnemy("spear skeleton"), Enemy.generateEnemy("pit"),
					Enemy.generateEnemy("spear skeleton") };
			Skill[] y = { Skill.generateSkill("swing", 99), Skill.generateSkill("dash", 2),
					Skill.generateSkill("slash", 1) };
			Player z = new Player(4, y);
			st = new Stage(x, z);
		} else if (lvl == 3) {
			Enemy[] x = { Enemy.generateEnemy("golem"), Enemy.generateEnemy("evil eye"),
					Enemy.generateEnemy("skeleton"), Enemy.generateEnemy("spear skeleton"),
					Enemy.generateEnemy("slime"), Enemy.generateEnemy("pit"), Enemy.generateEnemy("spear skeleton") };
			Skill[] y = { Skill.generateSkill("swing", 99), Skill.generateSkill("dash", 2),
					Skill.generateSkill("slash", 1), Skill.generateSkill("fireball", 1) };
			Player z = new Player(7, y);
			st = new Stage(x, z);
		}

		return st;
	}
	
	public static Stage copyStage(Stage old) {
		Player p = new Player(old.player.hp,old.player.skills);
		return new Stage(old.enemies, p);
	}
	
	
	/*
	 * AI
	 */



	// function that computes the game state after the player action
	public int applyActionAI(int i) {
		//System.out.println(player.skills[i].name+" :: "+player.skills[i].skill_count);
		Skill s = player.skills[i];
		int p = getCurrPos();

		// Implementing enemy damage
		for (int j = p; j < enemies.length && j < p + s.enemy_count; j++) {
			enemies[j].hp = enemies[j].hp - s.damage;
		}
		s.skill_count--;

		// Implementing win system
		if (getCurrPos() == -1) {
			won = true;
			return 1;
		}

		// Implementing player damage system
		if (enemies[p].hp > 0) {
			player.hp = player.hp - enemies[p].damage;
		} else if (enemies[getCurrPos()].isFirst) {
			player.hp = player.hp - enemies[getCurrPos()].damage;
		}

		// Implementing loss system
		if (player.hp <= 0) {
			loss = true;
			return -1;
		}

		return 0;

	}
	
	//returns true if i corresponds to a skill
	public boolean isSkill(int i) {
		if (i < 0 || i >= player.skills.length) {
			return false;
		}
		return true;
	}
}
