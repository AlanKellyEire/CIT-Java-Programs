import java.util.Scanner;

public class project {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner keyboard = new Scanner(System.in);
		final double CARD = 1.05, CASH = 0.95;
		double totalAmount = 0;
		int numMembers, numDays, numSession = 0, MAX_PEOPLE = 8, payOption, maxSesMusicians, DAILY_RATE1 = 260, DAILY_RATE2 = 240, DAILY_RATE3 = 210, DAILY_RATE4 = 200, SESSION_CHARGE = 100;
		String name = "", email = "", phone = "", date = "", memberName = "", memberInst = "", memberList = "", payType = "";
		
		//Prompt Booker for their Name
		System.out.println("What is your name? ");
		name = keyboard.nextLine();
		
		//Prompt Booker for their email
		System.out.println("What is your email address? ");
		email = keyboard.nextLine();
		
		//Prompt Booker for their number
		System.out.println("What is your phone number? ");
		phone = keyboard.nextLine();
				
		//Prompt for start Date
		System.out.println("What start date are you looking for (DD/MM/YYYY)? ");
		date = keyboard.nextLine();
				
		//Prompt for number of band members
		System.out.println("How many members of the band?  Max is 8");
				
		while (!keyboard.hasNextInt())
		{
			System.out.print("Invalid input! Enter an integer: ");
			keyboard.next();
		}
		numMembers = keyboard.nextInt();
			
		//Input Validation
		while (numMembers > 8 || numMembers <= 0) 
		{
			System.out.println("INVALID INPUT!! How many members of the band?  Max is 8");
			while (!keyboard.hasNextInt())
		    {
		    	System.out.print("Invalid input! Enter an integer: ");
		    	keyboard.next();
		    }
			numMembers = keyboard.nextInt();
		}
				
		maxSesMusicians = MAX_PEOPLE - numMembers;
				
		//Prompt for number of days
		System.out.println("How many days? ");
		while (!keyboard.hasNextInt())
		{
			System.out.println("Invalid input!Please enter a positive integer value for the number of days: ");
			keyboard.next();
		}
		//Input Validation
		numDays = keyboard.nextInt();
				
		while (numDays < 1) 
		{  
			System.out.println("Please enter a positive integer value for the number of days: ");
			while (!keyboard.hasNextInt())
		    {
				System.out.println("Invalid input! Please enter a positive integer value for the number of days: ");
				keyboard.next();
		    }
			numDays = keyboard.nextInt();
		}
			
		if (numDays == 1)
		{
			totalAmount += (DAILY_RATE1 * numDays); 
		}
		else if (numDays <= 4)
		{
			totalAmount += (DAILY_RATE2 * numDays);
		}
		else if (numDays <= 8)
		{
			totalAmount += (DAILY_RATE3 * numDays);
		}
		else
		{
			totalAmount += (DAILY_RATE4 * numDays);
		}
			    	
		//eat up new character line
		keyboard.nextLine();
		
		for (int bandMember = 1; bandMember <= numMembers; bandMember++)
		{
			//prompt for each band members name and their instrument
			System.out.println("What is band member " + bandMember + " name? ");
			memberList += bandMember + ": ";
			memberName = keyboard.nextLine();
			memberList += memberName;
			System.out.println("What is band member " + memberName + " instrument? ");
			memberInst = keyboard.nextLine();
			memberList += " - " + memberInst;
			memberList += "\n";
			
			/*
			 * Added by Dave. Could also have done:
			 * memberList += bandMember + ": " + memberName + " - " + memberInst + "\n";
			 */
		} 
		
		if (maxSesMusicians != 0)
		{
			//prompt for each number of session musicians
			System.out.println("There is room for " +  maxSesMusicians + " session musicans - how many do you want? ");
			while (!keyboard.hasNextInt())
			{
				System.out.print("Invalid input! Enter an integer: ");
				keyboard.next();
			}
				numSession = keyboard.nextInt();
			
			//Input Validation
			while (numSession < 0 || numSession > maxSesMusicians) 
			{
				System.out.println("INVALID INPUT!! There is room for " +  maxSesMusicians + " session musicans - how many do you want? ");
				while (!keyboard.hasNextInt())
				{
					System.out.print("Invalid input! Enter an integer: ");
					keyboard.next();
				}
				numSession = keyboard.nextInt();
			}
				
			totalAmount += (numSession * SESSION_CHARGE * numDays);
		}
		//let the user know there is no space for Session Musicians
		else
			System.out.println("Studio At MAX Capacity");	
		
		//prompt for payment type
		System.out.println("Will you pay by" + "\n1:\tCredit card (5% levy)" + "\n2:\tCash (5% Discount)" + "\n3:\tCheque");
		// validate
		while (!keyboard.hasNextInt())
		{
			System.out.println("Invalid input! Will you pay by" + "\n1:\tCredit card (5% levy)" + "\n2:\tCash (5% Discount)" + "\n3:\tCheque");
			keyboard.next();
		}
		//Input Validation
		payOption = keyboard.nextInt();
				
		while (payOption <= 0 || payOption > 3) 
		{  
			System.out.println("Please enter a positive integer value between 1 -3: ");
			while (!keyboard.hasNextInt())
		    {
				System.out.println("Please enter a positive integer value between 1 -3: ");
				keyboard.next();
		    }
			payOption = keyboard.nextInt();
		}
		if (payOption == 1)
		{
			payType = "CreditCard";
			totalAmount *= CARD; 
		}
		else if (payOption == 2)
		{
			payType = "Cash";
			totalAmount *= CASH;
		}
		else
		{
			payType = "Cheque";
		}

		System.out.println("Booking applicationn\n-------------------\nRequested by: " + name + " (Contact: " + email +" & " + phone + ")" + "\n\nDate requested --> " + date);
		System.out.println("\nBand Members\n-------------");
		System.out.println(memberList);
		System.out.println("Includes " +  numSession + " session musicians per day. ");
		System.out.printf("Payment will be €%.2f to be paid by " +  payType + ".", totalAmount);
		
		keyboard.close();
	}
}


