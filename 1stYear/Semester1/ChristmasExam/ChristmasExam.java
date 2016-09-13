import java.util.Scanner;

public class ChristmasExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner keyboard = new Scanner(System.in);
		
		char moreItems;
		String inputString, item, fruitVeg = "Fruit And Veg", dairy = "Dairy", confectionery = "Confectionery", maxItem ="";
		final double FRUIT = 0.0, DAIRY = 0.10, CONFECT = 0.20;
		double price, finalPrice = 0, vatPrice = 0, totalPrice = 0, average, maxPrice = 0;
		int catOption, noItems = 0;	
		
		do
		{
			//Prompt for Name
			System.out.println("What is the item?");
			item = keyboard.nextLine();
			while (item.length() < 3)
			{
				System.out.println("Must have min 3 characters! Please re-enter your item: ");
				item = keyboard.nextLine();
			}
			
			noItems += 1;
			
			//Prompt for price
			System.out.println("What is the price excluding VAT? ");
			while (!keyboard.hasNextDouble())
			{
				System.out.print("Invalid input! What is the price excluding VAT? ");
				keyboard.next();
			}
			price = keyboard.nextDouble();
						
			//Input Validation
			while (price <= 0) 
			{
				System.out.println("INVALID INPUT!! What is the price excluding VAT? ");
				while (!keyboard.hasNextDouble())
				{
					System.out.print("Invalid input! Enter an double: ");
					keyboard.next();
				}
				price = keyboard.nextDouble();
			}
			totalPrice += price;
			
			//Prompt for category
			System.out.println("What is the category?\n1: Fruit and Veg\n2: Dairy\n3: Confectionery");
			while (!keyboard.hasNextInt())
			{
				System.out.println("Invalid input! PWhat is the category?\n1: Fruit and Veg\n2: Dairy\n3: Confectionery");
				keyboard.next();
			}
			//Input Validation
			catOption = keyboard.nextInt();
							
			while (catOption < 1 || catOption >3 ) 
			{  
			System.out.println("Please enter a positive integer value for your format choice: ");
				while (!keyboard.hasNextInt())
				{
					System.out.println("Invalid input! Please enter a positive integer value for your format choice: ");
					keyboard.next();
				}
				catOption = keyboard.nextInt();
			}
			if (catOption == 1){
				vatPrice = price * FRUIT; 
				finalPrice = price + vatPrice;
				fruitVeg += "\n" + item + ": €" + price + " (ex VAT) + €" + vatPrice + " (VAT) = €" + finalPrice + " (inc Vat)\n";
				if (price > maxPrice)
				{
					maxPrice = price;
					maxItem = item + " In Fruit and Veg ";
				}
				}
			else if (catOption == 2){
				vatPrice = price * DAIRY;
				finalPrice = price + vatPrice;
				dairy += "\n" + item + ": €" + price + " (ex VAT) + €" + vatPrice + " (VAT) = €" + finalPrice + " (inc Vat)\n";
				if (price > maxPrice)
				{
					maxPrice = price;
					maxItem = item + " In Dairy";
				}
				}
			else if (catOption == 3){
				vatPrice = price * CONFECT;
				finalPrice = price + vatPrice;
				confectionery += "\n" + item + ": €" + price + " (ex VAT) + €" + vatPrice + " (VAT) = €" + finalPrice + " (inc Vat)\n";
				if (price > maxPrice)
				{
					maxPrice = price;
					maxItem = item + " In Confectionery";
				}
				}
			else{
				System.out.println("Invalid category choice");
				}
			
			System.out.printf(item + ": €%.3f (ex VAT) + €%.3f (VAT) = €%.3f (inc VAT)", price ,vatPrice ,finalPrice);
			
			keyboard.nextLine();
			//Prompt for more items
			System.out.println("Do you have more items?");
			inputString = keyboard.nextLine();
			moreItems = inputString.charAt(0);
		} 
		while (moreItems == 'y' || moreItems == 'Y');
			{
				
			}
		
		System.out.println(fruitVeg +"\n" + dairy + "\n" + confectionery);
		
		System.out.printf("you have spent €%.3f in total", finalPrice);
				
		if (noItems != 0)
		{
			average = totalPrice / noItems;
			System.out.printf("\nYou have spent an average of €%.5f per Item", average);
		}
		
		else
			System.out.println("no items ordered");
			
		System.out.println("\nThe most expensive item purchased was " + maxItem + "");
		
		
		keyboard.close();
		
	}

}
