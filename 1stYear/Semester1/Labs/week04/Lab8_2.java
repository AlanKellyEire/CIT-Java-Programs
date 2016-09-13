import java.util.Scanner;

public class Lab8_2 {

	public static void main(String[] args) {
		
		int numTries, numCon, numPen, numDrop, tryPoint, conPoint, penPoint, dropPoint, finalScore;
		
		Scanner keyboard = new Scanner(System.in);
				
		// Prompt user's for no. of tries,
		System.out.print("how many tries were scored? ");
		numTries = keyboard.nextInt();
				
		// Prompt user's for no. of conversions,
		System.out.print("how many conversions were scored? ");
		numCon = keyboard.nextInt();
		
		// Prompt user's for no. of penalties,
		System.out.print("how many penalties were scored? ");
		numPen = keyboard.nextInt();
		
		// Prompt user's for no. of drop goals,
		System.out.print("how many drop goals were scored? ");
		numDrop = keyboard.nextInt();
		
		tryPoint = numTries * 5;
		conPoint = numCon * 2;
		penPoint = numPen * 3;
		dropPoint = numDrop * 3;
		
		finalScore = tryPoint + conPoint + penPoint + dropPoint;
		
		// Display the resulting information
	   	System.out.println("Your team scored " + finalScore);
	   	
	}

}
