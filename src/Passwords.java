import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;

public class Passwords {

	protected static int num_passwords;
	protected static int password_length;
	protected static int[][] letter_grid;
	protected static int[] STARTERS;
	protected static int[] COUNTS;
	protected static int total_num_letters;
	protected static int num_starters;
	protected static File dict;
	protected static File f;
	protected static boolean custom_file;
	protected static LinkedHashSet<String> the_file;
	protected static ArrayList<String> bad_password;
	
	public static void main(String[] args) {
		if(args.length == 4){ custom_file = true; } else{ custom_file = false;}
		if(!custom_file && args.length != 3){
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
			dict = Paths.get("/usr/share/dict/words").toFile();
			the_file = new LinkedHashSet<String>();
			if(custom_file)
				f = new File(args[3]);
			bad_password = new ArrayList<String>();
		} catch (Exception e) {
			System.out.println("An error occured, please try again.");
			e.printStackTrace();
			System.exit(-1);
		}
		
		if(num_passwords <= 0){ System.out.println("Enter a larger number for amount of passwords to generate."); System.exit(-1);}
		if(password_length <= 0){ System.out.println("How can I generate a password with length 0...or less?!  You cray cray."); System.exit(-1);}
		parse_input(sc);
		print_array(letter_grid, false);
		Random r = new Random();
		process_dict(dict);
		if(custom_file) process_dict(f);
		
		System.out.println("Passwords are: ");
		while(num_passwords-- > 0){
			ArrayList<Byte> password = new ArrayList<Byte>();
			byte start_letter_index = get_letter(r, STARTERS, num_starters);
			password.add(start_letter_index);
			
			for(int i = 0; i < (password_length - 1); i++){
				password.add(new Integer(get_letter(r, letter_grid[password.get(i)], COUNTS[password.get(i)])).byteValue());
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			for(Byte b : password){
				out.write(b + 97);
			}
			try {
				String thoughshallnotpass = new String(out.toByteArray(), "UTF-8");
				if (password_length > 3)
					parse_password(thoughshallnotpass);
				else
					System.out.println(thoughshallnotpass);					
			} catch (UnsupportedEncodingException e) {
				System.out.println("Something wicked this way comes.");
				e.printStackTrace();
			}
		}
		
		
		System.out.println("\n" + bad_password.size() + " Bad Passwords: ");
		if(bad_password.size() > 0){
			Iterator<String> it = bad_password.iterator();
			while (it.hasNext()) System.out.println(it.next());
		}else{
			System.out.println("None found");
		}
	}
	
	private static void parse_password(String p){
		int length = p.length();
		for(int c = 0 ; c <= length-4 ; c++ )
		{
			for(int i = c+4 ; i < length+1; i++ )
			{
				String sub = p.substring(c, i);
				if (the_file.contains(sub)){
					bad_password.add("Bad password: " + p + ", Substring is: " + sub);
					return;
				}
			}
		}
		System.out.println(p);
	}

	private static void process_dict(File thing) {
		try {
			Scanner testing = new Scanner(thing);
			while(testing.hasNext()){
				the_file.add(testing.next().split("[^a-zA-Z ]")[0].toLowerCase());
			}
			testing.close();
		} catch (FileNotFoundException e) {
			System.out.println("uh oh");
		}
	}

	private static byte get_letter(Random r, int[] array, int total_letters) {
		int num = r.nextInt(total_letters);
		int subtotal = 0;
		for(int i = 0; i < array.length; i++){
			subtotal += array[i];
			if(subtotal >= num)
				return new Integer(i).byteValue();
		}
		return -1;
	}
	
	private static void parse_input(Scanner sc) {
		while(sc.hasNextLine()){
			String[] line = sc.nextLine().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
			for (String e: line){
				for (int i = 0; i < e.length(); i++){
					char x = e.charAt(i);
					int index = Math.abs(('z'-x) - 25);
					if(i == 0){ STARTERS[index]++; num_starters++; }
					if(i != (e.length()-1)) update_table(e, index, i);
					total_num_letters++;
				}
			}
		}
	}

	private static void update_table(String e, int index, int i) {
		COUNTS[index]++;
		letter_grid[index][Math.abs(('z' - e.charAt(i+1)) - 25)]++;
	}

	static void print_array(int[][] a, boolean hex){
		for(int[] i: a){
			for(int j: i){
				if(hex)
					System.out.print(Integer.toHexString(j) + " ");
				else
					System.out.format("%7s", j + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
