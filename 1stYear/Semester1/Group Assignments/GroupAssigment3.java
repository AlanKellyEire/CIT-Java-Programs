import java.util.Scanner;

public class GroupAssigment3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner keyboard = new Scanner(System.in);
		
		String name, phone, title, orderDetails = "", itemFormat, maxItem = "", orderNumber = "";
		double price, cdTotal = 0, lpTotal = 0, csTotal = 0, totalPrice = 0, maxPrice = 0, cdAverage, lpAverage, csAverage = 0;
		int numItems, format, cdNum = 0, lpNum = 0, csNum = 0, randomNumber, phoneLength;
		
		//Prompt Booker for their Name
		System.out.println("What is your name? ");
		name = keyboard.nextLine();
		while (name.length() < 2)
		{
			System.out.println("Must have min two characters! Please re-enter your name: ");
			name = keyboard.nextLine();
		}
		
		orderNumber += name.charAt(0);
		orderNumber += name.charAt(1);
		orderNumber = orderNumber.toUpperCase();
		
		//Prompt Booker for their number
		System.out.println("What is your phone number? ");
		phone = keyboard.nextLine();
		phoneLength = phone.length();
		while (phoneLength < 4)
		{
			System.out.println("Must have min four digits! Please re-enter your phone number: ");
			phone = keyboard.nextLine();
			phoneLength = phone.length();
		}
		
		for (int count = phoneLength - 4; count <= phoneLength - 1; count++)
			orderNumber += phone.charAt(count);
		
		/*	
		orderNumber += phone.charAt(phone.length() - 1);
		orderNumber += phone.charAt(phone.length() - 2); 
		orderNumber += phone.charAt(phone.length() - 3); 
		orderNumber += phone.charAt(phone.length() - 4);
		*/
		randomNumber = (int)(Math.random()*90)+10;
		orderNumber += randomNumber;
		
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
			System.out.println("Please enter the number of items you wish to purchase:?");
			while (!keyboard.hasNextInt())
			{
				System.out.println("Invalid input! Please enter a positive integer value for the number of item to be purchased: ");
				keyboard.next();
			}
			numItems = keyboard.nextInt();
		}
		
		
						
		for (int item = 1; item <= numItems; item++)
		{
			//prompt for each band members name and their instrument
			System.out.println("Please enter title # " + item + ".");
			//eat up new character line
			keyboard.nextLine();
			title = keyboard.nextLine();
			orderDetails += item + ": ";
			orderDetails += title;
			System.out.println("Please enter the format of " + title + ":" + "\n1:\tCD" + "\n2:\tLP" + "\n3:\tCassette");
			// validate
			while (!keyboard.hasNextInt())
			{
				System.out.println("Invalid input! Please enter the format of " + title + ":" + "\n1:\tCD" + "\n2:\tLP" + "\n3:\tCassette");
				keyboard.next();
			}
			//Input Validation
			format = keyboard.nextInt();
							
			while (format < 1 || format >4 ) 
			{  
			System.out.println("Please enter a positive integer value for your format choice: ");
				while (!keyboard.hasNextInt())
				{
					System.out.println("Invalid input! Please enter a positive integer value for your format choice: ");
					keyboard.next();
				}
				format = keyboard.nextInt();
			}
						        
			if (format == 1)
			{
				orderDetails += " CD";
				itemFormat = "LP";
				cdNum += 1; 
			}
			else if (format == 2)
			{
				orderDetails += " LP";
				itemFormat = "LP";
				lpNum += 1;
			}
			else
			{
				orderDetails += " Cassette";
				itemFormat = "LP";
				csNum += 1;
			}
					
			System.out.println("Please enter the price of " + title + ": \n€.");
			while (!keyboard.hasNextDouble())
			{
				System.out.print("Invalid input! Please enter the price of " + title + ": \n€.");
				keyboard.next();
			}
			price = keyboard.nextDouble();
						
			//Input Validation
			while (price <= 0) 
			{
				System.out.println("INVALID INPUT!! Please enter the price of " + title + ": \n€.");
				while (!keyboard.hasNextDouble())
				{
					System.out.print("Invalid input! Enter an double: ");
					keyboard.next();
				}
				price = keyboard.nextDouble();
			}
					
			orderDetails += " " + price;
			
			if (price > maxPrice)
			{
				maxPrice = price;
				maxItem = (title + " " + itemFormat);
			}
			orderDetails += "\n";
			totalPrice += price;
					
			if (format == 1)
			{
				cdTotal += price;
			}
				else if (format == 2)
			{
				lpTotal += price;
			}
				else
			{
				csTotal += price;
			}
		}
		
	
				
		System.out.println("Thank you for your order! Your order details are as follows:");
		System.out.println("\n=============");
		System.out.println("\n" + orderNumber +"\n-------------");
		System.out.printf("\n" + orderDetails +"\nTotal\t€%.2f", totalPrice);
		System.out.println("\n=============");
		System.out.println("\nThe average cost per format for your order is as follows:");
			
		if (csNum != 0)
		{
			csAverage = csTotal / csNum;
			System.out.println("CS:\t " + csAverage);
		}
		else
			System.out.println("CS: none ordered");
		
		if (lpNum != 0)
		{
			lpAverage = lpTotal / lpNum;
			System.out.println("LP:\t " + lpAverage);
		}
		else
			System.out.println("LP: none ordered");
		
		if (cdNum != 0)
		{
			cdAverage = cdTotal / cdNum;
			System.out.println("CD:\t " + cdAverage);
		}
		else
			System.out.println("CD: none ordered");
			
		System.out.println("\nThe most expensive item purchased was " + maxItem + "");
			
		keyboard.close();

	}

}
