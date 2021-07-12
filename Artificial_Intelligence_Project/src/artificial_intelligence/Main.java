package artificial_intelligence;

import Final_Dungeon.Stage;

public class Main {

	public static void main(String[] args) {
		SearchingTechniques S = new SearchingTechniques();

		Stage initial_table = Stage.generateStage(7);

		multitree tree = new multitree(initial_table);
		long t1 = System.currentTimeMillis();
		S.Depth(tree);
		long t2 = System.currentTimeMillis();

		System.out.println("Depth: " + (t2 - t1));

	}
}
