package artificial_intelligence;

public class table {

	public int timeleft = 15;
	public char torch = 'e';
	public char[] loc = { 'e', 'e', 'e', 'e' };
	public int[] speed = { 1, 2, 5, 8 };

	protected String action = "Initial state";

	public table() {
	}
	

	public table(table t) {
		this.timeleft = t.timeleft;
		this.torch = t.torch;
		for (int i = 0; i < loc.length; i++) {
			this.loc[i] = t.loc[i];
			this.speed[i] = t.speed[i];
		}
	}

	public table(int timeleft, char torch, char[] loc) {

		this.timeleft = timeleft;
		this.torch = torch;
		for (int i = 0; i < loc.length; i++) {
			this.loc[i] = loc[i];
			this.speed[i] = speed[i];
		}
	}

	public String EastOrWest(char c) {
		if (c == 'e')
			return "East";
		if (c == 'w')
			return "West";
		return "Error";
	}

	@Override
	public String toString() {
		char[] characters = {'A','B','C','D'};
		String str = "";
		for (int i = 0; i < loc.length; i++) {
			str = str +characters[i]+": "+loc[i]+"\t";
		}
		str = str+"\n";
		return str;
	}
	
	

	public boolean isequal(table t) {
		for (int i = 0; i < loc.length; i++) {
			if (loc[i] == 'e')
				return false;
		}
		return true;
	}

}
