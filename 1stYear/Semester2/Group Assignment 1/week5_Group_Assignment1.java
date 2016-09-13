import java.util.Scanner;

import javax.swing.*;
public class week5_Group_Assignment1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String input;
		int gameOption;

		System.out.println("================ Welcome to hangman ================");
		input = getInput();
		gameOption = getGameOption();
		playHangMan(input, gameOption);
		System.exit(0);

	}

	public static String getInput() {	
		Scanner keyboard = new Scanner(System.in);

		String input;

		do
		{
			input = JOptionPane.showInputDialog("Enter word to be guessed by user here:");
			for(int i = 0; i <= input.length()-1; i++)
			{
				if (Character.isAlphabetic(input.charAt(i)))
					return input;
			}

		} while(true);
	}

	public static int getGameOption() {
		Scanner keyboard = new Scanner(System.in);
		int opt;

		System.out.println("\n\nPlease choose from the following game options:\n\t1: Beginner (vowels displayed)\n\t2: Intermediate (number of vowels given)\n\t3: Expert (no additional information given)");
		opt = readInt();

		while (opt < 1 || opt >3 ) 
		{  
			System.out.println("Invalid input, please enter 1 - 3:");
			opt = readInt();
		}

		return opt;
	}

	public static int readInt() {
		Scanner keyboard = new Scanner(System.in);
		while (!keyboard.hasNextInt())
		{
			System.out.println("Invalid input, please enter an integer: ");
			keyboard.next();
		}
		return keyboard.nextInt();
	}

	public static String initialDisplayString(String input, int gameOption) {

		String displayString = "";

		for(int i = 0; i <=input.length()-1; i++)
		{
			if (!Character.isAlphabetic(input.charAt(i)) || (gameOption == 1 && isVowel(input.charAt(i))))
			{
				displayString += input.charAt(i);
			}
			else
			{
				displayString += '*';
			}
		}
		
		return displayString;
	}

	public static boolean isVowel(char letter) {

		letter = Character.toLowerCase(letter);
		if(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u')
			return true;
		else
			return false;
	}

	public static void playHangMan(String input, int gameOption) {

		int lives = 6;
		String displayString;
		char guess;

		if (gameOption == 2)
		{
			System.out.println (countVowels(input) + " of the characters in the string are vowels. \n");		
		}

		Scanner keyboard = new Scanner(System.in);

		displayString = initialDisplayString(input, gameOption);

		do {

			System.out.print(displayString);
			System.out.print("\tLives Remaining: " + lives);
			guess = getGuess();

			if (hasLetter(input, guess))
				displayString=displayLetter(input, displayString, guess);
			else
				lives --;

		} while (lives >0 && !displayString.equals(input)); 

		if (lives > 0)
			System.out.print("Congratulations! The answer was " + input);
		else
			System.out.print("Game over! The answer was " + input);
	}


	public static int countVowels(String input) {

		int numVowels = 0;

		for (int c=0; c<=input.length()-1; c++)
		{
			if(isVowel(input.charAt(c)))
				numVowels++;
		}
		return numVowels;
	}

	public static String displayLetter(String input, String displayString, char guess) {

		String output = "";
		for (int i=0; i<=input.length()-1; i++)
		{
			if(input.toLowerCase().charAt(i) == Character.toLowerCase(guess))
				output += input.charAt(i);
			else 
				output += displayString.charAt(i);
		}
		
		return output;

	}


	public static boolean hasLetter(String input, char guess){

		for(int c=0; c<=input.length()-1; c++)
		{
			if (input.toLowerCase().charAt(c) == Character.toLowerCase(guess))
				return true;
		}  
		return false;
	}
	
	
	public static char getGuess(){
		
		Scanner keyboard = new Scanner(System.in);

		char guess = 0;
		String inputString;
		boolean valid = false;
		
		do
		{
			System.out.print("\tEnter guess: ");
			inputString = keyboard.nextLine();
			if (inputString.length() == 1)
			{
				guess = inputString.charAt(0);
				if (Character.isAlphabetic(guess))
					valid = true;
			}
		} while (!valid);

		return guess;
		
	}

}















