import java.util.Scanner;

public class assignment_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner keyboard = new Scanner(System.in);
		
		final double PERKM = 1.50, MINCHARGE = 4.50;
		double tipPercent, distance, tipAm, finalPrice = 0;
		int NoPassengers, tipOption, tipinput;
		
		// Prompt user's for distance travelled in km,
		System.out.println("what distance did u travel in km");
		distance = keyboard.nextDouble();
		
		// Prompt user's number of passengers,
		System.out.println("How Many Passengers travelled");
		NoPassengers = keyboard.nextInt();
		
		finalPrice = distance * PERKM;
		if	(finalPrice < MINCHARGE)
			finalPrice = MINCHARGE;

		finalPrice += (NoPassengers - 1) * 1;
			
		//Prompt user for tip option
		System.out.println("Please choose from the following tip options:\n 1. no tip \n 2. 10% \n 3. 15% \n 4. 20% \n 5. other");
		tipOption = keyboard.nextInt();
			
		switch (tipOption)
		{
			case 1:
				break; 
				
			case 2:
				finalPrice *= 1.1 ;
			 	break;
			 	
			case 3:
				finalPrice *= 1.15 ;
				break;
				
			case 4:
				finalPrice *= 1.2 ;
				break;
			
			case 5:
				System.out.println("Please choose how you would like to tip: \n 1. percentage \n 2. euro amount: ");
				tipinput = keyboard.nextInt();
				if (tipinput == 1)
				{
					System.out.println("Please enter the percentage you would like to tip: ");
					tipPercent = keyboard.nextDouble();
					finalPrice *= (1 + tipPercent/100);
				}
				else if (tipinput == 2) 
				{
					System.out.println("Please enter the amount you would like to tip: ");
					tipAm = keyboard.nextDouble();
					finalPrice += tipAm;
				}
				else
					System.out.println("Invalid input!");
				break;
				
			default:
			System.out.println ("ERROR!");
		}
		
		System.out.printf("The total charge is:€%.2f\n", finalPrice);
		
						
		
			
		
	}

}
