import java.util.Scanner;

public class Lab9_1 {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		int totEggs, gross, dozen, eggs; 
		
		// Prompt user's for no. of eggs,
		System.out.print("how eggs were sold? ");
		totEggs = keyboard.nextInt();
				
		gross = totEggs / 144;
		dozen = (totEggs - (gross * 144)) / 12;
		eggs = (totEggs - (gross * 144) - (dozen * 12)) / 1;
		
		// Display the resulting information
		System.out.println("You sold " + gross + " gross, " + dozen + " dozen and " + eggs + " eggs.");
			   	
	}

}
