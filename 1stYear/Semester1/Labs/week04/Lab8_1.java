import java.util.Scanner;

public class Lab8_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double a, b, c, x1, x2;
		
		Scanner keyboard = new Scanner(System.in);
		
		// Prompt user's for a,
	    System.out.print("What is a? ");
	    a=keyboard.nextDouble();
		
	    // Prompt user's for b,
	    System.out.print("What is b? ");
	    b=keyboard.nextDouble();
		
	    // Prompt user's for c,
	    System.out.print("What is c? ");
	    c=keyboard.nextDouble();
	    
	    x1 = (-b + Math.sqrt(b*b-4*a*c) ) / (2*a);
	    		
	    x2 = (-b - Math.sqrt(b*b-4*a*c) ) / (2*a);
	    
	    // Display the resulting information
	   	System.out.print("x1 is " + x1 + " and x2 is " + x2);
		
	}

}
