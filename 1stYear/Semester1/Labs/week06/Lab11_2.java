import java.util.Scanner;

public class Lab11_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner keyboard = new Scanner(System.in);
		
		final double CHEESEBURGER = 2.45, HAMBURGER = 2.35, CHICKEN_BURGER = 3.65, SMALL_CHIPS = 2.90, LARGE_CHIPS = 3.50, CURRY_CHIPS = 3.85, COKE = 1.25, FANTA = 1.25, MILK = 1.50;
		int burgerChoice, chipChoice, drinkChoice;
		double orderPrice = 0;
		String orderDetails = "";
		
		// Prompt user's for Burger Type,
		System.out.println("Choose your Burger type\n1: Cheeseburger\n2: Hamburger\n3: Chicken Burger");
		burgerChoice = keyboard.nextInt();
		
		if (burgerChoice == 1){
			orderDetails += "CHEESEBURGER\n\t";
			orderPrice = CHEESEBURGER; 
			}
		else if (burgerChoice == 2){
			orderDetails += "HAMBURGER\n\t";
			orderPrice = HAMBURGER;
			}
		else if (burgerChoice == 3){
			orderDetails += "CHICKEN_BURGER\n\t";
			orderPrice = CHICKEN_BURGER;
			}
		else{
			System.out.println("Invalid Burger choice");
			}
		
		// Prompt user's for size chips,
		System.out.println("Choose your chip size\n1: Small\n2: Large\n3: Curry");
		chipChoice = keyboard.nextInt();
		
		if (chipChoice == 1){
			orderDetails += "SMALL_CHIPS\n\t";
			orderPrice += SMALL_CHIPS;
			} 
		else if (chipChoice == 2){
			orderDetails += "LARGE_CHIPS\n\t";
			orderPrice += LARGE_CHIPS;
			}
		else if (chipChoice == 3){
			orderDetails += "CURRY_CHIPS\n\t";
			orderPrice += CURRY_CHIPS;
			}
		else{
			System.out.println("Invalid chip choice");
			}
		
		// Prompt user's for drink,
		System.out.println("Choose your drink\n1: Coke\n2: Fanta\n3: Milk");
		drinkChoice = keyboard.nextInt();
		
		if (drinkChoice == 1){
			orderDetails += "COKE\n\t";
			orderPrice += COKE; 
			}
		else if (drinkChoice == 2){
			orderDetails += "FANTA\n\t";
			orderPrice += FANTA;
			}
		else if (drinkChoice == 3){
			orderDetails += "MILK\n\t";
			orderPrice += MILK;
			}
		else{
			System.out.println("Invalid chip choice");
			}
		
		
		
		System.out.print("your order is \n\t" + orderDetails + "That will cost €" + orderPrice + " please");

	
		
	}

}
