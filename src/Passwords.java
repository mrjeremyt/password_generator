import java.io.File;
import java.io.FileNotFoundException;
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
		} catch (FileNotFoundException e) {
			System.out.println("wasn't able to open the reference text");
			e.printStackTrace();
		}
		
		parse_input(sc);
	
	}

	private static void parse_input(Scanner sc) {
		while(sc.hasNextLine()){
			String line = sc.nextLine().replaceAll("[^A-Za-z]+", "");
			Scanner l = new Scanner(line);
			while(l.hasNext()){
				String t = l.next().toLowerCase();
				System.out.print(t);
			}
			System.out.println();
		}
	}

}
