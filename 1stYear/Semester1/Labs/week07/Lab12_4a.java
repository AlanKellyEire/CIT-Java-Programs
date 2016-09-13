import java.util.Scanner;

public class Lab12_4a {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner keyboard = new Scanner(System.in);
		
		int number;
		
		// Prompt user's for a Number,
		System.out.println("Enter a Number between 1 and 10");
		number = keyboard.nextInt();
		
		if (number == 1)
			System.out.println("Roman Numeral equivalent is I");
		else if (number == 2)
			System.out.println("Roman Numeral equivalent is II ");
		else if (number == 3)
			System.out.println("Roman Numeral equivalent is III");
		else if (number == 4)
			System.out.println("Roman Numeral equivalent is IV");
		else if (number == 5)
			System.out.println("Roman Numeral equivalent is V");
		else if (number == 6)
			System.out.println("Roman Numeral equivalent is VI");
		else if (number == 7)
			System.out.println("Roman Numeral equivalent is VII");
		else if (number == 8)
			System.out.println("Roman Numeral equivalent is VIII");
		else if (number == 9)
			System.out.println("Roman Numeral equivalent is IX");
		else if (number == 10)
			System.out.println("Roman Numeral equivalent is X");
		else
			System.out.print("an error has occured");
				
				

	}

}
