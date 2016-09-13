import java.util.Scanner;

public class lab15_3 {

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
		
		for (int NoStars = num; NoStars >= 1; NoStars--)
		{
			for (int starPrint = 1; starPrint <= NoStars; starPrint++)
			{
				System.out.print("*");
			}
			System.out.println();
		
		}
		
		keyboard.close();
		
	}

}
