import java.util.Scanner;

public class Assignment1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner keyboard = new Scanner(System.in);
		
		double dollar, euro, vat, importPercent, importDuty, totalPrice, totalVat, totalDuty;
		
		// Prompt user's for cost in dollars,
		System.out.print("Enter the cost in dollars? ");
		dollar = keyboard.nextDouble();

		// Prompt user's for import duty percentage,
		System.out.print("Enter the import duty percentage? ");
		importPercent = keyboard.nextDouble();
		
		euro = dollar * 0.79;
		importDuty = importPercent / 100;
		totalVat = euro * 0.23;
		totalDuty = euro * importDuty;
		totalPrice = euro + totalVat + totalDuty;
		
		// Display the resulting information
		System.out.println("Goods: \t \t €" + euro);
		
		// Display the resulting information
		System.out.println("VAT: \t \t €" + totalVat);
		
		// Display the resulting information
		System.out.println("Import Duty: \t €" + totalDuty);
		
		// Display the resulting information
		System.out.println("Total: \t \t €" + totalPrice);
		
	}

}
