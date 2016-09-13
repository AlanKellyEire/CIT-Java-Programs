import java.util.Scanner;

public class Lab12_4b {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner keyboard = new Scanner(System.in);
		
		int number;
		
		// Prompt user's for a Number,
		System.out.println("Enter a Number between 1 and 10");
		number = keyboard.nextInt();
		
		switch (number)
		{
		case 1:
			System.out.println("Roman Numeral equivalent is i");
			break;
		case 2:
			System.out.println("Roman Numeral equivalent is ii ");
			break;
		case 3:
			System.out.println("Roman Numeral equivalent is iii ");
			break;
		case 4:
			System.out.println("Roman Numeral equivalent is iV ");
			break;
		case 5:
			System.out.println("Roman Numeral equivalent is V ");
			break;
		case 6:
			System.out.println("Roman Numeral equivalent is Vi ");
			break;
		case 7:
			System.out.println("Roman Numeral equivalent is Vii ");
			break;
		case 8:
			System.out.println("Roman Numeral equivalent is Viii ");
			break;
		case 9:
			System.out.println("Roman Numeral equivalent is iX ");
			break;
		case 10:
			System.out.println("Roman Numeral equivalent is X ");
			break;
		default:
			System.out.println("an error has occured");
		}
		
				
	}

}
