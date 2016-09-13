import java.util.Scanner;

public class Lab15_2chb {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		int num;
		
		// Prompt user's for an odd number,
		System.out.println("Enter a positive number");
		num = keyboard.nextInt();
		while (num < 1)
		{
			System.out.println("Invalid input! Enter a positive number");
			num = keyboard.nextInt();
		}
		
		for (int factor = 1; factor <= num/2; factor++)
		{
			if (num % factor == 0)
				System.out.print(factor + " " );
		
		}
		
		keyboard.close();
	}

}

