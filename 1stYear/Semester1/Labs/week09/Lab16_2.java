import java.util.Scanner;

public class Lab16_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner keyboard = new Scanner(System.in);

		final double largePizza = 15.99, smallPizza = 12.99, fanta = 1.50, coke = 1.50, beer = 2.80, water = 1.50;
		double orderPrice = 0;
		int numPizza, size, drink, numDrink;
		String orderDetails = "";
		
		System.out.print("How many Pizza's would you like?");
		numPizza = keyboard.nextInt();
		
		while (numPizza < 1)
		{ 
			System.out.print("How many Pizza's would you like?");
			numPizza = keyboard.nextInt();
		}
		
		System.out.println("Would you like:\n1. small pizza\n2. large pizza");
		size = keyboard.nextInt();
		
		while (size != 1 && size != 2)
		{ 
			System.out.print("Would you like:\n1. small pizza\n2. large pizza");
			size = keyboard.nextInt();
		}
		if (size == 1){
			orderDetails += numPizza + "smallPizza\n\t";
			orderPrice = numPizza * smallPizza; 
			}
		else if (size == 2){
			orderDetails += numPizza + "largePizza\n\t";
			orderPrice = numPizza * largePizza;
			}
		
		System.out.print("How many Drinks's would you like?");
		numDrink = keyboard.nextInt();
			
		System.out.println("Would you like:\n1. Fanta\n2. Coke\n3. Beer\n4. Water");
		size = keyboard.nextInt();
		
		while (size != 1 && size != 2 && size != 3 && size != 4)
		{ 
			System.out.print("Would you like:\n1. Fanta\n2. Coke\n3. Beer\n4. Water");
			drink = keyboard.nextInt();
		}
		if (drink == 1){
			orderDetails += numDrink + "Fanta\n\t";
			orderPrice = numDrink * fanta; 
			}
		else if (drink == 2){
			orderDetails += numDrink + "Coke\n\t";
			orderPrice = numDrink * coke;
			}
		else if (drink == 3){
			orderDetails += numDrink + "Beer\n\t";
			orderPrice = numDrink * beer;
			}
		else if (drink == 4){
			orderDetails += numDrink + "Water\n\t";
			orderPrice = numDrink * water;
			}
		
		System.out.print("your order is \n\t" + orderDetails + "That will cost €" + orderPrice + " please");
	}

}
