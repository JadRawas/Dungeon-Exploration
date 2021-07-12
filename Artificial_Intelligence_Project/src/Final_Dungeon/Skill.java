package Final_Dungeon;
import javax.swing.plaf.SliderUI;

public class Skill {

	public String name;
	public String desc;
	public int damage;
	public int skill_count;
	public int enemy_count;

	public Skill(String name, int damage, int enemy_count, int skill_count) {
		this.name = name;
		this.damage = damage;
		this.enemy_count = enemy_count;
		this.skill_count = skill_count;
	}
	
	/*
	 * Utility
	 */
	
	public void printSkill() {
		String s=skill_count>=50?"Infinite":skill_count+"";
		System.out.println(name+" - "+s);
	}

	/*
	 * Static
	 */

	public static Skill generateSkill(String s, int c) {
		Skill n = null;

		if (s.equals("swing")) {
			n = new Skill(s, 1, 1, c);
			n.desc = "A regular sword swing, deals "+n.damage+" damage";
			return n;
		} else if (s.equals("slash")) {
			n = new Skill(s, 2, 1, c);
			n.desc = "Slashes the enemies with your sword to deal "+n.damage+" damage";
		} else if (s.equals("dash")) {
			n = new Skill(s, 999, 1, c);
			n.desc = "Dash to avoid any obstacle";
		} else if (s.equals("fireball")) {
			n = new Skill(s, 5, 2, c);
			n.desc = "Cast a fireball with your magic to deal damage "+n.damage+" for 2 enemies in a row";
		}

		return n;
	}
	
	public String getCount() {
		if(skill_count >= 50)
			return "Inf";
		return skill_count+"";
	}
	
	public String getDmg() {
		if(damage == 999)
			return "NA";
		return damage+"";
	}
	

}
