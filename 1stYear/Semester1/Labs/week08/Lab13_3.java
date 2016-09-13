import java.util.Scanner;


public class Lab13_3 {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);

		int har, value;
		double total=0;
		
		System.out.print("Please enter a number for harmonic: ");
		har = keyboard.nextInt();
		
		while (har < 1)
		{
			System.out.print("Invalid input. Please enter a number for harmonic: ");
			har = keyboard.nextInt();
		}
		
		for (value=1; value<=har; value++)
			total += 1.0/value;
		
		System.out.print("The harmonic series is:  "  + total);
			
		keyboard.close();
		
	}

}
