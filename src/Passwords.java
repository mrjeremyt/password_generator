import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.Scanner;


public class Passwords {

	protected int num_passwords;
	protected int password_length;
	protected int[][] letter_grid;
	protected int[] STARTERS;
	protected int[] COUNTS;
	
	public static void main(String[] args) {
		String[] parts = args[0].split("-");
		String part2 = parts[1];
		Scanner sc = null;
		try {
			sc = new Scanner(new File(part2));
		} catch (FileNotFoundException e) {
			System.out.println("wasn't able to open the reference text");
			e.printStackTrace();
		}
		
		parse_input(sc);
	
	}

	private static void parse_input(Scanner sc) {
		while(sc.hasNextLine()){
			
		}
	}

}
