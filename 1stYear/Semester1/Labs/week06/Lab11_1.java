import java.util.Scanner;

public class Lab11_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner keyboard = new Scanner(System.in);
		
		int day, month, year, magic;
		
		// Prompt user's What day were u born,
		System.out.println("What day were u born? ");
		day = keyboard.nextInt();
				
		// Prompt user's What month were u born,
		System.out.println("What month were u born? ");
		month = keyboard.nextInt();
			
		// Prompt user's What year were u born,
		System.out.println("What year were u born? ");
		year = keyboard.nextInt();
				
		if ((day * month) == year)
			System.out.println("your dob is magic");
		else
			System.out.println("your dob is not magic");

	}

}
