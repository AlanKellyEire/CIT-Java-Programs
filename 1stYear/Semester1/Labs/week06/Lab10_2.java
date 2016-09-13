import java.util.Scanner;

public class Lab10_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner keyboard = new Scanner(System.in);
		
		int day, month, year, finalName;
		char upperFirst;
		String name, nameLower, nameUpper;
		
		
	
		// Prompt user's What is their first name,
		System.out.println("What is your first name? ");
		name = keyboard.nextLine();
		nameUpper = name.toUpperCase();
		nameLower = name.toLowerCase();
		
		// Prompt user's What day were u born,
		System.out.println("What day were u born? ");
		keyboard.nextLine();
		
		// Prompt user's What month were u born,
		System.out.println("What month were u born? ");
		month = keyboard.nextInt();
		
		// Prompt user's What year were u born,
		System.out.println("What year were u born? ");
		year = keyboard.nextInt();
		
		finalName = nameUpper.charAt(0)
		+ month%10 + month/10 + year%10 + (year/10)%10;
		
		System.out.print("Your Password is " + (nameLower+finalName));
	}

}
