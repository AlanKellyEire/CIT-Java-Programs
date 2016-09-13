import java.util.Scanner;


public class Lab9_2 {

	public static void main(String[] args) {
	
		Scanner keyboard = new Scanner(System.in);
		
		int totSec, hours, minutes, secs; 
		
		// Prompt user's for no. of seconds it took,
		System.out.print("how many seconds? ");
		totSec = keyboard.nextInt();
				
		hours = totSec / 3600;
		secs =  totSec % 3600;
		minutes = secs / 60;
		secs =  secs % 60;
		
		// Display the resulting information
		System.out.println("You ran for " + hours + " hours, " + minutes + " minutes and " + secs + " seconds.");

	}

}
