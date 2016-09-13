import java.util.Scanner;

public class GroupAssigment3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner keyboard = new Scanner(System.in);
		
		String name, phone, title, orderDetails = "", itemFormat, maxItem;
		double price, cdTotal, lpTotal, csTotal;
		int numItems, format, cdNum, lpNum, csNum;
		
		//Prompt Booker for their Name
		System.out.println("What is your name? ");
		name = keyboard.nextLine();
		
		//Prompt Booker for their number
		System.out.println("What is your phone number? ");
		phone = keyboard.nextLine();
		
		//Prompt for number of Item to be purchased
		System.out.println("Please enter the number of items you wish to purchase:? ");
		while (!keyboard.hasNextInt())
		{
			System.out.println("Invalid input!Please enter the number of items you wish to purchase:? ");
			keyboard.next();
		}
		//Input Validation
		numItems = keyboard.nextInt();
						
		while (numItems < 0) 
		{  
			System.out.println("Please enter a positive integer value for the number of days: ");
			while (!keyboard.hasNextInt())
			{
				System.out.println("Invalid input! Please enter a positive integer value for the number of days: ");
				keyboard.next();
			}
			numItems = keyboard.nextInt();
		}
		
		//prompt for each band members name and their instrument
				System.out.println("Please enter title #1: ");
				//eat up new character line
				keyboard.nextLine();
				title = keyboard.nextLine();
				orderDetails += "1: " + title;
				System.out.println("Please enter the price of title #1: \n€.");
				while (!keyboard.hasNextDouble())
				{
					System.out.print("Invalid input! Please enter the price of title #1: \n€.");
					keyboard.next();
				}
				price = keyboard.nextDouble();
					
				//Input Validation
				while (price > 0) 
				{
					System.out.println("INVALID INPUT!! Please enter the price of title #1: \n€.");
					while (!keyboard.hasNextInt())
				    {
				    	System.out.print("Invalid input! Enter an double: ");
				    	keyboard.next();
				    }
					price = keyboard.nextDouble();
					orderDetails += " - " + price;
				}
				orderDetails += "\n";
						
				for (int item = 2; item <= numItems; item++)
				{
					//prompt for each band members name and their instrument
					System.out.println("Please enter title # " + item + ".");
					orderDetails += item + ": ";
					title = keyboard.nextLine();
					orderDetails += title;
					System.out.println("Please enter the price of title # " + item + " \n€.");
					price = keyboard.nextDouble();
					memberList += " - " + memberInst;
					memberList += "\n";
				}
	}

}
