import java.util.Scanner;

public class Lab9_3 {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		double run1, run2, run3, avg, avgMps, avgKph, avgMph km, meters, miles;
		
		// Prompt user's for Run no.1 time,
		System.out.print("Enter Run no.1 time in mins? ");
		run1 = keyboard.nextDouble();
		
		// Prompt user's for Run no.2 time,
		System.out.print("Enter Run no.2 time in mins? ");
		run2 = keyboard.nextDouble();
		
		// Prompt user's for Run no.3 time,
		System.out.print("Enter Run no.3 time in mins? ");
		run3 = keyboard.nextDouble();
		
		avg = (run1 + run2 + run3) / 3;
		km = 1;
		meters = 1000;
		miles = km *5/8
		avgMps = avg * 60;
		avgKph = avg / 60;
		avgMph = (avg / 60) * 5/8;
				
		// Display the resulting information
		System.out.println("Your Average was time was " + avg);
		
		
	}

}
