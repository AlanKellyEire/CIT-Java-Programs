import java.util.Scanner;

public class Lab15_1 {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		int num;
		
		// Prompt user's for an odd number,
		System.out.println("Enter a positive odd number");
		num = keyboard.nextInt();
		while (num < 1 || num % 2 == 0)
		{
			System.out.println("Invalid input! Enter a positive odd number");
			num = keyboard.nextInt();
		}
		
		for (int count = 1; count <= num; count++)
			System.out.print("*");
		
		keyboard.close();
			
	}

}
