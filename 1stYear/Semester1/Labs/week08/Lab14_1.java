import java.util.Scanner;

public class Lab14_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner keyboard = new Scanner(System.in);
		
		int guess, realNumber;
		
		// Prompt user's for Guess,
		System.out.println("enter your number between 1-100");
		guess = keyboard.nextInt();

		realNumber = (int)(Math.random()*100) +1;
		
		while (guess != realNumber)
		{
			if (guess > realNumber)
				System.out.println("too high guess again: ");
			else 
				System.out.println("too Low guess again: ");
			guess = keyboard.nextInt();
		}
				
		System.out.println("Congrats You are Correct");
				
		keyboard.close();
			
	}

}
