import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Passwords {

	protected static int num_passwords;
	protected static int password_length;
	protected static int[][] letter_grid;
	protected static int[] STARTERS;
	protected static int[] COUNTS;
	
	public static void main(String[] args) {
		if(args.length != 3){
			System.out.println("incorrect number of arguments. Please correct"); System.exit(-1);
		}
		Scanner sc = null;
		try {
			sc = new Scanner(new File(args[0].split("-")[1]));
			num_passwords = Integer.parseInt(args[1]);
			password_length = Integer.parseInt(args[2]);
			STARTERS = new int[26];
			COUNTS = new int[26];
			letter_grid = new int[26][26];
		} catch (FileNotFoundException e) {
			System.out.println("wasn't able to open the reference text");
			e.printStackTrace();
		}
		
		if(num_passwords == 0){ System.out.println("Enter a larger number for amount of passwords to generate."); System.exit(-1);}
		
		parse_input(sc);
//		print_array(letter_grid, false);
		Random t = new Random(9001);
		Random r = new Random(t.nextLong());
		
		while(num_passwords > 0){
			
		}
		
		
		
		
		
		
		
	}

	private static void parse_input(Scanner sc) {
		while(sc.hasNextLine()){
			String[] line = sc.nextLine().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
			for (String e: line){
				for (int i = 0; i < e.length(); i++){
					char x = e.charAt(i);
					int index = Math.abs(('z'-x) - 25);
					if(i == 0) STARTERS[index]++;
					if(i != (e.length()-1)) update_table(e, index, i);
				}
			}
		}
	}

	private static void update_table(String e, int index, int i) {
		COUNTS[index]++;
		letter_grid[Math.abs(('z' - e.charAt(i+1)) - 25)][index]++;
	}

	static void print_array(int[][] a, boolean hex){
		for(int[] i: a){
			for(int j: i){
				if(hex)
					System.out.print(Integer.toHexString(j) + " ");
				else
					System.out.format("%4s", j + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
