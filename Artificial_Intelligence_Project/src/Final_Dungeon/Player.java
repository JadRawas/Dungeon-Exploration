package Final_Dungeon;

public class Player {

	public int hp;
	public Skill[] skills;
	
	public Player(int hp, Skill[] s) {
		this.hp = hp;
		skills = new Skill[s.length];
		for (int i = 0; i < s.length; i++) {
			skills[i] = Skill.generateSkill(s[i].name, s[i].skill_count);
		}
	}
	
	
	/*
	 * Utility
	 */
	public void printPlayer() {
		System.out.println("hp: "+hp);
		System.out.println("skills: ");
		for (int i = 0; i < skills.length; i++) {
			System.out.print((i+1)+") ");
			skills[i].printSkill();
		}
	}
	
	public static Player copyPlayer(Player p) {
		return new Player(p.hp, p.skills);
	}
	
		
}
