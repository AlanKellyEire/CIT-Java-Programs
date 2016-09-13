import java.util.Scanner;


public class Lab13_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner keyboard = new Scanner(System.in);
		
		int n, value, total = 0;
		
		System.out.print("Please enter a number for n: ");
		n = keyboard.nextInt();
		
		while (n < 1)
		{
			System.out.print("Invalid input. Please enter a number for n: ");
			n = keyboard.nextInt();
		}
		
		for(value=1; value<=n; value++)
			total += value;
		
		System.out.print("The sum of the numbers is:  "  + total);

		keyboard.close();
		
	}

}
