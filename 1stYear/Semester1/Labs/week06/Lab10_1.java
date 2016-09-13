import java.util.Scanner;

public class Lab10_1 {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);

		final double LARGE = 15.99, SMALL = 12.99, DRINK = 2.49;
		double orderPrice = 0;
		int size, noDrink;
		char drink;
		String inputString;
		
		// Prompt user's for pizza size,
		System.out.println("Choose your pizza's size\n1: small\n2: large");
		size = keyboard.nextInt();
		
		if (size == 2)
			orderPrice = LARGE; 
		else if (size == 1)
			orderPrice = SMALL;
		else
			System.out.println("Invalid pizza choice");
		
		// Prompt user's if they would like a drink,
		System.out.println("would you like a drink y/n? ");
		keyboard.nextLine();
		inputString = keyboard.nextLine();
		drink = inputString.charAt(0);
				
		if (drink == 'y') {
			orderPrice += DRINK;
			System.out.println("How Many? ");
			noDrink = keyboard.nextInt();
			orderPrice += (DRINK * noDrink);
		}
		else if (drink != 'n')
			System.out.println("Invalid drink choice");
		
		
		System.out.print("That will cost €" + orderPrice + " please");

	
	}

}
