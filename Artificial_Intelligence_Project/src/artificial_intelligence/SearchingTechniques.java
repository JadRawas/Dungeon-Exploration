package artificial_intelligence;

import Final_Dungeon.Stage;

public class SearchingTechniques {

	protected int jDepth = 11;
	protected boolean found = false;
	protected boolean notend = false;;
	protected multitree<Stage> tree;
	public String soln;

	public void Depth(multitree tree1) {
		this.tree = tree1;
		Depth(tree.root, 0);
	}

	private void Depth(multinode node, int c) {
		c++;
		Stage table_node = (Stage) node.data;
		if (c > jDepth || found) {
			return;
		}

		if (table_node.won) {
			//System.out.println("yes");
			soln  = tree.get_solution(node);
			found = true;
			return;
		}
//		System.out.println("===============FIGHT===========");
//		tree.display_solution(node);
		if (table_node.loss) {
			//System.out.println("no");
			return;
		}

		else {
			int i = 0;
			while (true) {

				Stage new_table1 = apply(i, table_node);
				if (new_table1 != null) {
					String action = new_table1.player.skills[i].name + " was used";
					tree.insertnode(new_table1, node.id, action);
					Depth(tree.search_data(new_table1), c);
				} else if (notend) {
					notend = false;
				} else {
					break;
				}
				i++;
			}

		}

	}

	/*
	 * for (int i = 1; i <= 10; i++) { table new_table1 = new table(); new_table1 =
	 * apply(i, table_node);
	 * 
	 * if (new_table1 != null) { tree.insertnode(new_table1, node.id);
	 * Depth(tree.search_data(new_table1), c); } }
	 */

	/////////////////////////////////////////////////////////////////////////////
	public Stage apply(int op, Stage before) {
		
		Stage after = Stage.copyStage(before);
		if (!after.isSkill(op)) {
			return null;
		} else if (after.player.skills[op].skill_count > 0) {
			after.applyActionAI(op);
			
			
			/*System.out.println("====BEFORE====");
			before.printStage();
			System.out.println("====AFTER===="+(op+1));
			after.printStage();*/
			return after;
		} else {
			notend = true;
			return null;
		}

	}
}
