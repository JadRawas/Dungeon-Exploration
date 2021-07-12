package Final_Dungeon;
import java.io.Console;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		
		Stage s0 = Stage.generateStage(7);
		Stage s1 = Stage.copyStage(s0);
		s1.printStage();
		int result = 0;
		while(true) {
			System.out.println("\n=================================");
			int n = s.nextInt();
			result = s1.playerAction(n);
			System.out.println();
			s1.printStage();
			if(result == 1 || result == -1) {
				break;
			}
		
		}
		if(result == 1) {
		System.out.println("you won");
		} else {
			System.out.println("you lost");
		}

	}
	
	

}
